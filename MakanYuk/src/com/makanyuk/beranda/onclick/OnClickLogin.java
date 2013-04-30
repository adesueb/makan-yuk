package com.makanyuk.beranda.onclick;

import com.makanyuk.user.ActivityLogin;

import android.content.Context;
import android.content.Intent;
import android.view.View;


public class OnClickLogin implements View.OnClickListener{

	public OnClickLogin(Context context){
		this.context = context;
	}

	@Override
	public void onClick(View arg0) {
		Intent intent = new Intent(context,ActivityLogin.class);
		context.startActivity(intent);
	}
	
	private final Context context;
}
