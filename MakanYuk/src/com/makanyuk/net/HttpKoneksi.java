package com.makanyuk.net;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.makanyuk.net.util.Parameter;
import com.makanyuk.net.util.ReceiveCallBack;
import com.makanyuk.net.util.ReceiveContent;
import com.makanyuk.net.util.Status;

public class HttpKoneksi {

	public HttpKoneksi(String url){
		// contoh url = "http://www.yoursite.com/script.php"
		this.url = url;
	}
	
	public void requestGet(ReceiveCallBack receiveCallBack){
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		try {
			HttpResponse response = httpclient.execute(httpGet);
		    InputStream inputStream = response.getEntity().getContent();
		    ReceiveContent receiveContent = new ReceiveContent();
		    receiveContent.setContentInputStream(inputStream);
		    receiveContent.setStatus(Status.SUCCESS);
		    receiveCallBack.success(receiveContent);
		} catch (ClientProtocolException e) {
			receiveCallBack.failed("gagal");
		} catch (IOException e) {
			receiveCallBack.failed("gagal");
		}
	}
	
	public void requestPost(ReceiveCallBack receiveCallBack, List<Parameter> parameters){
		requestPost(receiveCallBack, parameters, false);
	}
	
	public void requestPostJson(ReceiveCallBack receiveCallBack, List<Parameter> parameters){
		requestPost(receiveCallBack, parameters, true);
	}
	
	public void requestPost(ReceiveCallBack receiveCallBack, List<Parameter> parameters, boolean isJson){
		HttpClient 	httpclient 	= new DefaultHttpClient();
		HttpPost 	httpPost 	= new HttpPost(url);
		if(isJson){
			httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
		}
		try {
			List<NameValuePair> nameValuePairs = null;
			if(parameters!=null && parameters.size()>0){
				nameValuePairs = new ArrayList<NameValuePair>(parameters.size());
				for(Parameter parameter:parameters){
					nameValuePairs.add
						(new BasicNameValuePair(parameter.getKey(), parameter.getValue()));
				}	
			}
			if(nameValuePairs!=null){
				httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));	
			}
			HttpResponse response = httpclient.execute(httpPost);
		    InputStream inputStream = response.getEntity().getContent();
		    ReceiveContent receiveContent = new ReceiveContent();
		    receiveContent.setContentInputStream(inputStream);
		    receiveContent.setStatus(Status.SUCCESS);
		    receiveCallBack.success(receiveContent);
		} catch (ClientProtocolException e) {
			receiveCallBack.failed("gagal");
		} catch (IOException e) {
			receiveCallBack.failed("gagal");
		}
	}
	
	public void requestPost(ReceiveCallBack receiveCallBack, String jsonOrXml){
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		try {
			httpPost.setEntity(new StringEntity(jsonOrXml));
		    HttpResponse response = httpclient.execute(httpPost);
		    InputStream inputStream = response.getEntity().getContent();
		    ReceiveContent receiveContent = new ReceiveContent();
		    receiveContent.setContentInputStream(inputStream);
		    receiveContent.setStatus(Status.SUCCESS);
		    receiveCallBack.success(receiveContent);
		} catch (ClientProtocolException e) {
			receiveCallBack.failed("gagal");
		} catch (IOException e) {
			receiveCallBack.failed("gagal");
		}
	}
	
	
	
	private final String url;
}
