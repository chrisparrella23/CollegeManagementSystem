package view;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Optional;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.IllegalISBNException;
import model.Name;
import model.Textbook;
import model.TextbookBag;
import utils.Alerts;

public class TextbookBox {
	DecimalFormat df = new DecimalFormat("0.00");
	private VBox textbookBox;
	private HBox box1;
	private HBox box2;
	private HBox buttonBox;

	private TextArea titleField;
	private TextField isbnField;
	private TextField priceField;
	private TextArea authorArea;
	private TextField publisherField;
	private TextField firstNameField;
	private TextField lastNameField;
	
	private Label titleLbl;
	private Label isbnLbl;
	private Label priceLbl;
	private Label authorLbl;
	private Label publisherLbl;
	private Label firstNameLbl;
	private Label lastNameLabl;

	private Button insertBtn;
	private Button searchBtn;
	private Button removeBtn;
	private Button updateBtn;
	private Button addAuthorBtn;
	private Button saveBtn;
	private Button exportBtn;
	private Button importBtn;
	private ArrayList<Name> nameList;
	
	private TextbookBag theBag;
	
	public TextbookBox(TextbookBag theBag) {
		this.theBag = theBag;
		textbookBox = new VBox(30);
		textbookBox.setAlignment(Pos.CENTER);

		isbnField = new TextField();
		isbnField.setPromptText("ISBN");
		isbnLbl = new Label("ISBN:");

		publisherField = new TextField();
		publisherField.setPromptText("Publisher");
		publisherLbl = new Label("Publisher:");
		box1 = new HBox(30);
		box1.setAlignment(Pos.CENTER);
		box1.getChildren().addAll(isbnLbl, isbnField, publisherLbl, publisherField);

		titleField = new TextArea();
		titleField.setPromptText("Title");
		titleField.setPrefSize(250, 10);
		titleField.setWrapText(true);
		titleLbl = new Label("Title:");
		
		priceField = new TextField();
		priceField.setPromptText("Price");
		priceLbl = new Label("Price:");
		
		authorArea = new TextArea();
		authorArea.setPromptText("Author(s)");
		authorArea.setPrefSize(250, 100);
		authorArea.setWrapText(true);
		authorLbl = new Label("Author(s):");
		
		box2 = new HBox(30);
		box2.setAlignment(Pos.CENTER);
		box2.getChildren().addAll(titleLbl, titleField, priceLbl, 
				priceField, authorLbl, authorArea);

		saveBtn = new Button("Save");
		exportBtn = new Button("Export");
		importBtn = new Button("Import");
		insertBtn = new Button("Insert");
		searchBtn = new Button("Search by ISBN");
		removeBtn = new Button("Remove");
		updateBtn = new Button("Update");
		addAuthorBtn = new Button("Add Author");
		firstNameField = new TextField();
		firstNameField.setPromptText("First Name");
		lastNameField = new TextField();
		lastNameField.setPromptText("Last Name");
		buttonBox = new HBox(30);
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.getChildren().addAll(saveBtn, exportBtn, importBtn, insertBtn, searchBtn, removeBtn, updateBtn, addAuthorBtn, 
				firstNameField, lastNameField);

		textbookBox.getChildren().addAll(box1, box2, buttonBox);

		setEventListeners();
	}
	
	public VBox getTextbookBox() {
		return textbookBox;
	}
	
	public void setEventListeners() {
		saveBtn.setOnAction(e -> {
			theBag.save(theBag);
		});
		
		exportBtn.setOnAction(e -> {
			theBag.export(theBag);
		});
		
		importBtn.setOnAction(e -> {
			// try using a dialog tool here
			theBag.importBooks(theBag, "input_data/textbook_test.txt");
		});
		
		insertBtn.setOnAction(e -> {
			ArrayList<Name> nameList = new ArrayList<>();
			String isbn = isbnField.getText();
			String title = titleField.getText();
			double price = Double.parseDouble(priceField.getText());
			String firstName = firstNameField.getText();
			String lastName = lastNameField.getText();
			Name name = new Name(firstName, lastName);
			String publisher = publisherField.getText();
			nameList.add(name);
			Textbook book = new Textbook(title, price, isbn, nameList, publisher);
			try {
				if (isbn.length() != 17) {
					Alerts.isbnAlert();
					throw new IllegalISBNException();
				} else {
					theBag.insert(book);
					clearFields();
					Alerts.insertBookAlert();
				}
			} catch (IllegalISBNException e1) {
//				e1.printStackTrace();
			}
		});
		
		searchBtn.setOnAction(e -> {
			TextInputDialog dialog = new TextInputDialog();
			dialog.setTitle("Search");
			dialog.setContentText("Enter an ISBN.");
			Optional<String> result = dialog.showAndWait();
			if (result.isPresent()) {
				String isbn = result.get();
				try {
					if (isbn.length() != 17 || theBag.findByISBN(isbn) == null) {
						throw new IllegalISBNException();
					} else {
						isbnField.setText(isbn);
						Textbook textbook = theBag.findByISBN(isbn);
						titleField.setText(textbook.getTitle());
						priceField.setText(df.format(textbook.getPrice()));
						authorArea.setText(String.valueOf(textbook.getNameList()));
						publisherField.setText(textbook.getPublisher());
					}
				} catch (IllegalISBNException e1) {
					Alerts.noBookAlert();
//					e1.printStackTrace();
				}
				
			} 
		}); 
		
		removeBtn.setOnAction(e -> {
			TextInputDialog dialog = new TextInputDialog();
			dialog.setTitle("Remove");
			dialog.setContentText("Enter an ISBN.");
			Optional<String> result = dialog.showAndWait();
			
			if (result.isPresent()) {
				String isbn = result.get();
				try {
					if (isbn.length() != 17 || theBag.findByISBN(isbn) == null) {
						Alerts.noBookAlert();
						throw new IllegalISBNException();
						
					} else {
						theBag.deleteByISBN(isbn);
						clearFields();
						Alerts.bookDeletedAlert();
					}
				} catch (IllegalISBNException e1) {
//					e1.printStackTrace();
				}
				
			}
		});
		
		updateBtn.setOnAction(e -> {
			String isbn = isbnField.getText();
			String title = titleField.getText();
			double price = Double.parseDouble(priceField.getText());
//			ArrayList<Name> oldList = 
//			ArrayList<Name> newList = new ArrayList<>();
			Textbook textbook = theBag.findByISBN(isbn);
			textbook.setTitle(title);
			textbook.setPrice(price);
			textbook.setNameList(nameList);
			clearFields();
			Alerts.bookUpdateAlert();
		});
		
		addAuthorBtn.setOnAction(e -> {
			String isbn = isbnField.getText();
			Textbook textbook = theBag.findByISBN(isbn);
			nameList = textbook.getNameList();
//			ArrayList<Name> nameList = new ArrayList<>();
			String firstName = firstNameField.getText();
			String lastName = lastNameField.getText();
			Name name = new Name(firstName, lastName);
			nameList.add(name);
			firstNameField.clear();
			lastNameField.clear();
			Alerts.addAuthorAlert();
		});
		
	}
	
	public void clearFields() {
		isbnField.clear();
		titleField.clear();
		priceField.clear();
		publisherField.clear();
		authorArea.clear();
		firstNameField.clear();
		lastNameField.clear();
	}
}
