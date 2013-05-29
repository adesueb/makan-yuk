package com.makanyuk.menuutama.onclick;

import com.makanyuk.kategori.ActivityDaftarKategori;

import android.content.Context;
import android.content.Intent;
import android.view.View;

public class OnClickKategori implements View.OnClickListener{
	
	public OnClickKategori(Context context){
		this.context = context;
	}
	
	@Override
	public void onClick(View arg0) {
		Intent intent = new Intent(context, ActivityDaftarKategori.class);
		context.startActivity(intent);
	}
	
	private final Context context;
}
