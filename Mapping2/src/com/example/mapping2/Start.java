package com.example.mapping2;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Start extends ActionBarActivity{
	public String str;
	public float[] ono;
	static Context context;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
	
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
		
	
	
		TextView tv = (TextView)findViewById(R.id.textView1);
		Sizemodify sm1 = new Sizemodify(this);
		sm1.test();
		final float minx = sm1.minlat();

		
		
		
		/*Sizemodify sm2= new Sizemodify();
		sm2.test();*/
		str=String.valueOf(minx);
		tv.setText(str);
		
		Button btn3 = (Button)findViewById(R.id.button3);
		
		btn3.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Start.this, FolderList.class);
				startActivity(intent);
				// TODO Auto-generated method stub
				
			}
		});
		
		
		
	}
	
	
}
