package com.exceptionhandling;

public class Employee {

	String eid;
	String name;
	double basicSal;
	double incrementPercentage;
	
	SalaryManager sm = new SalaryManager();
	
	Employee(String eid, String name, double basicSal, double incrementPercentage ){
		this.eid = eid;
		this.name = name;
		this.basicSal = basicSal;
		this.incrementPercentage = incrementPercentage;
	}

	// Validate whether the employee is a executive or not
	double validateEmployee(String eid){
		double sal = 0.0;
		// Validate employee as an executive employee if eid has 'E' letter
		if(eid.contains("E")) {
			sal =  sm.calculateSalary(this.eid, this.basicSal, this.incrementPercentage);
		}
		
		return sal;
	}
	
	
}
