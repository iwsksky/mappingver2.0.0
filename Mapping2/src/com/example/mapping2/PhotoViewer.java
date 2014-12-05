package com.example.mapping2;

import java.io.File;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

public class PhotoViewer extends ActionBarActivity {

	public int x;
	private File[] images;
	public int filenum;
	public Matrix matrix;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_photo_viewer);
		ImageView iv = (ImageView)findViewById(R.id.imageView1);
		
		
		Intent intent = getIntent();
		//値受け取り
		//DCIM/camera/の全ての写真のリストimage[i]の変数iを取得
		int num = intent.getIntExtra("KEY", 1);
		
		String path = Environment.getExternalStorageDirectory().getPath();
		images = new File(path+"/DCIM/Camera").listFiles();
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds=false;
		options.inSampleSize=1;
		matrix = new Matrix();
		Getmatrix gm = new Getmatrix();
		
		
		
	
		Bitmap bitmap =BitmapFactory.decodeFile(path+"/DCIM/Camera/"+images[num].getName(), options);
		Bitmap bitmap2 = Bitmap.createBitmap(bitmap, 0, 0, 800, 800, gm.getmatrix(path,num), true);
		
		
		
		
    	iv.setImageBitmap(bitmap2);
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
