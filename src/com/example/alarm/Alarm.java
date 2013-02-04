package com.example.alarm;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ToggleButton;

public class Alarm extends Activity {
	
		MediaPlayer alarmMusic;

		@Override
		public void onCreate(Bundle savedInstanceState)
		{
			super.onCreate(savedInstanceState);
			// ����ָ�����֣���Ϊ֮����MediaPlayer����
			alarmMusic = MediaPlayer.create(this, R.raw.alarm);
			alarmMusic.setLooping(true);
			// ��������
			alarmMusic.start();
			// ����һ���Ի���
			new AlertDialog.Builder(Alarm.this)
				.setTitle("Alarm")
				.setMessage("Ring!!!")
				.setPositiveButton(
					"Confirm" ,
					new OnClickListener()
					{
						@Override
						public void onClick(DialogInterface dialog , int which)
						{
							// ֹͣ����
							alarmMusic.stop();

							
							// ������Activity
							Alarm.this.finish();
						}
					}
				)
				.show();
		}
	}