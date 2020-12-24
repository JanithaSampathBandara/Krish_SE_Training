package com.janitha.designpatterns.chainofresponsibility.atmdispenser;

public class ThousandHandler extends Handler{

	@Override
	public String dispense(Currency currency) {
		
		double remainder;
		if((remainder = currency.getAmount() % 1000) == 0) {
			currency.setThousandCount((int)currency.getAmount() / 1000);
			return "1000 Notes : " + currency.getThousandCount();
		}
		else {
			if(currency.getAmount() >= 1000) {
				currency.setThousandCount((int)currency.getAmount() / 1000);
				currency.setAmount(remainder);
				System.out.println("1000 Notes : " + currency.getThousandCount());
			}
			
			return successor.dispense(currency);

		}
		
	}

}
