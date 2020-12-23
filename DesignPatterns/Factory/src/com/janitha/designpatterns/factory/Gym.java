package com.janitha.designpatterns.factory;

public class Gym extends Offer{
	
	private int code;
	private String name;
	private String location;

	
	public Gym(int startYear, int endYear, int code, String name, String location) {
		super(startYear, endYear);
		this.code = code;
		this.name = name;
		this.location = location;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}


	@Override
	public String toString() {
		return "Gym Offer[code=" + code + ", name=" + name + ", location=" + location + "]";
	}

}
