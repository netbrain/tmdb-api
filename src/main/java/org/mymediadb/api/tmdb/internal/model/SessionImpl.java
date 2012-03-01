package org.mymediadb.api.tmdb.internal.model;

import org.mymediadb.api.tmdb.api.AuthApi.Session;
import org.mymediadb.api.tmdb.api.TmdbStatus;

public class SessionImpl implements Session,TmdbStatus {

    private String username;
    private String session;
    private String status;
    private Integer code;

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getSessionKey() {
        return this.session;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "SessionImpl{" +
                "code=" + code +
                ", username='" + username + '\'' +
                ", session='" + session + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
