package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import utils.NameFactory;

public class NameBag implements Serializable {
	private static Name[] names;
	private static int nElems;
	
	public NameBag(Name[] names) {
		
	}
	
	public NameBag(int maxSize) {
		names = new Name[maxSize];
		nElems = 0;
	}
	
	public void insertName(Name name) {
		names[nElems++] = name;
	}
	
	public int getnElems() {
		return nElems;
	}
	
	public void setArr(Name[] names) {
		this.names = names;
	}
	
	public Name[] getNames() {
		return names;
	}
	
	public void displayNames() {
		for (int i = 0; i < nElems; i++) {
			System.out.println(names[i]);
		}
		System.out.println();
	}
	
	public static NameBag fillBag(NameBag nameBag) throws FileNotFoundException {
		NameFactory.importFirstNames("input_data/First Names.txt");
		NameFactory.importLastNames("input_data/Last Names.txt");
		for (int i = 0; i < nameBag.getNames().length; i++) {
			Name name = new Name();
			nameBag.insertName(name);
		}
		return nameBag;
	}
	
	public void save(NameBag theBag) {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream("output_data/names.dat");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			oos = new ObjectOutputStream(fos);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			oos.writeObject(theBag);
			System.out.println("Names saved.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void load() {
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream("output_data/names.dat");
			ois = new ObjectInputStream(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			NameBag theBag = (NameBag) ois.readObject();
			Name[] array = theBag.getNames();
			for (int i = 0; i < theBag.getnElems(); i++) {
				System.out.println(array[i]);
			}
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}
}
