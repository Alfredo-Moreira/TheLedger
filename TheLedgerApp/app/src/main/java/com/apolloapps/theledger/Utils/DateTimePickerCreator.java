package com.apolloapps.theledger.Utils;

import android.app.FragmentManager;
import android.content.Context;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.text.DateFormat;
import java.util.Calendar;

/**
 * Created by AMoreira on 8/5/16.
 */
public class DateTimePickerCreator {

    private static Calendar mCalendar;

    public static void createTimePicker(Context context,
                                                         FragmentManager manager,
                                                         TimePickerDialog.OnTimeSetListener listener){
        mCalendar = Calendar.getInstance();
        TimePickerDialog timePickerDialog = TimePickerDialog.newInstance(listener,
                mCalendar.get(Calendar.HOUR_OF_DAY),
                mCalendar.get(Calendar.MINUTE),
                android.text.format.DateFormat.is24HourFormat(context));
        timePickerDialog.show(manager,null);

    }

    public static void createDatePicker(FragmentManager manager,DatePickerDialog.OnDateSetListener listener){
        mCalendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(listener,
                mCalendar.get(Calendar.YEAR),
                mCalendar.get(Calendar.MONTH),
                mCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show(manager,null);
    }

    public static String timeConstructor(int hour,int minute,Context context) {
        return DateTimeUtils.createTime(hour,minute,context);
    }

    public static String dateConstructor(int year, int monthOfYear, int dayOfMonth, Context context) {
        return DateTimeUtils.createDate(year,monthOfYear,dayOfMonth,context);
    }
}
