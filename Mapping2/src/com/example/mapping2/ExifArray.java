package com.example.mapping2;

import android.graphics.Bitmap;

public class ExifArray {
	float lat;
	float lon;
	Bitmap bitmap;
	
	ExifArray(float lat, float lon, Bitmap thumb){
		this.lat=lat;
		this.lon=lon;
		this.bitmap=thumb;
	}
	
	

}
