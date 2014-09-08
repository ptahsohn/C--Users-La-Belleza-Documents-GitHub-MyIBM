package com.ptah.ibmdemo;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class NextActivity extends BaseActivity  {
	Context context;  
	private TextView lblTextViewOne;
	/** Called when the activity is first created. */
   @Override
   public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.screen_next);
       
       
       Button button1 = (Button) findViewById(R.id.button1);
       button1.setOnClickListener(new OnClickListener() {			
			public void onClick(View v) {
				startActivity(new Intent(getApplicationContext(), SmsSummery.class));
				finish();
			}
		});
      	
      	Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new OnClickListener() {			
			public void onClick(View v) {
				startActivity(new Intent(getApplicationContext(), CallSummery.class));
				finish();
			}
		});
   	
   }

   public void getGPSLocation()
   {// Acquire a reference to the system Location Manager
		LocationManager locationManager = (LocationManager) this
				.getSystemService(Context.LOCATION_SERVICE);

		// Define a listener that responds to location updates
		LocationListener locationListener = new LocationListener() {
			public void onLocationChanged(Location location) {
				location.getLatitude();
				Toast.makeText(context, "Current speed:" + location.getSpeed(),
						Toast.LENGTH_SHORT).show();

				 lblTextViewOne = (TextView) findViewById(R.id.lblTextViewOne);
			        lblTextViewOne.setText("Current speed:" + location.getSpeed());//
			        
			}

			public void onStatusChanged(String provider, int status,
					Bundle extras) {
			}

			public void onProviderEnabled(String provider) {
			}

			public void onProviderDisabled(String provider) {
			}
		};
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,
				0, locationListener);

	}
   
}