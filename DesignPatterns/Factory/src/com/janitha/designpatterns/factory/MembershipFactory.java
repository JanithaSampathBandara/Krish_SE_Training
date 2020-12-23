package com.janitha.designpatterns.factory;

public class MembershipFactory {
	
	public static Membership generateMembership(MembershipCode membershipCode) {
		
		switch(membershipCode) {
			
		case BEGINNER: return new BeginnerMembership();
		case PRO: return new ProMembership();
		case VIP: return new VIPMembership();
		default: return null;
		
		}
		
		
	}

}
