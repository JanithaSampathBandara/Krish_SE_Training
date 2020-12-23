package com.janitha.designpatterns.chainofresponsibility.atmdispenser;

public class ATM extends Handler{

	@Override
	public int dispense(Currency currency) {
		
		return successor.dispense(currency);
	}

	
	
}
