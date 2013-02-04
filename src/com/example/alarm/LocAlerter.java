package com.example.alarm;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.widget.Toast;

public class LocAlerter extends Activity{
	
	private String mLocService;
	private LocationManager mLocMgr;
	
	private double mLatitude;
	private double mLongitude;
	private float mRadius;
	
	public LocAlerter(double latitude, double longitude, float radius){
		this.mLatitude = latitude;
		this.mLongitude = longitude;
		this.mRadius = radius;
		mLocService = Context.LOCATION_SERVICE;
		mLocMgr = (LocationManager) getSystemService(mLocService);
	}
	
	public void alertAtLoc(){
		
		Intent i = new Intent(this, ProximityAlertReceiver.class);
		
		PendingIntent pi = PendingIntent.getBroadcast(this, -1, i, 0);			

		mLocMgr.addProximityAlert(mLatitude, mLongitude, mRadius, -1, pi);	
	}
	
	private void toastIt(){
		Toast.makeText(this
				, "Alert at " + mLongitude + mLongitude
				, Toast.LENGTH_LONG)
				.show();
	}

}
