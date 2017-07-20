package com.example.sahan.weatherapp2.data;

import org.json.JSONObject;

public class Units implements JSONPopulator{

    private static String temperature;

    public String getTemperature() {
        return temperature;
    }

    @Override
    public void populate(JSONObject data) {

        temperature=data.optString("temperature");

    }
}
