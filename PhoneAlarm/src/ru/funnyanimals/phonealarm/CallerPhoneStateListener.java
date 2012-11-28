package ru.funnyanimals.phonealarm;

import android.app.Application;
import android.content.Context;
import android.database.Cursor;
import android.media.AudioManager;
import android.os.Handler;
import android.provider.CallLog.Calls;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.content.ContentResolver;
import android.util.Log;

public class CallerPhoneStateListener extends PhoneStateListener{

	private boolean isPhoneCalling = false;
	private Context ApplicationContext;
	
	public CallerPhoneStateListener(Context context)
	{
		ApplicationContext = context;
	}
	
	@Override
	public void onCallStateChanged(int state, String incomingNumber) {
		// TODO Auto-generated method stub
		//super.onCallStateChanged(state, incomingNumber);
		
		switch (state) {
		case TelephonyManager.CALL_STATE_RINGING:
			 Log.i("Phone State", "RINGING, number: " + incomingNumber);
			break;			
		case TelephonyManager.CALL_STATE_OFFHOOK:
			 Log.i("Phone State", "OFFHOOK");
	            isPhoneCalling = true;
	            break;
	            
		case TelephonyManager.CALL_STATE_IDLE:
			 // run when class initial and phone call ended, need detect flag
            // from CALL_STATE_OFFHOOK
            Log.i("Phone State", "IDLE number");

            if (isPhoneCalling) {

                Handler handler = new Handler();

                //Put in delay because call log is not updated immediately when state changed
                // The dialler takes a little bit of time to write to it 500ms seems to be enough
                handler.postDelayed(new Runnable() {
                	
                    @Override
                    public void run() {
                        // get start of cursor
                          Log.i("CallLogDetailsActivity", "Getting Log activity...");
                            String[] projection = new String[]{Calls.NUMBER};
                            
                            Cursor cur = ApplicationContext.getContentResolver().query(Calls.CONTENT_URI, projection, null, null, Calls.DATE +" desc");
                            cur.moveToFirst();
                            String lastCallnumber = cur.getString(0);
                    }
                },500);

                isPhoneCalling = false;
            }
		default:
			break;
		}
	}
}
