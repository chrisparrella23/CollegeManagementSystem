package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Scanner;

import model.CourseGrade;
import model.CourseType;
import model.Faculty;
import model.Major;
import model.MiniCourse;
import model.MiniCourseBag;
import model.Name;
import model.NameBag;
import model.PeopleBag;
import model.Person;
import model.Student;
import model.Title;

public class PeopleHelper implements Serializable {
	private String[] firstNames;
	private String[] lastNames;
	private static int personCount = 0;
	private static int studentCount = 0;
	private static int facultyCount = 0;
	private static Title title;
	private static CourseType courseType;
	private static CourseGrade courseGrade;
	private static MiniCourseBag miniCourseBag;
	
	public static void importData(PeopleBag peopleBag, NameBag nameBag) throws FileNotFoundException {
		int facultyCount = peopleBag.getPeople().length / 4;
		int halfCount = peopleBag.getPeople().length / 2;
		int randomNumber = (int) (Math.random() * peopleBag.getnElems());
		for (int i = 0; i < facultyCount; i++) {
			Name name = NameFactory.emitName();
			int salary = (int) (10000 + Math.random() * 90001);
			int rand = (int) (Math.random() * Title.values().length);
			Title title = Title.values()[rand];
			Person person = new Faculty(name, salary, title);
			peopleBag.insert(person);
			personCount++;
		}
		
		for (int i = facultyCount + 1; i < halfCount + 1; i++) {
			Name name = NameFactory.emitName();
			int rand = (int) (Math.random() * Major.values().length);
			Major major = Major.values()[rand];
			Person person = new Student(name, major);
			peopleBag.insert(person);
			personCount++;
		}
	}
	
	public static int getPersonCount() {
		return personCount;
	}
	
	public static PeopleBag makePeople() {
		return null;
	}
	
	public static void save(PeopleBag theBag) {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream("output_data/people.dat");
		} catch (FileNotFoundException e) {
//			Alerts.noFileAlert();
//			e.printStackTrace();
		}
		try {
			oos = new ObjectOutputStream(fos);
		} catch (IOException e) {
//			Alerts.saveFailAlert();
//			e.printStackTrace();
		}
		try {
			oos.writeObject(theBag);
			oos.writeInt(Person.getIdCounter());
//			Alerts.saveAlert();
		} catch (IOException e) {
//			Alerts.saveFailAlert();
//			e.printStackTrace();
		}
	}
	
	public static PeopleBag load() {
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream("output_data/people.dat");
			ois = new ObjectInputStream(fis);
		} catch (IOException e) {
//			Alerts.loadFailAlert();
//			e.printStackTrace();
		}
		try {
			PeopleBag theBag = (PeopleBag) ois.readObject();
//			Person.setIdCounter(ois.readInt());
			Person[] array = theBag.getPeople();
//			return theBag;
//			for (int i = 0; i < theBag.getnElems(); i++) {
//				System.out.println(array[i]);
//			}
			return theBag;
		} catch (ClassNotFoundException | IOException e) {
//			Alerts.loadFailAlert();
//			e.printStackTrace();
		}
		return null;
	}
	
	public static void exportStudents(PeopleBag theBag) {
		Person[] array = theBag.getPeople();
		FileWriter fw = null;
		try {
			fw = new FileWriter("output_data/students.txt", false);
		} catch (IOException e) {
			Alerts.saveFailAlert();
//			e.printStackTrace();
		}
		PrintWriter pw = new PrintWriter(fw);
//		pw.format("%-10s%15s%15s%15s\n", "ID", "LastName")\
		for (int i = 0; i < array.length; i++) {
			if (array[i] instanceof Student) {
				Student student = (Student) array[i];
				pw.format("%s|%s|%s|%s|%s|%s|%s|\n", student.getId(), student.getLastName(), student.getFirstName(), student.getPhone(), student.getMajor(), student.getGpa(), student.getMiniCourseBag().exportString());
			}
		}
		pw.close();
		Alerts.textSaveAlert();
	}
	
	public static void exportFaculty(PeopleBag theBag) {
		Person[] array = theBag.getPeople();
		FileWriter fw = null;
		try {
			fw = new FileWriter("output_data/faculty.txt", false);
		} catch (IOException e) {
			Alerts.saveFailAlert();
//			e.printStackTrace();
		}
		PrintWriter pw = new PrintWriter(fw);
		for (int i = 0; i < array.length; i++) {
			if (array[i] instanceof Faculty) {
				Faculty faculty = (Faculty) array[i];
				pw.format("%s|%s|%s|%s|%s|%s|\n", faculty.getId(), faculty.getLastName(), faculty.getFirstName(), faculty.getPhone(), faculty.getTitle(), faculty.getSalary());
			}
		}
		pw.close();
		Alerts.textSaveAlert();
	}
	
	// Couldn't get this to work in time; should look at MiniCourseBag
	public static void importStudents(PeopleBag theBag, String importFile) {
		int lineCount = 1;
		File f = new File("input_data/students_test.txt");
		try {
			Scanner fileScan = new Scanner(f);
//			fileScan.nextLine();
			while(fileScan.hasNextLine()) {
				String[] arr = fileScan.nextLine().split("\\|");
				
				String lastName = arr[1];
				String firstName = arr[2];
				String phone = arr[3];
				Name name = new Name(firstName, lastName);
				Major major = Major.valueOf(arr[4]);
				miniCourseBag = new MiniCourseBag(26);
				MajorRequirements majorRequirements = new MajorRequirements();
//				if (major == Major.CSE) {
//					MajorRequirements.fillCSEMiniCourseBag(miniCourseBag);
//				} else {
//					MajorRequirements.fillMATMiniCourseBag(miniCourseBag);
//				}
				
				double gpa = Double.parseDouble(arr[5]);
				for (int i = 6; i < arr.length; i++) {
					String courseNumber = arr[7];
					String courseTypeTemp = arr[8];
					if (courseTypeTemp.equalsIgnoreCase("TO_TAKE")) {
						courseType = CourseType.TO_TAKE;
					} else if (courseTypeTemp.equalsIgnoreCase("TAKING")) {
						courseType = CourseType.TAKING;
					} else if (courseTypeTemp.equalsIgnoreCase("TAKEN")) {
						courseType = CourseType.TAKEN;
					}
					String courseGradeTemp = arr[9];
					if (courseGradeTemp.equalsIgnoreCase("A")) {
						courseGrade = CourseGrade.A;
					} else if (courseGradeTemp.equalsIgnoreCase("B_PLUS")) {
						courseGrade= CourseGrade.B_PLUS;
					} else if (courseGradeTemp.equalsIgnoreCase("B")) {
						courseGrade = CourseGrade.B;
					} else if (courseGradeTemp.equalsIgnoreCase("C_PLUS")) {
						courseGrade = CourseGrade.C_PLUS;
					} else if (courseGradeTemp.equalsIgnoreCase("C")) {
						courseGrade = CourseGrade.C;
					} else if (courseGradeTemp.equalsIgnoreCase("D_PLUS")) {
						courseGrade= CourseGrade.D_PLUS;
					} else if (courseGradeTemp.equalsIgnoreCase("D")) {
						courseGrade = CourseGrade.D;
					} else if (courseGradeTemp.equalsIgnoreCase("F")) {
						courseGrade = CourseGrade.F;
					} else {
						courseGrade = CourseGrade.NO_GRADE;
					}
//					CourseType courseType = CourseType.valueOf(arr[i++]);
//					CourseGrade courseGrade = CourseGrade.valueOf(arr[i]);
					MiniCourse miniCourse = new MiniCourse(courseNumber, courseType, courseGrade);
					miniCourseBag.insert(miniCourse);
				}
				Student student = new Student(name, phone, major, miniCourseBag);
				student.setId(String.valueOf(theBag.getnElems() + 1));
				theBag.insert(student);
			}
		} catch (FileNotFoundException e) {
//			Alerts.noFileAlert();
//			e.printStackTrace();
		}
	}
	
	public static void importFaculty(PeopleBag theBag, String importFile) {
		File f = new File(importFile);
		try {
			Scanner fileScan = new Scanner(f);
			while (fileScan.hasNextLine()) {
				String[] arr = fileScan.nextLine().split("\\|");
				
				String lastName = arr[1];
				String firstName = arr[2];
				String phone = arr[3];
				Title title = Title.valueOf(arr[4]);
				int salary = Integer.parseInt(arr[5]);
				Name name = new Name(firstName, lastName);
				Faculty faculty = new Faculty(name, phone, title, salary);
				faculty.setId(String.valueOf(theBag.getnElems() + 1));
				theBag.insert(faculty);
			}
			Alerts.textLoadAlert();
		} catch (FileNotFoundException e) {
			Alerts.noFileAlert();
		}
	}
	
}
