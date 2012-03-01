package org.mymediadb.api.tmdb.internal.model;


import org.mymediadb.api.tmdb.api.AuthApi.AuthToken;

public class AuthTokenImpl implements AuthToken {
    private String token;

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return "AuthTokenImpl{" +
                "token='" + token + '\'' +
                '}';
    }
}
