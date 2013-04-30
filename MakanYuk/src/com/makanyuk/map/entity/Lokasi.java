package com.makanyuk.map.entity;

public class Lokasi {

	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}	
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}


	private double longitude;
	private double latitude;
	private long time;
}
