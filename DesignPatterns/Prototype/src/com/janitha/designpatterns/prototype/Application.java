package com.janitha.designpatterns.prototype;

public class Application {

	public static void main(String[] args) {
		
		Registry registry = new Registry();
		Dog dog1 = (Dog) registry.getAnimal(AnimalType.DOG);
		
		System.out.println(dog1);
		dog1.setNoOfShows(10);
		System.out.println(dog1);
		System.out.println();
		
		Dog dog2 = (Dog) registry.getAnimal(AnimalType.DOG);
		System.out.println(dog2);

	}

}
