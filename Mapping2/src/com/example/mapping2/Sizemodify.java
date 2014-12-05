package com.example.mapping2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.media.ExifInterface;
import android.os.Environment;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

public class Sizemodify extends View{
	public Sizemodify(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	private File[] images,photos;
	private List<String> imagelist = new ArrayList<String>();
	public List<Float> orient;
	private int l = 0;
	private int m =0;
	private int n=0;
	public int z=0;
	public int photonum;
	public static float[][] aska;
	public float[] testnum;
	public ExifArray[] ea;
	
	public static Context context;
	public int diswidth,disheight;

	public int filenum,jpgnum;
	public int x,y;
	public float[][] test;
	
	public int jpgnum(String path2){
		//jpgファイルをロングクリックした場合fileでないものをfileとして扱うことになる
		photos = new File(path2).listFiles();
		jpgnum=0;
		for (int i=0; i<photos.length; i++){
			if(photos[i].isFile() && photos[i].getName().endsWith(".jpg")){
				jpgnum=jpgnum+1;
				if(jpgnum>2){
					break;
				}
			}
		}
		return jpgnum;
	}
	
	

    public float[][] test() {
    	context=this.getContext();
    	WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE) ;
    	Display disp = wm.getDefaultDisplay();
    	Point size = new Point();
    	disp.getSize(size);
		
		

		images = new File(GV.path).listFiles();//ファイル数１０
		filenum=images.length;
		//stringArray = new images.getName();
		//orient.size()がnull
		float[][] aska= new float[filenum][2];
		ea = new ExifArray[filenum];
		List<Float> orient = new ArrayList<Float>();
		/*クラスのみを追加しているのでnullになる
		 * activityを追加
		 */
	
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
							options.inSampleSize=size.x;
							//byte[] image = exif.getThumbnail();
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
		test=orientation;
		return orientation;
		
    
    }
    //有効画像が2枚以上ない場合Arrayoutofindex
    //GPSタグがついていない画像も0枚判定
    public float maxlat(){
    	
    	float[][] latmax = test;
    	float maxmurai=latmax[0][0];
    	for (int a=1; a<photonum/2; a++){
    		if(maxmurai<latmax[a][0]){
    			maxmurai=latmax[a][0];
    		}
    	}
    	return maxmurai;
    }
    public float minlat(){
    	float[][] latmin = test;
    	float minmurai=latmin[0][0];
    	for (int b=1; b<photonum/2; b++){
    		if(minmurai>latmin[b][0]){
    			minmurai=latmin[b][0];
    		}
    	}
    	return minmurai;
    }
    public float maxlon(){
    	float[][] lonmax = test;
    	float maxmurai=lonmax[0][1];
    	for (int d=1; d<photonum/2; d++){
    		if(maxmurai<lonmax[d][1]){
    			maxmurai=lonmax[d][1];
    		}
    	}
    	return maxmurai;
    }
    public float minlon(){
    	float[][] lonmin = test;
    	float minmurai=lonmin[0][1];
    	for (int e=1; e<photonum/2; e++){
    		if(minmurai>lonmin[e][1]){
    			minmurai=lonmin[e][1];
    		}
    	}
    	return minmurai;
    }
	
}
