package org.mymediadb.api.tmdb.internal.model;

import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.util.Date;

import static junit.framework.Assert.*;

public class MovieImplTest {

    private MovieImpl testObj;

    @Before
    public void setup() {
        testObj = new MovieImpl();
        testObj.name = "Test";
    }

    @Test
    public void testGetReleasedYearWithYear() {
        String testDate = "2010-01-01";
        testObj.released = testDate;
        Date released = testObj.getReleased();
        assertNotNull(released);

        DateFormat dateFormatter = MiscModelUtils.getInstance().getDateFormatter("yyyy-MM-dd");
        String dateAsString = dateFormatter.format(released);
        assertEquals(testDate, dateAsString);
    }

    @Test
    public void testGetReleasedYearWhenNull() {
        testObj.released = null;
        Date released = testObj.getReleased();
        assertNull(released);
    }

    @Test
    public void testGetLastModifiedAt() {
        String testDate = "2010-07-19 10:24:08";
        testObj.last_modified_at = testDate;
        Date lastModifiedAt = testObj.getLastModifiedAt();
        assertNotNull(lastModifiedAt);

        DateFormat dateFormatter = MiscModelUtils.getInstance().getDateFormatter("yyyy-MM-dd hh:mm:ss");
        String dateAsString = dateFormatter.format(lastModifiedAt);
        assertEquals(testDate, dateAsString);
    }
}
