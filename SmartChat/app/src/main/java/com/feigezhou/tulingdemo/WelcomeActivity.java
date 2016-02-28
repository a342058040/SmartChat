package com.feigezhou.tulingdemo;

import java.util.Timer;
import java.util.TimerTask;

import com.feigezhou.tulingdemo.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;


public class WelcomeActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.welcome);
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask(){
			@Override
			public void run() {
				Intent intent=new Intent(WelcomeActivity.this,LoginActivity.class);
				
				startActivity(intent);
				WelcomeActivity.this.finish();
			}
		}, 3500);
		
	}
}
