package com.janitha.designpatterns.factory;

public class VIPMembership extends Membership{

	@Override
	protected void createMembership() {
		
		offers.add(new Gym(2019,2019,3,"Power World", "Nugegoda"));
		offers.add(new Yoga(2019,2020,"Perera"));
		offers.add(new Pool(2019,2020,"Adult"));
		
	}

}
