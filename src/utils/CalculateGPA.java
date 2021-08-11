package utils;

import java.text.DecimalFormat;

import model.Course;
import model.CourseGrade;
import model.CourseType;
import model.MasterCourseBag;
import model.MiniCourse;
import model.MiniCourseBag;
import model.Person;
import model.Student;

public class CalculateGPA {
	public double getGradePoints(Person p, MasterCourseBag m) {
		double points = 0.0;
		double totalPoints = 0.0;
		double credits = 0.0;
		MiniCourseBag miniBag = ((Student) p).getMiniCourseBag();
		MiniCourse[] miniCourseArray = miniBag.getMiniCourses();
		Course[] courseArray = m.getCourses();
		
		for (int i = 0; i < miniCourseArray.length; i++) {
			if (miniCourseArray[i] != null && miniCourseArray[i].getCourseNumber().equals(courseArray[i].getCourseNumber())) {
				credits = courseArray[i].getCredits();
			}
			
			if (miniCourseArray[i] != null && miniCourseArray[i].getCourseGrade() == CourseGrade.A) {
				points = 4.0 * courseArray[i].getCredits();
				totalPoints += points;
			} else if (miniCourseArray[i] != null && miniCourseArray[i].getCourseGrade() == CourseGrade.B_PLUS) {
				points = 3.5 * courseArray[i].getCredits();
				totalPoints += points;
			} else if (miniCourseArray[i] != null && miniCourseArray[i].getCourseGrade() == CourseGrade.B) {
				points = 3.0 * courseArray[i].getCredits();
				totalPoints += points;
			} else if (miniCourseArray[i] != null && miniCourseArray[i].getCourseGrade() == CourseGrade.C_PLUS) {
				points = 2.5 * courseArray[i].getCredits();
				totalPoints += points;
			} else if (miniCourseArray[i] != null && miniCourseArray[i].getCourseGrade() == CourseGrade.C) {
				points = 2.0 * courseArray[i].getCredits();
				totalPoints += points;
			} else if (miniCourseArray[i] != null && miniCourseArray[i].getCourseGrade() == CourseGrade.D_PLUS) {
				points = 1.5 * courseArray[i].getCredits();
				totalPoints += points;
			} else if (miniCourseArray[i] != null && miniCourseArray[i].getCourseGrade() == CourseGrade.D) {
				points = 1.0 * courseArray[i].getCredits();
				totalPoints += points;
			} else {
				points = 0.0;
				totalPoints += points;
			}
		}

		return totalPoints;
	}
	
	public double getTotalCredits(Person p, MasterCourseBag m) {
		double totalCredits = 0.0;
		MiniCourseBag miniCourseBag = ((Student) p).getMiniCourseBag();
		MiniCourse[] miniCourseArray = miniCourseBag.getMiniCourses();
		Course[] courseArray = m.getCourses();
		for (int i = 0; i < courseArray.length; i++) {
			if (miniCourseArray[i] != null && miniCourseArray[i].getCourseType() == CourseType.TAKEN) {
				totalCredits += courseArray[i].getCredits();
			}
		}
		return totalCredits;
	}
	
	public static double calculateGPA(Person p, MasterCourseBag m) {
		DecimalFormat df = new DecimalFormat("0.0");
		double totalPoints = 0;
		double totalCredits = 0;
		MiniCourseBag miniBag = ((Student) p).getMiniCourseBag();
		for (int i = 0; i < miniBag.getnElems(); i++) {
			MiniCourse miniCourse = miniBag.getMiniCourses(i);
			Course course = m.findByCourseNumber(miniCourse.getCourseNumber());
			if (miniCourse.getCourseType().equals(CourseType.TAKEN)) {
				CourseGrade grade = miniCourse.getCourseGrade();
				double numOfCredits = course.getCredits();
				double gradePoint = computeGradePoint(grade, 4.0);
				totalPoints += (gradePoint * numOfCredits);
				totalCredits += numOfCredits;
//				return (totalPoints / totalCredits);
			}
		}
//		MiniCourse[] miniCourseArray = miniBag.getMiniCourses();
//		Course[] courseArray = m.getCourses();
//		
//		for (int i = 0; i < miniBag.getnElems(); i++) {
//			if (miniCourseArray[i] != null && miniCourseArray[i].getCourseType() == CourseType.TAKEN) {
//				totalCredits += courseArray[i].getCredits();
//			}
//		}
		
//		for (int i = 0; i < miniCourseArray.length; i++) {
//			if (miniCourseArray[i] != null && miniCourseArray[i].getCourseNumber().equals(courseArray[i].getCourseNumber())) {
//				credits = courseArray[i].getCredits();
//			}
//			
//			if (miniCourseArray[i] != null && miniCourseArray[i].getCourseGrade() == CourseGrade.A) {
//				points = 4.0 * courseArray[i].getCredits();
//				totalPoints += points;
//			} else if (miniCourseArray[i] != null && miniCourseArray[i].getCourseGrade() == CourseGrade.B_PLUS) {
//				points = 3.5 * courseArray[i].getCredits();
//				totalPoints += points;
//			} else if (miniCourseArray[i] != null && miniCourseArray[i].getCourseGrade() == CourseGrade.B) {
//				points = 3.0 * courseArray[i].getCredits();
//				totalPoints += points;
//			} else if (miniCourseArray[i] != null && miniCourseArray[i].getCourseGrade() == CourseGrade.C_PLUS) {
//				points = 2.5 * courseArray[i].getCredits();
//				totalPoints += points;
//			} else if (miniCourseArray[i] != null && miniCourseArray[i].getCourseGrade() == CourseGrade.C) {
//				points = 2.0 * courseArray[i].getCredits();
//				totalPoints += points;
//			} else if (miniCourseArray[i] != null && miniCourseArray[i].getCourseGrade() == CourseGrade.D_PLUS) {
//				points = 1.5 * courseArray[i].getCredits();
//				totalPoints += points;
//			} else if (miniCourseArray[i] != null && miniCourseArray[i].getCourseGrade() == CourseGrade.D) {
//				points = 1.0 * courseArray[i].getCredits();
//				totalPoints += points;
//			} else {
//				points = 0.0;
//				totalPoints += points;
//			}
//		}
		return (totalPoints / totalCredits);
//		double gpa = Double.parseDouble(df.format(totalPoints / totalCredits));
//		return gpa;
	}
	
	public static double computeGradePoint(CourseGrade grade, double numOfCredits) {
		double deltaX = numOfCredits / (CourseGrade.values().length - 1);
		if (grade.equals(CourseGrade.A)) {
			return numOfCredits;
		} else if (grade.equals(CourseGrade.B_PLUS)){
			return deltaX * 7;
		} else if (grade.equals(CourseGrade.B)) {
			return deltaX * 6;
		} else if (grade.equals(CourseGrade.C_PLUS)) {
			return deltaX * 5;
		} else if (grade.equals(CourseGrade.C)) {
			return deltaX * 4;
		} else if (grade.equals(CourseGrade.D)) {
			return deltaX * 3;
		} else if (grade.equals(CourseGrade.D_PLUS)) {
			return deltaX * 2;
		} else {
			return 0.0;
		}
	}
}
