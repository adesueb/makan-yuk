package com.makanyuk.kategori;

import java.util.List;

import com.makanyuk.resto.Resto;

public class Kategori {	
	
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
	public List<Resto> getRestos() {
		return restos;
	}
	public void setRestos(List<Resto> restos) {
		this.restos = restos;
	}
	
	private String id;
	private String nama;
	private List<Resto> restos;
}
