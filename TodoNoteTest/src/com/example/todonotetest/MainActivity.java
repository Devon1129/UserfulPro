package com.example.todonotetest;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends ListActivity {
	private DBHelper dbHelper;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        dbHelper = new DBHelper(this);
        
        //???
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.todo_row, getData(),
        		new String[]{DBHelper.KEY_CATEGORY, DBHelper.KEY_SUMMARY}, new int[]{R.id.todotype, R.id.summary});
        setListAdapter(adapter);
        
        btnActivity();
    }
    public void btnActivity(){
    	Button btn = (Button)findViewById(R.id.button1);
    	btn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, Details.class);
				startActivity(intent);
			}
    		
    	});
    }
    
    //取得資料庫資料
    public Cursor getData(){
    	return dbHelper.getAllData();
    }
}
