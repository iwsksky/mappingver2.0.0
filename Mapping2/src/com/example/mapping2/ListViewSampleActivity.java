package com.example.mapping2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.R.id;
import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class ListViewSampleActivity extends Activity {
	private List<String> imagelist = new ArrayList<String>();
	private File[] images;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_sample);
        String path = Environment.getExternalStorageDirectory().getPath();
		
		
		images = new File(path).listFiles();//ファイル数１０
		//stringArray = new images.getName();
		
		//フォルダ一覧
		for (int i=0; i<images.length; i++){//１０回回す
				imagelist.add(images[i].getName());
				
			}
		
		//data = Arrays.asList(info);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, imagelist);
        // アイテムを追加
        
        ListView listView = (ListView) findViewById(R.id.listView1);
        // アダプターを設定
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                ListView listView = (ListView) parent;
                // クリックされたアイテムを取得します
                String item = (String) listView.getItemAtPosition(position);
                Toast.makeText(ListViewSampleActivity.this, item, Toast.LENGTH_LONG).show();
            }
        });

    }
}