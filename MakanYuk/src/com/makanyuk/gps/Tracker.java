package com.makanyuk.gps;


import com.makanyuk.map.entity.Lokasi;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class Tracker implements LocationListener{
	
	
	public Tracker(Context context, Lokasi lokasi, Handler handler){
		mContext	= context;
		mLokasi		= lokasi;
		mHandler	= handler;
	}
	
	public Tracker(Context context, Handler handler){
		mContext	= context;
		mLokasi		= new Lokasi();
		mHandler	= handler;
	}
	
	
	public void startSinggleTrack(){
		mlocManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
		
		 if (!mlocManager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
			 return ;
		 }
		LocationProvider high =
			    mlocManager.getProvider(mlocManager.getBestProvider(createFineCriteria(), true));
		
	//	mlocManager.requestSingleUpdate(high.getName(), this, mHandler.getLooper());
		
	}
	

	
	public void startTracking(){
		mlocManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
		if (!mlocManager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
			 
				
			return ;
		}
		LocationProvider high =
			    mlocManager.getProvider(mlocManager.getBestProvider(createFineCriteria(), true));
			mlocManager.requestLocationUpdates(high.getName(), MILISECONDS_TRACKING, METERS_TRACKING,
				this);
		startMonitoring = true;
	}
	
	public void stopTracking(){
		mlocManager.removeUpdates(this);
		startMonitoring = false;
	}
	
	public Lokasi getLokasi(){
		return mLokasi;
	}
	
	public void onLocationChanged(Location loc) {
		mLokasi.setLatitude(loc.getLatitude());
		mLokasi.setLongitude(loc.getLongitude());
		mLokasi.setTime(loc.getTime());

		if(mHandler != null){
			Message message = new Message();
			message.what = Status.SUCCESS;
			message.setData(BundleLokasiMaker.makeBundleFromLokasi(mLokasi));
			mHandler.sendMessage(message);
		}	
	}

	public void onProviderDisabled(String provider) {
		stopTracking();
	}

	public void onProviderEnabled(String provider) {
		startTracking();
	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
	}
	
	private Criteria createFineCriteria() {

		  Criteria c = new Criteria();
		  c.setAccuracy(Criteria.ACCURACY_FINE);
		  c.setAltitudeRequired(false);
		  c.setBearingRequired(false);
		  c.setSpeedRequired(false);
		  c.setCostAllowed(true);
		  c.setPowerRequirement(Criteria.POWER_HIGH);
		  return c;

	}
	
	 
	
	public boolean isStartMonitoring() {
		return startMonitoring;
	}



	private final Lokasi 						mLokasi;	
	private final Context 						mContext;
	private final Handler 						mHandler;
	private LocationManager 					mlocManager;
	private boolean								startMonitoring = false;
	private final static int MILISECONDS_TRACKING 	= 40000;
	private final static int METERS_TRACKING		= 0;
	
}
