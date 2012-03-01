package org.mymediadb.api.tmdb.internal.model;

import org.mymediadb.api.tmdb.api.TmdbStatus;

public class TmdbStatusImpl implements TmdbStatus {

    private String status;
    private int code;

    public TmdbStatusImpl(String status, int code){
        this.status = status;
        this.code = code;
    }

    @Override
    public String getStatus() {
        return this.status;
    }

    @Override
    public int getCode() {
        return this.code;
    }
}
