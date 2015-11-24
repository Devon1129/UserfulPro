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
		
		buildViews();
		getData();
		mBtnEarth_Act();
	}

	public void buildViews() {
		mTextView = (TextView)findViewById(R.id.textView1_second);
		mBtnEarth = (Button)findViewById(R.id.btnEarth);
	}

	public void getData() {
		//抓取傳遞來的資料
		Intent intent = getIntent();
//		String Str = intent.getStringExtra("aaa!!!");//"aaa!!!"識別 key. //test1
		String Str = intent.getStringExtra(From.Earth_Msg);
		mTextView.setText(Str);
	}
	
	public void mBtnEarth_Act() {
		mBtnEarth.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(Earth.this, MainActivity.class);
				
				//傳遞資料回 MainActivity
				Bundle bundle = new Bundle();
				String Str = "火星來的消息: Hello, 我是火星的 jack, 非常高興你能來火星!";
//				bundle.putString("test", "this is test!"); //test1
				
				//將string內的text抽出來，放在一個 class內(From.java)，避免筆誤(string內的text不會檢查是否一致)
				bundle.putString(From.Msg, Str);
				intent.putExtras(bundle);
				
				//設定回去時接收的 resultCode.
				setResult(0, intent);
//				setResult(RESULT_OK, intent);//一般常用 RESULT_OK.
				finish();
			}
		});
	}
}
