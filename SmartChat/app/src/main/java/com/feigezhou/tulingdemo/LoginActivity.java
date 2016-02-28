package com.feigezhou.tulingdemo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
	public EditText name;
	public EditText password;
	public Button login;
	public Button register;
	private ProgressDialog mDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		init();
		login.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mDialog = new ProgressDialog(LoginActivity.this);
				mDialog.setTitle("登陆失败");
				mDialog.setMessage("正在登陆，请稍后...");
				mDialog.show();
				String name1=name.getText().toString().trim();
				String password1=password.getText().toString().trim();
				if ((name1.equals("zhou")&&password1.equals("123"))) {
					Toast.makeText(LoginActivity.this, "登录成功",
							Toast.LENGTH_SHORT).show();
					Intent intent = new Intent();
					intent.setClass(LoginActivity.this, MainActivity.class);
					startActivity(intent);
					mDialog.dismiss();
				}else{
					 Toast.makeText(LoginActivity.this,"登陆失败", Toast.LENGTH_SHORT).show();
					 mDialog.dismiss();
				}
			}
		});
		/*
		 * register.setOnClickListener(new Button.OnClickListener() {
		 * 
		 * @Override public void onClick(View v) { // TODO Auto-generated method
		 * stub Intent intent = new Intent();
		 * intent.setClass(LoginActivity.this, MainActivity.class);
		 * startActivity(intent); } });
		 */
	}

	public void init() {
		name = (EditText) findViewById(R.id.login_name);
		password = (EditText) findViewById(R.id.login_password);
		login = (Button) findViewById(R.id.login_login);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
