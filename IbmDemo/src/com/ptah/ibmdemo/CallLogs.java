package com.ptah.ibmdemo;

import java.util.Date;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.CallLog;
import android.view.View;
import android.widget.LinearLayout;

public class CallLogs  extends BaseActivity {
	LinearLayout linearChart;

	 @Override
	 protected void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.call_log);
	  getCallDetails();
	 }
	 public void getCallDetails() {

		  	int inbx = 1;
		 	int outbx = 1;
		 	int msdbx = 1;
			String msg = "";
			int outgCallDuration = 0;
		 	  StringBuffer sb = new StringBuffer();
	 	  Cursor managedCursor = managedQuery(CallLog.Calls.CONTENT_URI, null,
	 	    null, null, null);
	 	  int number = managedCursor.getColumnIndex(CallLog.Calls.NUMBER);
	 	  int type = managedCursor.getColumnIndex(CallLog.Calls.TYPE);
	 	  int date = managedCursor.getColumnIndex(CallLog.Calls.DATE);
	 	  int duration = managedCursor.getColumnIndex(CallLog.Calls.DURATION);
	 	  sb.append("Call Log :");
	 	  while (managedCursor.moveToNext()) {
	 	   String phNumber = managedCursor.getString(number);
	 	   String callType = managedCursor.getString(type);
	 	   String callDate = managedCursor.getString(date);
	 	   Date callDayTime = new Date(Long.valueOf(callDate));
	 	   String callDuration = managedCursor.getString(duration);
	 	   String dir = null;
	 	   int dircode = Integer.parseInt(callType);
	 	   switch (dircode) {
	 	   case CallLog.Calls.OUTGOING_TYPE:
	 	    dir = "OUTGOING";
	 	   outbx++;
	 	  outgCallDuration = Integer.parseInt(callDuration);
	 	    break;

	 	   case CallLog.Calls.INCOMING_TYPE:
	 	    dir = "INCOMING";
	 	   inbx++;
	 	    break;

	 	   case CallLog.Calls.MISSED_TYPE:
	 	    dir = "MISSED";
	 	   msdbx++;
	 	    break;
	 	   }
	 	   sb.append("\nPhone No: " + phNumber 
	 			   + " \nType: " + dir 
	 			   + " \nDate: " + callDayTime
	 	     + " \nCall duration: " + callDuration);
	 	   sb.append("\n*****************");
	 	   
	 	  }
	 	  //managedCursor.close();
	 	  //textView.setText(sb);
	 	 MyApplicationClass myApp = (MyApplicationClass) getApplication();
	 		
	 	 if(inbx<100)
	 		{inbx=inbx*10;}
	 		if(outbx<100)
	 		{outbx=outbx*10;}
	 		if(msdbx<100)
	 		{msdbx=msdbx*10;}
	 		
	 		myApp.setmyCallIn(inbx);
	 		myApp.setmyCallOut(outbx);
	 		myApp.setmyCallMiss(msdbx);
	 		msg = sb.toString();
	 		myApp.setmyMsgString(msg);
	 		
	 		drawChartbar(inbx,outbx,msdbx);
	 	  
	 	 }


	 private void drawChartbar(int a, int b, int c)
	 {
		 
		 linearChart = (LinearLayout) findViewById(R.id.linearChart);
		 int colerloop[] = { 1, 1, 1, 1, 2,2, 2, 2, 3, 3, 3, 3};
		  
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
