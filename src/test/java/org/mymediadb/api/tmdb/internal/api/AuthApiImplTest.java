package org.mymediadb.api.tmdb.internal.api;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mymediadb.api.tmdb.TmdbStatusException;
import org.mymediadb.api.tmdb.api.AuthApi;
import org.mymediadb.api.tmdb.api.TmdbApi;

import static junit.framework.Assert.*;

public class AuthApiImplTest {

    private TmdbApi tmdbApi;
    private AuthApi authApi;

    @Before
    public void setup(){
        tmdbApi = TmdbApiImpl.getInstance();
        authApi = tmdbApi.getAuthApi();
    }

    @Test
    public void testGetToken(){
        String token = authApi.getToken();
        assertNotNull(token);
        assertTrue(token.length() > 0);
    }

    @Test
    @Ignore("No way to automatically get correct token value")
    public void testGetSession(){
        String secretToken = System.getProperty("test.tmdb.token");
        AuthApi.Session session = authApi.getSession(secretToken);
        assertNotNull(session);
    }

    @Test(expected = TmdbStatusException.class)
    public void testGetSessionWithInvalidToken(){
        String secretToken = "invalidTestToken";
        AuthApi.Session session = authApi.getSession(secretToken);
    }
}
