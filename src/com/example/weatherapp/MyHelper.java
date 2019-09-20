package com.example.weatherapp;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import android.util.Log;

public class MyHelper  {
 String surl,scountry="Data Not Available",shumidity="Data Not Available",spressure="Data Not Available",stemp="Data Not Available";
protected boolean xyz;
	

	public MyHelper(String fullstr) {
		surl=fullstr;
		xyz=true;
	}

	public void fetchJSON() {
		Thread t=new Thread(new Runnable()
		{
	public void run()
	{
		try
		{
			URL url=new URL(surl);
			HttpURLConnection con=(HttpURLConnection) url.openConnection();
			con.setReadTimeout(15000);
			con.setConnectTimeout(15000);
			con.setDoInput(true);
			con.setRequestMethod("GET");
			con.connect();
			InputStream is=con.getInputStream();
			String s=readdata(is);

             selectdata(s);
             is.close();
             con.disconnect();
		}
		catch(Exception e)
		{
			Log.d("Error in fetching Data",e.toString());
		}
	}
		});
		t.start();
		
	}

	
	protected void selectdata(String s) {
		try
		{
			JSONObject obj1=new JSONObject(s);
			JSONObject obj2=obj1.getJSONObject("sys");
			JSONObject obj3=obj1.getJSONObject("main");
			scountry=obj2.getString("country");
			shumidity=obj3.getString("humidity");
			spressure=obj3.getString("pressure");
			stemp=obj3.getString("temp");
			xyz=false;
		}
		catch(Exception e)
		{
			
		}
		
	}

	private static String readdata(InputStream is) {
		java.util.Scanner scan=new java.util.Scanner(is).useDelimiter("\\A");
		return scan.hasNext()?scan.next():"No Data";
		
	}

	public CharSequence getCountry() {
		// TODO Auto-generated method stub
		return scountry;
	}

	public CharSequence getHumidity() {
		// TODO Auto-generated method stub
		return shumidity;
	}

	public CharSequence getTemp() {
		// TODO Auto-generated method stub
		return stemp;
	}

	public CharSequence getPressure() {
		// TODO Auto-generated method stub
		return spressure;
	}
	
	

}
