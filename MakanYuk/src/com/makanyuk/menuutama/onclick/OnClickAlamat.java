package com.makanyuk.menuutama.onclick;

import com.makanyuk.alamat.ActivityDaftarAlamat;

import android.content.Context;
import android.content.Intent;
import android.view.View;

public class OnClickAlamat implements View.OnClickListener{
	
	public OnClickAlamat(Context context){
		this.context = context;
	}
	
	@Override
	public void onClick(View arg0) {
		Intent intent =new Intent(context, ActivityDaftarAlamat.class);
		context.startActivity(intent);
	}
	private final Context context;
}