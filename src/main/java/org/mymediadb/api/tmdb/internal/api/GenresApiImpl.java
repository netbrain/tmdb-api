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
import org.mymediadb.api.tmdb.api.GenresApi;
import org.mymediadb.api.tmdb.internal.model.GenreImpl;
import org.mymediadb.api.tmdb.model.Genre;

import java.util.List;

public class GenresApiImpl implements GenresApi {
    private final static Logger log = Logger.getLogger(GenresApiImpl.class);
    private TmdbApiImpl tmdbApi;

    GenresApiImpl(TmdbApiImpl tmdbApi) {
        this.tmdbApi = tmdbApi;
    }

    public List<? extends Genre> getList() {
        String url = "http://api.themoviedb.org/2.1/Genres.getList/" + tmdbApi.getLanguage() + "/json/" + tmdbApi.getApiKey();
        TypeToken<List<GenreImpl>> resultType = new TypeToken<List<GenreImpl>>() {};
        List<GenreImpl> genres = tmdbApi.getResult(url,resultType);
        if(genres != null){
            log.info("retrieved list of genres : "+genres);
        }
        return genres;
    }

}
