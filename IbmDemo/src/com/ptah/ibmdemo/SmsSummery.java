package com.ptah.ibmdemo;

import java.util.Date;
import java.util.Scanner;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

public class SmsSummery extends BaseActivity {
	
	
	TextView textView;
	
    /** Called when the activity is first created. */
@Override
public void onCreate(Bundle savedInstanceState) {
   super.onCreate(savedInstanceState);
   setContentView(R.layout.sms_summery);
   textView = (TextView) findViewById(R.id.textview);
	getSMSDetails();

}

private void getSMSDetails() {
	
	int inbx = 1;
	int outbx = 1;
	int drftbx = 1;
	int mpsbx = 1;
	StringBuffer stringBuffer = new StringBuffer();
	stringBuffer.append("-------SMS Summery-------------- :");
	Uri uri = Uri.parse("content://sms");
	Cursor cursor = getContentResolver().query(uri, null, null, null, null);

	if (cursor.moveToFirst()) {
		for (int i = 0; i < cursor.getCount(); i++) 
		{
			String body = cursor.getString(cursor.getColumnIndexOrThrow("body"))
					.toString();
			String number = cursor.getString(cursor.getColumnIndexOrThrow("address"))
					.toString();
			String date = cursor.getString(cursor.getColumnIndexOrThrow("date"))
					.toString();
			Date smsDayTime = new Date(Long.valueOf(date));
			String type = cursor.getString(cursor.getColumnIndexOrThrow("type"))
					.toString();
			String typeOfSMS = null;
			switch (Integer.parseInt(type)) {
			case 1:
				typeOfSMS = "Inbox";
				inbx++;
				if(ScannerMpesa(body) == true)
				{
					mpsbx++;
				}
				break;

			case 2:
				typeOfSMS = "Sent";
				outbx++;
				if(ScannerMpesa(body) == true)
				{
					mpsbx++;
				}
				break;

			case 3:
				typeOfSMS = "Draft";
				drftbx++;
				break;
			}

			stringBuffer.append(
					"\nPhone No.: " + number + 
					" \nBody: " + body+
					" \nDate: " + smsDayTime+
					" \nType: "+ typeOfSMS 
					
					);
			
			stringBuffer.append("\n----------------------------------");
			
			cursor.moveToNext();
		}
		MyApplicationClass myApp = (MyApplicationClass) getApplication();
		
		myApp.setmySmsInbox(inbx);
		myApp.setmySmsSent(outbx);
		myApp.setmySmsDraft(drftbx);
		String mp = ""+mpsbx;
		myApp.setmyMpesatring(mp);
		
		StringBuffer sba = new StringBuffer();	 
		  
		  
		  if(outbx > 20)
		  {
			  sba.append("\nYour messaging rate is high, consider getting dailly subscriptions");
			  sba.append("\n----------------------------------\n");
			  sba.append(stringBuffer);
		  }else{
			  sba.append("\nYour message rate is at optimal!");
			  sba.append("\n----------------------------------\n");
			  
			  sba.append(stringBuffer);
		  }
		textView.setText(sba);
	}
	cursor.close();
}

private boolean ScannerMpesa(String msg)
{
	boolean rep= false; 
	//String input = "1 fish 2 fish red fish blue fish";
	String input = msg;
  
    Scanner s = new Scanner(input).useDelimiter("\\s*M-PESA\\s*");
    if(s.next()!= "")
    {
    	rep = true;
    	System.out.println(s.next());
    }
    s.close();
    
    return  rep;
}
}
