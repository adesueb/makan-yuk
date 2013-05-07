package com.makanyuk.kategori;

import java.util.List;

import com.makanyuk.R;
import com.makanyuk.handler.HandlerEntities;

import android.os.Bundle;
import android.app.ListActivity;

public class ActivityKategori extends ListActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.layout_list);
//		
//		gudangKategori = new GudangKategori();
//		
//		gudangKategori.getAllKategori(new HandlerKategori(this));
	}
	
	public void setAdapter(){
		adapter = new AdapterKategori(getApplicationContext(), 2, kategoris);
		getListView().setAdapter(adapter);
		adapter.notifyDataSetChanged();
	}
	
	
	public void setEntities(List<Kategori> entities) {
		this.kategoris = entities;
	}
	
	private AdapterKategori 	adapter;
	private KategoriService		gudangKategori;
	private List<Kategori>		kategoris;

	private final static class HandlerKategori extends HandlerEntities<Kategori>{

		public HandlerKategori(ActivityKategori activityKategori){
			this.activityKategori = activityKategori;
		}
		
		@Override
		public void handlerEntities(List<Kategori> entities) {
			activityKategori.setEntities(entities);
			activityKategori.setAdapter();
		}

		private final ActivityKategori activityKategori;
		
	}
}
