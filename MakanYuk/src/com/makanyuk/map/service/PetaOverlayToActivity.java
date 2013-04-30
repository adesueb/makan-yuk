package com.makanyuk.map.service;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;

import com.google.android.maps.OverlayItem;

public class PetaOverlayToActivity extends APetaOverlay{
	public PetaOverlayToActivity(Class<?> activityClass, Drawable defaultMarker, Context context) {
		super(boundCenterBottom(defaultMarker));
		this.context 		= context;
		this.activityClass 	= activityClass;
	}

	@Override
	protected OverlayItem createItem(int i) {
		return mOverlays.get(i);
	}
	
	@Override
	public void addOverLay(OverlayItem overLay){
		mOverlays.add(overLay);
		populate();
	}
	
	@Override
	protected boolean onTap(int index) {
		OverlayItem 		item 	= mOverlays.get(index);
		Intent intent = new Intent(context,activityClass);
		intent.putExtra("id", item.getTitle());
		context.startActivity(intent);
	  return true;
	}

	@Override
	public int size() {
		return mOverlays.size();
	}
		
	private Class<?> activityClass;
	private Context context;
	private List<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
}
