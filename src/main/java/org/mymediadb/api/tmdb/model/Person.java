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

package org.mymediadb.api.tmdb.model;


import org.mymediadb.api.tmdb.internal.model.ImageImpl;
import org.mymediadb.api.tmdb.internal.model.MovieImpl;

import java.util.Date;
import java.util.List;

public interface Person {

    public Long getId();

    public List<String> getAlsoKnownAs();

    public String getBiography();

    public Date getBirthday();

    public List<MovieImpl> getFilmography();

    public List<ImageImpl> getImages();

    public Integer getKnownMovies();

    public Date getLastModifiedAt();

    public String getName();

    public Integer getPopularity();

    public String getUrl();

    public Integer getVersion();

}
