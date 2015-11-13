package com.example.sqliteinserttest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
	private static final String DB_Name = "cusNote.db";
	private static final int DB_Version = 1;
	private static final String Table_Name = "cus";

	public DBHelper(Context context) {
		super(context, DB_Name, null, DB_Version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "create table " + Table_Name + 
				" ( cusNo varchar primary key, " + 
				" cusNa varchar(20) not null, " +
				" cusPho varchar(10) , " +
				" cusAdd varchar(50));";
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String sql = ("drop table if exsits " + Table_Name);
		db.execSQL(sql);
		onCreate(db);
	}
	
	//插入新增資料
	public long insert(String cusNo, String cusNa, String cusPho, String cusAdd){
		SQLiteDatabase db = getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("cusNo", cusNo);
		values.put("cusNa", cusNa);
		values.put("cusPho", cusPho);
		values.put("cusAdd", cusAdd);
		long rowId = db.insert(Table_Name, null, values);
		return rowId;
	}
	
	//資料筆數
	public int recCount(){
		SQLiteDatabase db = getWritableDatabase();
		String sql = "select * from " + Table_Name;
		Cursor curRec = db.rawQuery(sql, null);
		int recCount = curRec.getCount();
		return recCount;
	}

}
