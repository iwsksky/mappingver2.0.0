package com.example.mapping2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class FolderListFragment extends ListFragment {
	public static Context context;
	public File[] images;
	public ArrayList<String> folder= new ArrayList<String>();
	private List<String> imagelist = new ArrayList<String>();
	public String[] filename;

	@Override
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
                FolderCycle fc = new FolderCycle();
                Bundle bundle = new Bundle();
                String path2 = path + "/"+item;
        		bundle.putString("path", path2);
        		fc.setArguments(bundle);
        		FragmentManager manager = getFragmentManager();
        	    FragmentTransaction transaction = manager.beginTransaction();
        	    transaction.replace(R.id.container, fc);
        	    transaction.addToBackStack(null);
        	    transaction.commit();
            }
        });
		this.getListView().setOnKeyListener(new View.OnKeyListener() {
		    @Override
		    public boolean onKey(View v, int keyCode, KeyEvent event) {
		        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
		            if (event.getAction() == KeyEvent.ACTION_UP) {
		                getFragmentManager().popBackStack();
		                return true;
		            } else {
		                return true;
		            }
		        }
		        return false;
		    }
		});
		context=this.getActivity();
		
		this.getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(context, GV.class);
				startActivity(intent);
				return false;
			}
		});
		
		
	}

	

}
