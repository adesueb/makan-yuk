package com.makanyuk.resto;

import java.util.List;

import android.util.Log;

import com.makanyuk.config.VariableGeneral;
import com.makanyuk.handler.HandlerEntities;
import com.makanyuk.kategori.Kategori;
import com.makanyuk.net.HttpKoneksi;
import com.makanyuk.net.util.ReceiveCallBack;
import com.makanyuk.net.util.ReceiveContent;
import com.makanyuk.parser.MakanYukJsonParser;

public class RestoService {
	public void getRestosFromKategori(HandlerEntities<Resto> handler, Kategori kategori){
		Log.d("resto service", kategori.getId());
		String url = VariableGeneral.URL_GET_RESTOS+"?group=kategori&id="+kategori.getId();
		getRestos(handler, url);
	}
	
	public void getAllRestos(HandlerEntities<Resto> handler){
		getRestos(handler, VariableGeneral.URL_GET_RESTOS+"?group=all");
	}
	
	public void getRestosFromAlamat(HandlerEntities<Resto> handler, String alamat){
		String url = VariableGeneral.URL_GET_RESTOS+"?group=alamat&id='"+alamat+"'";
		getRestos(handler,url);
	}
	
	public void getRestosByAlamat(HandlerEntities<Resto> handler){
		String url = VariableGeneral.URL_GET_RESTOS+"?group=alamat";
		getRestos(handler,url);
	}
	
	public void getRestoMenusFromResto(final HandlerEntities<RestoMenu> handler, Resto resto){
		final String url = VariableGeneral.URL_GET_RESTO_MENUS+"?id="+resto.getId();
		
		new Thread(new Runnable(){

			@Override
			public void run() {
				HttpKoneksi http = new HttpKoneksi(url);
				http.requestGet(new ReceiveCallBack() {
					
					@Override
					public void onSuccess(ReceiveContent receiveContent) {
						String dataReceive = receiveContent.getContentData();
						List<RestoMenu> restos = MakanYukJsonParser.getRestoMenusFromJson(dataReceive); 
						handler.sendEntities(restos);
					}
					
					@Override
					public void onFailed(String failed) {
						
					}
				});
			}
			
		}).start();
	}
	
	public void addResto(final Resto resto){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				String json = MakanYukJsonParser.getJsonFromResto(resto);
				
				HttpKoneksi http = new HttpKoneksi(VariableGeneral.URL_ADD_RESTO);
				http.requestPost(new ReceiveCallBack() {
					
					@Override
					public void onSuccess(ReceiveContent receiveContent) {
						
					}
					
					@Override
					public void onFailed(String failed) {
						
					}
				}, json);
			}
		}).start();
		
		
	}
	
	private void getRestos(final HandlerEntities<Resto> handler, final String url){
		new Thread(new Runnable(){

			@Override
			public void run() {
				HttpKoneksi http = new HttpKoneksi(url);
				http.requestGet(new ReceiveCallBack() {
					
					@Override
					public void onSuccess(ReceiveContent receiveContent) {
						StringBuilder dataReceive = receiveContent.getLargeContentData();
						Log.d("RestoService", dataReceive.toString());
						List<Resto> restos = MakanYukJsonParser.getRestosFromLargeJson(dataReceive); 
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
