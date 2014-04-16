package com.example.dailytodo;

import java.io.FileOutputStream;
import java.io.PrintStream;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class AddItemActivity extends Activity {
	private EditText date,startTime,endTime,item;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.additem);
		date = (EditText)findViewById(R.id.date);
		startTime = (EditText)findViewById(R.id.startTime);
		endTime  = (EditText)findViewById(R.id.endTime);
		item = (EditText)findViewById(R.id.item);
	}

	public void chooseDate(View view)
	{
		DatePickerDialog datepd = new DatePickerDialog(this,new DatePickerDialog.OnDateSetListener() {
			
			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				date.setText(year + "-" + monthOfYear + "-" + dayOfMonth);
				
			}
		},2014,4,15);
		datepd.setMessage("选择日期");
		datepd.show();
	}
	public void chooseStartTime(View view)
	{
		TimePickerDialog timepd = new TimePickerDialog(this,new TimePickerDialog.OnTimeSetListener() {
			
			@Override
			public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
				startTime.setText(hourOfDay + ":" + minute);
			}
		},12,0,true);
		timepd.setMessage("开始时间");
		timepd.show();
	}
	public void chooseEndTime(View view)
	{
		TimePickerDialog timepd = new TimePickerDialog(this,new TimePickerDialog.OnTimeSetListener() {
			
			@Override
			public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
				endTime.setText(hourOfDay + ":" + minute);
			}
		},12,0,true);
		timepd.setMessage("结束时间");
		timepd.show();
	}
	public void save(View view)
	{
		StringBuilder result = new StringBuilder();
		result.append(date.getText().toString() + " ");
		result.append(startTime.getText().toString() + "-");
		result.append(endTime.getText().toString() + " ");
		result.append(item.getText().toString());
		try {
			FileOutputStream out = openFileOutput("item",MODE_APPEND);
			PrintStream ps = new PrintStream(out);
			ps.println(result.toString());
			ps.close();
			out.close();
			Toast.makeText(this, "保存完毕", Toast.LENGTH_LONG).show();
			Intent intent = getIntent();
			setResult(0x111,intent);
			finish();
		} catch (Exception e) {
			// TODO: handle exception
			Log.d("fuck", "save error" + e.getMessage());
		}
	}
	
}
