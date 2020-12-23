package com.janitha.designpatterns.prototype;

public class Dog extends Animal implements Cloneable{

	private String breed;
	private int noOfShows;

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}
	
	public int getNoOfShows() {
		return noOfShows;
	}

	public void setNoOfShows(int noOfShows) {
		this.noOfShows = noOfShows;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Number of shows participated : " + noOfShows;
	}
	
}
