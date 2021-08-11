package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

import model.Course;
import model.MasterCourseBag;
import model.TextbookBag;

public class CourseHelper {
	private Course[] courses;
	private static int courseCount = 0;
	
	public static void importCourses(MasterCourseBag masterCourseBag, TextbookBag textbookBag, String courseFile, String isbnFile) {
		File fileForCourse = new File(courseFile);
		File fileForISBNs = new File(isbnFile);
		int line = 1;
		try {
			Scanner courseScanner = new Scanner(fileForCourse);
			Scanner isbnScanner = new Scanner(fileForISBNs);
			while (courseScanner.hasNextLine()) {
				String courseNumber = courseScanner.nextLine();
				String courseTitle = courseScanner.nextLine();
				String courseDescription = courseScanner.nextLine();
				double credits = Double.parseDouble(courseScanner.nextLine());
				courseScanner.nextLine();
				line += 5;
				Course course = new Course(courseNumber, courseTitle, courseDescription, textbookBag.getRandomISBN(), credits);
				if (course.getCredits() >= 1.0) {
					masterCourseBag.insert(course);
					courseCount++;
				}
			}
		} catch (FileNotFoundException e) {
			Alerts.noFileAlert();
		} catch (NoSuchElementException e) {
			System.out.println(line);
		}
	}
	
	public static MasterCourseBag load() {
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream("output_data/courses.dat");
			ois = new ObjectInputStream(fis);
		} catch (IOException e) {
			Alerts.loadFailAlert();
//			e.printStackTrace();
		}
		try {
			MasterCourseBag theBag = (MasterCourseBag) ois.readObject();
			Course[] array = theBag.getCourses();
			System.out.println("Courses loaded.");
			return theBag;
		} catch (ClassNotFoundException | IOException e) {
			Alerts.loadFailAlert();
			e.printStackTrace();
		}
		return null;
	}
	
	public static void export(MasterCourseBag theBag) {
		Course[] array = theBag.getCourses();
		FileWriter fw = null;
		try {
			fw = new FileWriter("output_data/courses.txt");
		} catch (IOException e) {
			Alerts.saveFailAlert();
//			e.printStackTrace();
		}
		PrintWriter pw = new PrintWriter(fw);
		for (int i = 0; i < theBag.getnElems(); i++) {
			Course course = array[i];
			pw.format("%s|\n", course.exportString());
		}
		pw.close();
		Alerts.textSaveAlert();
	}
	
	public static void importCourseText(MasterCourseBag theBag, String importFile) {
		File f = new File(importFile);
		int line = 1;
		try {
			Scanner fileScan = new Scanner(f);
			while (fileScan.hasNextLine()) {
				String courseNumber = fileScan.nextLine();
				String courseTitle = fileScan.nextLine();
				String courseDescription = fileScan.nextLine();
				String isbn = fileScan.nextLine();
				double credits = Double.parseDouble(fileScan.nextLine());
				fileScan.nextLine();
				Course course = new Course(courseNumber, courseTitle, courseDescription, isbn, credits);
				line += 6;
				if (course.getCredits() >= 1.0) {
					theBag.insert(course);
				}
			}
			Alerts.textLoadAlert();
		} catch (FileNotFoundException e) {
			Alerts.noFileAlert();
		}
	}
	
}
