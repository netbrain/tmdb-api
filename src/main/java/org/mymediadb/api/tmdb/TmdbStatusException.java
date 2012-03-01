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

package org.mymediadb.api.tmdb;

import org.mymediadb.api.tmdb.api.TmdbStatus;
import org.mymediadb.api.tmdb.internal.model.TmdbStatusImpl;

public class TmdbStatusException extends RuntimeException {

    private TmdbStatus status;

    public TmdbStatusException(TmdbStatus status){
        this(null,status);
    }

    public TmdbStatusException(String message,TmdbStatus status){
        super(message);
        this.status = status;
    }

    public TmdbStatus getStatus() {
        return this.status;
    }
}
