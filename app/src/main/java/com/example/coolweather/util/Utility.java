package com.example.coolweather.util;

import android.text.TextUtils;
import android.util.Log;

import com.example.coolweather.model.City;
import com.example.coolweather.model.CoolWeatherDB;
import com.example.coolweather.model.County;
import com.example.coolweather.model.Province;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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
}
