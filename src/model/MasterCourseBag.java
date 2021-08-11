package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import utils.Alerts;

public class MasterCourseBag implements Serializable {
	private static final long serialVersionUID = -1584937020179063769L;
	private Course[] courses;
	private int nElems;
	
	public MasterCourseBag(int maxSize) {
		courses = new Course[maxSize];
		nElems = 0;
	}
	
	public Course[] getCourses() {
		return courses;
	}
	
	public void setCourses(Course[] courses) {
		this.courses = courses;
	}
	
	public int getnElems() {
		return nElems;
	}
	
	public void insert(Course c) {
		courses[nElems++] = c;
	}
	
	public Course findByCourseNumber(String courseNumber) {
		for (int i = 0; i < nElems; i++) {
			if (courses[i].getCourseNumber().equalsIgnoreCase(courseNumber)) {
				return courses[i];
			}
		}
		return null;
	}
	
	public Course deleteByCourseNumber(String courseNumber) {
		int i = 0;
		for (i = 0; i < nElems; i++) {
			if (courses[i].getCourseNumber().equalsIgnoreCase(courseNumber)) {
				break;
			}
		}
		
		if (i == nElems) {
			return null;
		} else {
			Course temp = courses[i];
			for (int j = i; j < nElems - 1; j++) {
				courses[j] = courses[j + 1];
			}
			nElems--;
			return temp;
		}
	}
	
	public void display() {
		for (int i = 0; i < nElems; i++) {
			System.out.println(courses[i]);
		}
		System.out.println();
	}
	
	public void save(MasterCourseBag theBag) {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream("output_data/courses.dat");
		} catch(FileNotFoundException e) {
//			Alerts.noFileAlert();
		}
		try {
			oos = new ObjectOutputStream(fos);
		} catch (IOException e) {
//			Alerts.saveFailAlert();
		}
		try {
			oos.writeObject(theBag);
//			System.out.println("Courses saved.");
		} catch (IOException e) {
//			Alerts.saveFailAlert();
		}
	}
	
	public static MasterCourseBag load() {
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream("output_data/courses.dat");
			ois = new ObjectInputStream(fis);
		} catch (IOException e) {
//			Alerts.loadFailAlert();
//			e.printStackTrace();
		}
		try {
			MasterCourseBag theBag = (MasterCourseBag) (ois.readObject());
			Course[] array = theBag.getCourses();
			for (int i = 0; i < theBag.getnElems(); i++) {
				System.out.println(array[i]);
			}
			return theBag;
		} catch (ClassNotFoundException | IOException e) {
//			Alerts.loadFailAlert();
//			e.printStackTrace();
		}
		return null;
	}
}
