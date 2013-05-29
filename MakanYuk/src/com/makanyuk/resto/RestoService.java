package com.makanyuk.resto;

import java.util.List;

import com.makanyuk.config.VariableGeneral;
import com.makanyuk.handler.HandlerEntities;
import com.makanyuk.kategori.Kategori;
import com.makanyuk.net.HttpKoneksi;
import com.makanyuk.net.util.ReceiveCallBack;
import com.makanyuk.net.util.ReceiveContent;
import com.makanyuk.parser.MakanYukJsonParser;

public class RestoService {
	public void getRestosFromKategori(HandlerEntities<Resto> handler, Kategori kategori){
		String url = VariableGeneral.URL_GET_RESTOS+"?id_kategori="+kategori.getId();
		getRestos(handler, url);
	}
	
	public void getAllRestos(HandlerEntities<Resto> handler){
		getRestos(handler, VariableGeneral.URL_GET_RESTOS);
	}
	
	private void getRestos(final HandlerEntities<Resto> handler, final String url){
		new Thread(new Runnable(){

			@Override
			public void run() {
				HttpKoneksi http = new HttpKoneksi(url);
				http.requestGet(new ReceiveCallBack() {
					
					@Override
					public void onSuccess(ReceiveContent receiveContent) {
						String dataReceive = receiveContent.getContentData();
						List<Resto> restos = MakanYukJsonParser.getRestosFromJson(dataReceive); 
						handler.sendEntities(restos);
					}
					
					@Override
					public void onFailed(String failed) {
						
					}
				});
			}
			
		}).start();
	}
	
}
