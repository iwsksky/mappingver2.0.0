package com.example.mapping2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class FolderCycle extends ListFragment {
	public static Context context;
	public File[] images;
	public ArrayList<String> folder= new ArrayList<String>();
	private List<String> imagelist = new ArrayList<String>();
	public String[] filename;

	
	public void onActivityCreated(Bundle savedInstanceState) {
	    super.onActivityCreated(savedInstanceState);
	    context = getActivity().getApplicationContext();
	    
	    
	    final String path = getArguments().getString("path");
		
		
		images = new File(path).listFiles();//ファイル数１０
		filename= new String[images.length];
		
		for (int i=0; i<images.length; i++){//１０回回す
			filename[i]=images[i].getName();
			imagelist.add(images[i].getName());
			}	
	    
	    
		
		//ListView lv=(ListView)getActivity().findViewById(R.id.listView1);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, imagelist);
		setListAdapter(adapter);
		
		this.getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                ListView listView = (ListView) parent;
                // クリックされたアイテムを取得します
                String item = (String) listView.getItemAtPosition(position);
                //Toast.makeText(context, item, Toast.LENGTH_LONG).show();
                FolderListFragment flf = new FolderListFragment();
                Bundle bundle = new Bundle();
                String path2 = path + "/"+item;
                if(!item.endsWith(".jpg")){
                	bundle.putString("path", path2);
                	flf.setArguments(bundle);
                	FragmentManager manager = getFragmentManager();
                	FragmentTransaction transaction = manager.beginTransaction();
                	transaction.replace(R.id.container, flf);
                	transaction.addToBackStack(null);
                	transaction.commit();
                }
            }
        });
		context=this.getActivity();
		final int i =getFragmentManager().getBackStackEntryCount();
		this.getListView().setOnKeyListener(new View.OnKeyListener() {
		    @Override
		    public boolean onKey(View v, int keyCode, KeyEvent event) {
		        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
		            if (event.getAction() == KeyEvent.ACTION_UP) {
		                getFragmentManager().popBackStack();
		                return true;
		            } if(i==0){
		            	getActivity().finish();
		            
		            } else {
		                return true;
		            }
		        }
		        return false;
		    }
		});
		this.getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(context, GV.class);
				Bundle bundle = new Bundle();
				ListView listView = (ListView) parent;
				String item = (String) listView.getItemAtPosition(position);
                String path2 = path + "/"+item;
                Sizemodify sm = new Sizemodify(context);
                if(sm.jpgnum(path2)==3){
				bundle.putString("path", path2);
				intent.putExtra("PATH", path2);
				startActivity(intent);
                }
                else{
                	Toast.makeText(context, "選択したフォルダには写真がありません。", Toast.LENGTH_LONG).show();
                
                }
                
				return false;
			}
		});
		
		
	}
	

}
