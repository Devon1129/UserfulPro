package com.example.intent_startactivityforresult02;

import com.example.intent_startactivityforresulttest2.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	private TextView mTextView;
	private TextView mTextView2;
	private Button mBtnMars, mBtnMoon;
	private static final int Mars = 0;
	private static final int Moon = 1;
	Drawable mDrawable;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mTextView = (TextView)findViewById(R.id.textView1_main);
        mTextView2 = (TextView)findViewById(R.id.textView2_main);
        
        mBtnMars = (Button)findViewById(R.id.btnMars);
        mBtnMars.setOnClickListener(new OnClickListener(){

        	//設定 intent傳遞的資料.
			@Override
			public void onClick(View v) {
				Intent intent_Mars = new Intent();
				intent_Mars.setClass(MainActivity.this, Second.class);

				String content = "來的消息:你好，我是來自地球上的小老鼠。我好想去你們的火星呀!!!";
				intent_Mars.putExtra(From.FromEarth, content);//+1
				
				startActivityForResult(intent_Mars, Mars);
				
			}
        });
        
        
        mBtnMoon = (Button)findViewById(R.id.btnMoon);
        mBtnMoon.setOnClickListener(new OnClickListener(){

        	//設定 intent傳遞的資料.
			@Override
			public void onClick(View v) {
				Intent intent_Moon = new Intent();
				intent_Moon.setClass(MainActivity.this, Third.class);
			
				String content = "來的消息:你好，我是來自地球上的小老鼠。我好想去你們的月球呀!";
				
				intent_Moon.putExtra(From.FromEarth, content);
				
				startActivityForResult(intent_Moon, Moon);
			}
        });
    }

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch(requestCode){
		case Mars:
			mDrawable = mBtnMars.getBackground();
			
			mBtnMars.setBackgroundColor(Color.RED);//設定 Button背景色
			mBtnMoon.setBackgroundDrawable(mDrawable);
			
			//文字偏好設定
			mTextView.setText("火星");
			mTextView.setTextColor(Color.RED);
			mTextView.setText(Html.fromHtml("<big><big>火星</big></big>"));
			
			//獲取傳遞來的文字
			Bundle MarsBundle = data.getExtras();//+1
			String MarsMessage = MarsBundle.getString(From.Message);//+1
			
			//將傳遞來的文字作不同區的偏好設定
			SpannableStringBuilder style_Mars = new SpannableStringBuilder(MarsMessage);
			style_Mars.setSpan(new ForegroundColorSpan(Color.RED), 13, 15, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			style_Mars.setSpan(new ForegroundColorSpan(Color.RED), 28, 30, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			
			//將設定好的偏好文字，設給 TextView顯示
			mTextView2.setText(style_Mars);
			
			break;
			
		case Moon:
			//取消 Button mBtnMars的背景色
			mBtnMars.setBackgroundDrawable(mDrawable);
			mBtnMoon.setBackgroundColor(Color.GREEN);
			
			//文字偏好設定
			mTextView.setText("月球");
			mTextView.setTextColor(Color.GREEN);
			
			Bundle MoonBundle = data.getExtras();
			String MoonMessage = MoonBundle.getString(From.Message);
			
			SpannableStringBuilder style_Moon = new SpannableStringBuilder(MoonMessage);
			style_Moon.setSpan(new ForegroundColorSpan(Color.GREEN), 13, 15, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			style_Moon.setSpan(new ForegroundColorSpan(Color.GREEN), 27, 29, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			mTextView2.setText(style_Moon);
			
			break;
		}
		
		//傳遞回來的資料，以 From.Message 做區分
//		Bundle MarsBundle = data.getExtras();//+1		
//		String MarsMessage = MarsBundle.getString(From.Message);//+1
//		mTextView2.setText(MarsMessage);		
	}
}

//http://blog.csdn.net/sjf0115/article/details/7387467
//http://zhidao.baidu.com/question/410078445.html

