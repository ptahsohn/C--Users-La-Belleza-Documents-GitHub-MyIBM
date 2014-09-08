package com.ptah.ibmdemo;

import android.app.Application;

public class MyApplicationClass extends Application {

	private String myMpesatring;

	public String getmyMpesatring() {
		return myMpesatring;
	}
	
	public void setmyMpesatring(String myApplicationString) {
		this.myMpesatring = myApplicationString;
	}
	
	private String myCallString;

	public String getmyCallString() {
		return myCallString;
	}
	
	public void setmyCallString(String myApplicationString) {
		this.myCallString = myApplicationString;
	}
	
	private String myMsgString;

	public String getmyMsgString() {
		return myMsgString;
	}
	
	public void setmyMsgString(String myApplicationString) {
		this.myMsgString = myApplicationString;
	}
	
	private String mySmsString;

	public String getmySmsString() {
		return mySmsString;
	}
	
	public void setmySmsString(String myApplicationString) {
		this.mySmsString = myApplicationString;
	}

	private int mySmsInbox;

	public int getmySmsInbox() {
		return mySmsInbox;
	}
	
	public void setmySmsInbox(int myApplicationString) {
		this.mySmsInbox = myApplicationString;
	}
	

	private int mySmsSent;

	public int getmySmsSent() {
		return mySmsSent;
	}
	
	public void setmySmsSent(int myApplicationString) {
		this.mySmsSent = myApplicationString;
	}
	

	private int mySmsDraft;

	public int getmySmsDraft() {
		return mySmsDraft;
	}
	
	public void setmySmsDraft(int myApplicationString) {
		this.mySmsDraft = myApplicationString;
	}
	
	private int myCallOut;

	public int getmyCallOut() {
		return myCallOut;
	}
	
	public void setmyCallOut(int myApplicationString) {
		this.myCallOut = myApplicationString;
	}
	

	private int myCallIn;

	public int getmyCallIn() {
		return myCallIn;
	}
	
	public void setmyCallIn(int myApplicationString) {
		this.myCallIn = myApplicationString;
	}
	

	private int myCallMiss;

	public int getmyCallMiss() {
		return myCallMiss;
	}
	
	public void setmyCallMiss(int myApplicationString) {
		this.myCallMiss = myApplicationString;
	}
}
