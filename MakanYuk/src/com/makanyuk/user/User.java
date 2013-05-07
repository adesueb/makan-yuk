package com.makanyuk.user;

public class User {

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getNamaLengkap() {
		return namaLengkap;
	}
	public void setNamaLengkap(String namaLengkap) {
		this.namaLengkap = namaLengkap;
	}
	public String getAlamatEmail() {
		return alamatEmail;
	}
	public void setAlamatEmail(String alamatEmail) {
		this.alamatEmail = alamatEmail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	private String userName;
	private String namaLengkap;
	private String alamatEmail;
	private String password;
}
