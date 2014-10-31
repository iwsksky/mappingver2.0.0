package com.example.mapping2;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import android.content.Context;
import android.hardware.Camera;
import android.os.Environment;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback,Camera.PictureCallback{
	private SurfaceHolder holder;
	private Camera camera;
	
	public CameraPreview(Context context){
		super(context);
		
		holder=getHolder();
		holder.addCallback(this);
		
		holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		
	}
	
	public void surfaceCreated(SurfaceHolder holder){
		try{
			camera=Camera.open();
			camera.setPreviewDisplay(holder);
		}
		catch(Exception e){}
	}
	
	public void surfaceChanged(SurfaceHolder holder, int format, int w, int h){
		camera.startPreview();
	}
	
	public void surfaceDestroyed(SurfaceHolder holder){
		camera.setPreviewCallback(null);
		camera.stopPreview();
		camera.release();
		camera=null;
	}
	

	public boolean onTouchEvent(MotionEvent event){
		if(event.getAction()==MotionEvent.ACTION_DOWN){
			camera.takePicture(null, null, this);
		}
		return true;
	}
	
	public void onPictureTaken(byte[] data,Camera camera){
		try{
			Calendar cal = Calendar.getInstance();
			String tmp = "'"+cal.get(Calendar.YEAR) + (cal.get(Calendar.MONTH) + 1) + cal.get(Calendar.DATE) + cal.get(Calendar.HOUR_OF_DAY) + cal.get(Calendar.MINUTE) +cal.get(Calendar.SECOND);
			String path=Environment.getExternalStorageDirectory()+"/"+tmp+".jpg";
			data2file(data,path);
			camera.startPreview();
			}
		catch(Exception e){}
		
	}
	
	private void data2file(byte[] w,String fileName) throws Exception{
		FileOutputStream out=null;
		try{
			out=new FileOutputStream(fileName);
			out.write(w);
			out.close();
		}
		catch(Exception e){
			if(out!=null) out.close();
			throw e;
		}
	}
}
