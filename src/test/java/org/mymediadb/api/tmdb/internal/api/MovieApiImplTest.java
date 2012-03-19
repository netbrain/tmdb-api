package org.mymediadb.api.tmdb.internal.api;

import org.junit.Before;
import org.junit.Test;
import org.mymediadb.api.tmdb.api.MovieApi;
import org.mymediadb.api.tmdb.api.TmdbApi;
import org.mymediadb.api.tmdb.model.Movie;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeNotNull;

public class MovieApiImplTest {

    private TmdbApi tmdbApi;
    private MovieApi movieApi;

    @Before
    public void setup() {
        tmdbApi = TmdbApiImpl.getInstance();
        movieApi = tmdbApi.getMovieApi();
        assumeNotNull(tmdbApi.getApiKey());
    }

    @Test
    public void testSearchWithEmptyQuery() {
        List movies = movieApi.search("");
        assertNull(movies);
    }

    @Test
    public void testSearchWithNullQuery() {
        List movies = movieApi.search(null);
        assertNull(movies);
    }

    @Test
    public void testSearchWithSpacesQuery() {
        List movies = movieApi.search("     ");
        assertNull(movies);
    }

    @Test
    public void testSearchWithProperQuery() {
        List movies = movieApi.search("Prince of persia");
        assertNotNull(movies);
    }

    @Test
    public void testImdbLookupWithNullId() {
        Movie movie = movieApi.imdbLookup(null);
        assertNull(movie);
    }

    @Test
    public void testImdbLookupWithWrongFormattedId() {
        Movie movie = movieApi.imdbLookup("wrongidformat");
        assertNull(movie);
    }

    @Test
    public void testImdbLookupWithWhitespaceFormattedId() {
        Movie movie = movieApi.imdbLookup(" tt0076759  ");
        assertNotNull(movie);
    }

    @Test
    public void testImdbLookupWithCorrectFormattedId() {
        Movie movie = movieApi.imdbLookup("tt0076759");
        assertNotNull(movie);
    }

    @Test
    public void testGetVersionWithNullId() {
        String id = null;
        List movies = movieApi.getVersion(id);
        assertNull(movies);
    }

    @Test
    public void testGetVersionWithEmptyStringAsId() {
        List movies = movieApi.getVersion("");
        assertNull(movies);
    }

    @Test
    public void testGetVersionWithMixedNullId() {
        List movies = movieApi.getVersion(512, null, 11);
        assertNotNull(movies);
        assertEquals(2, movies.size());
    }

    @Test
    public void testGetVersionWithNonExistingId() {
        List movies = movieApi.getVersion("nonexistingid");
        assertNull(movies);
    }

    @Test
    public void testGetVersionWithValidListOfIds() {
        List movies = movieApi.getVersion(510, 10, 11, 22);
        assertNotNull(movies);
    }

    @Test
    public void testGetTranslationsWithNullId() {
        Movie movie = movieApi.getTranslations(null);
        assertNull(movie);
    }

    @Test
    public void testGetTranslationsWithNonExistantId() {
        Movie movie = movieApi.getTranslations("nonexistant");
        assertNull(movie);
    }

    @Test
    public void testGetTranslationsWithProperId() {
        Movie movie = movieApi.getTranslations(11);
        assertNotNull(movie);
    }

    @Test
    public void testGetLatest() {
        Movie movie = movieApi.getLatest();
        assertNotNull(movie);
    }

    @Test
    public void testGetInfoWithNullId() {
        Movie movie = movieApi.getInfo(null);
        assertNull(movie);
    }

    @Test
    public void testGetInfoWithNonExistantId() {
        Movie movie = movieApi.getInfo("nonexistant");
        assertNull(movie);
    }

    @Test
    public void testGetInfoWithProperId() {
        Movie movie = movieApi.getInfo(11);
        assertNotNull(movie);
    }

    @Test
    public void testGetImagesWithNullId() {
        Movie movie = movieApi.getImages(null);
        assertNull(movie);
    }

    @Test
    public void testGetImagesWithNonExistantId() {
        Movie movie = movieApi.getImages("nonexistant");
        assertNull(movie);
    }

    @Test
    public void testGetImagesWithProperId() {
        Movie movie = movieApi.getInfo(11);
        assertNotNull(movie);
    }

    @Test
    public void testBrowse() {
        List movies = movieApi.browse(MovieApi.MovieOrderBy.TITLE, MovieApi.MovieOrder.ASC);
        assertNotNull(movies);
        assertTrue(movies.size() > 0);
    }

    @Test
    public void testBrowseWithOptionalParameters() {
        HashMap<String, Object> optionalArgs = new HashMap<String, Object>();
        optionalArgs.put("min_votes", 8);
        optionalArgs.put("rating_min", 6);
        optionalArgs.put("year", 2011);
        List movies = movieApi.browse(MovieApi.MovieOrderBy.TITLE, MovieApi.MovieOrder.ASC, optionalArgs);
        assertNotNull(movies);
        assertTrue(movies.size() > 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBrowseWithNullParameters() {
        movieApi.browse(null, null);
    }

}
