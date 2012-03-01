package org.mymediadb.api.tmdb.internal.api;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mymediadb.api.tmdb.api.GenresApi;
import org.mymediadb.api.tmdb.api.TmdbApi;
import org.mymediadb.api.tmdb.model.Genre;
import org.mymediadb.api.tmdb.model.Movie;

import java.util.List;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;

public class GenreApiImplTest {

    private TmdbApi tmdbApi;
    private GenresApi genresApi;

    @Before
    public void setup(){
        tmdbApi = TmdbApiImpl.getInstance();
        genresApi = tmdbApi.getGenresApi();
    }

    @Test
    public void testGetList(){
        List genres = genresApi.getList();
        assertNotNull(genres);
        assertTrue(genres.size() > 0);
    }




}
