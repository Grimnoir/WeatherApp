package com.example.weatherapp;



import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	Button jbtngo;
	EditText jetlocation,jetpressure,jethumidity,jettemp,jetcountry;
	String sloc,str1="http://api.openweathermap.org/data/2.5/weather?q=";
	String str2="&APPID=b0fec162d3077f436e657a6a0b2d567c";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jetlocation=(EditText)findViewById(R.id.etlocation);
        jetpressure=(EditText)findViewById(R.id.etpressure);
       
        jethumidity=(EditText)findViewById(R.id.ethumidity);
        jettemp=(EditText)findViewById(R.id.ettemp);
        jetcountry=(EditText)findViewById(R.id.etcountry);
        
        jbtngo=(Button)findViewById(R.id.btngo);
        jbtngo.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				boolean flag = false;
                flag = getData();
			if(flag)
			{
				String fullstr=str1+sloc+str2;
				MyHelper h=new MyHelper(fullstr);
				h.fetchJSON();
				while(h.xyz);
				jetcountry.setText("Country:"+h.getCountry());
				jethumidity.setText("Humidity:"+h.getHumidity());
				jettemp.setText("Temperature:"+h.getTemp());
				jetpressure.setText("Pressure:"+h.getPressure());
			}
				
			}
		});
        
    }

	private boolean getData() {
		
		boolean flag=false;
		try
		{
		
	        sloc=jetlocation.getText().toString();
			if(sloc.length() > 0)
	            flag = true;
	       
	           else
	               Toast.makeText(MainActivity.this,"Enter Location",Toast.LENGTH_SHORT).show();
			
		
		}
		catch(Exception e)
		{
			Toast.makeText(MainActivity.this, "Error In getting location", Toast.LENGTH_LONG).show();
		}
		return flag;
	}


    
}
