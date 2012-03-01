package org.mymediadb.api.tmdb.internal.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.TimeZone;

class MiscModelUtils {
    private static MiscModelUtils ourInstance = new MiscModelUtils();
    private static HashMap<String,DateFormat> dateFormatters = new HashMap<String,DateFormat>();

    private MiscModelUtils() {

    }

    static MiscModelUtils getInstance() {
        return ourInstance;
    }

    DateFormat getDateFormatter(String format){
        DateFormat formatter;
        if(!dateFormatters.containsKey(format)){
            formatter = new SimpleDateFormat(format);
            formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        }else{
            formatter = dateFormatters.get(format);
        }
        return formatter;
    }


}
