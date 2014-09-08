package com.ptah.ibmdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;


public class MainActivity extends BaseActivity  {

	
    
     private Thread splashThread;

      /** Called when the activity is first created. */
      @Override
      public void onCreate(Bundle savedInstanceState) {
             super.onCreate(savedInstanceState);
             setContentView(R.layout.activity_main);
            
             // The thread to wait for splash screen events
             splashThread = new Thread() {
                    @Override
                    public void run() {
                          try {
                           synchronized (this) {
                            // Wait given period of time or exit on touch
                        	 
                        	   wait(3000);
                                 }
                          } catch (InterruptedException ex) {
                          }
                          finish();
                          // Run next activity
                          startActivity(new Intent(getApplicationContext(),NextActivity.class));
                 		 //End the current activity
                 		 finish();
                          //Intent intent = new Intent();
                          //intent.setClass(SplashScreen.this,MainActivity.class);
                          //startActivity(intent);
                    }
             };

             splashThread.start();
      }

      /**
       * Processes splash screen touch events
       */
      @Override
      public boolean onTouchEvent(MotionEvent evt) {
             if (evt.getAction() == MotionEvent.ACTION_DOWN) {
                    synchronized (splashThread) {
                          splashThread.notifyAll();
                    }
             }
             return true;
      }
      
   
}
