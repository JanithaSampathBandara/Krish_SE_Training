package com.janitha.designpatterns.chainofresponsibility.atmdispenser;

public class TwoThousandHandler extends Handler{

	@Override
	public int dispense(Currency currency) {
		
		if(currency.getAmount() >= 2000) {
			currency.setTwoThousandCount((int)currency.getAmount() / 2000);
			currency.setAmount(currency.getAmount() % 2000);
			return currency.getTwoThousandCount();
		}
		else {
			return successor.dispense(currency);
		}
		
	}

}
