package com.janitha.designpatterns.factory;

public class Pool extends Offer{

	private String type;
	
	
	public Pool(int startYear, int endYear, String type) {
		super(startYear, endYear);
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Pool Offer[type=" + type + "]";
	}


	
	

}
