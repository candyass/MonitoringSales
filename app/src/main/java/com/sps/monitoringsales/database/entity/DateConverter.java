package com.sps.monitoringsales.database.entity;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Created by sigit on 06/05/2018.
 */


public class DateConverter {

    @TypeConverter
    public static Date longToDate(long value) {
        if(value > 0) {
            Date date = new Date(value);
            return date;
        }
        return null;
    }

    @TypeConverter
    public static long DateToLong(Date value) {
        if(value != null) {
            return value.getTime();
        }
        return -1;
    }
}
