package com.makanyuk.net.util;

import java.io.IOException;
import java.io.InputStream;

public class ReceiveContent {

	
	public String getContentData() {
		String data="";
		int read;
		try {
			while((read = inputStream.read())!=-1){
				data += (char)read;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public InputStream getContentInputStream() {
		return inputStream;
	}
	public void setContentInputStream(InputStream is) {
		this.inputStream = is;
	}
	
	private InputStream inputStream;
	private int status;
}
