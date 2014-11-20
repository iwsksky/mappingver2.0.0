package com.example.mapping2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.support.v7.app.ActionBarActivity;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

public class TestView extends View{
	Context context2= this.getContext();
	public int i;
	public int u;
	public int x;
	public int diswidth;
	public int disheight;
	public String str;
	
	/*コンストラクタ this.contextは上のstatic Context context;を指す。
	 * 引数のContext contextと上のstatic Context contextは別物
	 */
	
	public TestView(Context context) {
        super(context);
        this.context2=context;
    }
	public void size(){
		WindowManager wm = (WindowManager)context2.getSystemService(Context.WINDOW_SERVICE) ;
    	Display disp = wm.getDefaultDisplay();
    	Point size = new Point();
    	disp.getSize(size);
    	

    	diswidth=size.x;
    	disheight=size.y;
 
	}
	
	public void onDraw(Canvas canvas) {
    	WindowManager wm = (WindowManager)context2.getSystemService(Context.WINDOW_SERVICE) ;
    	Display disp = wm.getDefaultDisplay();
    	Point size = new Point();
    	disp.getSize(size);
    	
    	
    	Activity activity = (Activity)this.getContext();
    	
    	Sizemodify sm1 = new Sizemodify();
		final float maxx = sm1.maxlat();
		Sizemodify sm2 = new Sizemodify();
		final float minx = sm2.minlat();
		Sizemodify sm3 = new Sizemodify();
		final float maxy = sm3.maxlon();
		Sizemodify sm4 = new Sizemodify();
		final float miny = sm4.minlon();
		
		
		final float multipley = (float)0.75*size.y/(maxy-miny);
		final float multiplex = (float)0.8*size.x/(maxx-minx);
    	
		Sizemodify sm= new Sizemodify();
		
		canvas.drawColor(Color.BLACK);
		float[][] dot = new float[sm.photonum/2][2];
		dot=sm.test();
		for (i =0; i<sm.photonum/2; i=i+1){
			Paint paint = new Paint();
			
			paint.setARGB(50,255,255,255);
        	canvas.drawCircle(multiplex*(sm.ea[i].lat-minx)+20, multipley*(maxy-sm.ea[i].lon)+20,size.x/10, paint);
        	//canvas.drawPoint(4*dot[0][0], 4*dot[0][1], paint);
			str=String.valueOf(sm.ea[i].lat);
			//canvas.drawText(str, 4*dot[0][0], 4*dot[0][1], paint);
			paint.setTextSize(20);
        	canvas.drawText(str, multiplex*(sm.ea[i].lat-minx)+20, multipley*(maxy-sm.ea[i].lon)+20, paint);
        	//サイズ変更
        	sm.ea[i].bitmap= Bitmap.createScaledBitmap(sm.ea[i].bitmap, size.x/10, size.x/10, false);
        	canvas.drawBitmap(sm.ea[i].bitmap, multiplex*(sm.ea[i].lat-minx), multipley*(maxy-sm.ea[i].lon), paint);
        	
        	/*viewを作成しonclicklistenerイベントを作成しているが、canvas上にviewを配置できない
        	 * ontouchlistenerでcanvasの画像がある座標をタッチしたときにintentする方法のほうが良い
        	 */
		}
		
		Paint paint = new Paint();
    	paint.setStrokeWidth(1);
       	paint.setARGB(50,50,50,50);
       	
       	
       	
        for (int y = 0; y < size.y ; y = y + 10) {
            canvas.drawLine(0, y, 3000, y, paint);
        }
        for (int x = 0; x < size.x; x = x + 10) {
            canvas.drawLine(x, 0, x, 3000, paint);
        }
        
        
        /*this.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
				Intent intent = new Intent(TestView.context, PhotoViewer.class);
				v.getContext().startActivity(intent);
			}
			
			
		});*/
        this.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				Sizemodify smx = new Sizemodify();
				smx.test();
				for(int n=0; n<smx.photonum/2; n++){
	
				if(multiplex*(smx.ea[n].lat-minx)-50<event.getX()&&event.getX()<multiplex*(smx.ea[n].lat-minx)+50&&multipley*(maxy-smx.ea[n].lon)-50<event.getY()&&event.getY()<multipley*(maxy-smx.ea[n].lon)+50){
					Intent intent = new Intent(context2, PhotoViewer.class);
					intent.putExtra("KEY", n);
					v.getContext().startActivity(intent);
				}
				
				}
				return false;
			
			}
			
    			
    			
    		});
        /*this.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
						Intent intent = new Intent(TestView.context, PhotoViewer.class);
						v.getContext().startActivity(intent);
					
					
				/*
				int z = (int)event.getX();
				if(0<event.getX()&&event.getX()<100){
					Intent intent = new Intent(TestView.context, PhotoViewer.class);
					Sizemodify sm = new Sizemodify();
					//値渡し
					intent.putExtra("KEY", 5);
					v.getContext().startActivity(intent);
				}
				if(100<event.getX()&&event.getX()<200){
					Intent intent = new Intent(TestView.context, PhotoViewer.class);
					Sizemodify sm = new Sizemodify();
					//値渡し
					intent.putExtra("KEY", 4);
					v.getContext().startActivity(intent);
				}
				
				return true;
				
			}
		});*/

	}
	
}

