package com.example.coolweather.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by yang on 2016/9/9.
 */
public class CoolWeatherOpenHelper extends SQLiteOpenHelper{
    //建立Province表
    public static final String CREATE_PROVINCE = "create table Province ("
            + "id integer primary key autoincrement, "
            + "province_name text, "
            + "province_code text)";

    //建立City表
    public static final String CREATE_CITY = "create table City ("
            + "id integer primary key autoincrement"
            + "city_name text, "
            + "city_code text, "
            + "province_id integer)";

    //建立County表
    public static final String CREATE_COUNTY = "create table County )"
            + "id integer primary key autoincrement"
            + "county_name text, "
            + "county_code text, "
            + "city_id integer)";

    public CoolWeatherOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory
                                 factory, int version){
        super(context, name, factory ,version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_PROVINCE);
        sqLiteDatabase.execSQL(CREATE_CITY);
        sqLiteDatabase.execSQL(CREATE_COUNTY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
