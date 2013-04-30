package com.makanyuk.net.util;

public abstract class ReceiveCallBack {

	public void success(ReceiveContent receiveContent){
		onSuccess(receiveContent);
	}
	public abstract void onSuccess(ReceiveContent receiveContent);
	
	public void failed(String failed){
		onFailed(failed);
	}
	public abstract void onFailed(String failed);
}
