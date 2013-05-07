package com.makanyuk.user;

import com.makanyuk.net.HttpKoneksi;
import com.makanyuk.net.util.ReceiveCallBack;
import com.makanyuk.net.util.ReceiveContent;
import com.makanyuk.net.util.Status;
import com.makanyuk.parser.MakanYukJsonParser;

public class RegisterService {

	public void sendRegister(User user){
		String jsonUser = MakanYukJsonParser.getJsonFromUser(user);
		HttpKoneksi httpKoneksi = new HttpKoneksi("");
		httpKoneksi.requestPost(new ReceiveCallBack() {
			
			@Override
			public void onSuccess(ReceiveContent receiveContent) {
				if(receiveContent.getStatus() == Status.SUCCESS){
					
				}
			}
			
			@Override
			public void onFailed(String failed) {
				
			}
		}, jsonUser);
	}
}
