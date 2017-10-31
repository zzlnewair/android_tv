package com.zzl.dimen;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView tv,tv_dimen;
	private Button button = null;  
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		tv = (TextView ) findViewById(R.id.hello);
		tv_dimen = (TextView ) findViewById(R.id.tv_dimen);
		WindowManager wm = (WindowManager) getApplicationContext()
                .getSystemService(Context.WINDOW_SERVICE);

		int width = wm.getDefaultDisplay().getWidth();
		int height = wm.getDefaultDisplay().getHeight();
		DisplayMetrics metric = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(metric);
		int width2 = metric.widthPixels;     // 屏幕宽度（像素）
        int height2 = metric.heightPixels;   // 屏幕高度（像素）
		float density = metric.density;      // 屏幕密度（0.75 / 1.0 / 1.5）
	    int densityDpi = metric.densityDpi;  // 屏幕密度DPI（120 / 160 / 240）
	    
	    String str= "width="+width+" height="+height+" density="+density+" densityDpi="+densityDpi;
	    tv.setText(str);
	    
	    button = (Button)findViewById(R.id.button);  
        button.setOnClickListener(new buttonListener()); 
				
	}


	public class buttonListener implements OnClickListener{  
		  
        public void onClick(View v)  
        {  
            int[] location = new int[2];  
            button.getLocationOnScreen(location);  
            int x = location[0];  
            int y = location[1];  
            tv_dimen.setText("坐标x:"+x+"y:"+y+"\n"+"长："+(button.getRight()-button.getLeft())+"宽："+(button.getBottom()-button.getTop()));  
        }  
    }  
	
}
