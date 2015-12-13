package com.example.jsontest;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class Test extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		try {
			testJson();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void testJson() throws JSONException {
		JSONObject j;
		String tmp = 
				"{\"Data\":{\"Name\":\"MichaelChan\", \"Email\":\"XXX@XXX.com\", \"Phone\":[1234567, 0911123456]}}";
		
		j = new JSONObject(tmp);
		
		//1.抓取全部資料
//		Object jsonOb = j.getJSONObject("Data");
		
		//2.抓取 Name資料
//		Object jsonOb = j.getJSONObject("Data").get("Name");
		
		//3.抓取 Phone資料
//		Object jsonOb = j.getJSONObject("Data").getJSONArray("Phone");
		
		//4.抓取 Phone其中一筆資料
		Object jsonOb = j.getJSONObject("Data").getJSONArray("Phone").get(0);
				
		Toast.makeText(Test.this, jsonOb + "", Toast.LENGTH_LONG).show();
		Log.e("jsonOb", jsonOb + "");
		//1.抓取全部資料: jsonOb 輸出顯示:{"Name":"MichaelChan","Email":"XXXX@XXX.com","Phone":[1234567,0911123456]}
		//2.抓取 Name資料: jsonOb 輸出顯示:MichaelChan
		//3.抓取 Phone資料: jsonOb 輸出顯示:[1234567,0911123456]
		//4.抓取 Phone其中一筆資料: jsonOb 輸出顯示: 1234567
	}
	
}
