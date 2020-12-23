package com.janitha.designpatterns.factory;

import java.util.ArrayList;
import java.util.List;

public abstract class Membership {
	
	protected List<Offer> offers = new ArrayList<>();
	
	public Membership() {
		createMembership();
	}
	
	//Abstract because sub membership classes can include different offers.
	protected abstract void createMembership();

	@Override
	public String toString() {
		return "Membership [offers=" + offers + "]";
	}

	
	
}
