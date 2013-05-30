package com.makanyuk.resto;

import java.util.ArrayList;
import java.util.List;

import com.makanyuk.R;

import android.app.ListActivity;
import android.os.Bundle;

public class ActivityRestoSaran extends ListActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_resto_saran);
		adapter = new AdapterRestoMenu(this, R.layout.layout_list_item, restoMenus);
		getListView().setAdapter(adapter);
	}
	
	public void addRestoMenu(RestoMenu restoMenu){
		if(restoMenus==null){
			restoMenus = new ArrayList<RestoMenu>();
		}
		restoMenus.add(restoMenu);
	}
	
	private AdapterRestoMenu adapter;
	private List<RestoMenu> restoMenus;
	
	
}
