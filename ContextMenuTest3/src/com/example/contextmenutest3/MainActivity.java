package com.example.contextmenutest3;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
	private TextView textView01, textView02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        textView01 = (TextView)findViewById(R.id.textView1);
        textView02 = (TextView)findViewById(R.id.textView2);
        
        registerForContextMenu(textView01);
        registerForContextMenu(textView02);
    }
    

	int selectedIndex01 = -1;//作識別。是哪一個contextMenu。
	int selectedIndex02 = -1;

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		int viewId = v.getId();//識別哪一個viewID.
		MenuInflater inflator = new MenuInflater(this);
		
		if(viewId == R.id.textView1){
			inflator.inflate(R.menu.backgroundcolorlist, menu);
			menu.setHeaderIcon(R.drawable.ic_launcher);
			menu.setHeaderTitle("請選擇背景色");//+1
			
			if(selectedIndex01 >= 0){
				menu.getItem(selectedIndex01).setChecked(true);
			}
		}else if(viewId == R.id.textView2){
			inflator.inflate(R.menu.fontcolorlist, menu);
			menu.setHeaderTitle("請選擇前景色");
			
			if(selectedIndex02 >= 0){
				menu.getItem(selectedIndex02).setCheckable(true);
			}
		}
	}

	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case R.id.white:
			selectedIndex01 = 0;
			textView01.setBackgroundColor(Color.WHITE);
			break;
		case R.id.black:
			selectedIndex01 = 1;
			textView01.setBackgroundColor(Color.BLACK);
			break;
		case R.id.gray:
			selectedIndex01 = 2;
			textView01.setBackgroundColor(Color.GRAY);
			break;
		case R.id.red_font2:
			selectedIndex02 = 0;
			textView02.setTextColor(Color.RED);
			break;
		case R.id.green_font2:
			selectedIndex02 = 1;
			textView02.setTextColor(Color.GREEN);
			break;
		case R.id.blue_font2:
			selectedIndex02 = 2;
			textView02.setTextColor(Color.BLUE);
			break;
		}
		return super.onContextItemSelected(item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//方式1: new 新的 MenuInflater，將現在的 context放入。
		MenuInflater inflater = new MenuInflater(this);
		inflater.inflate(R.menu.fontlist, menu);
		
		//方式2: 目前已有 context，直接call inflate填充要用的view
//		getMenuInflater().inflate(R.menu.wordlist, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.isCheckable()){//+1
			item.setChecked(true);//+1
		}
		
		//判斷 MENU選項，單選後所做的響應
		switch(item.getItemId()){
		case R.id.font_10:
			//兩者的字體大小一樣
			textView01.setTextSize(20);
			textView02.setTextSize(10*2);
			break;
		case R.id.font_12:
			textView01.setTextSize(12 *2);
			textView02.setTextSize(12 *2);
			break;
		case R.id.font_14:
			textView01.setTextSize(14 *2);
			textView02.setTextSize(14 *2);
			break;
		case R.id.font_16:
			textView01.setTextSize(16 *2);
			textView02.setTextSize(16 *2);
			break;
		case R.id.font_18:
			textView01.setTextSize(18 *2);
			textView02.setTextSize(18 *2);
			break;
		case R.id.red_font:
			textView01.setTextColor(Color.RED);
			textView02.setTextColor(Color.RED);
			break;
		case R.id.green_font:
			textView01.setTextColor(Color.GREEN);
			textView02.setTextColor(Color.GREEN);
			break;
		case R.id.blue_font:
			textView01.setTextColor(Color.BLUE);
			textView02.setTextColor(Color.BLUE);
			break;
			
		}
		return super.onOptionsItemSelected(item);
	}
}
