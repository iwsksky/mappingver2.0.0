package com.example.mapping2;

import java.io.File;
import java.io.IOException;

import android.graphics.Matrix;
import android.media.ExifInterface;

public class Getmatrix {
	public int x;
	private File[] images;
	public int filenum;
	public int orientation;

	public Matrix getmatrix(String path,int num){
		
	
		Matrix matrix = new Matrix();
		images = new File(path+"/DCIM/Camera").listFiles();
		
		try {
			ExifInterface exif = new ExifInterface(path +"/DCIM/Camera/"+images[num].getName());
			orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION,ExifInterface.ORIENTATION_UNDEFINED);
			
			switch (orientation) {
			case ExifInterface.ORIENTATION_UNDEFINED:
				break;
			case ExifInterface.ORIENTATION_NORMAL:
				break;
			case ExifInterface.ORIENTATION_FLIP_HORIZONTAL:
				matrix.postScale(-1f, 1f);
				break;
			case ExifInterface.ORIENTATION_ROTATE_180:
				matrix.postRotate(180f);
				break;
			case ExifInterface.ORIENTATION_FLIP_VERTICAL:
				matrix.postScale(1f, -1f);
				break;
			case ExifInterface.ORIENTATION_ROTATE_90:
				matrix.postRotate(90f);
				break;
			case ExifInterface.ORIENTATION_TRANSVERSE:
				matrix.postRotate(-90f);
				matrix.postScale(1f, -1f);
				break;
			case ExifInterface.ORIENTATION_TRANSPOSE:
				matrix.postRotate(90f);
				matrix.postScale(1f, -1f);
				break;
			case ExifInterface.ORIENTATION_ROTATE_270:
				matrix.postRotate(-90f);
				break;
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	
		return matrix;
    	
	}

}
