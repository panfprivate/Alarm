/**
 * 
 */
package com.example.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.widget.Toast;


public class ProximityAlertReceiver extends BroadcastReceiver
{
	@Override
	public void onReceive(Context context, Intent intent)
	{
		
		boolean isEnter = intent.getBooleanExtra(
			LocationManager.KEY_PROXIMITY_ENTERING, false);
		if(isEnter)
		{
			
			Toast.makeText(context
				, "you are entering."
				, Toast.LENGTH_LONG)
				.show();
		}
		else
		{
			
			Toast.makeText(context
				, "you are leaving"
				, Toast.LENGTH_LONG)
				.show();		
		}
	}
}
