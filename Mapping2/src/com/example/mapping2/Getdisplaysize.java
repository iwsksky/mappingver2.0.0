package com.example.mapping2;

import android.content.Context;
import android.graphics.Point;
import android.support.v7.app.ActionBarActivity;
import android.view.Display;
import android.view.WindowManager;

public class Getdisplaysize extends ActionBarActivity{
	
	static int diswidth;
	static int disheight;
	
	public void size(){
		WindowManager wm = (WindowManager)getSystemService(Context.WINDOW_SERVICE) ;
    	Display disp = wm.getDefaultDisplay();
    	Point size = new Point();
    	disp.getSize(size);
    	

    	diswidth=size.x;
    	disheight=size.y;
 
	}

}
