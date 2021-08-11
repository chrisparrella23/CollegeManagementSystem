package model;

public class IllegalSalaryException extends Exception {
	public IllegalSalaryException() {
		super();
	}
	
	public IllegalSalaryException(String message) {
		super(message);
	}
}
