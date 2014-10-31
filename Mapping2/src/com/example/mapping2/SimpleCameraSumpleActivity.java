package com.example.mapping2;

import android.support.v7.app.ActionBarActivity;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;


public class SimpleCameraSumpleActivity extends ActionBarActivity {

	public void onCreate(Bundle bundle){
		super.onCreate(bundle);
		
		getWindow().clearFlags(
				WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(new CameraPreview(this));
		
	}
}