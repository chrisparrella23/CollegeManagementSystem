package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class PeopleBag implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3457908020932904683L;
	private Person[] people;
	private int nElems;

	public PeopleBag(int maxSize) {
		people = new Person[maxSize];
		nElems = 0;
	}

	public Person[] getPeople() {
		return people;
	}

	public void setPeople(Person[] people) {
		this.people = people;
	}

	public int getnElems() {
		return nElems;
	}

	public void insert(Person p) {
		people[nElems++] = p;
	}

	public void setnElems(int nElems) {
		this.nElems = nElems;
	}

	public Person findById(String id) {
		for (int i = 0; i < nElems; i++) {
			if (people[i].getId().equals(id)) {
				return people[i];
			}
		}
		return null;
	}

	public Person deleteById(String id) {
		int i = 0;
		for (i = 0; i < nElems; i++) {
			if (people[i].getId().equals(id)) {
				break;
			}
		}

		if (i == nElems) {
			return null;
		} else {
			Person temp = people[i];
			for (int j = i; j < nElems; j++) {
				people[j] = people[j + 1];
			}
			nElems--;
			return temp;
		}
	}

	public void display() {
		for (int i = 0; i < nElems; i++) {
			System.out.println(people[i]);
		}
		System.out.println();
	}

	public void save(PeopleBag theBag) {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream("output_data/people.dat");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			oos = new ObjectOutputStream(fos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			oos.writeObject(theBag);
			System.out.println("People saved.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void load() {
		FileInputStream fis;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream("output_data/people.dat");
			ois = new ObjectInputStream(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			PeopleBag theBag = (PeopleBag) ois.readObject();
			System.out.println("People loaded.");
			Person[] array = theBag.getPeople();
			nElems = theBag.nElems;
			for (int i = 0; i < array.length; i++) {
				if (array[i] != null) {
					System.out.println(array[i]);
				}
			}
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}
}
