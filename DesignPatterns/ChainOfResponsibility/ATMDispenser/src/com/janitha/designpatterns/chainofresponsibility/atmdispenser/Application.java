package com.janitha.designpatterns.chainofresponsibility.atmdispenser;

public class Application {

	public static void main(String[] args) {
		
		FiveThousandHandler fiveThousandHandler = new FiveThousandHandler();
		TwoThousandHandler twoThousandHandler = new TwoThousandHandler();
		ThousandHandler thousandHandler = new ThousandHandler();
		FiveHundredHandler fiveHundredHandler = new FiveHundredHandler();
		HundredHandler hundredHandler = new HundredHandler();
		FiftyHandler fiftyHandler = new FiftyHandler();
		TwentyHandler twentyHandler = new TwentyHandler();
		TenHandler tenHandler = new TenHandler();
		FiveHandler fiveHandler = new FiveHandler();
		TwoHandler twoHandler = new TwoHandler();
		
		ATM atm = new ATM();
		
		atm.setSuccessor(fiveThousandHandler);
		fiveThousandHandler.setSuccessor(twoThousandHandler);
		twoThousandHandler.setSuccessor(thousandHandler);
		thousandHandler.setSuccessor(fiveHundredHandler);
		fiveHundredHandler.setSuccessor(hundredHandler);
		hundredHandler.setSuccessor(fiftyHandler);
		fiftyHandler.setSuccessor(twentyHandler);
		twentyHandler.setSuccessor(tenHandler);
		tenHandler.setSuccessor(fiveHandler);
		fiveHandler.setSuccessor(twoHandler);
		
		//23688
		Currency currency = new Currency(23688);
		System.out.println("Amount : " + currency.getAmount());
		System.out.println();
		System.out.println(atm.dispense(currency));
		

	}

}
