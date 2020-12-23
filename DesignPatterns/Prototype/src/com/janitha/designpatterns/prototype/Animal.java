package com.janitha.designpatterns.prototype;

public abstract class Animal {

	private String name;
	private double age;
	private String gender;
	private double weight;
	private int noOfLegs;
	private boolean isVegetarian;
	
//	public Animal(String name, double age, String gender, double weight, int noOfLegs, boolean isVegetarian) {
//		super();
//		this.name = name;
//		this.age = age;
//		this.gender = gender;
//		this.weight = weight;
//		this.noOfLegs = noOfLegs;
//		this.isVegetarian = isVegetarian;
//	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getAge() {
		return age;
	}

	public void setAge(double age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public int getNoOfLegs() {
		return noOfLegs;
	}

	public void setNoOfLegs(int noOfLegs) {
		this.noOfLegs = noOfLegs;
	}

	public boolean isVegetarian() {
		return isVegetarian;
	}

	public void setVegetarian(boolean isVegetarian) {
		this.isVegetarian = isVegetarian;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {

		return super.clone();
	}
	
	
}
