package com.example.todonotetest;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends ListActivity {
	private static final int ACTIITY_CREATE = 0;
	private static final int ACTIVITY_EDIT = 1; 
	
	private Button mBtnCreateTodo;
	private DBManipulate mDBManipulate;
	
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //設定分隔線高度。default:灰色線
        this.getListView().setDividerHeight(5);
        
        //1.產生DB ManipulateData，用來讀取/寫入DB.
        mDBManipulate = new DBManipulate(this);
        mDBManipulate.open();
        
        //2.讀取所有todo.
        //2.1 先在這裡讀一次全部todo, 以檢查是否DB無資料並加入一筆default todo.
        Cursor cursor = mDBManipulate.getAllData();
		startManagingCursor(cursor);
		
		 if(cursor.getCount() == 0){
	        	mDBManipulate.createTodo("this is category", "this is summary", "this is description");
	        }
//		 cursor.close();Q:在何時關閉才適合?
		 
		 //2.2 讀取所有 todo，並填入至 ListView.
        fillData();
        btnActivity();
        
    }
    public void btnActivity(){
    	Button btn = (Button)findViewById(R.id.button1);
    	btn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				createTodo();
			}
    	});
    }
    
    @Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
		Intent intent_Item = new Intent();
		intent_Item.setClass(MainActivity.this, Details.class);
		
		intent_Item.putExtra(DBManipulate.KEY_ROWID, id);//+1
		startActivityForResult(intent_Item, ACTIVITY_EDIT);
	}
    
    
	//在此處理 Details傳回的資料
    @Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		fillData();
	}
    
    private void createTodo() {
		Intent intent_btn = new Intent();
		intent_btn.setClass(MainActivity.this, Details.class);
		startActivityForResult(intent_btn, ACTIITY_CREATE);
	}
    
	//取得資料庫資料
    public Cursor getData(){
    	return mDBManipulate.getAllData();
    }
    
    //填充資料於 ListView上
    private void fillData(){
    	Cursor cursor = mDBManipulate.getAllData();
		startManagingCursor(cursor);
        
        //讀取所有 todo,並顯示至ListView.
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.todo_row, getData(),
        		new String[]{mDBManipulate.KEY_CATEGORY, mDBManipulate.KEY_SUMMARY}, 
        		new int[]{R.id.todotype, R.id.summary});
        setListAdapter(adapter);
    }
    
	@Override
	protected void onDestroy() {
		super.onDestroy();
		if(mDBManipulate != null){
			mDBManipulate.close();
		}
	}
    
    
}
