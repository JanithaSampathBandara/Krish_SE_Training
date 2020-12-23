package com.janitha.designpatterns.memento;

import java.util.ArrayList;

public class Container {
	
	ArrayList<Item> items = new ArrayList<>();

	public void addItem(Item item) {
		items.add(item);
	}
	
	public ArrayList<Item> getItems() {
		return (ArrayList)items.clone();
	}

	public ContainerMomento save() {
		return new ContainerMomento(getItems());
	}
	
	public void previous(ContainerMomento containerMomento){
		items = containerMomento.getItems();
	}
	
	
	@Override
	public String toString() {
		return "Container [items=" + items + "]";
	}




	static class ContainerMomento{
		
		ArrayList<Item> items;

		public ContainerMomento(ArrayList<Item> items) {
			this.items = items;
		}

		private ArrayList<Item> getItems() {
			return items;
		}

		@Override
		public String toString() {
			return "ContainerMomento [items=" + items + "]";
		}
		
		
		
	}
	
}
