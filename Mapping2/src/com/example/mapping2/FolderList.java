package com.example.mapping2;

import java.io.File;
import java.util.ArrayList;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;

public class FolderList extends Activity {
	public File[] images;
	public ArrayList<String> folder= new ArrayList<String>();
	public String[] filename;
	public static Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_folder_list);
		FolderListFragment flf = new FolderListFragment();
		String path = Environment.getExternalStorageDirectory().getPath();
		
		Bundle bundle = new Bundle();
		bundle.putString("path", path);
		flf.setArguments(bundle);
		FragmentManager manager = getFragmentManager();
	    FragmentTransaction transaction = manager.beginTransaction();
	    transaction.add(R.id.container, flf, "fragment");
	    transaction.commit();
	    
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.folder_list, menu);
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
	
	@Override
	public void onBackPressed() {
	     super.onBackPressed();
	}
	
	
	
}
