package com.makanyuk.map.service;

import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public abstract class APetaOverlay extends ItemizedOverlay<OverlayItem>{

	public APetaOverlay(Drawable defaultMarker) {
		super(defaultMarker);
	}
	
	public abstract void addOverLay(OverlayItem overLay);

}
