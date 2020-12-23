package com.janitha.designpatterns.chainofresponsibility.atmdispenser;

public class FiveHandler extends Handler{

	@Override
	public int dispense(Currency currency) {
		
		if(currency.getAmount() >= 5) {
			currency.setFiveCount((int)currency.getAmount() / 5);
			currency.setAmount(currency.getAmount() % 5);
			return currency.getFiveCount();
		}
		else {
			return successor.dispense(currency);
		}
	}

	
	
}
