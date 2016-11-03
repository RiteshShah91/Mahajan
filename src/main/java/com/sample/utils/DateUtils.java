package com.sample.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public Date stringToDate(String value, String format) {
        Date date;
        try {
            DateFormat formatter;

            formatter = new SimpleDateFormat(format);
            date = (Date) formatter.parse(value);
            return date;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String dateWithFormat(Date d, String format) {
        if (d != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(d);
        }
        return "";
    }

    public Date dateFormat(Date d, String format) {
        if (d != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return stringToDate(sdf.format(d), format);
        }
        return null;
    }

    public int getYear() {
        Calendar now = Calendar.getInstance();
        return now.get(Calendar.YEAR);
    }

    //1 minute = 60 seconds
    //1 hour = 60 x 60 = 3600
    //1 day = 3600 x 24 = 86400
    public String printDifference(Date startDate) {
        Date endDate = new Date();
        //milliseconds
        long different = endDate.getTime() - startDate.getTime();

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        long elapsedSeconds = different / secondsInMilli;

        if (elapsedDays != 0) {
            return elapsedDays + " days ago.";
        } else if (elapsedHours != 0) {
            return elapsedHours + " hours ago.";
        } else if (elapsedMinutes != 0) {
            return elapsedMinutes + " minutes ago.";
        } else {
            return elapsedSeconds + " seconds ago.";
        }

    }

    /**
     * Check is the date currect or not.
     * @param value
     * @param format
     * @return 
     */
    public boolean isCurrectDate(String value, String format) {

        try {
            DateFormat formatter;

            formatter = new SimpleDateFormat(format);
            Date date = (Date) formatter.parse(value);
            return true;
        } catch (Exception e) {
//            e.printStackTrace();
            return false;
        }
    }
}
