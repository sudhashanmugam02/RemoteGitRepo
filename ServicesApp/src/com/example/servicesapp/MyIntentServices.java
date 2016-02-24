package com.example.servicesapp;

import android.app.IntentService;                  
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

public class MyIntentServices extends IntentService {

	public MyIntentServices() {
		super(MyIntentServices.class.getName());
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub
		//Toast.makeText(this, intent.getExtras().getString("key"), Toast.LENGTH_SHORT).show();
		 Intent localIntent =
		            new Intent(Constants.BROADCAST_ACTION)
		            // Puts the status into the Intent
		            .putExtra(Constants.EXTENDED_DATA_STATUS, "Brin");
		    // Broadcasts the Intent to receivers in this app.
		    LocalBroadcastManager.getInstance(this).sendBroadcast(localIntent);
		    sendBroadcast(localIntent);
	} 

}
