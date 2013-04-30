package com.makanyuk.parser;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.makanyuk.kategori.Kategori;
import com.makanyuk.resto.Resto;

public class MakanYukJsonParser {
	
	public static Kategori getkategori(String json){
		
		Kategori kategori = new Kategori();
		try {
			JSONObject jsonObject = new JSONObject(json);
			kategori.setId(jsonObject.getString("id"));
			kategori.setNama(jsonObject.getString("nama"));
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return kategori;
	}
	
	public static List<Kategori> getKategories(String json){
		
		List<Kategori> kategories = new ArrayList<Kategori>(); 
		
		try {
			JSONObject jsonObject = new JSONObject(json);
			JSONArray	jsonArray = jsonObject.getJSONArray("kategori");
			for(int i=0;i<jsonArray.length();i++){
				kategories.
					add(getkategori
							(jsonArray.getJSONObject(i).toString()));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return kategories;
	}
	
	
	public static String getJsonFromResto(Resto resto){
		
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("id", resto.getId());
			jsonObject.put("nama", resto.getNama());
			jsonObject.put("alamat", resto.getAlamat());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return jsonObject.toString();
	}
	
}
