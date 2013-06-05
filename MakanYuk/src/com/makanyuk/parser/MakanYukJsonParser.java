package com.makanyuk.parser;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.makanyuk.kategori.Kategori;
import com.makanyuk.map.entity.Lokasi;
import com.makanyuk.resto.Resto;
import com.makanyuk.resto.RestoMenu;
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
	
	public static User getUser(String json){
		User user = new User();
		try {
			JSONObject jsonObject = new JSONObject(json);
			user.setUserName(jsonObject.getString("userName"));
			user.setPassword(jsonObject.getString("password"));
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public static List<Kategori> getKategories(String json){
		
		List<Kategori> kategories = new ArrayList<Kategori>(); 
		
		try {
			JSONObject jsonObject = new JSONObject(json);
			JSONArray	jsonArray = jsonObject.getJSONArray("kategoris");
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
			Lokasi lokasi = resto.getLokasi();
			if(lokasi!=null){
				jsonObject.put("latitude", lokasi.getLatitude());
				jsonObject.put("longitude", lokasi.getLongitude());	
			}
			
			List<RestoMenu> restoMenus = resto.getRestoMenus();
			String jsonRestoMenus = getJsonFromRestoMenus(restoMenus);
			if(jsonRestoMenus!=null && !jsonRestoMenus.equals("")){
				jsonObject.put("restoMenus", jsonRestoMenus);	
			}
			

		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return jsonObject.toString();
	}
	
	public static String getJsonFromRestoMenus(List<RestoMenu> restoMenus){
		if(restoMenus!=null){
			JSONArray jsonArray = new JSONArray();
			for(RestoMenu restoMenu: restoMenus){
				jsonArray.put(getJsonFromRestoMenu(restoMenu));
			}
			return jsonArray.toString();
		}else{
			return "";
		}
		
	}
	
	public static String getJsonFromRestoMenu(RestoMenu restoMenu){
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("id", restoMenu.getId());
			jsonObject.put("nama", restoMenu.getNama());
			jsonObject.put("harga", restoMenu.getHarga());
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}
	
	
	public static List<Resto> getRestosFromJson(String json){
		List<Resto> restos = new ArrayList<Resto>();
		try {
			Log.d("Parser", json);
			JSONObject jsonObject = new JSONObject(json);
			JSONArray jsonArray = jsonObject.getJSONArray("restos");
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
			
			resto.setRestoMenus(getRestoMenusFromJson(json));
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return resto;
	}
	
	public static List<RestoMenu> getRestoMenusFromJson(String json){
		try {
			List<RestoMenu> restoMenus = new ArrayList<RestoMenu>();
			JSONObject jsonObjectResto = new JSONObject(json);
			JSONArray jsonArray = jsonObjectResto.getJSONArray("restoMenus");
			for(int i=0;i<jsonArray.length();i++){
				JSONObject jsonObject = (JSONObject) jsonArray.get(i);
				restoMenus.add(getRestoMenu(jsonObject.toString()));
			
			}
			return restoMenus;
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static RestoMenu getRestoMenu(String json){
		JSONObject jsonObject;
		try {
			jsonObject = new JSONObject(json);
			RestoMenu restoMenu = new RestoMenu();
			restoMenu.setId(jsonObject.getString("id"));
			restoMenu.setNama(jsonObject.getString("nama"));
			restoMenu.setHarga(jsonObject.getString("harga"));
			return restoMenu;
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
		
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
		JSONObject jsonUser = new JSONObject();
		try {
			jsonUser.put("user", jsonObject);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonUser.toString();
	}
	
}
