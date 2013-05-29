package com.makanyuk.resto;

import java.util.List;

import com.makanyuk.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AdapterDaftarResto extends ArrayAdapter<Resto>{

	public AdapterDaftarResto(Context context, int resource,
			List<Resto> restos) {
		super(context, resource, restos);
		this.resource 	= resource;
		this.restos		= restos;
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
			TextView tvId = (TextView) rowView.findViewById(R.id.tvId);
			tvId.setText(resto.getId());

			TextView tvNama = (TextView) rowView.findViewById(R.id.tvNama);
			tvNama.setText(resto.getNama());	
		}
		
		return super.getView(position, convertView, parent);
	}



	private List<Resto> restos;
	private int resource;

}
