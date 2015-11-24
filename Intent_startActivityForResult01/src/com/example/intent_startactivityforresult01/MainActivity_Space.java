package com.example.intent_startactivityforresult01;

import com.example.intent_startactivityforresulttest.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity_Space extends Activity {
	private static final int mRequestCode = 0;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.space);
        
        Button btn1 = (Button)findViewById(R.id.btn1);
        btn1.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(MainActivity_Space.this, SecondClass.class);
				startActivityForResult(intent, mRequestCode );//requestCode:0
			}
        });
    }

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch(requestCode){
		case mRequestCode://也可以使用 RESULT_OK
			Bundle bundle = data.getExtras();
			String StrIP = bundle.getString("IP");
			Toast.makeText(MainActivity_Space.this, StrIP, Toast.LENGTH_SHORT).show();
			break;
			
		default:
			Toast.makeText(MainActivity_Space.this, "test resultCode", Toast.LENGTH_SHORT).show();
			break;
				
		}
	}
}
