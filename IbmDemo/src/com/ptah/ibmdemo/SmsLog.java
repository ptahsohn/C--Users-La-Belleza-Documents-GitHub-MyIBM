package com.ptah.ibmdemo;

import java.util.Date;

import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class SmsLog extends BaseActivity {
	LinearLayout linearChart;

	 @Override
	 protected void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.sms_log);
	  
	  
	  
	  
	  getSMSDetails();

	 }

	 
	 private void getSMSDetails() {
	 	
	 	int inbx = 1;
	 	int outbx = 1;
	 	int drftbx = 1;
	 	String msg = "";
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
	 				typeOfSMS = "INBOX";
	 				inbx++;
	 				break;

	 			case 2:
	 				typeOfSMS = "SENT";
	 				outbx++;
	 				break;

	 			case 3:
	 				typeOfSMS = "DRAFT";
	 				drftbx++;
	 				break;
	 			}

	 			stringBuffer.append(
	 					"\nPhone No.: " + number + 
	 					" \nType: "+ typeOfSMS + 
	 					" \nDate: " + smsDayTime+
	 					" \nBody: " + body);
	 			
	 			stringBuffer.append("\n----------------------------------");
	 			
	 			cursor.moveToNext();
	 		}
	 		MyApplicationClass myApp = (MyApplicationClass) getApplication();
	 		
	 		if(inbx<100)
	 		{inbx=inbx*10;}
	 		if(outbx<100)
	 		{outbx=outbx*10;}
	 		if(drftbx<100)
	 		{drftbx=drftbx*10;}
	 		
	 		myApp.setmySmsInbox(inbx);
	 		myApp.setmySmsSent(outbx);
	 		myApp.setmySmsDraft(drftbx);
	 		msg = stringBuffer.toString();
	 		myApp.setmyMsgString(msg);
	 		
	 		drawChartbar(inbx,outbx,drftbx);
	 		
	 		//textView.setText(stringBuffer);
	 	}
	 	cursor.close();
	 }
	 private void drawChartbar(int a, int b, int c)
	 {
		 
		 linearChart = (LinearLayout) findViewById(R.id.linearChart);
		  int colerloop[] = { 1, 1, 1,1, 2, 2, 2, 2, 3, 3, 3, 3};
		  
		  int heightLoop[] = { a, a, a, a, b, b, b, b, c, c, c, c};
		  
		  for (int j = 0; j < colerloop.length; j++) {
		   drawChart(1, colerloop[j], heightLoop[j]);
		  }
		 
	 }

	 public void drawChart(int count, int color, int height) {
	  System.out.println(count + color + height);
	  if (color == 3) {
		   color = Color.MAGENTA;
		  } else if (color == 1) {
		   color = Color.BLUE;
		  } else if (color == 2) {
		   color = Color.DKGRAY;
		  }

	  for (int k = 1; k <= count; k++) {
	   View view = new View(this);
	   view.setBackgroundColor(color);
	   view.setLayoutParams(new LinearLayout.LayoutParams(25, height));
	   LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view
	     .getLayoutParams();
	   params.setMargins(3, 0, 0, 0); // substitute parameters for left,
	           // top, right, bottom
	   view.setLayoutParams(params);
	   linearChart.addView(view);
	  }
	 }

}
