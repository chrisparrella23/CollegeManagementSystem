package model;

import java.io.Serializable;

public class Course implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2146201018991198818L;
	private String courseTitle;
	private String courseNumber;
	private String courseDescription;
	private String isbn;
	private Textbook textbook;
	private double credits;
	
	public Course() {
		
	}
	
	public Course(String courseNumber, String courseTitle, String courseDescription, String isbn, double credits) {
		this.courseNumber = courseNumber;
		this.courseTitle = courseTitle;
		this.courseDescription = courseDescription;
//		this.textbook = textbook;
		this.isbn = isbn;
//		isbn = textbook.getIsbn();
		this.credits = credits;
	}
	
	public String getTitle() {
		return courseTitle;
	}
	
	public void setTitle(String title) {
		this.courseTitle = title;
	}

	public String getCourseNumber() {
		return courseNumber;
	}

	public void setCourseNumber(String courseNumber) {
		this.courseNumber = courseNumber;
	}

	public String getCourseDescription() {
		return courseDescription;
	}

	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Textbook getTextbook() {
		return textbook;
	}

	public void setTextbook(Textbook textbook) {
		this.textbook = textbook;
	}

	public double getCredits() {
		return credits;
	}

	public void setCredits(double credits) {
		try {
			if (credits < 1) {
				throw new IllegalCreditsException("Course must have at least 1 credit.");
			}
			this.credits = credits;
		} catch (IllegalCreditsException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public String toString() {
		return "Course [courseTitle=" + courseTitle + ", courseNumber=" + courseNumber + ", courseDescription="
				+ courseDescription + ", isbn=" + isbn + ", credits=" + credits + "]";
	}
	
	public String exportString() {
		return courseNumber + "|" + courseTitle + "|" + courseDescription + "|" + isbn + "|" + credits;
	}
	
	
}
