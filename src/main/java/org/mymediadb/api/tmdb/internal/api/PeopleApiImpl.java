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
import org.mymediadb.api.tmdb.api.PeopleApi;
import org.mymediadb.api.tmdb.internal.model.PersonImpl;
import org.mymediadb.api.tmdb.model.Person;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

public class PeopleApiImpl implements PeopleApi {
    private static final Logger log = Logger.getLogger(PeopleApiImpl.class);
    private TmdbApiImpl tmdbApi;

    PeopleApiImpl(TmdbApiImpl tmdbApi) {
        this.tmdbApi = tmdbApi;
    }

    public Person getInfo(int id) {
       String url = "http://api.themoviedb.org/2.1/Person.getInfo/" + tmdbApi.getLanguage() + "/json/" + tmdbApi.getApiKey() + "/" + id;

       TypeToken<List<PersonImpl>> resultType = new TypeToken<List<PersonImpl>>() {};
       List<PersonImpl> people = tmdbApi.getResult(url,resultType);
       PersonImpl person = people != null && people.size() > 0 ? people.get(0) : null;

       if(person != null)
           log.info("found person with id="+id+" : "+person);

        return person;
    }

    public Person getLatest() {
       String url = "http://api.themoviedb.org/2.1/Person.getLatest/" + tmdbApi.getLanguage() + "/json/" + tmdbApi.getApiKey();

       TypeToken<List<PersonImpl>> resultType = new TypeToken<List<PersonImpl>>() {};
       List<PersonImpl> people = tmdbApi.getResult(url,resultType);
       PersonImpl person = people != null && people.size() > 0 ? people.get(0) : null;

       if(person != null)
           log.info("found latest person : "+person);

        return person;
    }

    public List<? extends Person> getVersion(Object ... ids) {
       if(ids == null)
           return null;

       String commaSeparatedIds = tmdbApi.createCommaSeparatedStringOfElements(ids);
       if(commaSeparatedIds == null)
            return null;

       String url = "http://api.themoviedb.org/2.1/Person.getVersion/" + tmdbApi.getLanguage() + "/json/" + tmdbApi.getApiKey() + "/" + commaSeparatedIds;

       TypeToken<List<PersonImpl>> resultType = new TypeToken<List<PersonImpl>>() {};
       List<PersonImpl> people = tmdbApi.getResult(url,resultType);

       if(people != null)
           log.info("found people with ids="+ ids +" : "+people);

        return people;
    }

    public List<? extends Person> search(String searchQuery) {
        if(searchQuery == null)
            return null;

        searchQuery = searchQuery.trim();
        if(searchQuery.length() == 0)
            return null;

        try {
            searchQuery = URLEncoder.encode(searchQuery,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        String url = "http://api.themoviedb.org/2.1/Person.search/" + tmdbApi.getLanguage() + "/json/" + tmdbApi.getApiKey() + "/" + searchQuery;

        TypeToken<List<PersonImpl>> resultType = new TypeToken<List<PersonImpl>>() {};
        List<PersonImpl> people = tmdbApi.getResult(url,resultType);

        if(people != null){
            log.info("found persons where searchQuery=" + searchQuery + " : "+people);
        }
        return people;
    }
}
