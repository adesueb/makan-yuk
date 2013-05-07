package com.makanyuk.kategori;

import java.util.List;

import com.makanyuk.handler.HandlerEntities;
import com.makanyuk.net.HttpKoneksi;
import com.makanyuk.net.util.ReceiveCallBack;
import com.makanyuk.net.util.ReceiveContent;
import com.makanyuk.parser.MakanYukJsonParser;

public class KategoriService {

	public void getAllKategori(final HandlerEntities<Kategori> handler){
		HttpKoneksi http = new HttpKoneksi("");
		http.requestGet(new ReceiveCallBack() {
			
			@Override
			public void onSuccess(ReceiveContent receiveContent) {
				String dataReceive = receiveContent.getContentData();
				List<Kategori> kategories = MakanYukJsonParser.getKategories(dataReceive); 
				handler.sendEntities(kategories);
			}
			
			@Override
			public void onFailed(String failed) {
				
			}
		});
	}
}
