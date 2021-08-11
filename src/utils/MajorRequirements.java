package utils;

import java.io.Serializable;

import model.CourseGrade;
import model.CourseType;
import model.Major;
import model.MiniCourse;
import model.MiniCourseBag;

public class MajorRequirements implements Serializable {
	private static String[] cseCourses = { "CSE110", "CSE118", "CSE148", "CSE218", "CSE222", "CSE248",
			"ENG101", "ENG102", "MAT141", "MAT142", "MAT205", "MAT210", "PHY130", "PHY132", "PHY230",
			"PHY232", "PHY245", "PHY246", "PED145", "PED146", "HIS101", "HUM114", "ITL101", "HIS106" };
	
	private static String[] matCourses = { "COL101", "ENG101", "ENG102", "MAT141", "MAT142", "MAT200", "MAT203",
			"MAT204", "MAT206", "CSE118", "PED145", "PED146", "HIS101", "HIS103", "ITL101", "PHY130", "PHY132",
			"PHY230", "PHY232", "HIS104", "HUM114", "HUM120" };
	
	private static MiniCourse[] cseMiniCourses;
	private MiniCourse[] matMiniCourses;
	private static MiniCourseBag cseMiniCourseBag = new MiniCourseBag(30, Major.CSE);
	private static MiniCourseBag matMiniCourseBag = new MiniCourseBag(30, Major.MAT);
	
	public static MiniCourseBag fillCSEMiniCourseBag(MiniCourseBag miniCourseBag) {
		for (int i = 0; i < cseCourses.length; i++) {
			MiniCourse miniCourse = new MiniCourse(cseCourses[i], CourseType.TO_TAKE, CourseGrade.NO_GRADE);
			miniCourseBag.insert(miniCourse);
		}
		return miniCourseBag;
	}
	
	public static MiniCourseBag fillMATMiniCourseBag(MiniCourseBag miniCourseBag) {
		for (int i = 0; i < matCourses.length; i++) {
			MiniCourse miniCourse = new MiniCourse(matCourses[i], CourseType.TO_TAKE, CourseGrade.NO_GRADE);
			miniCourseBag.insert(miniCourse);
		}
		return miniCourseBag;
	}
	
	public static String[] getCSECourseNames() {
		return cseCourses;
	}
	
	public static String[] getMATCourseNames() {
		return matCourses;
	}
}
