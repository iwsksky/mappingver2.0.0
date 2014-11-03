package com.example.mapping2;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.media.ExifInterface;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;

public class GV extends ActionBarActivity {
	ScrollView scrollView;  
	HorizontalScrollView horizontalscrollView;
	LinearLayout linearLayout; 
	
	
	private File[] images;
	private List<String> imagelist = new ArrayList<String>();
	private List<String> data = new ArrayList<String>();
	private ListView lv;
	private String[] info;
	private String[] stringArray;
	private int l = 0;
	private float[] lat;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		scrollView = new ScrollView(this); 
		horizontalscrollView = new HorizontalScrollView(this);
	    linearLayout = new LinearLayout(this);  
	    linearLayout.setOrientation(LinearLayout.VERTICAL);
		
		//縦横スクロール
	    scrollView.addView(new GraphicView(this));
	    horizontalscrollView.addView(scrollView);
		setContentView(new GraphicView(this));
		
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