package com.janitha.designpatterns.prototype;

import java.util.HashMap;
import java.util.Map;

//Place where we create objects initially
public class Registry {
	
	private Map<AnimalType, Animal> animals = new HashMap<>();
	
	public Registry() {
		
		this.initialize();
	}
	
	public Animal getAnimal(AnimalType animalType) {
		
		Animal animal = null;
		
		try {
			
			 animal = (Animal) animals.get(animalType).clone();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
	return animal;
		
	}
	
	private void initialize() {
		
		Dog dog = new Dog(); 
		dog.setName("Toby");
		dog.setAge(1.3);
		dog.setGender("Male");
		dog.setNoOfLegs(4);
		dog.setVegetarian(false);
		dog.setWeight(45.2);
		dog.setBreed("Rottweiler");
		dog.setNoOfShows(2);
		
		Cat cat = new Cat();
		cat.setName("Kaylo");
		cat.setAge(3);
		cat.setGender("Female");
		cat.setNoOfLegs(4);
		cat.setVegetarian(false);
		cat.setWeight(5.2);
		cat.setColor("Black");
		
		animals.put(AnimalType.DOG,dog);
		animals.put(AnimalType.CAT,cat);
		
	}
}
