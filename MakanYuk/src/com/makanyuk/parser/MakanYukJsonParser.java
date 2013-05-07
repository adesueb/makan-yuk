package com.makanyuk.parser;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.makanyuk.kategori.Kategori;
import com.makanyuk.map.entity.Lokasi;
import com.makanyuk.resto.Resto;
import com.makanyuk.user.User;

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
	
	
	public static List<Resto> getRestosFromJson(String json){
		List<Resto> restos = new ArrayList<Resto>();
		try {
			JSONObject jsonObject = new JSONObject("json");
			JSONArray jsonArray = jsonObject.getJSONArray("resto");
			for(int i=0;i<jsonArray.length();i++){
				Resto resto = 
						getRestoFromJson
								(jsonArray.get(i).toString());
				restos.add(resto);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return restos;
		
	}
	

	public static Resto getRestoFromJson(String json){
		Resto resto = new Resto();
		try {
			JSONObject jsonObject = new JSONObject(json);
			resto.setId(jsonObject.getString("id"));
			resto.setNama(jsonObject.getString("nama"));
			resto.setAlamat(jsonObject.getString("alamat"));
			Lokasi lokasi = new Lokasi();
			lokasi.setLatitude(jsonObject.getLong("latitude"));
			lokasi.setLongitude(jsonObject.getLong("longitude"));
			resto.setLokasi(lokasi);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return resto;
	}
	
	public static String getJsonFromUser(User user){
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("namaLengkap", user.getNamaLengkap());
			jsonObject.put("userName", user.getUserName());
			jsonObject.put("alamatEmail", user.getAlamatEmail());
			jsonObject.put("password", user.getPassword());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}
	
	
}
