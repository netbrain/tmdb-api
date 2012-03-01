package org.mymediadb.api.tmdb.internal.model;

import com.google.gson.annotations.SerializedName;
import org.mymediadb.api.tmdb.model.Person;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class PersonImpl implements Person {

    Long id;
    Integer popularity;
    String name;
    List<String> also_known_as;
    String biography;
    Integer known_movies;
    String birthday;
    String url;
    List<MovieImpl> filmography;
    @SerializedName(value = "profile")
    List<ImageImpl> images;
    Integer version;
    String last_modified_at;

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public List<String> getAlsoKnownAs() {
        return this.also_known_as;
    }

    @Override
    public String getBiography() {
        return this.biography;
    }

    @Override
    public Date getBirthday() {
        DateFormat formatter = MiscModelUtils.getInstance().getDateFormatter("yyyy-MM-dd");
        try {
            return formatter.parse(this.birthday);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<MovieImpl> getFilmography() {
        return this.filmography;
    }

    @Override
    public List<ImageImpl> getImages() {
        return this.images;
    }

    @Override
    public Integer getKnownMovies() {
        return this.known_movies;
    }

    @Override
    public Date getLastModifiedAt() {
        DateFormat formatter = MiscModelUtils.getInstance().getDateFormatter("yyyy-MM-dd hh:mm:ss");
        try {
            return formatter.parse(this.last_modified_at);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Integer getPopularity() {
        return this.popularity;
    }

    @Override
    public String getUrl() {
        return this.url;
    }

    @Override
    public Integer getVersion() {
        return this.version;
    }

    @Override
    public String toString() {
        return "PersonImpl{" +
                "also_known_as=" + also_known_as +
                ", id=" + id +
                ", popularity=" + popularity +
                ", name='" + name + '\'' +
                ", biography='" + biography + '\'' +
                ", known_movies=" + known_movies +
                ", birthday='" + birthday + '\'' +
                ", url='" + url + '\'' +
                ", filmography=" + filmography +
                ", images=" + images +
                ", version=" + version +
                ", last_modified_at='" + last_modified_at + '\'' +
                '}';
    }
}