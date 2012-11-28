package ru.funnyanimals.phonealarm;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

public class CallerService extends Service {
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onCreate() {
		CallerPhoneStateListener callerPhoneStateListener = new CallerPhoneStateListener(this);
		TelephonyManager telephoneManager =(TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
		telephoneManager.listen(callerPhoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);
		super.onCreate();
	}

}
