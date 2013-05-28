package com.makanyuk.kategori;

import java.util.List;

import android.util.Log;

import com.makanyuk.handler.HandlerEntities;
import com.makanyuk.net.HttpKoneksi;
import com.makanyuk.net.util.ReceiveCallBack;
import com.makanyuk.net.util.ReceiveContent;
import com.makanyuk.parser.MakanYukJsonParser;

public class KategoriService implements Runnable{

	private HandlerEntities<Kategori> handler;
	
	public void getAllKategori(HandlerEntities<Kategori> handler){
		this.handler = handler;
		new Thread(this).start();
	}

	@Override
	public void run() {

		Log.d("---dapet---", "jalankan internet");
		HttpKoneksi http = new HttpKoneksi("http://192.168.43.235/makan_yuk/get_kategori.php");
		http.requestGet(new ReceiveCallBack() {
			
			@Override
			public void onSuccess(ReceiveContent receiveContent) {
				String dataReceive = receiveContent.getContentData();
				Log.d("---dapet---", dataReceive);
				List<Kategori> kategories = MakanYukJsonParser.getKategories(dataReceive); 
				handler.sendEntities(kategories);
			}
			
			@Override
			public void onFailed(String failed) {
				
			}
		});
	}
	
	
}
