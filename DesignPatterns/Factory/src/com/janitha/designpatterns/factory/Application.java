package com.janitha.designpatterns.factory;

public class Application {

	public static void main(String[] args) {
		
		Membership membership1 = MembershipFactory.generateMembership(MembershipCode.BEGINNER);
		System.out.println(membership1);
		
		Membership membership2 = MembershipFactory.generateMembership(MembershipCode.PRO);
		System.out.println(membership2);
		
		Membership membership3 = MembershipFactory.generateMembership(MembershipCode.VIP);
		System.out.println(membership3);
	}

}
