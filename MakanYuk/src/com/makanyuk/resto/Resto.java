package com.makanyuk.resto;

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


	private String id;
	private String nama;
	private Lokasi lokasi;
	private String alamat;
	
}
