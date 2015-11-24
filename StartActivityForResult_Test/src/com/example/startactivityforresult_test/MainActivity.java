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
	private Button mBtnMars, mBtnMoon;
	private TextView mTextView;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mTextView = (TextView)findViewById(R.id.textView1_main);
        mBtnMars = (Button)findViewById(R.id.btnMars);
        mBtnMoon = (Button)findViewById(R.id.btnMoon);
        
        mBtnMars.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, Earth.class);
				
				//傳資料給 Earth.class //test1
//				Bundle bundle = new Bundle();
//				bundle.putString("aaa!!!", "this is test ok!@@");
//				intent.putExtras(bundle);
				
				//傳資料給 Earth.class
				Bundle bundle = new Bundle();
				String Str = "地球來的消息: 您好，我是來自地球上的小老鼠。我好想去你們的火星呀!";
				bundle.putString("earth_Message", Str);
				intent.putExtras(bundle);//+1
				
				startActivityForResult(intent, 0);
			}
        	
        });
    }

    //接收傳遞來的資料，作處理
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch(resultCode){//+1
		case 0: //resultCode
			//從 data抓取綁定的資料
			Bundle bundle = data.getExtras();//+1
			String Str = bundle.getString("test");
			
			mTextView.setText(Str);
		}
		
	}
}