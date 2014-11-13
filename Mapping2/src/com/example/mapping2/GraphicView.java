package com.example.mapping2;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.media.ExifInterface;
import android.os.Environment;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

public class GraphicView extends View {
	//public float[] froot;
	
	private File[] images;
	private List<String> imagelist = new ArrayList<String>();
	private List<String> data = new ArrayList<String>();
	private ListView lv;
	private String[] info;
	private String[] stringArray;
	private int l = 0;
	private int m =0;
	private float[] aska;
	public float[] latlong;

	private int f=5;
	public int filenum;
	float[] mr;
	int latala;
	
	Context context;
	
    public GraphicView(Context context) {
        super(context);
        this.context = context;
    }
    
    @Override
    public void onDraw(Canvas canvas) {
    	WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE) ;
    	Display disp = wm.getDefaultDisplay();
    	Point size = new Point();
    	disp.getSize(size);
    	float[] mr = new float[100];
    	LatiLong chage= new LatiLong();
    	Paint paint = new Paint();
		//mr=chage.arraylat();
    	String path = Environment.getExternalStorageDirectory().getPath();
		
		
		
		FilenameFilter fFilter = new FilenameFilter() {
			    public boolean accept(File dir, String filename) {
			        return filename.matches(".+.jpg"); //正規表現で検索
			    }
		};

		images = new File(path+"/DCIM/Camera").listFiles();//ファイル数１０
		filenum=images.length;
		//stringArray = new images.getName();
		float[] aska= new float[100];
		float[] latlong = new float[2];
		
		//Start start= new Start();
		//都度都度インスタンス化しないとaska[m]がサイズオーバーする。
		//Mod()１回だとaska[m]にサイズと同じfilenum分のデータが格納されるが
		//２回目からオーバー
		Sizemodify sm1 = new Sizemodify();
		float maxx = sm1.maxlat();
		Sizemodify sm2 = new Sizemodify();
		float minx = sm2.minlat();
		Sizemodify sm3 = new Sizemodify();
		float maxy = sm3.maxlon();
		Sizemodify sm4 = new Sizemodify();
		float miny = sm4.minlon();
		
		
		float multipley = size.y/(maxy-miny)-1;
		float multiplex = size.x/(maxx-minx)-1;
		
		
		//画像のみのフォルダを作成する
		for (int i=0; i<filenum; i++){//１０回回す
			String[] stringArray =  new String[images.length];//配列数１０
			String[] info = new String[images.length];
			if(images[i].isFile() && images[i].getName().endsWith(".jpg")){
				imagelist.add(images[i].getName());
				//stringArray[l] = images[i].getName();
				try {
					ExifInterface exif = new ExifInterface(Environment.getExternalStorageDirectory().getPath() +"/DCIM/Camera/"+images[i].getName());
					if (exif != null) {
						//画像日付
						//info[l] = String.format("date: %s", exif.getAttribute(ExifInterface.TAG_DATETIME));	
						
						//画像位置
						exif.getLatLong(latlong);
						info[l] = String.format("latlong: %f, %f", latlong[0], latlong[1]);
						if(latlong[0]!=0){
						paint.setStrokeWidth(5);
			        	canvas.drawPoint(multiplex*(latlong[0]-minx), multipley*(latlong[1]-miny), paint);
						}//data.add(info[l]);
						//aska[m]=latlong[0];
						//aska[m+1]=latlong[1];
						}
				}
				catch (IOException e) {
					e.printStackTrace();
				}
				l=l+1;//インスタンス化でimages.length分生成してしまっている
				m=m+2;
			}
		}
        // 座標系がわかるような罫線を引く
    	LatiLong ll = new LatiLong();
    	//froot = new float[2];
    	//float[] imgX = new float[1];
    	//float[] imgY = new float[1];
    	//imgX=ll.getlatitude();*/
    	
        paint.setStrokeWidth(1);
       	paint.setARGB(50,50,50,50);
       	
        for (int y = 0; y < size.y ; y = y + 10) {
            canvas.drawLine(0, y, 3000, y, paint);
        }
        for (int x = 0; x < size.x; x = x + 10) {
            canvas.drawLine(x, 0, x, 3000, paint);
        }
        /*for (int i=0; i<ll.filenum; i++){
        	paint.setStrokeWidth(12);
        	canvas.drawPoint(mr[i], mr[i+1], paint);
        }*/
    }
    protected void onMeasure(int widthMeasureSpec,int heightMeasureSpec){
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // Viewの描画サイズを指定する
        //setMeasuredDimension(3600,3600);
    }
    
}