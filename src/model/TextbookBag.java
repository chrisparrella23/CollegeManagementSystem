package model;

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
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import utils.Alerts;

public class TextbookBag implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2645340491516718861L;
	private Textbook[] books;
	private int nElems;
	
	public TextbookBag(int maxSize) {
		books = new Textbook[maxSize];
		nElems = 0;
	}
	
	public Textbook[] getBooks() {
		return books;
	}
	
	public int getnElems() {
		return nElems;
	}
	
	public void insert(Textbook textbook) {
		books[nElems++] = textbook;
	}
	
	public void display() {
		for (int i = 0; i < nElems; i++) {
			System.out.println(books[i]);
		}
		System.out.println();
	}
	
	public Textbook findByISBN(String isbn) {
		for (int i = 0; i < nElems; i++) {
			if (isbn.equals(books[i].getIsbn())) {
				return books[i];
			}
		}
		return null;
	}
	
	// If this method is static, then
	// books and nElems have to be static!
	// That will affect saving!
	// Can I change that?
	public Textbook deleteByISBN(String isbn) {
		int i = 0;
		for (i = 0; i < nElems; i++) {
			if (books[i].getIsbn().equals(isbn)) {
				break;
			}
		}

		if (i == nElems) {
			return null;
		} else {
			Textbook temp = books[i];
			for (int j = i; j < nElems - 1; j++) {
				books[j] = books[j + 1];
			}
			nElems--;
			return temp;
		}
	}
	
	public Textbook getRandomTextbook() {
		Random rand = new Random();
		Textbook textbook = getBooks()[rand.nextInt(nElems)];
		return textbook;
	}
	
	public String getRandomISBN() {
		Random rand = new Random();
		String isbn = getBooks()[rand.nextInt(1 + nElems)].getIsbn();
		return isbn;
	}
	
	public void save(TextbookBag theBag) {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream("output_data/books.dat");
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
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void load() {
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream("output_data/books.dat");
			ois = new ObjectInputStream(fis);
		} catch (IOException e) {
//			Alerts.loadFailAlert();
//			e.printStackTrace();
		}
		try {
			TextbookBag theBag = (TextbookBag) ois.readObject();
			Textbook[] array = theBag.getBooks();
			System.out.println("Textbooks loaded.");
		} catch (ClassNotFoundException | IOException e) {
//			Alerts.loadFailAlert();
//			e.printStackTrace();
		}
	}
	
	public void export(TextbookBag theBag) {
		Textbook[] array = theBag.getBooks();
		FileWriter fw = null;
		try {
			fw = new FileWriter("output_data/books.txt");
		} catch (IOException e) {
			Alerts.saveFailAlert();
			e.printStackTrace();
		}
		PrintWriter pw = new PrintWriter(fw);
//		pw.format("%-20s%15s%15s%15s%20s\n", "Title", "LastName", "FirstName", "ISBN", "Price");
		for (int i = 0; i < theBag.getnElems(); i++) {
			Textbook book = array[i];
			pw.format("%s|\n", book.exportString());
		}
		pw.close();
		Alerts.textSaveAlert();
	}
	
	public void importBooks(TextbookBag theBag, String importFile) {
		File f = new File(importFile);
		try {
			Scanner fileScan = new Scanner(f);
			while (fileScan.hasNextLine()) {
				String[] arr = fileScan.nextLine().split("\\|");
				
				String title = arr[0];
				double price = Double.parseDouble(arr[1]);
				String isbn = arr[2];
				String publisher = arr[3];
				ArrayList<Name> nameList = new ArrayList<Name>();
				for (int i = 4; i < arr.length; i++) {
					String firstName = arr[i++];
					String lastName = arr[i];
					Name name = new Name(firstName, lastName);
					nameList.add(name);
				}
				Textbook textbook = new Textbook(title, price, isbn, nameList, publisher);
				theBag.insert(textbook);
			}
			Alerts.textLoadAlert();
		} catch (FileNotFoundException e) {
			Alerts.noFileAlert();
		}
	}
}
