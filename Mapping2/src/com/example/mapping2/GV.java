package com.example.mapping2;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.Matrix;
import android.graphics.Point;
import android.media.ExifInterface;
import android.os.Bundle;
import android.os.Environment;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;

public class GV extends ActionBarActivity {
	public static Context context;
	public static String path;
	ScrollView scrollView;  
	HorizontalScrollView horizontalscrollView;
	LinearLayout linearLayout;
	public View GraphicView;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		WindowManager wm = (WindowManager)getSystemService(WINDOW_SERVICE);
		Intent intent = getIntent();
		//値受け取り
		//DCIM/camera/の全ての写真のリストimage[i]の変数iを取得
		path = intent.getStringExtra("PATH");

		Display disp = wm.getDefaultDisplay();
		Point size = new Point();
		disp.getSize(size);

		
		scrollView = new ScrollView(this); 
		horizontalscrollView = new HorizontalScrollView(this);
	    linearLayout = new LinearLayout(this);  
	    linearLayout.setOrientation(LinearLayout.VERTICAL);
		
		//縦横スクロール
	    //scrollView.addView(new GraphicView(this));
	    horizontalscrollView.addView(scrollView);
		setContentView(new TestView(this));
		
	}
	public static Context getContext() {
	    return context;
	}
		


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.gv, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}