package com.example.servicesapp;

import java.util.Random;

import com.example.servicesapp.MyServices.LocalBinder;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyServices extends Service {

	private static final String TAG = "MyServices";
	private final Random mGenerator= new Random();

	LocalBinder mBinder = new LocalBinder();


	public class LocalBinder extends Binder {

		MyServices getServices(){

			return MyServices.this;
		}
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.i(TAG, "onCreate");
		Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();

	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		Log.i(TAG, "onStartCommand");
		Log.i(TAG, "onStartCommand = " + intent.getStringExtra("key"));
		Toast.makeText(this, "onStartCommand data = " + intent.getStringExtra("key"), Toast.LENGTH_SHORT).show();
		super.onStartCommand(intent, flags, startId);
		return Service.BIND_AUTO_CREATE;
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		Log.i(TAG, "onBind");
		Toast.makeText(this, "onBind", Toast.LENGTH_SHORT).show();
		return mBinder;
	}


	@Override
	public boolean onUnbind(Intent intent) {
		// TODO Auto-generated method stub
		Log.i(TAG, "onUnbind");
		Toast.makeText(this, "onUnbind", Toast.LENGTH_SHORT).show();
		return super.onUnbind(intent);


	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i(TAG, "onDestroy");
		Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();
	}

	public int getCricketScore(){

		return mGenerator.nextInt(100);
	}

}
