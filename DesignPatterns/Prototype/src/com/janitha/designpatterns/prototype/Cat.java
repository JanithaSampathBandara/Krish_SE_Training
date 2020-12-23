package com.janitha.designpatterns.prototype;

public class Cat extends Animal{

	private String color;

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Color : " + color;
	}
	
}
