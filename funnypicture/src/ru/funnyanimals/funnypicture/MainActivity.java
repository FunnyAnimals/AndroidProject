package ru.funnyanimals.funnypicture;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.support.v4.app.NavUtils;
import android.widget.*;

public class MainActivity extends Activity {

	private int countPlayers = 2;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ShowAlertDialog(R.string.dialog_tittle, R.array.choose);
        
        DrawView pictureView = new DrawView(this);
        Display display = getWindowManager().getDefaultDisplay();
        LayoutParams lp = new LayoutParams(display.getWidth(), display.getHeight()*countPlayers);
        pictureView.setLayoutParams(lp);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void ShowAlertDialog(int resourceTittle, int resourceItems){
    	new AlertDialog.Builder(this)
    	.setTitle(resourceTittle)
    	.setItems(resourceItems,
    	new DialogInterface.OnClickListener() {
    		public void onClick(DialogInterface dialoginterface,
    		int i) {
    		SetPlayers(i);
    		}
    		})
    		.show();
    }
    
    private void SetPlayers(int count) {
    	countPlayers = count + 2;
    }
}

    

