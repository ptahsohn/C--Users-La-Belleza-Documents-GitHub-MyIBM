package com.ptah.ibmdemo;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public abstract class BaseActivity extends Activity {

	  @Override
	  public boolean onCreateOptionsMenu(Menu menu) {
	  MenuInflater inflater = getMenuInflater();
	  inflater.inflate(R.menu.options_menu, menu);
	  return true;
	  }

	 @Override
 public boolean onOptionsItemSelected(MenuItem item) {
	  switch (item.getItemId()) 
	  {
	 case R.id.item1:
		 startActivity(new Intent(getApplicationContext(),CallLogs.class));
		 //End the current activity
		 finish();
		 return true;
	 case R.id.item2:
		 startActivity(new Intent(getApplicationContext(),
				 SmsLog.class));
		 //End the current activity
		 finish();
		 return true;
	 default:
		 return super.onContextItemSelected(item);

	         }

	    }
}
