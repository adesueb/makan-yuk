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
import com.makanyuk.handler.HandlerEntities;
import com.makanyuk.map.entity.Lokasi;
import com.makanyuk.map.service.PetaOverlayToActivity;
import com.makanyuk.resto.ActivityResto;
import com.makanyuk.resto.OverlayItemsResto;
import com.makanyuk.resto.Resto;
import com.makanyuk.resto.RestoService;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class ActivityMap extends MapActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_map);
		
		mapView = (MapView) findViewById(R.id.mapview);
		
		handler = new Handler();
		
		mapController 	= mapView.getController();

		mapController.setZoom(4);

		GpsManager gpsManager = new GpsManager(this, new PetaHandlerLocationCenter(this));

		Lokasi lokasi 	= gpsManager.getLastLokasi();
		
		if(lokasi!=null){
			setPetaCenter(lokasi);
		}else{
			gpsManager.searchLokasi();
		}
		
		restoService = new RestoService();
		restoService.getAllRestos(new HandlerMapDaftarResto(this));
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
		return false;
	}
		
	public void refreshOverlay(List<Resto> entities){
		PetaOverlayToActivity petaOverlay = 
				new PetaOverlayToActivity(ActivityResto.class, getResources().getDrawable(R.drawable.ic_launcher), this);
		
		List<OverlayItem> overlayItems = OverlayItemsResto.getOverlayItems(restos);
	    
		if(overlayItems!=null){
			for(OverlayItem overlay:overlayItems){
				petaOverlay.addOverLay(overlay);
			}	

			mapView.getOverlays().add(petaOverlay);
		}
		
	}
	
	private Handler handler;
	private RestoService restoService;
	private List<Resto> restos;
	private MapView mapView;
	
	private final static class HandlerMapDaftarResto extends HandlerEntities<Resto>{

		public HandlerMapDaftarResto(ActivityMap activityMap){
			this.activityMap = activityMap;
		}
		@Override
		protected void handlerEntities(final List<Resto> entities) {
			activityMap.handler.post(new Runnable(){

				@Override
				public void run() {
					activityMap.refreshOverlay(entities);
				}
				
			});
		}
		
		private final ActivityMap activityMap;
		
	}
}
