package com.makanyuk.gps;

import com.makanyuk.map.entity.Lokasi;

import android.os.Bundle;

public class BundleLokasiMaker {

	public static Bundle makeBundleFromLokasi(Lokasi lokasi){
		Bundle bundle = new Bundle();
		bundle.putDouble("longitude", lokasi.getLongitude());
		bundle.putDouble("latitude", lokasi.getLatitude());
		bundle.putLong("time", lokasi.getTime());
		return bundle;
	}
	
	public static Lokasi makeLokasiFromBundle(Bundle bundle){
		if(bundle.getDouble("latitude")==0){
			return null;
		}
		Lokasi lokasi = new Lokasi();
		lokasi.setLatitude(bundle.getDouble("latitude"));
		lokasi.setLongitude(bundle.getDouble("longitude"));
		lokasi.setTime(bundle.getLong("time"));
		return lokasi;
	}
}
