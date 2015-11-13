package com.example.sqliteinserttest;

import com.example.test.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	private DBHelper dbHelper;
	private EditText etCusNo, etCusNa, etCusPho, etCusAdd;
	
    
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        dbHelper = new DBHelper(this);
        
        buildViews();
    }
	
	public void buildViews(){
		etCusNo = (EditText)findViewById(R.id.editText1);
		etCusNa = (EditText)findViewById(R.id.editText2);
		etCusPho = (EditText)findViewById(R.id.editText3);
		etCusAdd = (EditText)findViewById(R.id.editText4);
		Button btn = (Button)findViewById(R.id.button1);
		btn.setOnClickListener(btnListener);
	}
	
	public OnClickListener btnListener = new OnClickListener(){

		@Override
		public void onClick(View v) {
			
			String cusNo = etCusNo.getText().toString();
			String cusNa = etCusNa.getText().toString();
			String cusPho = etCusPho.getText().toString();
			String cusAdd = etCusAdd.getText().toString();
			
//			dbHelper.recCount();
			
			long rowId = dbHelper.insert(cusNo, cusNa, cusPho, cusAdd);
			if(rowId == -1){
				Toast.makeText(MainActivity.this, "新增資料失敗", Toast.LENGTH_SHORT).show();
				
			}else{
				Toast.makeText(MainActivity.this, "共 " + rowId + "筆資料", Toast.LENGTH_SHORT).show();
				Toast.makeText(MainActivity.this, "目前 共 " + dbHelper.recCount() + "筆資料", Toast.LENGTH_SHORT).show();
			}
			
		}
	};
  
}