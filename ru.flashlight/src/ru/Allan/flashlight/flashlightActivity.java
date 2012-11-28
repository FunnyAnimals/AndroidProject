package ru.Allan.flashlight;

import javax.xml.datatype.Duration;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class flashlightActivity extends Activity {
    /** Called when the activity is first created. */
	private LinearLayout ll;
	private WindowManager.LayoutParams lp;
	private float previousY;
	private float displayBrightness = 1.0f;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.main);
        lp = getWindow().getAttributes();
        lp.screenBrightness = 1.0f;
        ll = (LinearLayout) findViewById(R.id.flashLight);
        ll.setBackgroundColor(Color.WHITE);

    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
    	switch (event.getAction()) {
    	
		case MotionEvent.ACTION_DOWN :
		{
			this.previousY = event.getY();
			break;
		}
		
		case MotionEvent.ACTION_MOVE :
		{ 
			float offsetY = this.previousY - event.getY();
			if (offsetY<0)
			{
				if (displayBrightness > 0.1f)
					displayBrightness = displayBrightness - 0.05f;
			}
			else	
			{
				if (displayBrightness < 1.0f)
					displayBrightness = displayBrightness + 0.05f;
			}
			lp.screenBrightness = displayBrightness;
			getWindow().setAttributes(lp);
			this.previousY = event.getY();
			break;
		}
		case MotionEvent.ACTION_UP :
		{
			
			//Toast.makeText(this, String.valueOf(displayBrightness), Toast.LENGTH_LONG);
			break;
		}
		
		default:
		{
			break;
		}
		}
    	return super.onTouchEvent(event);
    }
}