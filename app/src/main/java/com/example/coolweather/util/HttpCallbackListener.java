package com.example.coolweather.util;

/**
 * Created by yang on 2016/9/12.
 */
public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
