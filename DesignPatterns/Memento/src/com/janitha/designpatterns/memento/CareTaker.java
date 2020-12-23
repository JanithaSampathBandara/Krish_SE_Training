package com.janitha.designpatterns.memento;

import java.util.Stack;

public class CareTaker {
	
	Stack<Container.ContainerMomento> sessions = new Stack<>();

	public void save(Container container) {
		sessions.push(container.save());
	}
	
	public void restore(Container container) {
		if(!sessions.isEmpty() ){
			container.previous(sessions.pop());
		}
		else {
			System.out.println("Nothing to go back!!");
		}
	}
	
}
