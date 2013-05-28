package com.makanyuk.kategori;


import java.util.List;

import com.makanyuk.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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
	public Kategori getItem(int position) {
		return kategoris.get(position);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		LayoutInflater inflater = (LayoutInflater) getContext()
				
		        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View rowView = inflater.inflate(resource, parent, false);
		Kategori kategori = getItem(position);
		TextView tvId = (TextView) rowView.findViewById(R.id.tvId);
		tvId.setText(kategori.getId());

		Log.d("kategori id", kategori.getNama());
		TextView tvNama = (TextView) rowView.findViewById(R.id.tvNama);
		tvNama.setText(kategori.getNama());
		return rowView;
		
	}
	
	private List<Kategori> kategoris;
	private int resource;
}
