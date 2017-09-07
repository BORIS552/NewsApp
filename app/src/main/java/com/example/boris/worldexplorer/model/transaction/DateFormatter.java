package com.example.boris.worldexplorer.model.transaction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Created by boris on 9/7/17.
 */

public class DateFormatter {
    String input;
    SimpleDateFormat f;
    TimeZone utc = TimeZone.getTimeZone("UTC");
    GregorianCalendar cal;

    public DateFormatter(String date) {
        this.input = date;
        f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        f.setTimeZone(utc);
        cal = new GregorianCalendar(utc);
    }

    public Date convertDate() {

        try {
            cal.setTime(f.parse(input));
        }catch (ParseException e) {
            System.out.print("cannot parse the date");
        }

        return cal.getTime();
    }

}
