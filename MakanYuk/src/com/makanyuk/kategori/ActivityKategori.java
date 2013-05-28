package com.makanyuk.kategori;

import java.util.List;

import com.makanyuk.R;
import com.makanyuk.handler.HandlerEntities;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.app.ListActivity;

public class ActivityKategori extends ListActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.layout_list);
		kategoriService = new KategoriService();
		kategoriService.getAllKategori(new HandlerKategori(this));
		handler = new Handler();
	}
	
	public void setAdapter(){
		adapter = new AdapterKategori(getApplicationContext(), R.layout.layout_list_item_kategori, kategoris);
		getListView().setAdapter(adapter);
		adapter.notifyDataSetChanged();
	}
	
	
	public void setEntities(List<Kategori> entities) {
		Log.d("banyak kategori", entities.size()+"");
		this.kategoris = entities;
	}
	
	private AdapterKategori 	adapter;
	private KategoriService		kategoriService;
	private List<Kategori>		kategoris;
	private Handler handler;

	private final static class HandlerKategori extends HandlerEntities<Kategori>{

		public HandlerKategori(ActivityKategori activityKategori){
			this.activityKategori = activityKategori;
		}
		
		@Override
		public void handlerEntities(final List<Kategori> entities) {
			activityKategori.handler.post(new Runnable(){

				@Override
				public void run(){
					activityKategori.setEntities(entities);
					activityKategori.setAdapter();	
				}
			});
			
		}

		private final ActivityKategori activityKategori;
		
	}
}
