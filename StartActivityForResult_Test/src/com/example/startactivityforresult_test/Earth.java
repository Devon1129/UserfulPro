package com.example.startactivityforresult_test;

import com.example.testarea.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Earth extends Activity {
	private Button mBtnEarth;
	private TextView mTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_earth);
		
		
		mTextView = (TextView)findViewById(R.id.textView1_second);
		mBtnEarth = (Button)findViewById(R.id.btnEarth);
		
		//抓取傳遞來的資料
		Intent intent = getIntent();
//		String Str = intent.getStringExtra("aaa!!!");//"aaa!!!"識別 key. //test1
		String Str = intent.getStringExtra("earth_Message");
		mTextView.setText(Str);
		
		mBtnEarth.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(Earth.this, MainActivity.class);
				
				//傳遞資料回 MainActivity
				Bundle bundle = new Bundle();
				String Str = "火星來的消息: Hello, 我是火星的 jack, 非常高興你能來火星!";
//				bundle.putString("test", "this is test!"); //test1
				bundle.putString("test", Str);
				intent.putExtras(bundle);
				
				//設定回去時接收的 resultCode.
				setResult(0, intent);
				finish();
			}
		});
	}
}
