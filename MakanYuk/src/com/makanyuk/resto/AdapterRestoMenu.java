package com.makanyuk.resto;

import java.util.List;

import com.makanyuk.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AdapterRestoMenu extends ArrayAdapter<RestoMenu>{

	public AdapterRestoMenu(Context context, int resource,
			List<RestoMenu> objects) {
		super(context, resource, objects);
		this.resource = resource;
	}
	
	@Override
	public int getCount() {
		return restoMenus!=null? restoMenus.size():0;
	}

	@Override
	public RestoMenu getItem(int position) {
		return restoMenus!=null?restoMenus.get(position):null;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = (LayoutInflater) getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View rowView = inflater.inflate(resource, parent, false);
		RestoMenu restoMenu = getItem(position);
		if(restoMenu!=null){
			TextView tvPertama = (TextView) rowView.findViewById(R.id.tvPertama);
			tvPertama.setText(restoMenu.getNama());
			TextView tvKedua = (TextView) rowView.findViewById(R.id.tvKedua);
			tvKedua.setText(restoMenu.getHarga());
		}
		return super.getView(position, convertView, parent);
	}

	private final int resource;
	private List<RestoMenu> restoMenus;

}
