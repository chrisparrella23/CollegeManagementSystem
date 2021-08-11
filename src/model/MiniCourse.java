package model;

import java.io.Serializable;

public class MiniCourse implements Serializable {
	private String courseNumber;
	private CourseType courseType;
	private CourseGrade courseGrade;

	public MiniCourse(String courseNumber, CourseType courseType, CourseGrade courseGrade) {
		this.courseNumber = courseNumber;
		this.courseType = courseType;
		this.courseGrade = courseGrade;
	}

	public String getCourseNumber() {
		return courseNumber;
	}

	public void setCourseNumber(String courseNumber) {
		this.courseNumber = courseNumber;
	}

	public CourseType getCourseType() {
		return courseType;
	}

	public void setCourseType(CourseType courseType) {
		this.courseType = courseType;
	}

	public CourseGrade getCourseGrade() {
		return courseGrade;
	}

	public void setCourseGrade(CourseGrade courseGrade) {
		this.courseGrade = courseGrade;
	}

	@Override
	public String toString() {
		return "MiniCourse [courseNumber=" + courseNumber + ", courseType=" + courseType + ", courseGrade="
				+ courseGrade + "]";
	}
	
	public String exportString() {
		return String.format("|%s|%s|%s", courseNumber, courseType, courseGrade);
	}

}
