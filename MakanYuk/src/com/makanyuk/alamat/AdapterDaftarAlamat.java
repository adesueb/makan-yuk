package com.makanyuk.alamat;


import java.util.List;

import com.makanyuk.R;
import com.makanyuk.resto.ActivityDaftarResto;
import com.makanyuk.resto.Resto;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AdapterDaftarAlamat extends ArrayAdapter<Resto>{

	public AdapterDaftarAlamat(Context context, int resource,
			List<Resto> restos) {
		super(context, resource, restos);
		this.context 	= context;
		this.resource 	= resource;
		this.restos	= restos;
	}

	public void setRestos(List<Resto> restos){
		this.restos = restos;
	}
	
	@Override
	public int getCount() {
		return restos!=null?restos.size():0;
	}
	
	
	@Override
	public Resto getItem(int position) {
		return restos!=null?restos.get(position):null;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		LayoutInflater inflater = (LayoutInflater) getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View rowView = inflater.inflate(resource, parent, false);
		Resto resto = getItem(position);
		if(resto!=null){
			TextView tvPertama = (TextView) rowView.findViewById(R.id.tvPertama);
			tvPertama.setText(resto.getAlamat());

			TextView tvKedua = (TextView) rowView.findViewById(R.id.tvKedua);
			tvKedua.setText(resto.getAlamat());	
			rowView.setOnClickListener(new OnClickKategoriItem(context, resto));
		}
		return rowView;
		
	}
	
	private List<Resto> restos;
	private int resource;
	private Context context;
	
	private static final class OnClickKategoriItem implements View.OnClickListener{
		public OnClickKategoriItem(Context context, Resto resto){
			this.context = context;
			this.resto = resto;
		}
		@Override
		public void onClick(View arg0) {
			Intent intent = new Intent(context,ActivityDaftarResto.class);
			intent.putExtra("group", ActivityDaftarResto.ALAMAT);
			intent.putExtra("alamat", resto.getAlamat());
			context.startActivity(intent);
		}
		
		private final Context context;
		private final Resto resto;
		
	}
}
