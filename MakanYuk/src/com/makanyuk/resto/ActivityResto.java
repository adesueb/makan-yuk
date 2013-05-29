package com.makanyuk.resto;

import com.makanyuk.R;
import com.makanyuk.map.entity.Lokasi;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.app.ListActivity;
import android.content.Intent;

public class ActivityResto extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_resto);
		
		Intent intent = getIntent();
		
		String id 		= intent.getStringExtra("id");
		String nama 	= intent.getStringExtra("nama");
		String alamat 	= intent.getStringExtra("alamat");
		
		long latitude 	= intent.getLongExtra("latitude", 0);
		long longitude 	= intent.getLongExtra("longitude", 0);

		Lokasi lokasi = new Lokasi();
		lokasi.setLatitude(latitude);
		lokasi.setLongitude(longitude);
		
		resto = new Resto();
		resto.setId(id);
		resto.setNama(nama);
		resto.setAlamat(alamat);
		resto.setLokasi(lokasi);
		
		TextView tvNama = (TextView) findViewById(R.id.tvNama);
		tvNama.setText(nama);
		TextView tvAlamat = (TextView) findViewById(R.id.tvAlamat);
		tvAlamat.setText(alamat);
		Button btLokasi = (Button) findViewById(R.id.btLokasi);
		btLokasi.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO 
			}
		});
	}

	private Resto resto;

}
