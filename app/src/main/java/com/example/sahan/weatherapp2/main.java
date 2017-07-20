package com.example.sahan.weatherapp2;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sahan.weatherapp2.data.Channel;
import com.example.sahan.weatherapp2.data.Item;
import com.example.sahan.weatherapp2.data.Location;
import com.example.sahan.weatherapp2.list_adapter.CustomListAdapter;
import com.example.sahan.weatherapp2.services.WeatherServiceCallback;
import com.example.sahan.weatherapp2.services.YahooWeatherService;

import java.util.List;


public class main extends AppCompatActivity implements WeatherServiceCallback {

    private ImageView backgroundImageView;
    private ImageView weatherIconImageView;
    private TextView temperatureTextView;
    private TextView conditionTextView;
    private TextView locationTextView;
    private TextView celsiusTextView;
    private TextView fahrenheitTextView;
    private SearchView searchView;

    private YahooWeatherService service;
    private ProgressDialog dialog;

    private static String tempUnit="c";
    private static String city_name="Kandy";
    private static String location_Tilte="Null";

    ListView list;

    String[] dayArray = new String[5];
    Integer[] imageID=new Integer[5];
    String[] testCondition=new String[5];
    Integer[] testLowTemperatureArray = new Integer[5];
    Integer[] testHighTemperatureArray = new Integer[5];
    String[] testDate=new String[5];


    String[] availableLocations;
    String[] tempUnits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        backgroundImageView=(ImageView) findViewById(R.id.backgroundImageView);
        weatherIconImageView=(ImageView) findViewById(R.id.weathericonImageView);
        temperatureTextView=(TextView)findViewById(R.id.temperatureTextView);
        conditionTextView=(TextView)findViewById(R.id.conditionTextView);
        locationTextView=(TextView)findViewById(R.id.locationTextView);
        celsiusTextView =(TextView) findViewById(R.id.celsiusTextView);
        fahrenheitTextView=(TextView) findViewById(R.id.fahrenheitTextView);

        Toolbar toolbar = (Toolbar)findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(main.this);
        tempUnit= sharedPref.getString("temp_unit","c");
        city_name=sharedPref.getString("default_city","NULL");

        if(tempUnit.equals("c")){
            celsiusTextView.setTextColor(Color.parseColor("#FFFFFF"));
            fahrenheitTextView.setTextColor(Color.parseColor("#808080"));
        }
        else{
            fahrenheitTextView.setTextColor(Color.parseColor("#FFFFFF"));
            celsiusTextView.setTextColor(Color.parseColor("#808080"));
        }

        availableLocations=getResources().getStringArray(R.array.availableLocations);
        tempUnits=getResources().getStringArray(R.array.tempUnits);





        dialog=new ProgressDialog(this);
        dialog.setMessage("Please Wait...");
        dialog.show();
        service=new YahooWeatherService(this);








        service.refreshWeather(city_name,tempUnit);

        celsiusTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                celsiusTextView.setTextColor(Color.parseColor("#FFFFFF"));
                fahrenheitTextView.setTextColor(Color.parseColor("#808080"));
                tempUnit="c";
                Toast.makeText(getApplicationContext(),"You Selected Celsius",Toast.LENGTH_LONG).show();
                service.refreshWeather(city_name,tempUnit);
            }
        });

        fahrenheitTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fahrenheitTextView.setTextColor(Color.parseColor("#FFFFFF"));
                celsiusTextView.setTextColor(Color.parseColor("#808080"));
                tempUnit="f";
                Toast.makeText(getApplicationContext(),"You Selected Fahrenheit",Toast.LENGTH_LONG).show();
                service.refreshWeather(city_name,tempUnit);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.settings_menu, menu);

        final MenuItem searchItem = menu.findItem(R.id.action_search);
        searchView =(SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                city_name=searchView.getQuery()+"";
                service.refreshWeather(city_name,tempUnit);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    @Override
    public void serviceSuccess(Channel channel) {

        dialog.hide();

        final Item item = channel.getItem();

        String city=Location.getCity();
        String country=Location.getCountry();
        String region=Location.getRegion();

        location_Tilte=city+", "+country;


        final int resourceID=getResources().getIdentifier("drawable/a"+ item.getCondition().getCode(),null,getPackageName());
        int backgroundID=getResources().getIdentifier("drawable/b"+item.getCondition().getCode(),null,getPackageName());

        @SuppressWarnings ("deprecation")
        Drawable weatherIconDrawable=getResources().getDrawable(resourceID);
        @SuppressWarnings ("deprecation")
        Drawable weatherBackDrawable=getResources().getDrawable(backgroundID);

        backgroundImageView.setBackground(weatherBackDrawable);
        weatherIconImageView.setImageDrawable(weatherIconDrawable);

        temperatureTextView.setText(item.getCondition().getTemperature()+"");
        conditionTextView.setText(item.getCondition().getDescription());
        locationTextView.setText(location_Tilte);

        imageID[0]=getResources().getIdentifier("drawable/a"+ item.getCode(0),null,getPackageName());
        imageID[1]=getResources().getIdentifier("drawable/a"+ item.getCode(1),null,getPackageName());
        imageID[2]=getResources().getIdentifier("drawable/a"+ item.getCode(2),null,getPackageName());
        imageID[3]=getResources().getIdentifier("drawable/a"+ item.getCode(3),null,getPackageName());
        imageID[4]=getResources().getIdentifier("drawable/a"+ item.getCode(4),null,getPackageName());

        dayArray=item.getDay();
        testLowTemperatureArray=item.getLow();
        testHighTemperatureArray=item.getHigh();
        testCondition=item.getDescription();
        testDate=item.getDate();

        //Creating The CustomList
        CustomListAdapter adapter=new CustomListAdapter(this, dayArray, imageID, testLowTemperatureArray, testHighTemperatureArray,testCondition);
        list=(ListView)findViewById(R.id.customList);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub

                int image_code=item.getCode(position);

                Intent intentForecast=new Intent(main.this,Forecast.class);
                intentForecast.putExtra("city_name",location_Tilte);
                intentForecast.putExtra("day",dayArray[position]);
                intentForecast.putExtra("image_id",imageID[position]);
                intentForecast.putExtra("image_id_back",image_code);
                intentForecast.putExtra("temp_low",testLowTemperatureArray[position]);
                intentForecast.putExtra("temp_high",testHighTemperatureArray[position]);
                intentForecast.putExtra("condition",testCondition[position]);
                intentForecast.putExtra("temp_unit",tempUnit);
                intentForecast.putExtra("date",testDate[position]);
                startActivity(intentForecast);
//                String Selecteditem= dayArray[position];
//                Toast.makeText(getApplicationContext(), Selecteditem, Toast.LENGTH_SHORT).show();

            }
        });

    }


    @Override
    public void serviceFailure(Exception exception) {
        dialog.hide();
        Toast.makeText(this,exception.getMessage(),Toast.LENGTH_SHORT);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                service.refreshWeather(city_name,tempUnit);
                return true;
            case R.id.action_settings:
                Intent intentSettings=new Intent(main.this,com.example.sahan.weatherapp2.settings.SettingMenu.class);
                startActivity(intentSettings);
                Toast.makeText(getApplicationContext(), "Settings", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_about_us:
                Intent intentAbout=new Intent(main.this,com.example.sahan.weatherapp2.about.AboutActivity.class);
                startActivity(intentAbout);

                Toast.makeText(getApplicationContext(), "ABOUT US", Toast.LENGTH_SHORT).show();
                return true;

            default:
// If we got here, the user's action was not recognized.
// Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }
}
