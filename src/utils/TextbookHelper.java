package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

import model.Name;
import model.Textbook;
import model.TextbookBag;

public class TextbookHelper implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String[] titles;
	private static String[] isbns;
	private static int bookCount = 0;
	
	public static void importData(TextbookBag textbookBag, String titleFile, String isbnFile, String[] publishers) throws FileNotFoundException {
		File fileForTitle = new File(titleFile);
		File fileForIsbns = new File(isbnFile);
		publishers = Textbook.getPublisherArray();
		BufferedReader br1 = new BufferedReader(new FileReader(fileForTitle));
		BufferedReader br2 = new BufferedReader(new FileReader(fileForIsbns));
		Scanner titleScanner = new Scanner(br1);
		Scanner isbnScanner =new Scanner(br2);
		
		while(titleScanner.hasNextLine()) {
			String title = titleScanner.nextLine();
			String isbn = isbnScanner.nextLine();
			ArrayList<Name> nameList = NameFactory.emitNameList();
			double price = (Math.random() * 200);
			String publisher = Textbook.getRandomPublisher();
			Textbook textbook = new Textbook(title, price, isbn, nameList, publisher);
			textbookBag.insert(textbook);
			bookCount++;
		}
	}
	
	public static int getBookCount() {
		return bookCount;
	}
	
	public static String[] getTitles() {
		return titles;
	}
	
	public static String[] getIsbns() {
		return isbns;
	}
	
	public static void save(TextbookBag theBag) {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream("output_data/books.dat");
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
			System.out.println("Textbooks saved.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static TextbookBag load() {
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream("output_data/books.dat");
			ois = new ObjectInputStream(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			TextbookBag theBag = (TextbookBag) ois.readObject();
			Textbook[] array = theBag.getBooks();
			return theBag;
//			for (int i = 0; i < theBag.getnElems(); i++) {
//				System.out.println(array[i]);
//			}
//			return theBag;
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void export(TextbookBag theBag) {
		Textbook[] array = theBag.getBooks();
		FileWriter fw = null;
		try {
			fw = new FileWriter("output_data/books.txt", false);
		} catch (IOException e) {
//			e.printStackTrace();
		}
		PrintWriter pw = new PrintWriter(fw);
		for (int i = 0; i < theBag.getnElems(); i++) {
			pw.format("%-5s%10s%40s%20s%20s\n", array[i].getTitle(), array[i].getPrice(), array[i].getNameList(), array[i].getIsbn(), array[i].getPublisher());
		}
		pw.close();
		Alerts.textSaveAlert();
	}
}
