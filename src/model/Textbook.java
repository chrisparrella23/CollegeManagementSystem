package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import utils.TextbookHelper;

public class Textbook implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5806765251271175440L;
	private String title;
	private String firstName;
	private String lastName;
	private double price;
	private String isbn;
	private ArrayList<Name> nameList;
	private String publisher;
	private DecimalFormat df = new DecimalFormat("0.00");
	private static String[] publishers;

	public Textbook() {
		super();
		int rand = (int) (Math.random() * TextbookHelper.getIsbns().length);
		title = TextbookHelper.getTitles()[rand];
		price = (Math.random() * 200);

		try {
			if (isbn.length() < 17 || isbn.length() > 17) {
				throw new IllegalISBNException("ISBN must be 17 digits.");
			}
			this.isbn = isbn;
		} catch (IllegalISBNException e) {
			System.out.println(e.getMessage());
		}
	}

	public Textbook(String title, double price, String isbn, ArrayList<Name> nameList, String publisher) {
		this.title = title;
		this.price = price;
		this.isbn = isbn;
		this.nameList = nameList;
		this.publisher = publisher;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public ArrayList<Name> getNameList() {
		return nameList;
	}

	public void setNameList(ArrayList<Name> nameList) {
		this.nameList = nameList;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public static String[] importPublishers(String publisherFile) throws FileNotFoundException {
		int counter = 0;
		File fileForPublishers = new File(publisherFile);
		Scanner in = new Scanner(fileForPublishers);
		Scanner in2 = new Scanner(fileForPublishers);
		while (in.hasNextLine()) {
			counter++;
			in.nextLine();
		}
		publishers = new String[counter];
		while (in2.hasNextLine()) {
			for (int i = 0; i < counter; i++) {
				publishers[i] = in2.nextLine();
			}
			return publishers;
		}
		return null;
	}

	public static String[] getPublisherArray() {
		return publishers;
	}

	public static String getRandomPublisher() {
		Random rand = new Random();
		String publisher = publishers[rand.nextInt(publishers.length)];
		return publisher;
	}

	public static void displayPublishers() {
		for (int i = 0; i < publishers.length; i++) {
			System.out.println(publishers[i]);
		}
		System.out.println();
	}

	@Override
	public String toString() {
		return "Textbook [title=" + title + ", price=" + df.format(price) + ", isbn=" + isbn + ", nameList=" + nameList
				+ ", publisher=" + publisher + "]";
	}
	
	public String exportString() {
		StringBuilder export = new StringBuilder();
		for (int i = 0; i < nameList.size(); i++) {
			String name = nameList.get(i).exportString();
			export.append(String.format("%s", name));
		}
		String string = export.toString();
		return title + "|" + price + "|" + isbn + "|" + publisher + "|" + export;
	}

}
