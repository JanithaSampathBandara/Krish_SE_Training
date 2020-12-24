package com.janitha.designpatterns.chainofresponsibility.atmdispenser;

public class TenHandler extends Handler{

	@Override
	public String dispense(Currency currency) {
		
		double remainder;
		if((remainder = currency.getAmount() % 10) == 0) {
			currency.setTenCount((int)currency.getAmount() / 10);
			return "10 Notes : " + currency.getTenCount();
		}
		else {
			if(currency.getAmount() >= 10) {
				currency.setTenCount((int)currency.getAmount() / 10);
				currency.setAmount(remainder);
				System.out.println("10 Notes : " + currency.getTenCount());
			}
			
			return successor.dispense(currency);

		}

	}

}
