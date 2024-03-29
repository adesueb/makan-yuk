package com.makanyuk.map;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
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
import com.makanyuk.map.service.PetaOverlayHandlerAction;
import com.makanyuk.resto.ActivityResto;
import com.makanyuk.resto.OverlayItemsResto;
import com.makanyuk.resto.Resto;
import com.makanyuk.resto.RestoService;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;

public class ActivityDaftarRestoMap extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_map);
		 
		googleMap =  ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapView)).getMap();
		
	  if (googleMap!=null){
      Marker hamburg = googleMap.addMarker(new MarkerOptions().position(HAMBURG)
          .title("Hamburg"));
      MarkerOptions markerOption = new MarkerOptions()
	      .position(KIEL)
	      .title("Kiel")
	      .snippet("Kiel is cool")
	      .icon(BitmapDescriptorFactory
	          .fromResource(R.drawable.ic_launcher));
      Marker kiel = googleMap.addMarker(markerOption);
      googleMap.setOnMarkerClickListener(new OnMarkerClickListener() {
				
				@Override
				public boolean onMarkerClick(Marker marker) {
					return false;
				}
			});
      
    }
		
		mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);
		
		handler = new Handler();
		
		mapController 	= mapView.getController();

		mapController.setZoom(12);

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
 	
 	public void openResto(String id){
 		Resto resto = mapResto.get(id);
 		if(resto!=null){
 			Intent intent = new Intent(this, ActivityResto.class);
			intent.putExtra("id", resto.getId());
			intent.putExtra("nama", resto.getNama());
			intent.putExtra("alamat", resto.getAlamat());
			
			Lokasi lokasi = resto.getLokasi();
			intent.putExtra("latitude", lokasi.getLatitude());
			intent.putExtra("longitude", lokasi.getLongitude());
			
			startActivity(intent);
 		}
 	}
	
	private MapController mapController;
	
	private static final class PetaHandlerLocationCenter extends Handler{
  		public PetaHandlerLocationCenter(ActivityDaftarRestoMap peta){
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
  		private ActivityDaftarRestoMap mPeta;
  }

		
	public void refreshOverlay(List<Resto> entities){
		
		mapResto = new HashMap<String, Resto>();
		
		for(Resto resto:entities){
			mapResto.put(resto.getId(), resto);
		}
		
		PetaOverlayHandlerAction petaOverlay = 
				new PetaOverlayHandlerAction(getResources().getDrawable(R.drawable.ic_launcher), new HandlerOnTapMap(this));

		List<OverlayItem> overlayItems = OverlayItemsResto.getOverlayItems(entities);
	  	
		if(overlayItems!=null){
			for(OverlayItem overlay:overlayItems){
				petaOverlay.addOverLay(overlay);
			}	

			mapView.getOverlays().add(petaOverlay);
		}
		
	}
	
	private Handler handler;
	private RestoService restoService;
	private Map<String,Resto> mapResto;
	private MapView mapView;
	private GoogleMap googleMap;

  static final LatLng HAMBURG = new LatLng(53.558, 9.927);
  static final LatLng KIEL = new LatLng(53.551, 9.993);
	
	private final static class HandlerMapDaftarResto extends HandlerEntities<Resto>{

		public HandlerMapDaftarResto(ActivityDaftarRestoMap activityMap){
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
		
		private final ActivityDaftarRestoMap activityMap;
		
	}
	
	private final static class HandlerOnTapMap extends Handler{

		public HandlerOnTapMap(ActivityDaftarRestoMap activityMap){
			this.activityMap = activityMap;
		}
		@Override
		public void handleMessage(Message msg) {
			String id = msg.getData().getString("id");
			activityMap.openResto(id);
		}
		
		private ActivityDaftarRestoMap activityMap;
		
	}
}
