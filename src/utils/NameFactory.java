package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import model.Name;
import model.NameBag;

public class NameFactory {
	private static String[] firstNames;
	private static String[] lastNames;
	private static String firstName;
	private static String lastName;
	private static ArrayList<Name> nameList;
	
	public static String[] importFirstNames(String firstNameFile) throws FileNotFoundException {
		int counter = 0;
		File fileForFirst = new File(firstNameFile);
		Scanner in1 = new Scanner(fileForFirst);
		Scanner in2 = new Scanner(fileForFirst);
		while (in1.hasNextLine()) {
			counter++;
			in1.next();
		}
		firstNames = new String[counter];
		while(in2.hasNextLine()) {
			for (int i = 0; i < counter; i++) {
				firstNames[i] = in2.next();
			}
			return firstNames;
		}
		return null;
	}
	
	public static String[] importLastNames(String lastNameFile) throws FileNotFoundException {
		int counter = 0;
		File fileForFirst = new File(lastNameFile);
		Scanner in1 = new Scanner(fileForFirst);
		Scanner in2 = new Scanner(fileForFirst);
		while (in1.hasNextLine()) {
			counter++;
			in1.next();
		}
		lastNames = new String[counter];
		while(in2.hasNextLine()) {
			for (int i = 0; i < counter; i++) {
				lastNames[i] = in2.next();
			}
			return lastNames;
		}
		return null;
	}

	public static String[] getFirstNames() {
		return firstNames;
	}

	public static void setFirstNames(String[] firstNames) {
		NameFactory.firstNames = firstNames;
	}

	public static String[] getLastNames() {
		return lastNames;
	}

	public static void setLastNames(String[] lastNames) {
		NameFactory.lastNames = lastNames;
	}
	
	public static void displayFirstNames() {
		for (int i = 0; i < firstNames.length; i++) {
			System.out.println(firstNames[i]);
		}
	}
	
	public static void displayLastNames() {
		for (int i = 0; i < lastNames.length; i++) {
			System.out.println(lastNames[i]);
		}
	}
	
	public static String emitFirstName() {
		Random rand = new Random();
		String firstName = firstNames[rand.nextInt(firstNames.length)];
		return firstName;
	}
	
	public static String emitLastName() {
		Random rand = new Random();
		String lastName = lastNames[rand.nextInt(lastNames.length)];
		return lastName;
	}
	
	public static Name emitName() {
//		firstName = emitFirstName();
//		lastName = emitLastName();
		Name name = new Name();
		return name;
	}
	
	public static ArrayList<Name> emitNameList() {
		nameList = new ArrayList<Name>();
		int nameCount = (int) (1 + Math.random() * 3);
		for (int i = 0; i < nameCount; i++) {
			nameList.add(new Name());
		}
		return nameList;
	}
	
	public static NameBag fillBag(NameBag nameBag, String[] firstNames, String[] lastNames) {
		for (int i = 0; i < nameBag.getnElems(); i++) {
			String firstName = emitFirstName();
			String lastName = emitLastName();
			Name name = emitName();
			nameBag.insertName(name);
		}
		return nameBag;
	}
}
