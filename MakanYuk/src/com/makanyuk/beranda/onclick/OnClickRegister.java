package com.makanyuk.beranda.onclick;


import com.makanyuk.user.ActivityRegister;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

public class OnClickRegister implements OnClickListener{

	public OnClickRegister(Context context){
		this.context = context;
	}
	
	@Override
	public void onClick(View arg0) {
		Intent intent = new Intent(context, ActivityRegister.class);
		context.startActivity(intent);
	}


	private final Context context;
}
