package com.example.mapping2;


import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.media.ExifInterface;
import android.media.Image;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Imagelist extends ActionBarActivity {

	private File[] images;
	private List<String> imagelist = new ArrayList<String>();
	private List<String> data = new ArrayList<String>();
	private ListView lv;
	private String[] info;
	private String[] stringArray;
	private int l = 0;

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_imagelist);
		String path = Environment.getExternalStorageDirectory().getPath();
		
		
		images = new File(path).listFiles();//ファイル数１０
		//stringArray = new images.getName();
		
		//画像のみのフォルダを作成する
		for (int i=0; i<images.length; i++){//１０回回す
			String[] stringArray =  new String[images.length];//配列数１０
			String[] info = new String[images.length];
				imagelist.add(images[i].getName());
				stringArray[l] = images[i].getName();
				/*try {
					ExifInterface exif = new ExifInterface(Environment.getExternalStorageDirectory().getPath() +"/DCIM/Camera/"+images[i].getName());
					if (exif != null) {
						//画像日付
						//info[l] = String.format("date: %s", exif.getAttribute(ExifInterface.TAG_DATETIME));	
						
						//画像位置
						float[] latlong = new float[2];
						exif.getLatLong(latlong);
						info[l] = String.format("latlong: %f, %f", latlong[0], latlong[1]);
						if(latlong[0]!=0){
						data.add(info[l]);
						}
						}
				}
				catch (IOException e) {
					e.printStackTrace();
				}*/
				l=l+1;//インスタンス化でimages.length分生成してしまっている
			}
		
		//data = Arrays.asList(info);
		//消してた部分ここ
		ListView lv=(ListView)findViewById(R.id.listView1);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, imagelist);
		lv.setAdapter(adapter);
		
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			 public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				ListView lv = (ListView) parent;
				String item = (String) lv.getSelectedItem();
                Toast.makeText(Imagelist.this, item, Toast.LENGTH_LONG).show();
				
			}
    			
    		});
	
		
	
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.imagelist, menu);
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
