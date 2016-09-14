package com.example.coolweather.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;

import com.example.coolweather.model.City;
import com.example.coolweather.model.CoolWeatherDB;
import com.example.coolweather.model.County;
import com.example.coolweather.model.Province;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by yang on 2016/9/12.
 */
public class Utility {

    public synchronized static boolean handleProvincesResponse(CoolWeatherDB coolWeatherDB,
                                                               String response) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONObject json = new JSONObject(response);
                JSONArray jsonArray = json.names();
                for(int i = 0; i < jsonArray.length(); i++){
                    String code = jsonArray.getString(i);
                    String name = (String)json.get(code);
                    Province province = new Province();
                    province.setProvinceCode(code);
                    province.setProvinceName(name);
                    coolWeatherDB.saveProvince(province);
                }
                return true;
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean handleCitiesResponse(CoolWeatherDB coolWeatherDB,
                                               String response, int provinceId) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONObject json = new JSONObject(response);
                JSONArray jsonArray = json.names();
                for(int i = 0; i < jsonArray.length(); i++){
                    String code = jsonArray.getString(i);
                    String name = (String)json.get(code);
                    City city = new City();
                    city.setCityCode(code);
                    city.setCityName(name);
                    city.setProvinceId(provinceId);
                    coolWeatherDB.saveCity(city);
                }
                return true;
            }catch(Exception e){
                e.printStackTrace();
            }
            }
        return false;
    }

    public static boolean handleCountiesResponse(CoolWeatherDB coolWeatherDB,
                                                 String response, int cityId) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONObject json = new JSONObject(response);
                JSONArray jsonArray = json.names();
                for(int i = 0; i < jsonArray.length(); i++){
                    String code = jsonArray.getString(i);
                    String name = (String)json.get(code);
                    County county = new County();
                    county.setCountyCode(code);
                    county.setCountyName(name);
                    county.setCityId(cityId);
                    coolWeatherDB.saveCounty(county);
                }
                return true;
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }

    public static void handleWeatherResponse(Context context, String response){
        try {
            JSONObject json = new JSONObject(response);
            JSONObject weatherInfo = json.getJSONObject("weatherinfo");
            String cityName = weatherInfo.getString("city");
            String cityCode = weatherInfo.getString("cityid");
            String temp1 = weatherInfo.getString("temp1");
            String temp2 = weatherInfo.getString("temp2");
            String weather = weatherInfo.getString("weather");
            String publishTime = weatherInfo.getString("ptime");
            Log.d("Utility", cityName + " " + temp1 + " " + temp2 + " " + weather);
            saveWeatherInfo(context, cityName, cityCode, temp1, temp2, weather, publishTime);
        }catch(JSONException e){
            e.printStackTrace();
        }
    }

    public static void saveWeatherInfo(Context context, String cityName, String cityCode,
                                       String temp1, String temp2, String weather,
                                       String publishTime){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年M月d日", Locale.CHINA);
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putBoolean("city_selected", true);
        editor.putString("city_name", cityName);
        editor.putString("city_code", cityCode);
        editor.putString("temp1", temp1);
        editor.putString("temp2", temp2);
        editor.putString("weather", weather);
        editor.putString("publish_time", publishTime);
        editor.putString("current_date", sdf.format(new Date()));
        editor.apply();
    }
}
