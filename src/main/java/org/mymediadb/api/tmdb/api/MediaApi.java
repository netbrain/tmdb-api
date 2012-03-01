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

public interface MediaApi {

    public static enum MediaType {
        FILE,
        DVD;

        public String toString(){
            return this.name().toLowerCase();
        }
    }

    public void addId(int id, String mediaId, MediaType mediaType, long byteSize, float fps, String volumeLabel) throws TmdbStatusException;

    public void addId(int id, String mediaId, MediaType mediaType, long byteSize, float fps) throws TmdbStatusException;

    public Movie getInfo(String dvdId);

    public Movie getInfo(String hash, long byteSize);
}
