package com.makanyuk.resto;

import java.util.List;

import com.makanyuk.R;
import com.makanyuk.handler.HandlerEntities;
import com.makanyuk.map.entity.Lokasi;

import android.os.Bundle;
import android.os.Handler;
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
		
		handler = new Handler();
		
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
				
		adapter = new AdapterRestoMenu(this, R.layout.layout_list_item, resto.getRestoMenus());
	
		getListView().setAdapter(adapter);
		
		restoService = new RestoService();
		restoService.getRestoMenusFromResto(new HandlerResto(this), resto);
		
	}
	
	public void setRestoMenus(List<RestoMenu> restoMenus){
		resto.setRestoMenus(restoMenus);
	}
	
	public void refresh(){
		adapter.notifyDataSetChanged();
	}
	

	private Resto resto;
	private Handler handler;
	private AdapterRestoMenu adapter;
	
	private RestoService restoService;
	
	private final static class HandlerResto extends HandlerEntities<RestoMenu>{

		public HandlerResto(ActivityResto activityResto){
			this.activityResto = activityResto;
		}
		@Override
		protected void handlerEntities(final List<RestoMenu> entities) {
			activityResto.handler.post(new Runnable(){

				@Override
				public void run() {
					activityResto.setRestoMenus(entities);
					activityResto.refresh();
				}
				
			});
		}
		
		private final ActivityResto activityResto;
		
	}

}
