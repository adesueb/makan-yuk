package com.makanyuk.gps;


import com.makanyuk.map.entity.Lokasi;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

public class TandaLokasiSendiri{

	public TandaLokasiSendiri(Context context, Handler handler){
		mHandler = handler;
		mContext = context;
		mGps = new GpsManager(context, handlerSendiri);
	}
	public void actionTandaiLokasi() {
		if(mGps.isGpsActif()){
			mGps.searchLokasi();
		}else{
			mHandler.post(new Runnable(){
				public void run() {
					Toast.makeText(mContext, "mengambil lokasi gagal,\naktifkan GPS!!", 
							Toast.LENGTH_SHORT).show();
				}	
			});
		}
		
	}

	public Lokasi getLokasi() {
		
		return mLokasi;
	}
	
	Handler handlerSendiri = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch(msg.what){
				case Status.SUCCESS :{
					if(mGps!=null){
						mLokasi = new Lokasi();
						mLokasi.setLatitude(mGps.getLokasi().getLatitude());
						mLokasi.setLongitude(mGps.getLokasi().getLongitude());
						mHandler.sendEmptyMessage(Status.SUCCESS);
						
					}
					break;
				}
			}
		}
		
	};

	private GpsManager 	mGps;
	private Context 	mContext;
	
	private 		Lokasi 	mLokasi;
	private 		Handler mHandler;
	

}
