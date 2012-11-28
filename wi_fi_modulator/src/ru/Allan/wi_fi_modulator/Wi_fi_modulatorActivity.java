package ru.Allan.wi_fi_modulator;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Wi_fi_modulatorActivity extends Activity {
    /** Called when the activity is first created. */
	private int state = 0;
	private final static int WIFIHELPER_CREATED = 1;
	private final static int ACCESS_POINT_CREATED = 2;
	TextView tv;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        tv= (TextView)findViewById(R.id.text);
        this.showText("start");
        try
        {
        WifiHelper wifiHelper = new WifiHelper(this);
        this.state = WIFIHELPER_CREATED;
        this.showText("WifiHelper created");
        wifiHelper.createWifiAccessPoint();
        this.state = ACCESS_POINT_CREATED;
        this.showText("Access point created");
        }
        catch (Exception e) {
        	if (this.state == WIFIHELPER_CREATED)
        	{
        	  this.showText("Don't create access point");
        	}
        	
        	if (this.state == ACCESS_POINT_CREATED)
        	{
        	  this.showText("Created access point");
        	}
			// TODO: handle exception
		}
       // 
    }
    
    private void showText(String text) {
    	tv.setText(text);
    	Toast.makeText(this, text, Toast.LENGTH_LONG).show();
	}
}