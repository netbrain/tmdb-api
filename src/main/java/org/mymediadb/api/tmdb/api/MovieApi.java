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

package org.mymediadb.api.tmdb.api;

import org.mymediadb.api.tmdb.TmdbStatusException;
import org.mymediadb.api.tmdb.model.Movie;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface MovieApi {

    public static enum MovieOrderBy {
        RATING,
        RELEASE,
        TITLE;

        @Override
        public String toString(){
            return this.name().toLowerCase();
        }
    }

    public static enum MovieOrder {
        ASC,
        DESC;

        @Override
        public String toString(){
            return this.name().toLowerCase();
        }
    }


    public void addRating(int id, float rating) throws TmdbStatusException;

    public List<? extends Movie> browse(MovieOrderBy orderBy, MovieOrder order);

    public List<? extends Movie> browse(MovieOrderBy orderBy, MovieOrder order, Map<String, Object> optionalArgs);

    public Movie getImages(Object id);

    public Movie getInfo(Object id);

    public Movie getLatest();

    public Movie getTranslations(Object id);

    public List<? extends Movie> getVersion(Object... ids);

    public Movie imdbLookup(Object id);

    public List<? extends Movie> search(String searchQuery);

}
