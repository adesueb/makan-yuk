package com.makanyuk.beranda;

import com.makanyuk.R;
import com.makanyuk.beranda.onclick.OnClickLogin;
import com.makanyuk.beranda.onclick.OnClickRegister;

import android.os.Bundle;
import android.widget.Button;
import android.app.Activity;

public class ActivityBeranda extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_beranda);
		setMenu();
	}
	
	private void setMenu(){
		Button buttonLogin 		= (Button) findViewById(R.id.buttonLogin);
		buttonLogin.setOnClickListener(new OnClickLogin(this));
		
		Button buttonRegister 	= (Button) findViewById(R.id.buttonRegister);
		buttonRegister.setOnClickListener(new OnClickRegister(this));
	}

	

}
