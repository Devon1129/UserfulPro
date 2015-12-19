package com.example.quakewithgooglemap_ex;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        try {
			buildViews();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    
    }

	private void buildViews() throws JSONException{
		ListView lv = (ListView)findViewById(R.id.listView1);
		
		StrictMode.ThreadPolicy policy  = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        
        String fullJson = (new GetHttpTest()).getHTML("http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/4.5_day.geojson");
        
        JSONObject Obj = new JSONObject(fullJson);
        
        JSONArray features = Obj.getJSONArray("features");
        
        JSONObject featuresOne = features.getJSONObject(0);
        JSONObject properties = featuresOne.getJSONObject("properties");
        String place = properties.getString("place");
        String time = properties.getString("time");
        
        Log.e("Ex", place + ": " +  time);
        
        
        JSONObject geometry = featuresOne.getJSONObject("geometry");
        JSONArray coordinates = geometry.getJSONArray("coordinates");
        
        
        Log.e("Ex", coordinates.toString());
        
        String[] Str ={place, time, coordinates.toString()};
        
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Str);
        lv.setAdapter(adapter);
        
        List<String> q = new ArrayList<String>(); 
	}
}
