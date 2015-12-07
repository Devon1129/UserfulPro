package com.example.todonotetest;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends ListActivity {
	private static final int ACTIITY_CREATE = 0;
	private static final int ACTIVITY_EDIT = 1;
	private static final int DELETE_ID = Menu.FIRST + 1;
	
	private int mPosition;
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
//		 cursor.close();//Q:在何時關閉才適合?
		
		 
		 //2.2 讀取所有 todo，並填入至 ListView.
        fillData();
        
        //註冊上下文給 getListView().
        registerForContextMenu(getListView());//+1
        
        btnActivity();
        
//        cursor.close();//Q:在何時關閉才適合?
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
    
    private void createTodo() {
		Intent intent = new Intent();
		intent.setClass(MainActivity.this, Details.class);
		startActivityForResult(intent, ACTIITY_CREATE);
		
	}
    
    @Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.add(0, DELETE_ID, 0, R.string.memu_delete);
	}
    
    	
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflator = getMenuInflater();
		inflator.inflate(R.menu.listmenu, menu);
		return super.onCreateOptionsMenu(menu);
	}
    
    //當 Menu有命令被選時，會調用此方法
    //這裡將 OptionsMenu 及 contextMenu的選項寫於此
 	@Override
 	public boolean onMenuItemSelected(int featureId, MenuItem item) {
 		switch(item.getItemId()){
 		case R.id.insert:
 			createTodo();
 			return true;
 		case DELETE_ID:
 			/*
 			 * 被選的 View item是一個 ListView item．為了在選的一個 view item上执行相應的動作，
 			 * 應用程式需要知道 View item中的信息．為了獲得View ，程式中調用了 getMenuInfo(),
 			 * 它返回一個 AdapterView.AdapterContextMenuInfo 對象。
             */
 			AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo(); 
 			mDBManipulate.deleteTodo(info.id);//+1
 			fillData();
 			Toast.makeText(MainActivity.this, "delete action: " + info.id, Toast.LENGTH_SHORT).show();
 			break;
		default:
			Toast.makeText(MainActivity.this, "Other action: ", Toast.LENGTH_SHORT).show();
			break;
 		}
 		return super.onMenuItemSelected(featureId, item);
 	}   
    
    
//    @Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		switch(item.getItemId()){
//		case R.insert:
//			createTodo();
// 			return true;
//		}
//		return super.onOptionsItemSelected(item);
//	}
    
 
	//	@Override
//	public boolean onContextItemSelected(MenuItem item) {
//		switch(item.getItemId()){
//		case DELETE_ID:
//			mDBManipulate.delete(rowId);
//		}
//		return super.onContextItemSelected(item);
//	}
 	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
		mPosition =  position;
		
		Intent intent_Item = new Intent();
		intent_Item.setClass(MainActivity.this, Details.class);
		
		intent_Item.putExtra(DBManipulate.KEY_ROWID, id);//+1
		startActivityForResult(intent_Item, ACTIVITY_EDIT);
		
		Toast.makeText(MainActivity.this, "修改位置 " + mPosition, Toast.LENGTH_SHORT).show();
	}
    
    
	//在此處理 Details傳回的資料
    @Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		Toast.makeText(MainActivity.this, "已修改位置 " + mPosition, Toast.LENGTH_SHORT).show();
		
		fillData();
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
    
    //記得關掉DB連線(即close() method內做的事)
	@Override
	protected void onDestroy() {
		super.onDestroy();
		if(mDBManipulate != null){
			mDBManipulate.close();
		}
	}
}
