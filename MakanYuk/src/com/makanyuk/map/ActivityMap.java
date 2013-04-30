package com.makanyuk.map;

import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;
import com.makanyuk.R;
import com.makanyuk.gps.GpsManager;
import com.makanyuk.gps.Status;
import com.makanyuk.map.entity.Lokasi;
import com.makanyuk.map.service.PetaOverlayToActivity;
import com.makanyuk.resto.ActivityResto;
import com.makanyuk.resto.OverlayItemsResto;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class ActivityMap extends MapActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_map);MapView mapView = (MapView) findViewById(R.id.mapview);
		
		PetaOverlayToActivity petaOverlay = 
				new PetaOverlayToActivity(ActivityResto.class, getResources().getDrawable(R.drawable.ic_launcher), this);
		
		List<OverlayItem> overlayItems = OverlayItemsResto.getOverlayItems(null);
	    
		if(overlayItems!=null){
			for(OverlayItem overlay:overlayItems){
				petaOverlay.addOverLay(overlay);
			}	

			mapView.getOverlays().add(petaOverlay);
		}
		
		mapController 	= mapView.getController();

		mapController.setZoom(4);

		GpsManager gpsManager = new GpsManager(this, new PetaHandlerLocationCenter(this));

		Lokasi lokasi 	= gpsManager.getLastLokasi();
		
		if(lokasi!=null){
			setPetaCenter(lokasi);
		}else{
			gpsManager.searchLokasi();
		}
	}
	
 	public void setPetaCenter(Lokasi lokasi){
  		if(mapController!=null&&lokasi!=null){
  			mapController.setCenter(new GeoPoint((int)(lokasi.getLatitude()*1E6), 
  					(int)(lokasi.getLongitude()*1E6)));
  		}
		
  	}
	
	private MapController mapController;
	
	private static final class PetaHandlerLocationCenter extends Handler{
  		public PetaHandlerLocationCenter(ActivityMap peta){
  			mPeta = peta;
  		}
  		@Override
		public void handleMessage(Message msg) {
			if(msg.what==Status.SUCCESS){
				Bundle bundle = msg.getData();
			
				Lokasi lokasi = new Lokasi();
				lokasi.setLatitude(bundle.getDouble("latitude"));
				lokasi.setLongitude(bundle.getDouble("longitude"));
				mPeta.setPetaCenter(lokasi);
			}
  			
			
		}
  		private ActivityMap mPeta;
  	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
}
