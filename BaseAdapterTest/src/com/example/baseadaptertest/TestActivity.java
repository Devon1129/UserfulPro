package com.example.baseadaptertest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.test.R;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class TestActivity extends ListActivity {
	private List<Map<String, Object>> mData;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mData = getData();
		MyAdapter adapter = new MyAdapter(this);
		setListAdapter(adapter);
		
		
	}
	
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
//		super.onListItemClick(l, v, position, id);
		Log.d("MyListView-click!!", (String) mData.get(position).get("title"));
	}



	public List<Map<String, Object>> getData(){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title", "test1");
		map.put("info", "test001");
		map.put("img", R.drawable.ic_launcher);
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("title", "test2");
		map.put("info", "test002");
		map.put("img", R.drawable.ic_launcher);
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("title", "test3");
		map.put("info", "test003");
		map.put("img", R.drawable.ic_launcher);
		list.add(map);
		
		return list;
	}
	
	
	public class MyAdapter extends BaseAdapter{
		private LayoutInflater mInflater;
		
		public MyAdapter(Context context){
			this.mInflater = LayoutInflater.from(context);
		}
		

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 3;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			Log.d("My Adapter", "getView()");
			
			ViewHolder holder = null;
			if(convertView == null){
				holder = new ViewHolder();
				
				convertView = mInflater.inflate(R.layout.activity_main, null);
				holder.img = (ImageView)convertView.findViewById(R.id.img);
				holder.info = (TextView)convertView.findViewById(R.id.info);
				holder.title = (TextView)convertView.findViewById(R.id.title);
				holder.viewBtn = (Button)convertView.findViewById(R.id.view_btn);
				convertView.setTag(holder);
				
			}else{
				holder = (ViewHolder) convertView.getTag(); 
			}
			
			holder.img.setBackgroundResource((Integer) mData.get(position).get("img"));
			holder.info.setText((String) mData.get(position).get("info"));
			holder.title.setText((String) mData.get(position).get("title"));
			
			final String titleStr = holder.title.getText().toString();
			final String infoStr = holder.info.getText().toString();
			
			holder.viewBtn.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					showInfo(titleStr, infoStr);
				}
			});
			
			return convertView;
		}
		
	}
	
	public class ViewHolder{
		public ImageView img;
		public TextView title, info;
		public Button viewBtn;
	}
	
	public void showInfo(String str, String str2){
		new AlertDialog.Builder(this)
		.setTitle("§Úªºlistview ")
		.setMessage(str + " ¤¶²Ð... " + str2)
		.setPositiveButton("½T©w!", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
		})
		.show();
	}

}
