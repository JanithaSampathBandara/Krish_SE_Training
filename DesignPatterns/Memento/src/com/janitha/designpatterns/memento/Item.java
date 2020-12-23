package com.janitha.designpatterns.memento;

public class Item {

	private int id;
	private String name;
	private String origin;
	
	public Item(int id, String name, String origin) {
		super();
		this.id = id;
		this.name = name;
		this.origin = origin;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", origin=" + origin + "]";
	}
	
	
}
