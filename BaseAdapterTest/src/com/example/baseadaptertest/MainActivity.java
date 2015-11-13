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
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ListActivity {
	private List<Map<String, Object>> mData;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        
        mData = getData();
        MyAdapter adapter = new MyAdapter(this);
        setListAdapter(adapter);
    }
    
    public List<Map<String, Object>> getData(){
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("title", "this is title01");
    	map.put("info", "title01 info~~");
    	map.put("img", R.drawable.ic_launcher);
    	list.add(map);
    	
    	map = new HashMap<String, Object>();
    	map.put("title", "this is title02");
    	map.put("info", "title02 info~~");
    	map.put("img", R.drawable.ic_launcher);
    	list.add(map);
    	
    	map = new HashMap<String, Object>();
    	map.put("title", "this is title03");
    	map.put("info", "title03 info~~");
    	map.put("img", R.drawable.ic_launcher);
    	list.add(map);
    	
    	//在 LogCat裡，輸出加入 map的內容
    	for(int i = 0; i < list.size();i++){
    		Log.d("getData()", map.keySet()+ "");
    	}
    	
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
			Log.d("My Adapter", "getVeiw()");
			
			ViewHolder holder = null; 
			if(convertView == null){
				holder = new ViewHolder();
				
				convertView = mInflater.inflate(R.layout.activity_main, null);
				holder.title = (TextView)convertView.findViewById(R.id.title);
				holder.info =  (TextView)convertView.findViewById(R.id.info);
				holder.img =  (ImageView)convertView.findViewById(R.id.img);
				holder.viewBtn =  (Button)convertView.findViewById(R.id.view_btn);
				convertView.setTag(holder);
			
			}else{
				holder = (ViewHolder) convertView.getTag();
			}
			
			holder.img.setBackgroundResource((Integer) mData.get(position).get("img"));
			holder.title.setText((String) mData.get(position).get("title"));
			holder.info.setText((String)mData.get(position).get("info"));
			
			final String TitleStr = holder.title.getText().toString();
			final String InfoStr = holder.info.getText().toString();
			
			holder.viewBtn.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					showInfo(TitleStr, InfoStr);
					
				}
				
			});
			return convertView;
		}
    }
    
    public class ViewHolder{
    	public TextView title, info;
    	public ImageView img;
    	public Button viewBtn;
    }
    
    public void showInfo(String str, String str2){
    	new AlertDialog.Builder(this)
    	.setTitle("showInfo@@")
    	.setMessage(str + " ... " + str2)
    	.setPositiveButton("ok", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
		})
		.show();
    }
}
