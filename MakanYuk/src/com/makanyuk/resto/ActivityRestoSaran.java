package com.makanyuk.resto;

import java.util.ArrayList;
import java.util.List;

import com.makanyuk.R;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ActivityRestoSaran extends ListActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.layout_resto_saran);
		
		adapter = new AdapterRestoMenu(this, R.layout.layout_list_item, restoMenus);
		getListView().setAdapter(adapter);
		
		Button btLokasi = (Button) findViewById(R.id.btLokasi);
		btLokasi.setOnClickListener(new OnLokasiClick(this));
		
		Button btKirim = (Button) findViewById(R.id.btKirim);
		btKirim.setOnClickListener(new OnKirimClick(this));
		
		Button btTambahMenu = (Button) findViewById(R.id.btTambahMenu);
		btTambahMenu.setOnClickListener(new OnTambahMenuClick(this));
	
	}
	
	public void addRestoMenu(RestoMenu restoMenu){
		if(restoMenus==null){
			restoMenus = new ArrayList<RestoMenu>();
		}
		restoMenus.add(restoMenu);
		adapter.notifyDataSetChanged();
	}
	
	private AdapterRestoMenu adapter;
	private List<RestoMenu> restoMenus;
	
	private static final class OnKirimClick implements  OnClickListener{
		public OnKirimClick(ActivityRestoSaran activityRestoSaran){
			this.activityRestoSaran = activityRestoSaran;
		}
		@Override
		public void onClick(View arg0) {
			
		}
		
		private final ActivityRestoSaran activityRestoSaran;
	}
	
	private static final class OnTambahMenuClick implements  OnClickListener{
		public OnTambahMenuClick(ActivityRestoSaran activityRestoSaran){
			this.activityRestoSaran = activityRestoSaran;
		}
		@Override
		public void onClick(View arg0) {
			
		}
		
		private final ActivityRestoSaran activityRestoSaran;
	}
	
	private static final class OnLokasiClick implements  OnClickListener{
		public OnLokasiClick(ActivityRestoSaran activityRestoSaran){
			this.activityRestoSaran = activityRestoSaran;
		}
		@Override
		public void onClick(View arg0) {
			
		}
		
		private final ActivityRestoSaran activityRestoSaran;
	}
	
}
