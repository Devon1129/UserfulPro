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
	JSONArray mCoordinates; 
	
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
        mCoordinates = geometry.getJSONArray("coordinates");
        
//      todo this...
        showMsg();
        
        LatLon alatLon = new LatLon();
        String tmpLatLon = "";
		
		List<LatLon> posClassList = new ArrayList<LatLon>();
		List<String>  latlonList = new ArrayList<String>();
		posClassList.add(alatLon);//return boolean type.
		latlonList.add(tmpLatLon);
		
        Log.e("Ex", mCoordinates.toString());
        
        String[] Str ={place, time, mCoordinates.toString()};
        
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Str);
        lv.setAdapter(adapter);
        
        List<String> q = new ArrayList<String>(); 
	}
	
	
	public void showMsg() throws JSONException{
		//使用 class賦值
	//  LatLon alatLon = new LatLon();
	//	alatLon.lat = coordinates.getDouble(0);
	//	alatLon.lon = coordinates.getDouble(1);
	//	
	//	Toast.makeText(MainActivity.this,
	//			"Lat: " + alatLon.lat
	//			+ " Lon:" + alatLon.lon 
	//			+ " dep:" + coordinates.getDouble(2) + "" , Toast.LENGTH_LONG).show();
		
		String tmpLatLon = "";
		
	    //type為 double.，要串接，type需為 String類型
		LatLon alatLon = new LatLon();
		alatLon.lon = mCoordinates.getDouble(0);
		alatLon.lat = mCoordinates.getDouble(1);
		
		for(int i = 0; i < mCoordinates.length(); i++){
			tmpLatLon += String.valueOf(mCoordinates.getString(i) + "|");
			if(i == 1){
				break;
				//return會返回到 call此 method的位置
				//finish會直接結束程式，but Toast有出現
			}
		}
		Toast.makeText(MainActivity.this, "msg: " + tmpLatLon, Toast.LENGTH_LONG).show();
	}
}
