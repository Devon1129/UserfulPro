package com.example.todonotetest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class DBManipulate {
	private static final String Table_Name = "todolist";
	public static final String 	KEY_ROWID = "_id";
	public static final String 	KEY_CATEGORY = "category";
	public static final String 	KEY_SUMMARY = "summary";
	public static final String 	KEY_DESCRIPTION = "description";
	
	private Context context;
	private DBHelper dbHelper;
	private SQLiteDatabase database;
	
	public DBManipulate(Context context){
		this.context = context;
	}
	
	public void open() throws SQLException{
		dbHelper = new DBHelper(context);
		database = dbHelper.getWritableDatabase();
	}
	
	public void close(){
		dbHelper.close();
	}
	
	public long createTodo(String category, String summary, String description){
		ContentValues initialValues =
				createContentValues(category, summary,description);
		return database.insert(Table_Name, null, initialValues);
	}
	
	public boolean updateTodo(long rowId, String category, String summary, String description){
		ContentValues values = 
				createContentValues(category, summary,description);
		return database.update(Table_Name, values, KEY_ROWID + " =" + rowId, null) > 0;
	}
	
	public boolean deleteTodo(long rowId){
		String msg = "";
		if(rowId == 0){
			msg = "找不到資料!";
		}else{
			msg = "刪除 資料" + rowId;
		}
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
		return database.delete(Table_Name, KEY_ROWID + "=" + rowId, null) > 0;
	}
	
	public Cursor getAllData(){
		return database.query(Table_Name, new String[]{KEY_ROWID, KEY_CATEGORY, KEY_SUMMARY, KEY_DESCRIPTION}, 
				null, null, null, null, null);
	}
	
	public Cursor getData(long rowId) throws SQLException{
		Cursor mCursor = database.query(
				true, //if you want each row to be unique, false otherwise.
				Table_Name, 
				new String[]{KEY_ROWID, KEY_CATEGORY, KEY_SUMMARY, KEY_DESCRIPTION}, 
				KEY_ROWID + " = " + rowId, 
				null, null, null, null, null, null);
		
		/*
		 * 用 SQLite得到的 Query結果會存在 Cursor這個指標裡
		 * 但是這個指標一開始是指向 -1的位置
		 * 所以源碼裡有一行 moveToFirst()，把 cursor指到第一個位置， 
		 */
		if(mCursor != null){
			mCursor.moveToFirst();//將指標移至第一筆資料
		}
		return mCursor;
	}
	
	public ContentValues createContentValues(String category, String summary,
			String description) {
		ContentValues values = new ContentValues();
		values.put(KEY_CATEGORY, category);
		values.put(KEY_SUMMARY, summary);
		values.put(KEY_DESCRIPTION, description);
		return values;
	}
}
