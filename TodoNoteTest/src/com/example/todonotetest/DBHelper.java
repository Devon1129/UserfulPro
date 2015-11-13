package com.example.todonotetest;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
	private static final String DB_Name = "applicationdata.db";
	private static final int DB_Version = 1;
	private static final String Table_Name = "todolist";  
	
	public static final String 	KEY_ROWID = "_id";
	public static final String 	KEY_CATEGORY = "category";
	public static final String 	KEY_SUMMARY = "summary";
	public static final String 	KEY_DESCRIPTION = "description";

	public DBHelper(Context context) {
		super(context, DB_Name, null, DB_Version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "create table " + Table_Name + 
				" (_id integer primary key autoincrement, " +
				" category text not null, " + " summary text not null, " +
				" description text not null);";
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String sql = ("dorp table if exsits " + Table_Name);
		db.execSQL(sql);
		onCreate(db);
	}
	
	public Cursor getAllData(){
		SQLiteDatabase db = getReadableDatabase();
		return db.query(Table_Name, new String[]{KEY_ROWID, KEY_CATEGORY, KEY_SUMMARY, KEY_DESCRIPTION}, 
				null, null, null, null, null);
	}
}
