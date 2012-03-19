package org.mymediadb.api.tmdb.internal.api;

import org.junit.Before;
import org.junit.Test;
import org.mymediadb.api.tmdb.api.GenresApi;
import org.mymediadb.api.tmdb.api.TmdbApi;

import java.util.List;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assume.assumeNotNull;

public class GenreApiImplTest {

    private TmdbApi tmdbApi;
    private GenresApi genresApi;

    @Before
    public void setup() {
        tmdbApi = TmdbApiImpl.getInstance();
        genresApi = tmdbApi.getGenresApi();
        assumeNotNull(tmdbApi.getApiKey());
    }

    @Test
    public void testGetList() {
        List genres = genresApi.getList();
        assertNotNull(genres);
        assertTrue(genres.size() > 0);
    }


}
