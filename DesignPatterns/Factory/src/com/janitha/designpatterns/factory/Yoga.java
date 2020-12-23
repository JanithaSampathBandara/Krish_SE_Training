package com.janitha.designpatterns.factory;

public class Yoga extends Offer{

	private String instructor;
	

	public Yoga(int startYear, int endYear, String instructor) {
		super(startYear, endYear);
		this.instructor = instructor;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	@Override
	public String toString() {
		return "Yoga Offer[instructor=" + instructor + "]";
	}
		
}
