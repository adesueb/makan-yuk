package com.makanyuk.map.service;

import java.util.ArrayList;
import java.util.List;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.google.android.maps.OverlayItem;

public class PetaOverlayHandlerAction extends APetaOverlay{
	public PetaOverlayHandlerAction(Drawable defaultMarker, Handler handlerAction) {
		super(boundCenterBottom(defaultMarker));
		this.handler = handlerAction;
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
		if(handler!=null){
			Message message = new Message();
			Bundle bundle = new Bundle();
			OverlayItem item = mOverlays.get(index);
			bundle.putString("id", item.getTitle());
			message.setData(bundle);
			handler.sendMessage(message);	
		}
		return true;
	}

	@Override
	public int size() {
		return mOverlays.size();
	}
	private Handler handler;
	private List<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
}
