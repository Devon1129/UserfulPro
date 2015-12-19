package com.example.quakewithgooglemap_ex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetHttpTest {
	public String getHTML(String urlToRead){
		URL url;
		BufferedReader rd;
		HttpURLConnection conn;
		
		String result = "";
		String line;
		
		try{
			url = new URL(urlToRead);
			result = new String();
			
			conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("GET");
			
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			while((line = rd.readLine()) != null){
				result += line;
			}
			rd.close();
			
		}catch(IOException e){
			e.printStackTrace();
		}catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
}
