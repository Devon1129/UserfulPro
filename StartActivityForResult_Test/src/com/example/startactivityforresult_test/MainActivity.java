package com.example.startactivityforresult_test;

import com.example.testarea.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	private static final String Message = "地球來的消息: 您好，我是來自地球上的小老鼠。我好想去你們的";
	private static final int Mars = 0;//將requestCode 抽出來
	private static final int Moon = 1;
	
	private Button mBtnMars, mBtnMoon;
	private TextView mTextView;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        buildView();
        mBtnMars_Act();
        mBtnMoon_Act();
    }
    
    private void buildView(){
    	 mTextView = (TextView)findViewById(R.id.textView1_main);
         mBtnMars = (Button)findViewById(R.id.btnMars);
         mBtnMoon = (Button)findViewById(R.id.btnMoon);
    }
    
    private void mBtnMars_Act(){
    	 mBtnMars.setOnClickListener(new OnClickListener(){

 			@Override
 			public void onClick(View v) {
 				Intent intent_Mars = new Intent();
 				intent_Mars.setClass(MainActivity.this, Earth.class);
 				
 				//傳資料給 Earth.class //test1
// 				Bundle bundle = new Bundle();
// 				bundle.putString("aaa!!!", "this is test ok!@@");
// 				intent.putExtras(bundle);
 				
 				//綁定資料傳給 Earth.class
 				Bundle bundle = new Bundle();
 				String Str = Message + "火星呀!";
 				bundle.putString(From.Earth_Msg, Str);
 				intent_Mars.putExtras(bundle);//+1
 				
 				startActivityForResult(intent_Mars, Mars);
 			}
         	
         });
    }
    
    private void mBtnMoon_Act(){
    	mBtnMoon.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent_Moon = new Intent();
				intent_Moon.setClass(MainActivity.this, Earth_2.class);
				
				Bundle bundle = new Bundle();
				String Str = Message + "月球呀!";
				bundle.putString(From.Earth_Msg, Str);
				intent_Moon.putExtras(bundle);//+1
				
				startActivityForResult(intent_Moon, Moon);
				
			}
		});
		
    }

    //接收傳遞來的資料，作處理
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		//從 data抓取綁定的資料
		Bundle bundle = data.getExtras();//+2
		String Str = bundle.getString(From.Msg);
		
		switch(requestCode){//+2
			case Mars: //resultCode
				mTextView.setText(Str);
				break;
			
			case Moon:
				mTextView.setText(Str);
				break;
			}
	}
}