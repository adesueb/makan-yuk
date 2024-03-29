package com.makanyuk.resto;

import java.util.ArrayList;
import java.util.List;

import com.google.android.maps.OverlayItem;
import com.makanyuk.map.service.OverlayItemMaker;

public class OverlayItemsResto {

	public static List<OverlayItem> getOverlayItems(List<Resto> restos){
		if(restos==null){
			return null;
		}
		List<OverlayItem> overlayItems = new ArrayList<OverlayItem>();
		for(Resto resto:restos){
			OverlayItemMaker overlayItemMaker = new OverlayItemMaker();
			overlayItemMaker.setLocation(resto.getLokasi());
			overlayItemMaker.setTitle(resto.getId());
			overlayItemMaker.setDescription(resto.getNama());
			OverlayItem overlay = overlayItemMaker.makeOverlayItemFromResto();
			if(overlay!=null){
				overlayItems.add(overlay);				
			}
		}
		return overlayItems;
	}
	
	public  static OverlayItem getOverlayItem(Resto resto){
		if(resto==null){
			return null;
		}
		OverlayItemMaker overlayItemMaker = new OverlayItemMaker();
		overlayItemMaker.setLocation(resto.getLokasi());
		overlayItemMaker.setTitle(resto.getId());
		overlayItemMaker.setDescription(resto.getNama());
		OverlayItem overlay = overlayItemMaker.makeOverlayItemFromResto();
			
		return overlay;
	}
}
