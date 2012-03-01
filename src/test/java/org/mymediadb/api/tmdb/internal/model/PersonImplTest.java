package org.mymediadb.api.tmdb.internal.model;

import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.util.Date;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

public class PersonImplTest {

    private PersonImpl testObj;

    @Before
    public void startup(){
        testObj = new PersonImpl();
    }

    @Test
    public void testGetLastModifiedAt(){
        String testDate = "2010-07-19 10:24:08";
        testObj.last_modified_at = testDate;
        Date lastModifiedAt = testObj.getLastModifiedAt();
        assertNotNull(lastModifiedAt);

        DateFormat formatter = MiscModelUtils.getInstance().getDateFormatter("yyyy-MM-dd hh:mm:ss");
        String dateAsString = formatter.format(lastModifiedAt);
        assertEquals(testDate,dateAsString);
    }

    @Test
    public void testGetBirthday(){
        String testDate = "1985-07-19";
        testObj.birthday = testDate;
        Date birthday = testObj.getBirthday();
        assertNotNull(birthday);

        DateFormat formatter = MiscModelUtils.getInstance().getDateFormatter("yyyy-MM-dd");
        String dateAsString = formatter.format(birthday);
        assertEquals(testDate,dateAsString);
    }
}
