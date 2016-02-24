package com.example.servicesapp;

import com.example.servicesapp.MyServices.LocalBinder;

import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBarActivity;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	Intent serviceIntent;
	MyServices myServices;
	TextView isResTextView;
	ResponseReceiver receiver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		isResTextView = (TextView)findViewById(R.id.textViewService);
		serviceIntent = new Intent(MainActivity.this,MyServices.class);
		serviceIntent.putExtra("key", "Brindhaa");
		receiver = new ResponseReceiver();
		IntentFilter filter=new IntentFilter(Constants.BROADCAST_ACTION);
		LocalBroadcastManager.getInstance(this).registerReceiver(receiver, filter);
		
	}

	
	public void handleServices(View v){

		//ServiceConnection connection = null;
		switch (v.getId()) {
		case R.id.buttonStart:
			serviceIntent.putExtra("key","Brin");
			startService(serviceIntent);
			break;
		case R.id.buttonStop:
			stopService(serviceIntent);
			break;

		case R.id.buttonBind:
			bindService(serviceIntent, connection, Service.BIND_AUTO_CREATE);
			break;

		case R.id.buttonUnbind:
			unbindService(connection);
			break;
		case R.id.buttonIntent:
			Intent serIntent = new Intent(MainActivity.this,MyIntentServices.class);
			serIntent.putExtra("key", "Cognizant");
			startService(serIntent);
			break;

		}
	}

	private class ResponseReceiver extends BroadcastReceiver
	{
	    // Prevents instantiation
	    public void onReceive(Context context, Intent intent) {
	    	isResTextView.setTag(intent.getExtras().getString(Constants.EXTENDED_DATA_STATUS));
	
	    }
	}

	ServiceConnection connection = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			Toast.makeText(MainActivity.this, "onServiceDisconnected", Toast.LENGTH_SHORT).show();

		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder mBinder) {
			// TODO Auto-generated method stub
			Toast.makeText(MainActivity.this, "onServiceConnected", Toast.LENGTH_SHORT).show();
			LocalBinder lBinder= (LocalBinder)mBinder;
			myServices = lBinder.getServices();
			Toast.makeText(MainActivity.this,
					"Cricket Score = " + myServices.getCricketScore(),
					Toast.LENGTH_SHORT).show();
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
