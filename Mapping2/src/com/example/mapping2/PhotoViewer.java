package com.example.mapping2;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

public class PhotoViewer extends ActionBarActivity {

	private Context context;
	public int x;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_photo_viewer);
		
		Intent intent = getIntent();
		//値受け取り
		int num = intent.getIntExtra("KEY", 1);
		
		Sizemodify sm= new Sizemodify();
		sm.test();
		Bitmap bmp = sm.ea[num].bitmap;
		ImageView iv = (ImageView)findViewById(R.id.imageView1);
    	iv.setImageBitmap(bmp);
    	//setContentView(iv);
    	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.photo_viewer, menu);
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
