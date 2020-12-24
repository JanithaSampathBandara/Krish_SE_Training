package com.janitha.designpatterns.chainofresponsibility.atmdispenser;

public class TwentyHandler extends Handler{

	@Override
	public String dispense(Currency currency) {
		
		double remainder;
		if((remainder = currency.getAmount() % 20) == 0) {
			currency.setTwentyCount((int)currency.getAmount() / 20);
			return "20 Notes : " + currency.getTwentyCount();
		}
		else {
			if(currency.getAmount() >= 20) {
				currency.setTwentyCount((int)currency.getAmount() / 20);
				currency.setAmount(remainder);
				System.out.println("20 Notes : " + currency.getTwentyCount());
			}
			
			return successor.dispense(currency);

		}
		
	}

}
