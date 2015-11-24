package com.example.intent_startactivityforresult01;

import com.example.intent_startactivityforresulttest.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SecondClass extends Activity {
	
	private Button mBtnOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        
        mBtnOk = (Button)findViewById(R.id.btnok);
        mBtnOk.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				EditText etHttp = (EditText)findViewById(R.id.ethttp);
				String Http = etHttp.getText().toString();
				
				//MainActivity_Space沒有傳資料過來
				Intent intent = new Intent();//設定 intent傳遞的資料.
				Bundle bundle = new Bundle();
				bundle.putString("IP", Http);
				intent.putExtras(bundle);
				
				setResult(RESULT_OK, intent);
				finish();
			}
        });
    }
}
