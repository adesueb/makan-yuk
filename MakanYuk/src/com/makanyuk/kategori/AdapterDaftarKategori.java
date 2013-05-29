package com.makanyuk.kategori;


import java.util.List;

import com.makanyuk.R;
import com.makanyuk.resto.ActivityDaftarResto;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AdapterDaftarKategori extends ArrayAdapter<Kategori>{

	public AdapterDaftarKategori(Context context, int resource,
			List<Kategori> kategoris) {
		super(context, resource, kategoris);
		this.context 	= context;
		this.resource 	= resource;
		this.kategoris	= kategoris;
	}

	public void setKategoris(List<Kategori> kategoris){
		this.kategoris = kategoris;
	}
	
	@Override
	public int getCount() {
		return kategoris!=null?kategoris.size():0;
	}
	
	
	@Override
	public Kategori getItem(int position) {
		return kategoris!=null?kategoris.get(position):null;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		LayoutInflater inflater = (LayoutInflater) getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View rowView = inflater.inflate(resource, parent, false);
		Kategori kategori = getItem(position);
		if(kategori!=null){
			TextView tvId = (TextView) rowView.findViewById(R.id.tvId);
			tvId.setText(kategori.getId());

			TextView tvNama = (TextView) rowView.findViewById(R.id.tvNama);
			tvNama.setText(kategori.getNama());	
			rowView.setOnClickListener(new OnClickKategoriItem(context, kategori));
		}
		return rowView;
		
	}
	
	private List<Kategori> kategoris;
	private int resource;
	private Context context;
	
	private static final class OnClickKategoriItem implements View.OnClickListener{
		public OnClickKategoriItem(Context context, Kategori kategori){
			this.context = context;
			this.kategori = kategori;
		}
		@Override
		public void onClick(View arg0) {
			Intent intent = new Intent(context,ActivityDaftarResto.class);
			intent.putExtra("group", ActivityDaftarResto.KATEGORI);
			intent.putExtra("idKategori", kategori.getId());
			context.startActivity(intent);
		}
		
		private final Context context;
		private final Kategori kategori;
		
	}
}
