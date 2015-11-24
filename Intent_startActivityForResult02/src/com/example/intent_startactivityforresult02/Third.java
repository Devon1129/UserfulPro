package com.example.intent_startactivityforresult02;

import com.example.intent_startactivityforresulttest2.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Third extends Activity {
	private TextView mTextView;  
	private TextView mTextView2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second_activity);
		
		mTextView = (TextView)findViewById(R.id.textView1_second);
		mTextView2 = (TextView)findViewById(R.id.textView2_second);
		
		//獲取傳遞來的資料
		Intent intent_Earth = getIntent();
		
		//取得傳遞來的 String
		String EarthMessage = intent_Earth.getStringExtra(From.FromEarth);
		
		//設定文字偏好
		String area = "地球";
		mTextView.setText(area);
		mTextView.setTextColor(Color.BLUE);
		
		SpannableStringBuilder style = new SpannableStringBuilder(EarthMessage);
		style.setSpan(new ForegroundColorSpan(Color.BLUE), 12, 14, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		style.setSpan(new ForegroundColorSpan(Color.GREEN), 27, 29, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		style.setSpan(new UnderlineSpan(), 27, 29, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);//下劃線
		mTextView2.setText(style);

		Button btnEarth = (Button)findViewById(R.id.btnEarth);
		btnEarth.setOnClickListener(new OnClickListener(){

			//Intent傳遞資料回 MainActivity
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				
				String passStr = "來的消息:Hello,我是月球的Lucy,非常歡迎你來月球";
				intent.putExtra(From.Message, passStr);
				
				intent.setClass(Third.this, MainActivity.class);
				setResult(RESULT_OK, intent);
				finish();
			}
		});
	}
}
