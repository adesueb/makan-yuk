package com.makanyuk.alamat;

import java.util.List;

import com.makanyuk.R;
import com.makanyuk.handler.HandlerEntities;
import com.makanyuk.resto.Resto;
import com.makanyuk.resto.RestoService;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.Handler;

public class ActivityDaftarAlamat extends ListActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.layout_list);
		
		handler = new Handler();
		
		adapter = new AdapterDaftarAlamat(getApplicationContext(), R.layout.layout_list_item, restos);
		getListView().setAdapter(adapter);
		restoService = new RestoService();
		restoService.getAllRestos(new HandlerDaftarAlamat(this));
	}

	public void refresh(){
		adapter.notifyDataSetChanged();
	}
	
	
	public void setEntities(List<Resto> entities) {
		this.restos = entities;
		this.adapter.setRestos(entities);
	}
	
	private AdapterDaftarAlamat 	adapter;
	private RestoService			restoService;
	private List<Resto>				restos;
	private Handler handler;

	private final static class HandlerDaftarAlamat extends HandlerEntities<Resto>{

		public HandlerDaftarAlamat(ActivityDaftarAlamat activityAlamat){
			this.activityDaftarAlamat = activityAlamat;
		}
		
		@Override
		public void handlerEntities(final List<Resto> entities) {
			activityDaftarAlamat.handler.post(new Runnable(){

				@Override
				public void run(){
					activityDaftarAlamat.setEntities(entities);
					activityDaftarAlamat.refresh();	
				}
			});
		}

		private final ActivityDaftarAlamat activityDaftarAlamat;
		
	}

	
}
