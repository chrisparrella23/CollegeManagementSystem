package model;

import java.io.Serializable;
import java.util.Arrays;

import utils.MajorRequirements;
import utils.NameFactory;

public class Student extends Person implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8652236415942340077L;
	private double gpa;
	private Major major;
	private MiniCourseBag miniCourseBag;
	private MajorTemplate majorTemplate;
	private String[] majors = { "CSE", "CST", "ENG", "MAT", "PHY" };

	public Student() {
		super();
	}
	
//	public Student(Name name, Major major) {
//		super(name);
//		this.major = major;
//	}
	
	public Student(String firstName, String lastName, String phone, Major major) {
		super(new Name(firstName, lastName), phone);
		this.major = major;
		if (major == Major.CSE) {
			MiniCourseBag cseMiniCourseBag = new MiniCourseBag(24, Major.CSE);
			MajorRequirements.fillCSEMiniCourseBag(cseMiniCourseBag);
			miniCourseBag = cseMiniCourseBag;
		} else if (major == Major.MAT) {
			MiniCourseBag matMiniCourseBag = new MiniCourseBag(22, Major.MAT);
			MajorRequirements.fillMATMiniCourseBag(matMiniCourseBag);
			miniCourseBag = matMiniCourseBag;
		}
	}
	
	public Student(Name name, String phone, Major major) {
		super(name);
		this.major = major;
	}
	
	public Student(Name name, Major major) {
		super(name);
		this.major = major;
		if (major == Major.CSE) {
			MiniCourseBag cseMiniCourseBag = new MiniCourseBag(30, Major.CSE);
			MajorRequirements.fillCSEMiniCourseBag(cseMiniCourseBag);
			miniCourseBag = cseMiniCourseBag;
		} else if (major == Major.MAT) {
			MiniCourseBag matMiniCourseBag = new MiniCourseBag(30, Major.MAT);
			MajorRequirements.fillMATMiniCourseBag(matMiniCourseBag);
			miniCourseBag = matMiniCourseBag;
		}
	}
	
	public Student(Name name, Major major, MiniCourseBag miniCourseBag) {
		super(name);
		this.major = major;
		if (major == Major.CSE) {
			
		}
		this.miniCourseBag = miniCourseBag;
	}
	
	public Student(Major major, MiniCourseBag miniCourseBag) {
		this.major = major;
		this.miniCourseBag = miniCourseBag;
	}

	public Student(Name name, String phone, Major major, MiniCourseBag miniCourseBag) {
		super(name, phone);
		this.major = major;
		this.miniCourseBag = miniCourseBag;
	}

	public double getGpa() {
		return gpa;
	}

	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	public Major getMajor() {
		return major;
	}

	public void setMajor(Major major) {
		this.major = major;
	}

	public MiniCourseBag getMiniCourseBag() {
		return miniCourseBag;
	}

	public void setMiniCourseBag(MiniCourseBag miniCourseBag) {
		this.miniCourseBag = miniCourseBag;
	}

	public String[] getMajors() {
		return majors;
	}

	public void setMajors(String[] majors) {
		this.majors = majors;
	}

	
	
	@Override
	public String toString() {
		return super.toString() + " Student [gpa=" + gpa + ", major=" + major + ", miniCourseBag=" + miniCourseBag
				+ "]";
	}
	
}
