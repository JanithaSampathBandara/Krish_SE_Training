package com.janitha.designpatterns.chainofresponsibility.atmdispenser;

public class TwoHandler extends Handler{

	@Override
	public int dispense(Currency currency) {
		
		if(currency.getAmount() >= 2) {
			currency.setTwoCount((int)currency.getAmount() / 2);
			currency.setAmount(currency.getAmount() % 2);
			return currency.getTwoCount();
		}
		else {
			return (int)currency.getAmount();
		}
	}

	
}
