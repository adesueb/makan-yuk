package com.makanyuk.menuutama.onclick;

import com.makanyuk.map.ActivityMap;

import android.content.Context;
import android.content.Intent;
import android.view.View;

public class OnClickPeta implements View.OnClickListener{
	
	public OnClickPeta(Context context){
		this.context = context;
	}
	
	@Override
	public void onClick(View arg0) {

		Intent intent = new Intent(context, ActivityMap.class);
		context.startActivity(intent);
	}
	
	private final Context context;
}
