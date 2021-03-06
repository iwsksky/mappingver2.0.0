package com.example.mapping2;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.media.ExifInterface;
import android.os.Bundle;
import android.os.Environment;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

public class GraphicView extends ActionBarActivity {

	private File[] images;
	private List<String> imagelist = new ArrayList<String>();
	private List<String> data = new ArrayList<String>();
	public List<Float> orient;
	private String[] info;
	private String[] stringArray;
	private int l = 0;
	private int m =0;
	private int n=0;
	private int k=0;
	public int z=0;
	public int photonum;
	public static float[][] aska;
	public float[] testnum;
	public ExifArray[] ea;
	int orientation;
	Context context;
	

	public int filenum;
	

	
	public void up(){
		z=z+1;
	}
	
	

    public float[][] test() {
    	
		//mr=chage.arraylat();
    	String path = Environment.getExternalStorageDirectory().getPath();
		

		images = new File(path+"/DCIM/Camera").listFiles();//ファイル数１０
		filenum=images.length;
		//stringArray = new images.getName();
		//orient.size()がnull
		//float[] orientation = new float[100];
		float[][] aska= new float[filenum][2];
		ea = new ExifArray[filenum];
		List<Float> orient = new ArrayList<Float>();
		
		/*クラスのみを追加しているのでnullになる
		 * activityを追加しなければいけない
		 */
		TestView tv = new TestView(this.getApplicationContext());
		//画像のみのフォルダを作成する
		for (int i=0; i<filenum; i++){//１０回回す
			String[] info = new String[images.length];
			if(images[i].isFile() && images[i].getName().endsWith(".jpg")){
				imagelist.add(images[i].getName());
				//stringArray[l] = images[i].getName();
				try {
					ExifInterface exif = new ExifInterface(Environment.getExternalStorageDirectory().getPath() +"/DCIM/Camera/"+images[i].getName());
					if (exif != null) {
						//画像日付
						//info[l] = String.format("date: %s", exif.getAttribute(ExifInterface.TAG_DATETIME));	
						
						
						
						//位置情報取得
						float[] latlong = new float[2];
						exif.getLatLong(latlong);
						info[l] = String.format("latlong: %f, %f", latlong[0], latlong[1]);
						
						//data.add(info[l]);
						//配列の生成数が多いからnull=0になってる
						if(latlong[0] !=0){
							orient.add(latlong[0]);
							orient.add(latlong[1]);
							
							aska[m][0]=latlong[0];
							aska[m][1]=latlong[1];
							
							//サムネイル取得
							BitmapFactory.Options options = new BitmapFactory.Options();
							/*options.inJustDecodeBounds=true;
							BitmapFactory.decodeFile(path+"/DCIM/Camera/"+images[i].getName(), options);
							int imageHeight = options.outHeight;  
							int imageWidth = options.outWidth;  
							TestView tv = new TestView(TestView.context);
							int modheight=imageHeight/tv.disheight();
							int modwidth = imageWidth/tv.diswidth();
							
							int scale = Math.max(modheight, modwidth);*/
							
							options.inJustDecodeBounds=false;
							options.inSampleSize=100;
							//byte[] image = exif.getThumbnail();
							//orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION,ExifInterface.ORIENTATION_UNDEFINED);
							/*Matrix matrix = new Matrix();
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
							}*/
							//Bitmap bitmap =BitmapFactory.decodeFile(path+"/DCIM/Camera/"+images[i].getName(), options);
							//Bitmap bmp = Bitmap.createBitmap(bitmap, 0, 0, 10,10);
							//Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
							ea[m]=new ExifArray(latlong[0],latlong[1],i);
							m=m+1;
						
						}
						
						
					}
				}
				catch (IOException e) {
					e.printStackTrace();
				}
				
				l=l+1;//インスタンス化でimages.length分生成してしまっている
			
			}
		}
		photonum = orient.size();
		float[] testnum = new float[2];
		testnum[0]=5;
		float[][] orientation = new float[photonum][2];
		for(int c=0; c<photonum; c=c+2){
			//string[c]=(String)orient.get(c);
			orientation[n][0]= orient.get(c);
			orientation[n][1]= orient.get(c+1);
			n=n+1;
		}
		
		//オブジェクト型配列生成
		
		return orientation;
    
    }
    public float maxlat(){
    	
    	float[][] latmax = test();
    	float maxmurai=latmax[0][0];
    	for (int n=1; n<photonum/2; n++){
    		if(maxmurai<latmax[n][0]){
    			maxmurai=latmax[n][0];
    		}
    	}
    	return maxmurai;
    }
    public float minlat(){
    	float[][] latmin = test();
    	float minmurai=latmin[0][0];
    	for (int n=1; n<photonum/2; n++){
    		if(minmurai>latmin[n][0]){
    			minmurai=latmin[n][0];
    		}
    	}
    	return minmurai;
    }
    public float maxlon(){
    	float[][] lonmax = test();
    	float maxmurai=lonmax[0][1];
    	for (int n=1; n<photonum/2; n++){
    		if(maxmurai<lonmax[n][1]){
    			maxmurai=lonmax[n][1];
    		}
    	}
    	return maxmurai;
    }
    public float minlon(){
    	float[][] lonmin = test();
    	float minmurai=lonmin[0][1];
    	for (int n=1; n<photonum/2; n++){
    		if(minmurai>lonmin[n][1]){
    			minmurai=lonmin[n][1];
    		}
    	}
    	return minmurai;
    }
}