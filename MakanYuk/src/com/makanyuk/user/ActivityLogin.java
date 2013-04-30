package com.makanyuk.user;

import com.makanyuk.R;
import com.makanyuk.menuutama.ActivityMenuUtama;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;

public class ActivityLogin extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_login);
		setButtonOk();
	}

	private void setButtonOk(){
		Button buttonOk = (Button) findViewById(R.id.buttonOk);
		buttonOk.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = 
						new Intent(ActivityLogin.this, ActivityMenuUtama.class);
				startActivity(intent);
			}
		});
	}

}
