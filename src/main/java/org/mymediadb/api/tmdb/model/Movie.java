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

import java.util.Collection;
import java.util.Date;

public interface Movie extends Media {

    public enum MovieType {
        MOVIE
    }

    public String getName();

    public Double getScore();

    public Boolean isTranslated();

    public Boolean isAdult();

    public String getLanguage();

    public String getOriginalName();

    public String getAlternativeName();

    public MovieType getMovieType();

    public Long getId();

    public String getImdbId();

    public String getUrl();

    public Integer getVotes();

    public Double getRating();

    public String getCertification();

    public String getOverview();

    public Date getReleased();

    public Collection<? extends Image> getPosters();

    public Collection<? extends Image> getBackdrops();

    public Integer getVersion();

    public Date getLastModifiedAt();

    public String getTagline();

    public Integer getRuntime();

    public Long getBudget();

    public Long getRevenue();

    public String getHomepage();

    public String getTrailer();

    public Collection<? extends Genre> getGenres();

    public String getJob();

    public String getDepartment();

    public String getCharacter();

}
