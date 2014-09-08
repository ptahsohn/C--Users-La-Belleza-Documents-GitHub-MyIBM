package com.ptah.ibmdemo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.widget.TextView;

public class CallSummery extends BaseActivity {
	TextView textView = null;
	StringBuffer sba = new StringBuffer();
	StringBuffer sb = new StringBuffer();
	  
    /** Called when the activity is first created. */
@Override
public void onCreate(Bundle savedInstanceState) {
   super.onCreate(savedInstanceState);
   setContentView(R.layout.call_summery);
   
   textView = (TextView) findViewById(R.id.textview_call);
   getCallDetails();
}
public void getCallDetails() {
	int outgCallDuration = 0;
	String callDate = "";
	Date callDayTime  = null;
	int datePeriod = 0;
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
	    callDate = managedCursor.getString(date);
	    callDayTime = new Date(Long.valueOf(callDate));
	   String callDuration = managedCursor.getString(duration);
	   String dir = null;
	   int dircode = Integer.parseInt(callType);
	   switch (dircode) {
	   case CallLog.Calls.OUTGOING_TYPE:
	    dir = "OutGoing";
	    outgCallDuration = Integer.parseInt(callDuration);
	 	 
	    break;

	   case CallLog.Calls.INCOMING_TYPE:
	    dir = "InComing";
	    break;

	   case CallLog.Calls.MISSED_TYPE:
	    dir = "Missed";
	    break;
	   }
	   sb.append("\nPhone No: " + phNumber 
			   + " \nDuration in sec : " + callDuration
			   + " \nType: "+ dir 
			   + " \nDate: " + callDayTime
	     );
	   sb.append("\n*************************************");
	  }
	  //managedCursor.close();
	  
	  
		 
	  
	  //check on call duration
	  if(outgCallDuration > (60*800))
	  {
		  sba.append("\nYour call expenditure lot more for a prepaid customer, please reconsider joining post pay!");
		  sba.append("\n----------------------------------\n");
		  sba.append(sb);
	  }else{
		  sba.append("\nYour call expenditure lot fits more for a prepaid account!");
		  sba.append("\n----------------------------------\n");
		  
		 // sba.append(sb);
		 
	  }
	  //un highlight this
	 // sba.append(sb);
	 //textView.setText(sba);
	 
	// the string representation of date (month/day/year)
	  DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	  String reportDate = df.format(callDayTime);
	  //group dates
	  CallGrouping(reportDate);
	 }


	private void CallGrouping(String date)
	{
		int su = 0;
		int mo = 0;
		int tu = 0;
		int we = 0;
		int th = 0;
		int fr = 0;
		int sa = 0;
		
		int Jan = 0;
		int Feb = 0;
		int Mar = 0;
		int Apr = 0;
		int May = 0;
		int Jun = 0;
		int Jul = 0;
		int Aug = 0;
		int Sep = 0;
		int Oct = 0;
		int Nov = 0;
		int Dec = 0;
		
		String s = "Hello World, I'm the rest.";
	    String[] result = s.split(" ", 5);
	    String day = result[0];
	    String month = result[1];
	    String dat = result[2];
	    String tym = result[3];
	    String rst = result[4];
	    System.out.println("day: " + day);
	    System.out.println("month: " + month);
	    System.out.println("dat: " + dat);
	    System.out.println("tym: " + tym);
	    
	    if(day=="Mon"){mo++;}
	    if(day=="Tue"){tu++;}
	    if(day=="Wed"){we++;}
	    if(day=="Thur"){th++;}
	    if(day=="Fri"){fr++;}
	    if(day=="Sat"){sa++;}
	    if(day=="Sun"){su++;}
	    
	    
	    //checking the days in a week with most calls
	    String daywithHighestcallsa = "Mon";
	    //
	    int arrInta[] = new int[7];
	   
	    arrInta [0] =  su ;
	    arrInta[1] = mo;
	    arrInta[2] = tu;
	    arrInta[3] = we;
	    arrInta[4] = th;
	    arrInta[5] = fr;
	    arrInta[6] = sa ;
	    //check the largest month
	    int largesta = arrInta[0];
	    int ctra = 1;
	    while ( ctra <= arrInta.length-1)
	    {
	    if (arrInta [ctra] > largesta)
	    {
	    largesta = arrInta[ctra];
	    if(ctra==0){daywithHighestcallsa ="Mon";}
	    if(ctra==1){daywithHighestcallsa ="Tue";}
	    if(ctra==2){daywithHighestcallsa ="Wed";}
	    if(ctra==3){daywithHighestcallsa ="Thur";}
	    if(ctra==4){daywithHighestcallsa ="Fri";}
	    if(ctra==5){daywithHighestcallsa ="Sat";}
	    if(ctra==6){daywithHighestcallsa ="Sun";}
	    
	    }
	    ctra++;
	    } 
	    
	    //checking the month with most calls made
	    if(month=="Jan"){Jan++;}
	    if(month=="Feb"){Feb++;}
	    if(month=="Mar"){Mar++;}
	    if(month=="Apr"){Apr++;}
	    if(month=="May"){May++;}
	    if(month=="Jun"){Jun++;}
	    if(month=="Jul"){Jul++;}
	    if(month=="Aug"){Aug++;}
	    if(month=="Sep"){Sep++;}
	    if(month=="Oct"){Oct++;}
	    if(month=="Nov"){Nov++;}
	    if(month=="Dec"){Dec++;}
	    String monthwithHighestcalls = "Sep";
	    //
	    int arrInt[] = new int[12];
	    
	    
	    arrInt[0] =  Jan;
	    arrInt[1] =  Feb;
	    arrInt[2] =  Mar ;
	    arrInt[3] =  Apr ;
	    arrInt[4] =  May ;
	    arrInt[5] =  Jun;
	    arrInt[6] =  Jul;
	    arrInt[7] =  Aug;
	    arrInt[8] =  Sep ;
	    arrInt[9] =  Oct;
	    arrInt[10] =  Nov ;
	    arrInt[11] =  Dec ;
	    //check the largest month
	    int largest = arrInt[0];
	    int ctr = 1;
	    while ( ctr <= arrInt.length-1)
	    {
	    if (arrInt [ctr] > largest)
	    {
	    largest = arrInt[ctr];
	    if(ctr==0){monthwithHighestcalls ="Jan";}
	    if(ctr==1){monthwithHighestcalls ="Feb";}
	    if(ctr==2){monthwithHighestcalls ="Mar";}
	    if(ctr==3){monthwithHighestcalls ="Apr";}
	    if(ctr==4){monthwithHighestcalls ="May";}
	    if(ctr==5){monthwithHighestcalls ="Jun";}
	    if(ctr==6){monthwithHighestcalls ="Jul";}
	    if(ctr==7){monthwithHighestcalls ="Aug";}
	    if(ctr==8){monthwithHighestcalls ="Sep";}
	    if(ctr==9){monthwithHighestcalls ="Oct";}
	    if(ctr==10){monthwithHighestcalls ="Nov";}
	    if(ctr==11){monthwithHighestcalls ="Dec";}
	    }
	    ctr++;
	    } 
	    sba.append("\nDays with most cals: "+daywithHighestcallsa);
	    sba.append("\nMonth with most Calls: "+monthwithHighestcalls+"\n");
	    sba.append("\n**************************\n");
	    sba.append(sb);
	    textView.setText(sba); 
	}
	
}
