package com.makanyuk.map.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.android.maps.OverlayItem;
import android.content.Context;
import android.os.Handler;

public class FactoryPetaOverlay {

	public FactoryPetaOverlay(Context context, Handler handler, Class<?> activityClass){
		this.context 		= context;
		this.handler 		= handler;
		this.activityClass	= activityClass;
	}
	
	private APetaOverlay createPetaOverlay(int id, int idGambar){
		if(handler != null){
			return new PetaOverlayGetLocation(context.getResources().getDrawable(idGambar), handler);
		}else if(activityClass!=null){
			return new PetaOverlayHandlerAction(context.getResources().getDrawable(idGambar), handler);
		}else{
			return new PetaOverlay(id,context.getResources().getDrawable(idGambar), context);
		}
	}
	
	public void makeOverlay(int idOverlay, int idGambar, List<OverlayItem> overlayItems){
		
		APetaOverlay petaOverlay	= createPetaOverlay(idOverlay, idGambar);
		
		for(OverlayItem overlayItem:overlayItems){
			petaOverlay.addOverLay(overlayItem);	
		}
		
		petaOverlays.put(idOverlay, petaOverlay);
	}
	
	public APetaOverlay getOverlay(int idOverlay){
		return petaOverlays.get(idOverlay);
	}
	
	public boolean anyOverlay(int idOverlay){
		return petaOverlays.get(idOverlay)!=null;
	}
	
	private final Context context;
	private final Handler handler;
	private final Class<?> activityClass;
	
	private Map<Integer,APetaOverlay> petaOverlays = 
			new HashMap<Integer, APetaOverlay>();
}
