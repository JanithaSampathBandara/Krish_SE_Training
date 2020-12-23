package com.janitha.designpatterns.factory;

public abstract class Offer {

	private int startYear;
	private int endYear;
	
	public Offer(int startYear, int endYear) {
		super();
		this.startYear = startYear;
		this.endYear = endYear;
	}

	public int getStartYear() {
		return startYear;
	}

	public void setStartYear(int startYear) {
		this.startYear = startYear;
	}

	public int getEndYear() {
		return endYear;
	}

	public void setEndYear(int endYear) {
		this.endYear = endYear;
	}
	
	
}
