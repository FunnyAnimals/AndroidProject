package ru.Allan.wi_fi_modulator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;

public class WifiHelper {
	
	private Context context;
	private WifiManager wifiManager;
	private String ssid = "1236";
	
	WifiHelper(Context context)
	{
		super( );
		this.context = context;
		this.wifiManager  = (WifiManager) this.context.getSystemService(Context.WIFI_SERVICE);
	}
	
	public void createWifiAccessPoint() {
    
	    Method[] wmMethods = wifiManager.getClass().getDeclaredMethods();   //Get all declared methods in WifiManager class     
	    boolean methodFound=false;
	    for(Method method: wmMethods){
	        if(method.getName().equals("setWifiApEnabled")){
	            methodFound=true;
	            WifiConfiguration netConfig = new WifiConfiguration();
	            netConfig.SSID = "\""+ssid+"\"";
	            netConfig.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
	            //netConfig.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
	            //netConfig.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
	            //netConfig.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
	            //netConfig.preSharedKey=password;
	            //netConfig.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
	            //netConfig.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
	            //netConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
	            //netConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
	            try {
	                boolean apstatus=(Boolean) method.invoke(wifiManager, netConfig,true);          
	                //statusView.setText("Creating a Wi-Fi Network \""+netConfig.SSID+"\"");
	                for (Method isWifiApEnabledmethod: wmMethods)
	                {
	                    if(isWifiApEnabledmethod.getName().equals("isWifiApEnabled")){
	                        while(!(Boolean)isWifiApEnabledmethod.invoke(wifiManager)){
	                        };
	                        for(Method method1: wmMethods){
	                            if(method1.getName().equals("getWifiApState")){
	                                int apstate;
	                                apstate=(Integer)method1.invoke(wifiManager);
	                                //                    netConfig=(WifiConfiguration)method1.invoke(wifi);
	                                //statusView.append("\nSSID:"+netConfig.SSID+"\nPassword:"+netConfig.preSharedKey+"\n");
	                            }
	                        }
	                    }
	                }
	                if(apstatus)
	                {
	                    System.out.println("SUCCESSdddd");  
	                    //statusView.append("\nAccess Point Created!");
	                    //finish();
	                    //Intent searchSensorsIntent = new Intent(this,SearchSensors.class);            
	                    //startActivity(searchSensorsIntent);

	                }else
	                {
	                    System.out.println("FAILED");   

	                    //statusView.append("\nAccess Point Creation failed!");
	                }

	            } catch (IllegalArgumentException e) {
	                e.printStackTrace();
	            } catch (IllegalAccessException e) {
	                e.printStackTrace();
	            } catch (InvocationTargetException e) {
	                e.printStackTrace();
	            }
	        }      
	    }
	    if(!methodFound){
	        //statusView.setText("Your phone's API does not contain setWifiApEnabled method to configure an access point");
	    }
	}



}
