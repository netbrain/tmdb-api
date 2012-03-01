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
import org.mymediadb.api.tmdb.api.AuthApi;
import org.mymediadb.api.tmdb.internal.model.AuthTokenImpl;
import org.mymediadb.api.tmdb.internal.model.SessionImpl;


public class AuthApiImpl implements AuthApi {
    private final Logger log = Logger.getLogger(AuthApiImpl.class);
    private final TmdbApiImpl tmdbApi;


    AuthApiImpl(TmdbApiImpl tmdbApi) {
        this.tmdbApi = tmdbApi;
    }

    public Session getSession(String token) {
        String url = "http://api.themoviedb.org/2.1/Auth.getSession/json/" + tmdbApi.getApiKey() + "/" + token;
        TypeToken<SessionImpl> resultType = new TypeToken<SessionImpl>() {};
        SessionImpl session = tmdbApi.getResult(url, resultType);
        if(tmdbReturnedWithError(session)){
            throw new TmdbStatusException("Failed retrieving session!",session);
        }
        return session;
    }

    private boolean tmdbReturnedWithError(SessionImpl session) {
        return session.getUsername() == null && session.getSessionKey() == null;
    }

    public String getToken() {
        String url = "http://api.themoviedb.org/2.1/Auth.getToken/json/" + tmdbApi.getApiKey();
        TypeToken<AuthTokenImpl> resultType = new TypeToken<AuthTokenImpl>() {};
        AuthTokenImpl authTokenImpl = tmdbApi.getResult(url,resultType);
        log.info("recieved token : "+authTokenImpl);
        return authTokenImpl.getToken();
    }
}
