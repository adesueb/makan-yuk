package com.makanyuk.map.service;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.OverlayItem;
import com.makanyuk.map.entity.Lokasi;

public class OverlayItemMaker {

	public OverlayItem makeOverlayItemFromResto(){
		
		if(geoPoint==null){
			return null;
		}else{
			OverlayItem overlayItem = 
					new OverlayItem
						(geoPoint, title, description);
			return overlayItem;	
		}
		
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public void setLocation(Lokasi location){
		if(location!=null){
			this.geoPoint = 
					new GeoPoint((int)
							(location.getLatitude()*1E6),(int) (location.getLongitude()*1E6));	
		}else{
			this.geoPoint = 
					new GeoPoint((int)
							(0),(int) (0));
		}
		
	}
	
	private GeoPoint geoPoint;
	private String title = "";
	private String description = "";
}
