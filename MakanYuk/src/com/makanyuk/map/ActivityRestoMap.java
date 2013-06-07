package com.makanyuk.map;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;
import com.makanyuk.R;
import com.makanyuk.map.entity.Lokasi;
import com.makanyuk.map.service.PetaOverlayHandlerAction;
import com.makanyuk.resto.OverlayItemsResto;
import com.makanyuk.resto.Resto;

public class ActivityRestoMap extends MapActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_map);
		Intent intent = getIntent();
		String id 		= intent.getStringExtra("id");
		String nama 	= intent.getStringExtra("nama");
		String alamat 	= intent.getStringExtra("alamat");
		
		final double latitude 	= intent.getDoubleExtra("latitude", 0);
		final double longitude	= intent.getDoubleExtra("longitude", 0);

		Lokasi lokasi = new Lokasi();
		lokasi.setLatitude(latitude);
		lokasi.setLongitude(longitude);
		

		
		mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);
		
		mapController 	= mapView.getController();

		mapController.setZoom(12);

		Resto resto = new Resto();
		resto.setId(id);
		resto.setNama(nama);
		resto.setAlamat(alamat);
		resto.setLokasi(lokasi);
		
		refreshOverlay(resto);
		
		setPetaCenter(lokasi);
	}
	
 	public void setPetaCenter(Lokasi lokasi){
  		if(mapController!=null&&lokasi!=null){
  			mapController.setCenter(new GeoPoint((int)(lokasi.getLatitude()*1E6), 
  					(int)(lokasi.getLongitude()*1E6)));
  		}
		
  	}
 	
 		
	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
		
	public void refreshOverlay(Resto resto){
		
		PetaOverlayHandlerAction petaOverlay = 
				new PetaOverlayHandlerAction(getResources().getDrawable(R.drawable.ic_launcher), null);

		OverlayItem overlayItem = OverlayItemsResto.getOverlayItem(resto);
	  	
		if(overlayItem!=null){
			petaOverlay.addOverLay(overlayItem);
			mapView.getOverlays().add(petaOverlay);
		}
		
	}
	
	private MapView mapView;
	
	private MapController mapController;
	
}