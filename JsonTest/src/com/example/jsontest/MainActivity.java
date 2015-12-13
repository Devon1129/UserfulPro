package com.example.jsontest;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private ListView lvList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        try {
			buildViews();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    private void buildViews() throws JSONException{
		lvList = (ListView)findViewById(R.id.listView1);
	
		//練習1 列出ListView
//		String[] items = {"Milk", "Butter", "Yogurt", "Toothpaste", "Ice Cream"};
//		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
//		lvList.setAdapter(adapter);
		
//		//練習2
//		String[] items = {
//				"1:\r\n"
//				+"Place:4km SSE of Anza, California\r\n"
//				+"Time:2015-03-31 23:02:57\r\n"
//				+"-116.6533333\r\n"
//				+"33.5253333\r\n"
//				+"10.33\r",
//				"2:\r\n"
//				+"Place:17km N of Yucca Valley, California\r\n"
//				+"Time:2015-03-31 22:47:06\r\n"
//				+"-116.4458333\r\n"
//				+"34.2698333\r\n"
//				+"8.96\r",
//				"3:\r\n"
//				+"Place:45km ESE of Beatty, Nevada\r\n"
//				+"Time:2015-03-31 22:34:18\r\n"
//				+"-116.2967\r\n"
//				+"36.7307\r\n"
//				+"10.39\r",
//				"4:\r\n"
//				+"Place:13km SE of Anza, California\r\n"
//				+"Time:2015-03-31 22:33:07\r\n"
//				+"-116.576\r\n"
//				+"33.4713333\r\n"
//				+"9.1\r",
//				"5:\r\n"
//				+"Place:95km NNW of Talkeetna, Alaska\r\n"
//				+"Time:2015-03-31 22:31:10\r\n"
//				+"-150.8032\r\n"
//				+"63.1226\r\n"
//				+"117.6\r"};
//		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
//		lvList.setAdapter(adapter);
		
		//練習JSONObject與ListView結合使用
		/*
		 * ***對應方式: key :(冒號) values； ,(逗號)區別下一筆
		 * 因為下面要抓""內的值，因此，使用\" 與 \"，區隔 type String
		 * 留意 type Array自成一格。(就不用\"，區隔了)
		 */
		
		String tmp =
				"{\"Data\":{\"place\":\"M 2.1 - 4km E of Cabazon, California\",\"time\":\"1427208592120\",\"laction\":[-116.749,33.9211667], \"depth\":\"19.63 KM\"}}";
		
//		http://json.parser.online.fr/
//		{"Data":{"place":"M 2.1 - 4km E of Cabazon, California","time":"1427208592120","laction":[-116.749,33.9211667], "depth":"19.63 KM"}}
		
		JSONObject obj = new JSONObject(tmp);
		
		String feat = obj.getJSONObject("Data").getString("place") + "\r\n" +   
				obj.getJSONObject("Data").getString("time") + "\r\n" +
				obj.getJSONObject("Data").getString("laction") + "\r\n" +
				obj.getJSONObject("Data").getString("depth");
		
		Toast.makeText(MainActivity.this, obj + " ", Toast.LENGTH_SHORT).show();
		Toast.makeText(MainActivity.this, feat + " ", Toast.LENGTH_SHORT).show();
		
		
//		String[] items = {feat, "", obj.toString(), lvList.toString()};
		String[] items = {feat, obj.toString(), lvList.toString()};
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
		lvList.setAdapter(adapter);
	}
}
