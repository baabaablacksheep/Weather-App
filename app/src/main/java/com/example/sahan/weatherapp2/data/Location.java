package com.example.sahan.weatherapp2.data;

import org.json.JSONObject;

public class Location implements JSONPopulator{

    private static String city;
    private static String country;
    private static String region;

    public static String getCity() {
        return city;
    }

    public static String getCountry() {
        return country;
    }

    public static String getRegion() {
        return region;
    }

    @Override
    public void populate(JSONObject data) {

        city=data.optString("city");
        country=data.optString("country");
        region=data.optString("region");

    }
}

