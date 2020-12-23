package com.janitha.designpatterns.factory;

public class BeginnerMembership extends Membership{

	@Override
	protected void createMembership() {
		
		offers.add(new Gym(2019,2019,3,"Power World", "Nugegoda"));
		
	}

}
