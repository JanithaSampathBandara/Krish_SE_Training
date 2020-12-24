package com.janitha.designpatterns.chainofresponsibility.atmdispenser;

public abstract class Handler {
	
	protected Handler successor;

	public void setSuccessor(Handler successor) {
		this.successor = successor;
	}

	public abstract String dispense(Currency currency);

}
