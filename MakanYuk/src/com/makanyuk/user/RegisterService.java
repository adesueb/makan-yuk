package com.makanyuk.user;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.makanyuk.config.VariableGeneral;
import com.makanyuk.net.HttpKoneksi;
import com.makanyuk.net.util.Parameter;
import com.makanyuk.net.util.ReceiveCallBack;
import com.makanyuk.net.util.ReceiveContent;
import com.makanyuk.net.util.Status;
import com.makanyuk.parser.MakanYukJsonParser;

public class RegisterService {

	public void sendRegister(final User user, final Handler handler){
		new Thread(new Runnable(){

			@Override
			public void run() {
				String jsonUser = MakanYukJsonParser.getJsonFromUser(user);
				HttpKoneksi httpKoneksi = new HttpKoneksi(VariableGeneral.URL_REGISTER);
				List<Parameter> parameters = new ArrayList<Parameter>();
				Parameter parameter = new Parameter();
				parameter.setKey("json");
				parameter.setValue(jsonUser);
				parameters.add(parameter);
				httpKoneksi.requestPostJson(new ReceiveCallBack() {
					
					@Override
					public void onSuccess(ReceiveContent receiveContent) {
						if(receiveContent.getStatus() == Status.SUCCESS){
							handler.sendEmptyMessage(Status.SUCCESS);
						}
					}
					
					@Override
					public void onFailed(String failed) {
						
					}
				}, parameters);
			}
			
		}).start();
		
	}
	
	public void cekLogin(final User user, final Handler handler){
		new Thread(new Runnable(){

			@Override
			public void run() {
								
				String jsonUser = MakanYukJsonParser.getJsonFromUser(user);
				HttpKoneksi httpKoneksi = new HttpKoneksi(VariableGeneral.URL_CEK_LOGIN);
					
				List<Parameter> parameters = new ArrayList<Parameter>();
				Parameter parameter = new Parameter();
				parameter.setKey("json");
				parameter.setValue(jsonUser);
				parameters.add(parameter);
				httpKoneksi.requestPostJson(new ReceiveCallBack() {
					
					@Override
					public void onSuccess(ReceiveContent receiveContent) {
						String dataReceive = receiveContent.getContentData();
						Bundle bundle = new Bundle();
						bundle.putString("hasil", dataReceive);
						Message message = new Message();
						message.setData(bundle);
						handler.sendMessage(message);
					}
					
					@Override
					public void onFailed(String failed) {
						
					}
				}, parameters);
				
			}
			
		}).start();
	}
}
