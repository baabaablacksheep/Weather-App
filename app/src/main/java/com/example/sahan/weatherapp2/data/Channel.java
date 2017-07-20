package com.example.sahan.weatherapp2.data;


import org.json.JSONObject;

public class Channel implements JSONPopulator {

    private Item item;
    private Units units;
    private Location location;

    public Item getItem() {
        return item;
    }

    public Units getUnits() {
        return units;
    }

    public Location getLocation() {
        return location;
    }

    @Override
    public void populate(JSONObject data) {

        item=new Item();
        item.populate(data.optJSONObject("item"));

        units=new Units();
        units.populate(data.optJSONObject("units"));

        location=new Location();
        location.populate(data.optJSONObject("location"));

    }
}
