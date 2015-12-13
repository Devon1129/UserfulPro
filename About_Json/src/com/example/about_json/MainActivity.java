package com.example.about_json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
//      testJson01();  
        
//      try {
//		testJson2();
//	} catch (JSONException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
      
      try {
		testJson03();
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      
}

    //直接 throws JSONException
	public void testJson2() throws JSONException {
		JSONObject jsonObject1 = new JSONObject();
		jsonObject1.put("Name", "Tom");
		jsonObject1.put("age", "11");
		  
		Log.e("jsonTest2", "解析字符串:" + "\r" + jsonObject1.getString("Name"));
		  
		  
		JSONObject jsonObject = new JSONObject("{'Name':'Tom', 'age': '11'}");
		
		JSONObject jsonObject2 = new JSONObject();
		jsonObject2.put("jsonObject", jsonObject);
		
		Log.e("jsonTest2", "解析對象:");
		Log.e("jsonTest2", jsonObject2.getJSONObject("jsonObject").toString());
	}

	public void testJson03() throws JSONException{
		//初始化JSONObject 方法一  
		JSONObject jsonObject1 = new JSONObject();
		jsonObject1.put("Name", "Tom");
		jsonObject1.put("age", "11");
		
		Log.e("jsonObject1", jsonObject1.toString());
		//note:輸出.....,"age":"11"
		
		
		//初始化JSONObject 方法二
		JSONObject jsonObject2 = new JSONObject("{Name:Tom, age:11}");
		Log.e("jsonObject2", jsonObject2.toString());
		//note:輸出....,"age":11
		
		//初始化JSONArray 方法一
		JSONArray jsonAry1 = new JSONArray();
		jsonAry1.put("abc");
		jsonAry1.put("xyz");
		
		Log.e("jsonAry1", jsonAry1.toString());
		
		//初始化JSONArray 方法二
		JSONArray jsonAry2 = new JSONArray("[abc, xyz]");
		Log.e("jsonAry2", jsonAry2.toString());
		
		
	}
	
	//沒有 throws JSONObject，compiler 就會一直要求你 try catch
	public void testJson01() {
		//初始化JSONObject 方法一  
		    JSONObject jsonObject1 = new JSONObject();  
		    try {
				jsonObject1.put("Name", "Tom");
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
		    try {
				jsonObject1.put("age", "11");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
		    Log.e("jsonObject1", jsonObject1.toString());
		    
		    //初始化JSONObject 方法二  
		    try {
				JSONObject jsonObject2 = new JSONObject("{'Name':'Tom','age':'11'}");
				
				Log.e("jsonObject2", jsonObject2.toString());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}  
		      
		    //初始化JSONArray 方法一  
		    JSONArray jsonArray1 = new JSONArray();  
		    jsonArray1.put("abc");  
		    jsonArray1.put("xyz");  
		    
		    Log.e("jsonArray1", jsonArray1.toString());
		      
		    //初始化JSONArray 方法二  
		    try {
				JSONArray jsonArray2 = new JSONArray("['abc','xyz']");
				Log.e("jsonArray2", jsonArray2.toString());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
        
}
