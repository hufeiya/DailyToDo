package com.example.dailytodo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private ListView itemList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        itemList = (ListView)findViewById(R.id.listView);
        getItemList();
    }

    private void getItemList()
    {
    	ArrayList<String> list = new ArrayList<String>();
    	try {
			FileInputStream in = openFileInput("item");
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String line  ="";
			while((line = br.readLine()) != null)
			{
				list.add(0,line);
			}
			
			br.close();
			in.close();
			String [] contents = {};
			String [] allItem = list.toArray(contents);
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_checked,allItem);
			itemList.setAdapter(adapter);
			itemList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		
		} catch (Exception e) {
			// TODO: handle exception
		}
    }
    public void add(View view)
    {
    	Intent intent  = new Intent(MainActivity.this,AddItemActivity.class);
    	startActivityForResult(intent,0x111);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	// TODO Auto-generated method stub
    	if(requestCode == 0x111 && resultCode == 0x111)
    	{
    		getItemList();
    	}
    	super.onActivityResult(requestCode, resultCode, data);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    private void saveItemCompleteInfo()
    {
    	try {
    		FileOutputStream out = openFileOutput("complete",MODE_APPEND);
			PrintStream ps = new PrintStream(out);
			//ps.println(result.toString());
			ps.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
    }
    
}
