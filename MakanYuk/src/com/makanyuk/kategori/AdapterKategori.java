package com.makanyuk.kategori;


import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class AdapterKategori extends ArrayAdapter<Kategori>{

	public AdapterKategori(Context context, int resource,
			List<Kategori> kategoris) {
		super(context, resource, kategoris);
		this.resource 	= resource;
		this.kategoris	= kategoris;
	}

	@Override
	public int getCount() {
		return kategoris!=null?kategoris.size():0;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		LayoutInflater inflater = (LayoutInflater) getContext()
				
		        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View rowView = inflater.inflate(resource, parent, false);
		
		return rowView;
		
	}
	
	private List<Kategori> kategoris;
	private int resource;
}
