package com.janitha.designpatterns.chainofresponsibility.atmdispenser;

public class Application {

	public static void main(String[] args) {
		
		FiveThousandHandler fiveThousandHandler = new FiveThousandHandler();
		TwoThousandHandler twoThousandHandler = new TwoThousandHandler();
		ThousandHandler thousandHandler = new ThousandHandler();
		FiveHundredHandler fiveHundredHandler = new FiveHundredHandler();
		HundredHandler hundredHandler = new HundredHandler();
		FiftyHandler fiftyHandler = new FiftyHandler();
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
		fiftyHandler.setSuccessor(tenHandler);
		tenHandler.setSuccessor(fiveHandler);
		fiveHandler.setSuccessor(twoHandler);
		
		Currency currency = new Currency(25000);
		System.out.println(atm.dispense(currency));
		

	}

}
