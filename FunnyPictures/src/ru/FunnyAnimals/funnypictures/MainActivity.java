package ru.FunnyAnimals.funnypictures;

import ru.FunnyAnimals.funnypictures.R.layout;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Point;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.support.v4.app.NavUtils;

public class MainActivity extends Activity {
private int countPlayers = 2;
private DrawerView pictureView;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ShowAlertDialog(R.string.dialog_tittle, R.array.choose);
        setContentView(R.layout.main_activity);
        pictureView =(DrawerView) findViewById(R.id.drawer_view);
    }
    
    public void cancelButtonOnClick(View v)
    {
    	pictureView.stepBack();
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
    		setPlayers(i);
    		//setLayoutParams();
    		}
    		})
    		.show();
    }
    
    private void setPlayers(int count) {
    	countPlayers = count + 2;
    }
    
    private void setLayoutParams()
    {
       
        WindowManager wm = (WindowManager) this.getSystemService(this.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, display.getHeight()*countPlayers);
        pictureView.setLayoutParams(lp);
    }
}
