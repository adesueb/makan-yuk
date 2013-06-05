package com.makanyuk.user;

import com.makanyuk.R;
import com.makanyuk.net.util.Status;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.Activity;

public class ActivityRegister extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_register);

		handler = new Handler();
		
		Button button = (Button) findViewById(R.id.buttonOk);
		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				EditText password = (EditText) findViewById(R.id.password);				
				String passwordText = password.getText().toString()+"";
				
				EditText passwordLagi = (EditText) findViewById(R.id.passwordLagi);				
				String passwordLagiText = passwordLagi.getText().toString()+"";
				
				EditText namaLengkap = (EditText) findViewById(R.id.namaLengkap);
				String namaLengkapText = namaLengkap.getText().toString()+"";
				
				EditText userName = (EditText) findViewById(R.id.userName);				
				String userNameText = userName.getText().toString()+"";
				
				EditText alamatEmail = (EditText) findViewById(R.id.alamatEmail);				
				String alamatEmailText = alamatEmail.getText().toString()+"";
				
				if(!namaLengkapText.equals("") && 
						!userNameText.equals("") && 
						!alamatEmailText.equals("") && 
						!password.equals("") && 
						passwordText.equals(passwordLagiText)){
					User user = new User();
					user.setNamaLengkap(namaLengkapText);
					user.setUserName(userNameText);
					user.setPassword(passwordText);
					user.setAlamatEmail(alamatEmailText);
					new RegisterService().
						sendRegister(user, new HandlerRegister(ActivityRegister.this));
				}else{
					Toast.makeText(ActivityRegister.this, "password harus sama dan di isi semua", Toast.LENGTH_SHORT).show();	
				}
				
			}
		});
		
	}
	
	private final static class HandlerRegister extends Handler{

		public HandlerRegister(ActivityRegister activityRegister){
			this.activityRegister = activityRegister;
		}
		
		@Override
		public void handleMessage(Message msg) {
			if(msg.what==Status.SUCCESS){
				activityRegister.handler.post(new Runnable(){

					@Override
					public void run() {
						Toast.makeText(activityRegister, "sukses", Toast.LENGTH_SHORT).show();
						activityRegister.finish();
					}
					
				});
			}
			
		}
		
		private final ActivityRegister activityRegister;
		
	}
	
	private Handler handler;

}
