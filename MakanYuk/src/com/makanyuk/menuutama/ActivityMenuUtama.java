package com.makanyuk.menuutama;

import com.makanyuk.R;
import com.makanyuk.menuutama.onclick.OnClickKategori;
import com.makanyuk.menuutama.onclick.OnClickAlamat;
import com.makanyuk.menuutama.onclick.OnClickPeta;

import android.os.Bundle;
import android.widget.Button;
import android.app.Activity;

public class ActivityMenuUtama extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_menu_utama);
		setMenu();
	}
	
	private void setMenu(){
		
		Button buttonAlamat = (Button) findViewById(R.id.buttonAlamat);
		buttonAlamat.setOnClickListener(new OnClickAlamat(this));
		
		Button buttonKategori = (Button) findViewById(R.id.buttonKategori);
		buttonKategori.setOnClickListener(new OnClickKategori(this));
		
		Button buttonPeta = (Button) findViewById(R.id.buttonPeta);
		buttonPeta.setOnClickListener(new OnClickPeta(this));
		
	}
}
