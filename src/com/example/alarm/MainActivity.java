package com.example.alarm;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends Activity implements DTpicker.DTpickerListener{

	private TextView tv2, tv3;
	private TextView tv;
	private ToggleButton tb,tb2;
	private Calendar c;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");
	private AlarmManager aManager;
	private int myear, mmonth, mday, mhour, mminute;
	private Button bt;
	private EditText et;
	private GoogleMap mMap;
	private Geocoder mgc;
	private List<Address> address;
	private DTtransfer mDtt;
	private static final LatLng SYDNEY = new LatLng(-33.88,151.21);
	private static final LatLng MOUNTAIN_VIEW = new LatLng(37.4, -122.1);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SYDNEY, 15));
		mMap.animateCamera(CameraUpdateFactory.zoomIn());
		mgc = new Geocoder(this);

		// Construct a CameraPosition focusing on Mountain View and animate the camera to that position.
		CameraPosition cameraPosition = new CameraPosition.Builder()
		    .target(MOUNTAIN_VIEW)      // Sets the center of the map to Mountain View
		    .zoom(17)                   // Sets the zoom
		    .bearing(90)                // Sets the orientation of the camera to east
		    .tilt(30)                   // Sets the tilt of the camera to 30 degrees
		    .build();                   // Creates a CameraPosition from the builder
		mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
		
		c = Calendar.getInstance();
		aManager = (AlarmManager) getSystemService(Service.ALARM_SERVICE);
		
		tv = (TextView)findViewById(R.id.textView1);
		tv2 = (TextView)findViewById(R.id.textView2);
		tv3 = (TextView)findViewById(R.id.textView3);
		tv.setText(sdf.format(c.getTime()));
		tv2.setText(sdf2.format(c.getTime()));
		tb2 = (ToggleButton)findViewById(R.id.toggleButton2);
		et = (EditText)findViewById(R.id.et1);
		mDtt = new DTtransfer(2,2,2,2,2);
		
		Dialog dialog = new Dialog(this);

		dialog.setContentView(R.layout.dtdialog);
		dialog.setTitle("Custom Dialog");
		
		Button bt = (Button)findViewById(R.id.button1);
		bt.setText("DTPICKER");
		
		bt.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
		tv.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View source)
			{
				Calendar c = Calendar.getInstance(); 
				// ������������DatePickerDialog��������������������������
				new DatePickerDialog(MainActivity.this,
					// ����������
					new DatePickerDialog.OnDateSetListener()
					{
						@Override
						public void onDateSet(DatePicker dp, int year,
							int month, int dayOfMonth)
						{
							month++;
							tv.setText(year + "-" + month + "-"
								+ dayOfMonth);
							myear = year;
							mmonth = month;
							mday = dayOfMonth;
						}
					}
				//������������
				, c.get(Calendar.YEAR)
				, c.get(Calendar.MONTH)
				, c.get(Calendar.DAY_OF_MONTH)).show();
			}			
		});
		
		tv2.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View source)
			{
				Calendar c = Calendar.getInstance();
				// ��������TimePickerDialog����������������������
				new TimePickerDialog(MainActivity.this,
					// ����������
					new TimePickerDialog.OnTimeSetListener()
					{
						@Override
						public void onTimeSet(TimePicker tp, int hourOfDay,
							int minute)
						{
							tv2.setText(hourOfDay + ":" + minute
								);
							mhour = hourOfDay;
							mminute = minute;
						}
					}
				//������������
				, c.get(Calendar.HOUR_OF_DAY)
				, c.get(Calendar.MINUTE)
				//true��������24������
				, true).show();
			}
		});	
		
		
		tb = (ToggleButton)findViewById(R.id.toggleButton1);
		
		tb.setOnCheckedChangeListener(new OnCheckedChangeListener()
		{

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1)
			{
				if(arg1)
				{
					DTpicker df = new DTpicker();
					df.show(getFragmentManager(), "dt_picker");
				}
				else
				{
					tv3.setText("");
				}			
			}
		});
		
		tb2.setOnCheckedChangeListener(new OnCheckedChangeListener()
		{

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1)
			{
				if(arg1)
				{
					String s = et.getText().toString();
					try {
					    address = mgc.getFromLocationName(s ,5);
					    if (address == null) {
					        
					    }
					    Address location = address.get(0);
					    location.getLatitude();
					    location.getLongitude();
					    
					    tv3.setText("latitude: " + location.getLatitude() + "; longitude: " + location.getLongitude());
					    
					    LocAlerter la = new LocAlerter(location.getLatitude(), location.getLongitude(), (float)100);
					    la.alertAtLoc();

					} catch(Exception e){
						
					}
				}
				else
				{

				}			
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	private void setUpMapIfNeeded() {
	    // Do a null check to confirm that we have not already instantiated the map.
	    if (mMap == null) {
	        mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
	                            .getMap();
	        // Check if we were successful in obtaining the map.
	        if (mMap != null) {
	            // The Map is verified. It is now safe to manipulate the map.
	        	mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
	        }
	    }
	}

    // The dialog fragment receives a reference to this Activity through the
    // Fragment.onAttach() callback, which it uses to call the following methods
    // defined by the DTpicker.NoticeDialogListener interface
    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        // User touched the dialog's positive button
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        // User touched the dialog's negative button
    }

	@Override
	protected Dialog onCreateDialog(int id, Bundle args) {
		// TODO Auto-generated method stub
		return super.onCreateDialog(id, args);
	}
    
    
}
