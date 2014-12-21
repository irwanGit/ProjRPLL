package com.studio.ir.cikarangsmartangkotbeta;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

public class Activity_Splash extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_splash);

		Thread timer = new Thread() {
			public void run() {
				try {
					
					sleep(3500);
				} catch (InterruptedException e) {
					// TODO: handle exception
					e.printStackTrace();
				} finally {
					
					Intent i = new Intent(Activity_Splash.this, Activity_MenuUtama.class);
					startActivity(i);
				    Activity_Splash.this.finish();
				}
			}
		};
		timer.start();
	}
}
