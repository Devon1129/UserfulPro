package com.example.startactivityforresult_test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.testarea.R;

public class Earth_2 extends Activity {
	private TextView mTextView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_earth);
		
		mTextView = (TextView)findViewById(R.id.textView1_second);
		
		getDate();
		btnEarth_Act();
	}

	public void getDate() {
		Intent intent = getIntent();
		String Str = intent.getStringExtra(From.Earth_Msg);
		mTextView.setText(Str);
	}

	public void btnEarth_Act() {
		Button btnEarth = (Button)findViewById(R.id.btnEarth);
		btnEarth.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(Earth_2.this, MainActivity.class);
				
				String Str = "月球來的消息:Hello, 我是月球的 Lucy,非常歡迎你來月球";
				Bundle bundle = new Bundle();
				bundle.putString(From.Msg, Str);
				intent.putExtras(bundle);
				
				setResult(RESULT_OK, intent);
//				setResult(1, intent);
				finish();				
			}
		});
	}
}
