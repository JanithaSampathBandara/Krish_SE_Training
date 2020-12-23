package com.janitha.designpatterns.chainofresponsibility.atmdispenser;

public class Currency {

	private double amount;
	private int oneCount;
	private int twoCount;
	private int fiveCount;
	private int tenCount;
	private int twentyCount;
	private int fiftyCount;
	private int hundredCount;
	private int fiveHundredCount;
	private int thousandCount;
	private int twoThousandCount;
	private int fiveThousandCount;
	
	public Currency(double amount) {

		this.amount = amount;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}


	public void setTenCount(int tenCount) {
		this.tenCount = tenCount;
	}


	public void setTwentyCount(int twentyCount) {
		this.twentyCount = twentyCount;
	}


	public void setHundredCount(int hundredCount) {
		this.hundredCount = hundredCount;
	}


	public void setFiveHundredCount(int fiveHundredCount) {
		this.fiveHundredCount = fiveHundredCount;
	}


	public void setThousandCount(int thousandCount) {
		this.thousandCount = thousandCount;
	}


	public void setTwoThousandCount(int twoThousandCount) {
		this.twoThousandCount = twoThousandCount;
	}


	public void setFiveThousandCount(int fiveThousandCount) {
		this.fiveThousandCount = fiveThousandCount;
	}

	public int getTenCount() {
		return tenCount;
	}

	public int getTwentyCount() {
		return twentyCount;
	}

	public int getHundredCount() {
		return hundredCount;
	}

	public int getFiveHundredCount() {
		return fiveHundredCount;
	}

	public int getThousandCount() {
		return thousandCount;
	}

	public int getTwoThousandCount() {
		return twoThousandCount;
	}

	public int getFiveThousandCount() {
		return fiveThousandCount;
	}

	public int getOneCount() {
		return oneCount;
	}

	public void setOneCount(int oneCount) {
		this.oneCount = oneCount;
	}

	public int getTwoCount() {
		return twoCount;
	}

	public void setTwoCount(int twoCount) {
		this.twoCount = twoCount;
	}

	public int getFiveCount() {
		return fiveCount;
	}

	public void setFiveCount(int fiveCount) {
		this.fiveCount = fiveCount;
	}

	public int getFiftyCount() {
		return fiftyCount;
	}

	public void setFiftyCount(int fiftyCount) {
		this.fiftyCount = fiftyCount;
	}

	
	
	
}
