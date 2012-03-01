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
import org.mymediadb.api.tmdb.api.MediaApi;
import org.mymediadb.api.tmdb.api.TmdbStatus;
import org.mymediadb.api.tmdb.internal.model.TmdbStatusImpl;
import org.mymediadb.api.tmdb.model.Movie;

import java.util.HashMap;

public class MediaApiImpl implements MediaApi {
    private static final Logger log = Logger.getLogger(MediaApiImpl.class);
    private TmdbApiImpl tmdbApi;

    MediaApiImpl(TmdbApiImpl tmdbApi) {
        this.tmdbApi = tmdbApi;
    }

    public void addId(int id, String mediaId, MediaType mediaType, long byteSize, float fps, String volumeLabel) throws TmdbStatusException {
        String url = "http://api.themoviedb.org/2.1/Media.addID";

        HashMap<String, Object> postData = new HashMap<String, Object>();
        postData.put("media_id", mediaId);
        postData.put("media_type", mediaType);
        postData.put("total_size", byteSize);
        postData.put("fps", fps);
        if (volumeLabel != null)
            postData.put("volume_label", volumeLabel);

        String response = tmdbApi.sendPOSTRequest(url, id, postData);

        TypeToken<TmdbStatusImpl> resultType = new TypeToken<TmdbStatusImpl>() {
        };
        TmdbStatus returnStatus = tmdbApi.convertFromJsonToObjectOfType(response, resultType);
        if (returnStatus.getCode() != 1) {
            throw new TmdbStatusException("error occured when calling Media.addID", returnStatus);
        }
    }

    public void addId(int id, String mediaId, MediaType mediaType, long byteSize, float fps) throws TmdbStatusException {
        addId(id, mediaId, mediaType, byteSize, fps, null);
    }

    public Movie getInfo(String dvdId) {
        if (dvdId == null)
            return null;

        dvdId = dvdId.trim();
        if (dvdId.length() == 0)
            return null;

        String url = "http://api.themoviedb.org/2.1/Media.getInfo/" + tmdbApi.getLanguage() + "/json/" + tmdbApi.getApiKey() + "/" + dvdId;
        Movie movie = tmdbApi.getMovieFromUrl(url);

        if (movie != null) {
            log.info("found movie from dvdId=" + dvdId + " : " + movie);
        }

        return movie;
    }

    public Movie getInfo(String hash, long byteSize) {
        if (hash == null)
            return null;

        hash = hash.trim();
        if (hash.length() == 0)
            return null;

        String url = "http://api.themoviedb.org/2.1/Media.getInfo/" + tmdbApi.getLanguage() + "/json/" + tmdbApi.getApiKey() + "/" + hash + "/" + byteSize;
        Movie movie = tmdbApi.getMovieFromUrl(url);

        if (movie != null) {
            log.info("found movie from hash=" + hash + "/filesize=" + byteSize + " : " + movie);
        }

        return movie;
    }

}
