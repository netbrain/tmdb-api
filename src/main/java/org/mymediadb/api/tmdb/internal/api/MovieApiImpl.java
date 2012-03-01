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

import com.google.gson.reflect.TypeToken;
import org.apache.log4j.Logger;
import org.mymediadb.api.tmdb.TmdbStatusException;
import org.mymediadb.api.tmdb.api.MovieApi;
import org.mymediadb.api.tmdb.api.TmdbStatus;
import org.mymediadb.api.tmdb.internal.model.MovieImpl;
import org.mymediadb.api.tmdb.internal.model.TmdbStatusImpl;
import org.mymediadb.api.tmdb.model.Movie;

import java.net.URLEncoder;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieApiImpl implements MovieApi {
    private static final Logger log = Logger.getLogger(MovieApiImpl.class);
    private final TmdbApiImpl tmdbApi;

    MovieApiImpl(TmdbApiImpl tmdbApi) {
        this.tmdbApi = tmdbApi;
    }

    public void addRating(int id, float rating) throws TmdbStatusException {
        String url = "http://api.themoviedb.org/2.1/Movie.addRating";

        HashMap<String,Object> postData = new HashMap<String,Object>();
        postData.put("rating",rating);

        String response = tmdbApi.sendPOSTRequest(url, id,postData);

        TypeToken<TmdbStatusImpl> resultType = new TypeToken<TmdbStatusImpl>() {};
        TmdbStatus returnStatus = tmdbApi.convertFromJsonToObjectOfType(response,resultType);
        if(returnStatus.getCode() != 1){
            throw new TmdbStatusException("error occured when calling Media.addID",returnStatus);
        }
    }

    public List<? extends Movie> browse(MovieOrderBy orderBy, MovieOrder order) {
        return browse(orderBy,order,null);
    }

    public List<? extends Movie> browse(MovieOrderBy orderBy, MovieOrder order, Map<String, Object> optionalArgs) {

        if(orderBy == null || order == null)
            throw new IllegalArgumentException("required parameters cannot be null");

        String url = "http://api.themoviedb.org/2.1/Movie.browse/" + tmdbApi.getLanguage() + "/json/" + tmdbApi.getApiKey() + "?order_by=" + orderBy + "&order=" + order;

        if(optionalArgs != null){
            try {
                for(String key : optionalArgs.keySet()){
                    url += "&"+key+"="+URLEncoder.encode(optionalArgs.get(key).toString().trim(), "UTF-8");
                }
            } catch (Exception x) {
                throw new RuntimeException(x);
            }
        }

        List<? extends Movie> movies = tmdbApi.getMoviesFromUrl(url);

        if (movies != null) {
            log.info("found movies : " + movies);
        }
        return movies;
    }

    public Movie getImages(Object id) {
        if(id == null)
            return null;

        String url = "http://api.themoviedb.org/2.1/Movie.getImages/" + tmdbApi.getLanguage() + "/json/" + tmdbApi.getApiKey() + "/" + id.toString().trim();

        Movie movie = tmdbApi.getMovieFromUrl(url);

        if (movie != null) {
            log.info("found movie images with id=" + id + " : " + movie);
        }
        return movie;
    }

    public Movie getInfo(Object id) {
        if(id == null)
            return null;

        String url = "http://api.themoviedb.org/2.1/Movie.getInfo/" + tmdbApi.getLanguage() + "/json/" + tmdbApi.getApiKey() + "/" + id.toString().trim();

        Movie movie = tmdbApi.getMovieFromUrl(url);

        if (movie != null) {
            log.info("found movie with id=" + id + " : " + movie);
        }
        return movie;
    }

    public Movie getLatest() {

        String url = "http://api.themoviedb.org/2.1/Movie.getLatest/" + tmdbApi.getLanguage() + "/json/" + tmdbApi.getApiKey();

        Movie movie = tmdbApi.getMovieFromUrl(url);

        if (movie != null) {
            log.info("found latest movie : " + movie);
        }
        return movie;
    }

    public Movie getTranslations(Object id) {
        if(id == null)
            return null;

        String url = "http://api.themoviedb.org/2.1/Movie.getTranslations/" + tmdbApi.getLanguage() + "/json/" + tmdbApi.getApiKey() + "/" + id.toString().trim();

        Movie movie = tmdbApi.getMovieFromUrl(url);

        if (movie != null) {
            log.info("found movie with id=" + id + " : " + movie);
        }
        return movie;
    }

    public List<? extends Movie> getVersion(Object... ids) {
        if(ids == null)
            return null;

        String commaSeparatedIds = tmdbApi.createCommaSeparatedStringOfElements(ids);
        if(commaSeparatedIds == null)
            return null;

        String url = "http://api.themoviedb.org/2.1/Movie.getVersion/" + tmdbApi.getLanguage() + "/json/" + tmdbApi.getApiKey() + "/" + commaSeparatedIds;

        List<? extends Movie> movies = tmdbApi.getMoviesFromUrl(url);

        if (movies != null) {
            log.info("found movies with id's=" + commaSeparatedIds + " : " + movies);
        }
        return movies;
    }


    public Movie imdbLookup(Object id) {

        if(id == null)
            return null;

        String url = "http://api.themoviedb.org/2.1/Movie.imdbLookup/" + tmdbApi.getLanguage() + "/json/" + tmdbApi.getApiKey() + "/" + id.toString().trim();

        Movie movie = tmdbApi.getMovieFromUrl(url);

        if (movie != null) {
            log.info("found movie with imdbId=" + id + " : " + movie);
        }
        return movie;
    }

    public List<? extends Movie> search(String searchQuery) {

        if(searchQuery == null){
            return null;
        }

        searchQuery = searchQuery.trim();
        if(searchQuery.length() == 0)
            return null;

        try {
            searchQuery = URLEncoder.encode(searchQuery, "UTF-8");
        } catch (Exception x) {
            throw new RuntimeException(x);
        }

        String url = "http://api.themoviedb.org/2.1/Movie.search/" + tmdbApi.getLanguage() + "/json/" + tmdbApi.getApiKey() + "/" + searchQuery;

        List<? extends Movie> movies = tmdbApi.getMoviesFromUrl(url);

        if (movies != null) {
            log.info("found one or more movie candidates with search=" + searchQuery + ": " + movies);
        }

        return movies;
    }
}
