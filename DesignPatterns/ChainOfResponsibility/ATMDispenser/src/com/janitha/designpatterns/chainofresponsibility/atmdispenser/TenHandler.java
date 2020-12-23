package com.janitha.designpatterns.chainofresponsibility.atmdispenser;

public class TenHandler extends Handler{

	@Override
	public int dispense(Currency currency) {
		
		if(currency.getAmount() >= 10) {
			currency.setTenCount((int)currency.getAmount() / 10);
			currency.setAmount(currency.getAmount() % 10);
			return currency.getTenCount();
		}
		else {
			return successor.dispense(currency);
		}
		
	}

}
