package com.makanyuk.resto;

import java.util.List;

import com.makanyuk.R;
import com.makanyuk.handler.HandlerEntities;
import com.makanyuk.kategori.Kategori;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class ActivityDaftarResto extends ListActivity{

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_list);
		
		handler = new Handler();
		
		adapter = new AdapterDaftarResto(this, R.layout.layout_list_item, restos);
		getListView().setAdapter(adapter);
		
		Intent intent = getIntent();
		
		restoService = new RestoService();
		
		int group = intent.getIntExtra("group", DEFAULT);
		
		switch(group){
			case DEFAULT:{
				restoService.getAllRestos(new HandlerDaftarResto(this));
				break;
			}case KATEGORI:{
				Kategori kategori = new Kategori();
				kategori.setId(intent.getStringExtra("idKategori"));
				restoService.getRestosFromKategori(new HandlerDaftarResto(this), kategori);
				break;
			}case LOKASI:{
				
				break;
			}
		}
	}
	
	public void refresh(){
		adapter.notifyDataSetChanged();
	}
	
	public void setEntities(List<Resto> entities) {
		this.restos = entities;
		this.adapter.setRestos(entities);
	}
	
	private RestoService restoService;
	private List<Resto> restos;
	private AdapterDaftarResto adapter;
	private Handler handler;
	
	public static final int DEFAULT	= 0;
	public static final int KATEGORI	= 1;
	public static final int LOKASI 	= 2;

	private final static class HandlerDaftarResto extends HandlerEntities<Resto>{

		public HandlerDaftarResto(ActivityDaftarResto activityDaftarResto){
			this.activityDaftarResto = activityDaftarResto;
		}
		@Override
		protected void handlerEntities(final List<Resto> entities) {
			activityDaftarResto.handler.post(new Runnable(){

				@Override
				public void run() {
					activityDaftarResto.setEntities(entities);
					activityDaftarResto.refresh();
				}
				
			});
		}
		
		private final ActivityDaftarResto activityDaftarResto;
		
	}
	
}
