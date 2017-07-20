package com.example.sahan.weatherapp2.list_adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.sahan.weatherapp2.R;
import com.example.sahan.weatherapp2.data.Units;


public class CustomListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] itemname;
    private final Integer[] imgid;
    private Integer[] temperatureLow;
    private Integer[] temperatureHigh;
    private final String[] condition;

    public CustomListAdapter(Activity context, String[] itemname, Integer[] imgid,Integer[] tempidLow,Integer[] tempidHigh,String[] condition) {
        super(context, R.layout.mylist, itemname);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.itemname=itemname;
        this.imgid=imgid;
        this.temperatureLow =tempidLow;
        this.temperatureHigh=tempidHigh;
        this.condition=condition;

    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.mylist, null,true);
        Units unit=new Units();

        TextView dayTextView = (TextView) rowView.findViewById(R.id.dayTextView);
        ImageView forecast_weather_icon = (ImageView) rowView.findViewById(R.id.forecast_weather_icon);
        TextView forecastConditionTextView = (TextView) rowView.findViewById(R.id.forecastConditionTextView);
        TextView froecastTemperatureLowTextView=(TextView) rowView.findViewById(R.id.forecastTemperatureLowTextView);
        TextView froecastTemperatureHighTextView=(TextView) rowView.findViewById(R.id.forecastTemperatureHighTextView);

        dayTextView.setText(itemname[position]);
        forecast_weather_icon.setImageResource(imgid[position]);
        forecastConditionTextView.setText(condition[position]);
        froecastTemperatureLowTextView.setText(temperatureLow[position]+"\u00B0"+unit.getTemperature());
        froecastTemperatureHighTextView.setText(temperatureHigh[position]+"\u00B0"+unit.getTemperature());

        return rowView;

    };
}

