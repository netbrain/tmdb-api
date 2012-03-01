/*
 * This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.mymediadb.api.tmdb.internal.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.ContentEncodingHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.mymediadb.api.tmdb.api.*;
import org.mymediadb.api.tmdb.internal.deserializer.MovieImageDeserializer;
import org.mymediadb.api.tmdb.internal.model.ImageImpl;
import org.mymediadb.api.tmdb.internal.model.MovieImpl;
import org.mymediadb.api.tmdb.model.Movie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public class TmdbApiImpl implements TmdbApi {
    private static final Logger log = Logger.getLogger(TmdbApiImpl.class);
    private static TmdbApi instance = null;

    private static final DefaultHttpClient httpClient;
    private static final Gson gson;

    private final AuthApi auth;
    private final MediaApi media;
    private final MovieApi movie;
    private final PeopleApi people;
    private final GenresApi genres;

    private String apiKey = null;
    private AuthApi.Session session = null;
    private String languageTag = "en";


    private static final int HTTP_CONNECTION_TIMEOUT = 240000;
    private static final int HTTP_MAX_CONNECTIONS = 100;
    private static final int HTTP_MAX_CONNECTIONS_PER_ROUTE = 16;

    static {
        //Initialize httpclient
        HttpParams params = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(params, HTTP_CONNECTION_TIMEOUT);

        ThreadSafeClientConnManager connectionManager = new ThreadSafeClientConnManager();
        connectionManager.setMaxTotal(HTTP_MAX_CONNECTIONS);
        connectionManager.setDefaultMaxPerRoute(HTTP_MAX_CONNECTIONS_PER_ROUTE);

        httpClient = new ContentEncodingHttpClient(connectionManager, params);

        //Initialize GSON
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(ImageImpl.class, new MovieImageDeserializer());
        gsonBuilder.serializeNulls();
        gson = gsonBuilder.create();
    }

    private TmdbApiImpl() {
        auth = new AuthApiImpl(this);
        media = new MediaApiImpl(this);
        movie = new MovieApiImpl(this);
        people = new PeopleApiImpl(this);
        genres = new GenresApiImpl(this);
        apiKey = System.getProperty("tmdb.api.key");
    }

    @Override
    public AuthApi getAuthApi() {
        return this.auth;
    }

    @Override
    public MediaApi getMediaApi() {
        return this.media;
    }

    @Override
    public MovieApi getMovieApi() {
        return this.movie;
    }

    @Override
    public PeopleApi getPeopleApi() {
        return this.people;
    }

    @Override
    public GenresApi getGenresApi() {
        return this.genres;
    }

    @Override
    public void setSession(AuthApi.Session session) {
        this.session = session;
    }

    @Override
    public AuthApi.Session getSession() {
        return this.session;
    }

    @Override
    public void setLanguage(String languageTag) {
        this.languageTag = languageTag;
    }

    @Override
    public String getLanguage() {
        return this.languageTag;
    }

    @Override
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public String getApiKey() {
        return this.apiKey;
    }

    public static TmdbApi getInstance() {
        if (instance == null)
            instance = new TmdbApiImpl();
        return instance;
    }

    <T> T getResult(String url, TypeToken<T> resultType) {

        String response = sendGETRequest(url);

        log.debug("request returned response: " + response);

        if (!response.matches("^\\[\"Nothing found\\.\"]$"))
            return convertFromJsonToObjectOfType(response, resultType);

        return null;
    }

    <T> T convertFromJsonToObjectOfType(String response, TypeToken<T> type) {
        try {

            return (T) gson.fromJson(response, type.getType());

        } catch (JsonParseException x) {
            log.warn("could not parse json", x);
        } catch (Exception x) {
            log.error("Unhandled exception!", x);
        }
        return null;
    }

    String sendGETRequest(String url) {
        log.debug("making GET request against: " + url);

        if (getApiKey() == null)
            throw new RuntimeException("No tmdb api key is defined!");

        HttpGet getRequest = new HttpGet(url);
        getRequest.setHeader("Accept", "text/json");
        try {
            HttpResponse response = httpClient.execute(getRequest);
            return EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            throw new RuntimeException("unexpected error", e);
        }
    }

    String sendPOSTRequest(String url, int id, Map<String, Object> postData) {
        log.debug("making POST request against: " + url);

        if (getApiKey() == null)
            throw new RuntimeException("No tmdb api key is defined!");

        //adding required post parameters
        postData.put("type", "json");
        postData.put("api_key", getApiKey());
        postData.put("session_key", getSession().getSessionKey());
        postData.put("id", id);

        try {
            HttpPost postRequest = new HttpPost(url);
            postRequest.setHeader("Accept", "text/json");

            List<NameValuePair> postDataArray = new ArrayList<NameValuePair>();
            for (String key : postData.keySet()) {
                postDataArray.add(new BasicNameValuePair(key, postData.get(key).toString()));
            }

            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(postDataArray, "UTF-8");
            postRequest.setEntity(entity);

            HttpResponse response = httpClient.execute(postRequest);
            return EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            throw new RuntimeException("unexpected error", e);
        }
    }

    List<? extends Movie> getMoviesFromUrl(String url) {
        TypeToken<List<MovieImpl>> resultType = new TypeToken<List<MovieImpl>>() {
        };
        return getResult(url, resultType);
    }

    Movie getMovieFromUrl(String url) {
        List<? extends Movie> movies = getMoviesFromUrl(url);
        return (movies != null && movies.size() > 0) ? movies.get(0) : null;
    }

    String createCommaSeparatedStringOfElements(Object[] elements) {
        String commaSeparatedElements = "";
        for (Object idObj : elements) {
            if (idObj != null) {
                String id = idObj.toString().trim();
                if (id.length() != 0)
                    commaSeparatedElements += id + ",";
            }
        }
        if (commaSeparatedElements.length() == 0)
            return null;
        else
            commaSeparatedElements = commaSeparatedElements.substring(0, commaSeparatedElements.length() - 1);
        return commaSeparatedElements;
    }

}
