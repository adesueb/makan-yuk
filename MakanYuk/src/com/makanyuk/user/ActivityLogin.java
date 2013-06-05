package com.makanyuk.user;

import com.makanyuk.R;
import com.makanyuk.menuutama.ActivityMenuUtama;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;

public class ActivityLogin extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_login);
		
		
		Button buttonOk = (Button) findViewById(R.id.buttonOk);
		buttonOk.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				EditText etUserName = (EditText) findViewById(R.id.userName);
				EditText etPassword = (EditText) findViewById(R.id.password);
				
				String userName = etUserName.getText().toString();
				
				String password = etPassword.getText().toString();
				
				User user = new User();
				user.setUserName(userName);
				user.setPassword(password);
				
				new RegisterService().cekLogin(user,new HandlerLogin(ActivityLogin.this));
			}
		});
	}
		
	private final static class HandlerLogin extends Handler{

		public HandlerLogin(ActivityLogin activityLogin){
			this.activityLogin = activityLogin;
		}
		@Override
		public void handleMessage(Message msg) {
			Bundle bundle = msg.getData();
			String hasil = bundle.getString("hasil")+"";
			if(hasil.equals("sukses")){
				Intent intent = 
						new Intent(activityLogin, ActivityMenuUtama.class);
				activityLogin.startActivity(intent);	
			}else{
				Toast.makeText(activityLogin, hasil, Toast.LENGTH_SHORT).show();
			}
		}
		
		private final ActivityLogin activityLogin;
		
	}

}
