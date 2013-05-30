package com.makanyuk.menuutama.onclick;

import com.makanyuk.resto.ActivityRestoSaran;

import android.content.Context;
import android.content.Intent;
import android.view.View;

public class OnClickRestoSaran implements View.OnClickListener{
	
	public OnClickRestoSaran(Context context){
		this.context = context;
	}
	
	@Override
	public void onClick(View arg0) {
		Intent intent = new Intent(context, ActivityRestoSaran.class);
		context.startActivity(intent);
	}
	private final Context context;
}