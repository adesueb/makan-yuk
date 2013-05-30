package com.makanyuk.resto;

import java.util.List;

import com.makanyuk.map.entity.Lokasi;

public class Resto {

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public String getAlamat() {
		return alamat;
	}
	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}
	public Lokasi getLokasi() {
		return lokasi;
	}
	public void setLokasi(Lokasi lokasi) {
		this.lokasi = lokasi;
	}
	public List<RestoMenu> getRestoMenus() {
		return restoMenus;
	}
	public void setRestoMenus(List<RestoMenu> restoMenus) {
		this.restoMenus = restoMenus;
	}




	private String id;
	private String nama;
	private Lokasi lokasi;
	private String alamat;
	
	private List<RestoMenu> restoMenus;
	
}
