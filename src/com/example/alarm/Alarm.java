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
			// 加载指定音乐，并为之创建MediaPlayer对象
			alarmMusic = MediaPlayer.create(this, R.raw.alarm);
			alarmMusic.setLooping(true);
			// 播放音乐
			alarmMusic.start();
			// 创建一个对话框
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
							// 停止音乐
							alarmMusic.stop();

							
							// 结束该Activity
							Alarm.this.finish();
						}
					}
				)
				.show();
		}
	}