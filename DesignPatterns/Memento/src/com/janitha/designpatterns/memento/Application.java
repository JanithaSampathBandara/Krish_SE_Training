package com.janitha.designpatterns.memento;

public class Application {

	public static void main(String[] args) {
		
		CareTaker careTaker = new CareTaker();
		Container container = new Container();
		container.addItem(new Item(100,"Phone","China"));
		container.addItem(new Item(200,"Mug","India"));
		careTaker.save(container);
		System.out.println(container);
		
		container.addItem(new Item(300,"Purse","China"));
		careTaker.save(container);
		System.out.println(container);
		
		container.addItem(new Item(400,"Box","Russia"));
		careTaker.save(container);
		System.out.println(container);
		
		container.addItem(new Item(500,"Bed","Japan"));
		careTaker.save(container);
		System.out.println(container);
		
		careTaker.restore(container);
		System.out.println(container);
		
		careTaker.restore(container);
		System.out.println(container);
		
		careTaker.restore(container);
		System.out.println(container);
		
		careTaker.restore(container);
		System.out.println(container);
	}

}
