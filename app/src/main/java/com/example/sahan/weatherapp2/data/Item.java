package com.example.sahan.weatherapp2.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Item implements JSONPopulator{

    private Condition condition;


    private int[]code=new int[5];
    private Integer[]high=new Integer[5];
    private Integer[]low=new Integer[5];
    private String[]day=new String[5];
    private String[]description=new String[5];
    private String[]date=new String[5];

    public int getCode(int x) {
        return code[x];
    }

    public Integer[] getHigh() {
        return high;
    }

    public Integer[] getLow() {
        return low;
    }

    public String[] getDay() {
        return day;
    }

    public String[] getDescription() {
        return description;
    }

    public Condition getCondition() {
        return condition;
    }

    public String[] getDate() {
        return date;
    }

    @Override
    public void populate(JSONObject data) {



        condition = new Condition();
        condition.populate(data.optJSONObject("condition"));

        try {
            JSONArray arr=data.getJSONArray("forecast");

            //getting values of the forecast day 1
            JSONObject obj0=arr.getJSONObject(0);
            code[0]=obj0.getInt("code");
            high[0]=obj0.getInt("high");
            low[0]=obj0.getInt("low");
            day[0]=obj0.getString("day");
            description[0]=obj0.getString("text");
            date[0]=obj0.getString("date");

            //getting values of the forecast day 2
            JSONObject obj1=arr.getJSONObject(1);
            code[1]=obj1.getInt("code");
            high[1]=obj1.getInt("high");
            low[1]=obj1.getInt("low");
            day[1]=obj1.getString("day");
            description[1]=obj1.getString("text");
            date[1]=obj1.getString("date");

            //getting values of the forecast day 3
            JSONObject obj2=arr.getJSONObject(2);
            code[2]=obj2.getInt("code");
            high[2]=obj2.getInt("high");
            low[2]=obj2.getInt("low");
            day[2]=obj2.getString("day");
            description[2]=obj2.getString("text");
            date[2]=obj2.getString("date");

            //getting values of the forecast day 4
            JSONObject obj3=arr.getJSONObject(3);
            code[3]=obj3.getInt("code");
            high[3]=obj3.getInt("high");
            low[3]=obj3.getInt("low");
            day[3]=obj3.getString("day");
            description[3]=obj3.getString("text");
            date[3]=obj3.getString("date");

            //getting values of the forecast day 5
            JSONObject obj4=arr.getJSONObject(4);
            code[4]=obj4.getInt("code");
            high[4]=obj4.getInt("high");
            low[4]=obj4.getInt("low");
            day[4]=obj4.getString("day");
            description[4]=obj4.getString("text");
            date[4]=obj4.getString("date");

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
