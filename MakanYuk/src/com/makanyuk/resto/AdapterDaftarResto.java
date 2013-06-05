package com.makanyuk.resto;

import java.util.List;

import com.makanyuk.R;
import com.makanyuk.map.entity.Lokasi;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AdapterDaftarResto extends ArrayAdapter<Resto>{

	public AdapterDaftarResto(Context context, int resource,
			List<Resto> restos) {
		super(context, resource, restos);
		this.resource 	= resource;
		this.restos		= restos;
		this.context	= context;
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
			
			rowView.setOnClickListener(new OnClickDaftarResto(context, resto));
			
			TextView tvPertama = (TextView) rowView.findViewById(R.id.tvPertama);
			tvPertama.setText(resto.getId()+"");

			TextView tvKedua = (TextView) rowView.findViewById(R.id.tvKedua);
			tvKedua.setText(resto.getNama());	
			return rowView;
		}
		
		return super.getView(position, convertView, parent);
	}

	private List<Resto> 	restos;
	private final int 	resource;
	private final Context 	context;
	
	private static final class OnClickDaftarResto implements OnClickListener{

		public OnClickDaftarResto(Context context, Resto resto){
			this.context 	= context;
			this.resto 		= resto;
		}
		
		@Override
		public void onClick(View arg0) {
			
			Intent intent = new Intent(context, ActivityResto.class);
			intent.putExtra("id", resto.getId());
			intent.putExtra("nama", resto.getNama());
			intent.putExtra("alamat", resto.getAlamat());
			
			Lokasi lokasi = resto.getLokasi();
			intent.putExtra("latitude", lokasi.getLatitude());
			intent.putExtra("longitude", lokasi.getLongitude());
			
			context.startActivity(intent);
			
		}
		
		private final Context 	context;
		private final Resto 	resto;
	}

}
