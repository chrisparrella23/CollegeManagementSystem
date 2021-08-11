package model;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;

public class MiniCourseBag implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3608789268225092852L;
	private MiniCourse[] miniCourses;
	private int nElems = 0;
	private Major major;
	
	public MiniCourseBag(int maxSize) {
		miniCourses = new MiniCourse[maxSize];
//		nElems = 0;
	}
	
	public MiniCourseBag(int maxSize, Major major) {
		miniCourses = new MiniCourse[maxSize];
		this.major = major;
//		nElems = 0;
	}
	
//	public MiniCourse find(String courseNumber) {
//		int i;
//		for (i = 0; i < nElems; i++) {
//			if (miniCourses[i].getCourseNumber().contentEquals(courseNumber)) {
//				return miniCourses[i];
//			}
//		}
//		System.out.println("This is not a course.");
//		return null;
//	}

	public MiniCourse getMiniCourses(int i) {
		return miniCourses[i];
	}
	
	public MiniCourse[] getMiniCourses() {
		return miniCourses;
	}

	public void setMiniCourses(MiniCourse[] miniCourses) {
		this.miniCourses = miniCourses;
	}

	public int getnElems() {
		return nElems;
	}
	
	public void setMajor(Major major) {
		this.major = major;
	}
	
	public Major getMajor() {
		return major;
	}
	
	public void insert(MiniCourse m) {
		miniCourses[nElems++] = m;
	}
	
	public MiniCourse findByCourseNumber(String courseNumber) {
		for (int i = 0; i < nElems; i++) {
			if (courseNumber.equalsIgnoreCase(miniCourses[i].getCourseNumber())) {
				return miniCourses[i];
			}
		}
		return null;
	}
	
	public void display() {
		for (int i = 0; i < nElems; i++) {
			System.out.println(miniCourses[i]);
		}
		System.out.println();
	}
	
	public void save(MiniCourseBag theBag) {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
	}

	@Override
	public String toString() {
		return "MiniCourseBag [major=" + major + ", miniCourses=" + Arrays.toString(miniCourses) + "]";
	}
	
	public String exportString() {
		StringBuilder export = new StringBuilder();
		for (int i = 0; i < nElems; i++) {
			MiniCourse miniCourse = miniCourses[i];
			export.append(String.format("%s", miniCourse.exportString()));
		}
		String string = export.toString();
		return string;
	}
}
