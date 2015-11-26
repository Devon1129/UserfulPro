package com.example.todonotetest;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class Details extends Activity {
	private Spinner mSpnCategory;
	private EditText mEtSummary, mEtDescription;
	private Button mBtnConfirm;
	private Long mRowId;
	
	private DBManipulate mDBManipulate;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.todo_details);
		
		buildViews();
		
		//1.產生 DB Manipulate，用來讀取/寫入DB. 
		mDBManipulate = new DBManipulate(this);
		mDBManipulate.open();
		
		//2.讀取從上一個畫面傳進來的資料包
		//若有資料，更新 row id,即[編輯]模式，
		//沒有資料，則為 [新增]模式
		Bundle extras = getIntent().getExtras();
		if(extras != null){
			mRowId = extras.getLong(DBManipulate.KEY_ROWID);
		}
		
		populateFields();//填寫欄位
		btnConfirm_Act();
	}


	
	public void buildViews(){
		mSpnCategory = (Spinner)findViewById(R.id.spncategory);
		mEtSummary = (EditText)findViewById(R.id.etsummary);
		mEtDescription = (EditText)findViewById(R.id.etdescription);
		mBtnConfirm = (Button)findViewById(R.id.btnConfirm);
	}
	
	public void btnConfirm_Act() {
		mBtnConfirm.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				setResult(RESULT_OK);
				saveState();
				finish();
			}
			
		});
	}
	
	public void saveState(){
		String category = (String) mSpnCategory.getSelectedItem();//spinner抓選項(category)
		String summary = mEtSummary.getText().toString();
		String description = mEtDescription.getText().toString();
		
		if(mRowId == null){
			long id = mDBManipulate.createTodo(category, summary, description);
			if(id > 0){
				mRowId = id;
			}
		}else{
			mDBManipulate.updateTodo(mRowId, category, summary, description);
		}
	}
	
	//填寫欄位  **做到這了
	public void populateFields(){
		if(mRowId != null){
			Cursor curTodo = mDBManipulate.getData(mRowId);
			startManagingCursor(curTodo);
			
			String category = 
					curTodo.getString(
							curTodo.getColumnIndexOrThrow(DBManipulate.KEY_CATEGORY));//cursor內抓 index name.(category)
			
			//設定spinner item 與 curTodo的category 一致.
			for(int i = 0; i < mSpnCategory.getCount(); i++){
				String Str = (String) mSpnCategory.getItemAtPosition(i);
				
				Log.e("category exsits or not", Str + " " + category);
				
				if(Str.equalsIgnoreCase(category)){
					mSpnCategory.setSelection(i);
				}
			}
			mEtSummary.setText(curTodo.getString(curTodo.getColumnIndexOrThrow(DBManipulate.KEY_SUMMARY)));
			mEtDescription.setText(curTodo.getString(curTodo.getColumnIndexOrThrow(DBManipulate.KEY_DESCRIPTION)));
		}
	}
}
