package com.janitha.designpatterns.builder;

public class Application {
	
	public static void main(String[] args) {
		
		House.Builder builder = new House.Builder("Janitha's House", 10, 5, 4, 2);
		House house = builder.garden(true).garage(true).swimmingPool(true).build();
		System.out.println(house);
	}

}
