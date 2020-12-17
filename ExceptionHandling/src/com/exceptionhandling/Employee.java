package com.exceptionhandling;

public class Employee {

	String eid;
	String name;
	double basicSal;
	double incrementPercentage;
	
	SalaryManager salaryManager = new SalaryManager();
	
	Employee(String eid, String name, double basicSal, double incrementPercentage )throws ArithmeticException{
		this.eid = eid;
		this.name = name;
		this.basicSal = basicSal;
		this.incrementPercentage = incrementPercentage;
	}

	// Validate whether the employee is a executive or not
	double validateEmployee(String eid){
		double salary = 0.00;
		// Validate employee as an executive employee if eid has 'E' letter and calculate salary
		if(eid.contains("E")) {
			salary =  salaryManager.calculateSalary(this.eid, this.basicSal, this.incrementPercentage);
		}
		
		return salary;
	}
	
	
}
