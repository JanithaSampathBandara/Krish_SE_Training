package com.janitha.designpatterns.chainofresponsibility.atmdispenser;

public class ATM extends Handler{

	@Override
	public String dispense(Currency currency) {
		
		return successor.dispense(currency);
	}

	
	
}
