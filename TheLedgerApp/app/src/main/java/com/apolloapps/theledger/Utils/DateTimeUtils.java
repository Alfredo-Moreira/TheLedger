package com.apolloapps.theledger.Utils;

import android.content.Context;

import com.apolloapps.theledger.Common.AppConstants;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by AMoreira on 8/10/16.
 */
public class DateTimeUtils {


    private static Date getTimeInUTCFromString(String stringDate) {
        Date date = null;

        SimpleDateFormat dateFormat = new SimpleDateFormat(AppConstants.DATE_FORMAT);
        dateFormat.setTimeZone(TimeZone.getTimeZone(AppConstants.UTC_TIMEZONE));

        try {
            date = dateFormat.parse(stringDate);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return date;
    }

    public static String getLocalizedDateFormat(String date, Context context) {

        java.text.DateFormat mFormatDate = android.text.format.DateFormat.getDateFormat(context);
        Date tempDate = getTimeInUTCFromString(date);
        String dateOutput = null;
        mFormatDate.setTimeZone(TimeZone.getDefault());

        try {
            dateOutput = mFormatDate.format(tempDate);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dateOutput;
    }

    public static String getLocalizedTimeFormat(String date, Context context) {
        java.text.DateFormat FormatTime = android.text.format.DateFormat.getTimeFormat(context);
        Date tempDate = getTimeInUTCFromString(date);
        String dateOutput = null;
        FormatTime.setTimeZone(TimeZone.getDefault());

        try
        {
            dateOutput = FormatTime.format(tempDate);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dateOutput;
    }

    public static String createDate(int year,int month, int day, Context context) {
        @SuppressWarnings("deprecation")
        Date date = new Date(year,month,day);

        java.text.DateFormat mFormatDate = android.text.format.DateFormat.getDateFormat(context);
        String dateOutput = null;
        mFormatDate.setTimeZone(TimeZone.getDefault());

        try {
            dateOutput = mFormatDate.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dateOutput;
    }

    public static String createTime(int hour,int minute, Context context) {

        Date date = new Date();
        date.setHours(hour);
        date.setMinutes(minute);

        java.text.DateFormat FormatTime = android.text.format.DateFormat.getTimeFormat(context);
        String dateOutput = null;
        FormatTime.setTimeZone(TimeZone.getDefault());

        try
        {
            dateOutput = FormatTime.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dateOutput;

    }


}
