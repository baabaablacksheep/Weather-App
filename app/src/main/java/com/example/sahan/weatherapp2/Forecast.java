package com.example.sahan.weatherapp2;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class Forecast extends AppCompatActivity {

    private ImageView backgroundImageViewForecast;
    private ImageView weatherIconImageViewForecast;
    private TextView temperatureLowTextViewForecast;
    private TextView temperatureHighTextViewForecast;
    private TextView conditionTextViewForecast;
    private TextView locationTextViewForecast;
    private TextView tempUnitLowTextViewForecast;
    private TextView tempUnitHighTextViewForecast;
    private TextView dayTextViewForecast;
    private TextView dateTextViewForecast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        ActionBar aBar = getSupportActionBar();
        aBar.setDisplayHomeAsUpEnabled(true);
        aBar.setTitle("Forecast");

        backgroundImageViewForecast=(ImageView) findViewById(R.id.backgroundImageViewForecast);
        weatherIconImageViewForecast=(ImageView) findViewById(R.id.weathericonImageViewForecast);
        temperatureLowTextViewForecast =(TextView)findViewById(R.id.temperatureLowTextViewForecast);
        temperatureHighTextViewForecast=(TextView)findViewById(R.id.temperatureHighTextViewForecast);
        conditionTextViewForecast=(TextView)findViewById(R.id.conditionTextViewForecast);
        locationTextViewForecast=(TextView)findViewById(R.id.locationTextViewForecast);
        tempUnitLowTextViewForecast =(TextView) findViewById(R.id.tempUnitLowTextViewForecast);
        tempUnitHighTextViewForecast =(TextView) findViewById(R.id.tempUnitHighTextViewForecast);
        dayTextViewForecast=(TextView) findViewById(R.id.dayTextViewForecast);
        dateTextViewForecast=(TextView) findViewById(R.id.dateTextViewForecast);

        String city_name = getIntent().getExtras().getString("city_name");
        String day = getIntent().getExtras().getString("day");
        int image_id = getIntent().getExtras().getInt("image_id");
        int image_id_back = getIntent().getExtras().getInt("image_id_back");
        int temp_low = getIntent().getExtras().getInt("temp_low");
        int temp_high = getIntent().getExtras().getInt("temp_high");
        String condition = getIntent().getExtras().getString("condition");
        String temp_unit = getIntent().getExtras().getString("temp_unit");
        String date=getIntent().getExtras().getString("date");

        //Toast.makeText(getApplicationContext(), date+"", Toast.LENGTH_LONG).show();

        int backgroundID=getResources().getIdentifier("drawable/b"+image_id_back,null,getPackageName());

        @SuppressWarnings ("deprecation")
        Drawable weatherIconDrawable=getResources().getDrawable(image_id);
        @SuppressWarnings ("deprecation")
        Drawable weatherBackDrawable=getResources().getDrawable(backgroundID);

        weatherIconImageViewForecast.setImageDrawable(weatherIconDrawable);
        backgroundImageViewForecast.setBackground(weatherBackDrawable);

        locationTextViewForecast.setText(city_name);
        dayTextViewForecast.setText(day);
        dateTextViewForecast.setText(date);
        temperatureLowTextViewForecast.setText(temp_low+"");
        temperatureHighTextViewForecast.setText(temp_high+"");
        conditionTextViewForecast.setText(condition);

        if(temp_unit.equals("f")){
            tempUnitLowTextViewForecast.setText("°F");
            tempUnitHighTextViewForecast.setText("°F");
        }
        else{
            tempUnitLowTextViewForecast.setText("°C");
            tempUnitHighTextViewForecast.setText("°C");
        }


    }
}


