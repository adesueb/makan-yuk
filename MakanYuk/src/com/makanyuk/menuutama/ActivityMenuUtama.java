package com.makanyuk.menuutama;

import com.makanyuk.R;
import com.makanyuk.menuutama.onclick.OnClickKategori;
import com.makanyuk.menuutama.onclick.OnClickLokasi;
import com.makanyuk.menuutama.onclick.OnClickPeta;
import com.makanyuk.menuutama.onclick.OnClickSaranResto;

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
		
		Button buttonLokasi = (Button) findViewById(R.id.buttonLokasi);
		buttonLokasi.setOnClickListener(new OnClickLokasi(this));
		
		Button buttonKategori = (Button) findViewById(R.id.buttonKategori);
		buttonKategori.setOnClickListener(new OnClickKategori(this));
		
		Button buttonPeta = (Button) findViewById(R.id.buttonPeta);
		buttonPeta.setOnClickListener(new OnClickPeta(this));
		
		Button buttonSaran = (Button) findViewById(R.id.buttonSaran);
		buttonSaran.setOnClickListener(new OnClickSaranResto(this));
	}
}
