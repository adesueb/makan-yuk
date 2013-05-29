package com.makanyuk.kategori;

import java.util.List;

import com.makanyuk.R;
import com.makanyuk.handler.HandlerEntities;

import android.os.Bundle;
import android.os.Handler;
import android.app.ListActivity;

public class ActivityDaftarKategori extends ListActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.layout_list);
		
		handler = new Handler();
		
		adapter = new AdapterDaftarKategori(getApplicationContext(), R.layout.layout_list_item, kategoris);
		getListView().setAdapter(adapter);
		
		kategoriService = new KategoriService();
		kategoriService.getAllKategori(new HandlerDaftarKategori(this));
	}

	public void refresh(){
		adapter.notifyDataSetChanged();
	}
	
	
	public void setEntities(List<Kategori> entities) {
		this.kategoris = entities;
		this.adapter.setKategoris(entities);
	}
	
	private AdapterDaftarKategori 	adapter;
	private KategoriService		kategoriService;
	private List<Kategori>		kategoris;
	private Handler handler;

	private final static class HandlerDaftarKategori extends HandlerEntities<Kategori>{

		public HandlerDaftarKategori(ActivityDaftarKategori activityKategori){
			this.activityKategori = activityKategori;
		}
		
		@Override
		public void handlerEntities(final List<Kategori> entities) {
			activityKategori.handler.post(new Runnable(){

				@Override
				public void run(){
					activityKategori.setEntities(entities);
					activityKategori.refresh();	
				}
			});
		}

		private final ActivityDaftarKategori activityKategori;
		
	}
}
