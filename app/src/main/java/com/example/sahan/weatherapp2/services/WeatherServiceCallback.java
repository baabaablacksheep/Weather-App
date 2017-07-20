package com.example.sahan.weatherapp2.services;
import com.example.sahan.weatherapp2.data.Channel;

public interface WeatherServiceCallback {

    void serviceSuccess(Channel channel);
    void serviceFailure(Exception exception);


}
