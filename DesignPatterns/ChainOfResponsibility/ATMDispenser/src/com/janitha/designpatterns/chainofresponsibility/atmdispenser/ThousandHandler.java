package com.janitha.designpatterns.chainofresponsibility.atmdispenser;

public class ThousandHandler extends Handler{

	@Override
	public int dispense(Currency currency) {
		
		if(currency.getAmount() >= 1000) {
			currency.setThousandCount((int)currency.getAmount() / 1000);
			currency.setAmount(currency.getAmount() % 1000);
			return currency.getThousandCount();
		}
		else {
			return successor.dispense(currency);
		}
		
	}

}
