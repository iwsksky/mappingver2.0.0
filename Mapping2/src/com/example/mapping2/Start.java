package com.example.mapping2;

import android.support.v7.app.ActionBarActivity;
import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
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

public class Start extends ActionBarActivity{
	public String str;
	public float[] ono;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		
		WindowManager wm = (WindowManager)getSystemService(WINDOW_SERVICE);
		// ディスプレイのインスタンス生
		Display disp = wm.getDefaultDisplay();
		Point size = new Point();
		disp.getSize(size);
		
	
		//button1
		
        Button btn = (Button)findViewById(R.id.button1);
		btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(Start.this, PhotoViewer.class);
				startActivity(intent);}});
		//button2
		Button btn2 = (Button)findViewById(R.id.button2);
	
		btn2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Start.this, GV.class);
				startActivity(intent);
				// TODO Auto-generated method stub
				
			}
		});
		
	
		Sizemodify sm= new Sizemodify();
		float[][] ono = new float[2*sm.filenum][2];
		TextView tv = (TextView)findViewById(R.id.textView1);
		ono=sm.test();
		
		/*Sizemodify sm2= new Sizemodify();
		sm2.test();*/
		str=String.valueOf(sm.ea[1].lat);
		tv.setText(str);
		
		Button btn3 = (Button)findViewById(R.id.button3);
		
		btn3.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Start.this, Imagelist.class);
				startActivity(intent);
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	
	
}
