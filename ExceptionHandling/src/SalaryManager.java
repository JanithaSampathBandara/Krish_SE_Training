
public class SalaryManager {
	
double calculateSalary(String eid, double basicSal, double incrementPercentage) throws ArithmeticException{
		
		double netSal = 0.0;
		netSal = basicSal + (basicSal * (incrementPercentage / 100));
		Thread.dumpStack();
		
		return netSal;
			
	}

}
