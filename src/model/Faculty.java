package model;

import java.io.Serializable;

import utils.NameFactory;

public class Faculty extends Person implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 237162228755353135L;
	/**
	 * 
	 */
	private Title title;
	private int salary;

	public Faculty(Title title) {
		this.title = title;
		salary = (int) (10000 + Math.random() * 90001);
	}

	public Faculty(Name name) {
		super(name);
	}

	public Faculty(Name name, int salary, Title title) {
		super(name);
		this.salary = salary;
		this.title = title;
	}

	public Faculty(Name name, String phone, Title title, int salary) {
		super(name, phone);
		this.title = title;
		try {
			if (salary < 0) {
				throw new IllegalSalaryException("Salary cannot be negative.");
			}
			this.salary = salary;
		} catch (IllegalSalaryException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return super.toString() + " Faculty [title=" + title + ", salary=" + salary + "]";
	}

}
