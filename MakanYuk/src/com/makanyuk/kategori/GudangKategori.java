package com.makanyuk.kategori;

import com.makanyuk.handler.HandlerEntities;
import com.makanyuk.net.HttpKoneksi;
import com.makanyuk.net.util.ReceiveCallBack;
import com.makanyuk.net.util.ReceiveContent;

public class GudangKategori {

	public void getAllKategori(HandlerEntities<Kategori> handler){
		HttpKoneksi http = new HttpKoneksi("");
		http.requestGet(new ReceiveCallBack() {
			
			@Override
			public void onSuccess(ReceiveContent receiveContent) {
				
			}
			
			@Override
			public void onFailed(String failed) {
				
			}
		});
	}
}
