package org.mymediadb.api.tmdb.internal.api;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mymediadb.api.tmdb.api.MediaApi;
import org.mymediadb.api.tmdb.api.TmdbApi;
import org.mymediadb.api.tmdb.model.Movie;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;

public class MediaApiImplTest {

    private TmdbApi tmdbApi;
    private MediaApi mediaApi;

    @Before
    public void setup() {
        tmdbApi = TmdbApiImpl.getInstance();
        mediaApi = tmdbApi.getMediaApi();
    }

    @Test
    public void testGetInfoHashWithNullValues() {
        Movie movie = mediaApi.getInfo(null, 0L);
        assertNull(movie);
    }

    @Test
    public void testGetInfoHashWithEmptyValues() {
        Movie movie = mediaApi.getInfo("", 0L);
        assertNull(movie);
    }

    @Ignore("missing proper dataset")
    @Test
    public void testGetInfoHashWithRealValues() {
        Movie movie = mediaApi.getInfo("907172e7fe51ba57", 742086656L);
        assertNotNull(movie);
    }

    @Test
    public void testGetInfoDvdIdWithNullValues() {
        Movie movie = mediaApi.getInfo(null);
        assertNull(movie);
    }

    @Test
    public void testGetInfoDvdIdWithEmptyValues() {
        Movie movie = mediaApi.getInfo("");
        assertNull(movie);
    }

    @Ignore("missing proper dataset")
    @Test
    public void testGetInfoDvdIdWithRealValues() {
        Movie movie = mediaApi.getInfo("15570");
        assertNotNull(movie);
    }


}
