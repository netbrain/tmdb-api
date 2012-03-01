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

package org.mymediadb.api.tmdb.internal.model;

import org.apache.log4j.Logger;
import org.mymediadb.api.tmdb.model.Genre;
import org.mymediadb.api.tmdb.model.Image;
import org.mymediadb.api.tmdb.model.Movie;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Collection;
import java.util.Date;


public class MovieImpl implements Movie {

    private static final Logger log = Logger.getLogger(MovieImpl.class);

    String name;
    Double score;
    Boolean translated;
    Boolean adult;
    String language;
    String original_name;
    String alternative_name;
    Long id;
    String imdb_id;
    String url;
    Integer votes;
    Double rating;
    String certification;
    String overview;
    String released;
    Collection<ImageImpl> posters;
    Collection<ImageImpl> backdrops;
    Integer version;
    String last_modified_at;
    String movie_type;
    Collection<GenreImpl> categories;
    String tagline;
    Integer runtime;
    Long budget;
    Long revenue;
    String homepage;
    String trailer;
    Collection<GenreImpl> genres;
    String character;
    String department;
    String job;


    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Double getScore() {
        return this.score;
    }

    @Override
    public Boolean isTranslated() {
        return this.translated;
    }

    @Override
    public Boolean isAdult() {
        return this.adult;
    }

    @Override
    public String getLanguage() {
        return this.language;
    }

    @Override
    public String getOriginalName() {
        return this.original_name;
    }

    @Override
    public String getAlternativeName() {
        return this.alternative_name;
    }

    @Override
    public MovieType getMovieType() {
        try {
            return MovieType.valueOf(this.movie_type.toUpperCase());
        } catch (IllegalArgumentException x) {
            log.warn("The specified enum name " + this.movie_type + " was not found!", x);
            return null;
        }
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public String getImdbId() {
        return this.imdb_id;
    }

    @Override
    public String getUrl() {
        return this.url;
    }

    @Override
    public Integer getVotes() {
        return this.votes;
    }

    @Override
    public Double getRating() {
        return this.rating;
    }

    @Override
    public String getCertification() {
        return this.certification;
    }

    @Override
    public String getOverview() {
        return this.overview;
    }

    @Override
    public Date getReleased() {
        if (this.released == null) {
            return null;
        } else {
            DateFormat formatter = MiscModelUtils.getInstance().getDateFormatter("yyyy-MM-dd");
            try {
                return formatter.parse(this.released);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public Collection<? extends Image> getPosters() {
        return this.posters;
    }

    @Override
    public Collection<? extends Image> getBackdrops() {
        return this.backdrops;
    }

    @Override
    public Integer getVersion() {
        return this.version;
    }

    @Override
    public Date getLastModifiedAt() {
        DateFormat formatter = MiscModelUtils.getInstance().getDateFormatter("yyyy-MM-dd hh:mm:ss");
        try {
            return formatter.parse(this.last_modified_at);
        } catch (ParseException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public String getTagline() {
        return this.tagline;
    }

    @Override
    public Integer getRuntime() {
        return this.runtime;
    }

    @Override
    public Long getBudget() {
        return this.budget;
    }

    @Override
    public Long getRevenue() {
        return this.revenue;
    }

    @Override
    public String getHomepage() {
        return this.homepage;
    }

    @Override
    public String getTrailer() {
        return this.trailer;
    }

    @Override
    public Collection<? extends Genre> getGenres() {
        return this.genres;
    }

    @Override
    public String getJob() {
        return this.job;
    }

    @Override
    public String getDepartment() {
        return this.department;
    }

    @Override
    public String getCharacter() {
        return this.character;
    }

    @Override
    public String toString() {
        return "MovieImpl{" +
                "adult=" + adult +
                ", name='" + name + '\'' +
                ", score=" + score +
                ", translated=" + translated +
                ", language='" + language + '\'' +
                ", original_name='" + original_name + '\'' +
                ", alternative_name='" + alternative_name + '\'' +
                ", id=" + id +
                ", imdb_id='" + imdb_id + '\'' +
                ", url='" + url + '\'' +
                ", votes=" + votes +
                ", rating=" + rating +
                ", certification='" + certification + '\'' +
                ", overview='" + overview + '\'' +
                ", released='" + released + '\'' +
                ", posters=" + posters +
                ", backdrops=" + backdrops +
                ", version=" + version +
                ", last_modified_at='" + last_modified_at + '\'' +
                ", movie_type='" + movie_type + '\'' +
                ", categories=" + categories +
                ", tagline='" + tagline + '\'' +
                ", runtime=" + runtime +
                ", budget=" + budget +
                ", revenue=" + revenue +
                ", homepage='" + homepage + '\'' +
                ", trailer='" + trailer + '\'' +
                ", genres=" + genres +
                ", character='" + character + '\'' +
                ", department='" + department + '\'' +
                ", job='" + job + '\'' +
                '}';
    }
}
