package com.example.intent_startactivityforresult02;

import com.example.intent_startactivityforresulttest2.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Second extends Activity {
	private TextView mTextView;
	private TextView mTextView2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second_activity);
		
		mTextView = (TextView)findViewById(R.id.textView1_second);
		mTextView2 = (TextView)findViewById(R.id.textView2_second);
		
		//獲取 MainActivity傳遞來的資料
		Intent intent_Earth = getIntent();
		String EarthMessage = intent_Earth.getStringExtra(From.FromEarth);//+1
		
		//文字偏好設定
		String area = "地球";
		mTextView.setText(area);
		mTextView.setTextColor(Color.BLUE);
//		SpannableStringBuilder style_area = new SpannableStringBuilder(area);
//		style_area.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 1, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//		mTextView.setText(style_area);
		
		mTextView.setText(Html.fromHtml("<h6><font color = 'ff0000ff'>地球</font></h6>"));
		
		//將傳遞過來的文字(資料)作偏好設定
		mTextView2.setText(EarthMessage);
		SpannableStringBuilder style = new SpannableStringBuilder(EarthMessage);
		style.setSpan(new ForegroundColorSpan(Color.BLUE), 12, 14, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		style.setSpan(new BackgroundColorSpan(Color.YELLOW), 12, 14, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		style.setSpan(new ForegroundColorSpan(Color.RED), 27, 29, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		mTextView2.setText(style);
		
		Button btnEarth = (Button)findViewById(R.id.btnEarth);
		btnEarth.setOnClickListener(new OnClickListener(){

			//Intent傳遞資料回 MainActivity
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(Second.this, MainActivity.class);
					
				//部分文字偏好設定
				String passStr = "來的消息:Hello,我是火星的Jack，非常高興你能來火星^^~~";
				SpannableStringBuilder style = new SpannableStringBuilder(passStr);
				style.setSpan(new ForegroundColorSpan(Color.BLUE), 13, 15, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				style.setSpan(new ForegroundColorSpan(Color.BLUE), 28, 30, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				
				//綁定給 intent 要傳遞的資料
				intent.putExtra(From.Message, passStr);//+1
				
				//設定 RusultCode
				setResult(RESULT_OK, intent);
				finish();
			}
		});
	}
}
