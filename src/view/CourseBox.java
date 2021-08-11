package view;

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
import model.Course;
import model.IllegalCourseException;
import model.IllegalCreditsException;
import model.IllegalISBNException;
import model.MasterCourseBag;
import model.Textbook;
import model.TextbookBag;
import utils.Alerts;
import utils.CourseHelper;

public class CourseBox {
	private VBox courseBox;
	private HBox box1;
	private HBox box2;
	private HBox buttonBox;
	
	private TextField courseNumberField;
	private TextField courseTitleField;
	private TextArea courseDescriptionArea;
	private TextField creditField;
	private TextField isbnField;
	
	private Button saveBtn;
	private Button insertBtn;
	private Button searchBtn;
	private Button removeBtn;
	private Button updateBtn;
	private Button exportBtn;
	private Button importBtn;

	private Label courseNumberLbl;
	private Label courseTitleLbl;
	private Label courseDescriptionLbl;
	private Label creditLbl;
	private Label isbnLbl;
	
	private MasterCourseBag theBag;
	private TextbookBag bookBag;
	
	public CourseBox(MasterCourseBag theBag, TextbookBag bookBag) {
		this.theBag = theBag;
		this.bookBag = bookBag;
		courseBox = new VBox(30);
		courseBox.setAlignment(Pos.CENTER);
		
		courseNumberField = new TextField();
		courseNumberField.setPromptText("Course Number");
		courseNumberLbl = new Label("Course Number:");
		courseTitleField = new TextField();
		courseTitleField.setPromptText("Course Title");
		courseTitleLbl = new Label("Course Title:");
		isbnField = new TextField();
		isbnField.setPromptText("ISBN");
		isbnLbl = new Label("ISBN:");
		box1 = new HBox(30);
		box1.setAlignment(Pos.CENTER);
		box1.getChildren().addAll(courseNumberLbl, courseNumberField, courseTitleLbl, courseTitleField,
				isbnLbl, isbnField);
		
		courseDescriptionArea = new TextArea();
		courseDescriptionArea.setPromptText("Course Description");
		courseDescriptionArea.setWrapText(true);
		courseDescriptionLbl = new Label("Course Description:");
		creditField = new TextField();
		creditField.setPromptText("Credits");
		creditLbl = new Label("Credits:");
		box2 = new HBox(30);
		box2.setAlignment(Pos.CENTER);
		box2.getChildren().addAll(courseDescriptionLbl, courseDescriptionArea, creditLbl, creditField);
		
		saveBtn = new Button("Save");
		exportBtn = new Button("Export");
		importBtn = new Button("Import");
		insertBtn = new Button("Insert");
		searchBtn = new Button("Search by Course Number");
		removeBtn = new Button("Remove");
		updateBtn = new Button("Update");
		buttonBox = new HBox(30);
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.getChildren().addAll(saveBtn, exportBtn, importBtn, insertBtn, searchBtn, removeBtn, updateBtn);
		
		courseBox.getChildren().addAll(box1, box2, buttonBox);
		
		setEventListeners();
	}
	
	public VBox getCourseBox() {
		return courseBox;
	}
	
	public void setEventListeners() {
		saveBtn.setOnAction(e -> {
			theBag.save(theBag);
			Alerts.saveAlert();
		});
		
		exportBtn.setOnAction(e -> {
			CourseHelper.export(theBag);
		});
		
		importBtn.setOnAction(e -> {
			CourseHelper.importCourseText(theBag, "input_data/courses_test.txt");
		});
		
		insertBtn.setOnAction(e -> {
			String courseNumber = courseNumberField.getText();
			String courseTitle = courseTitleField.getText();
			String courseDescription = courseDescriptionArea.getText();
//			Textbook textbook = TextbookBag.getRandomTextbook();
			Textbook textbook = bookBag.getRandomTextbook();
			double credits = Double.parseDouble(creditField.getText());
			String isbn = isbnField.getText();
			Course course = new Course(courseNumber, courseTitle, courseDescription, isbn, credits);
			try {
				if (credits < 1.0) {
					Alerts.creditsAlert();
					throw new IllegalCreditsException();
				} else if (isbn.length() != 17) {
					Alerts.isbnAlert();
					throw new IllegalISBNException();
				} else {
					theBag.insert(course);
					clearFields();
					Alerts.insertCourseAlert();
				}
			} catch (IllegalCreditsException | IllegalISBNException e1) {
//				Alerts.creditsAlert();
			}
		});
		
		searchBtn.setOnAction(e -> {
			TextInputDialog dialog = new TextInputDialog();
			dialog.setTitle("Search");
			dialog.setContentText("Enter a course number.");
			Optional<String> result = dialog.showAndWait();
			if (result.isPresent()) {
				String courseNumber = result.get();
				try {
					if (theBag.findByCourseNumber(courseNumber) == null) {
						Alerts.noCourseAlert();
						throw new IllegalCourseException();
					} else {
						courseNumberField.setText(courseNumber);
						Course course = theBag.findByCourseNumber(courseNumber);
						courseTitleField.setText(course.getTitle());
						courseDescriptionArea.setText(course.getCourseDescription());
						creditField.setText(String.valueOf(course.getCredits()));
						isbnField.setText(course.getIsbn());
					}
				} catch (IllegalCourseException e1) {
//					e1.printStackTrace();
				}
			}
		});
		
		removeBtn.setOnAction(e -> {
			TextInputDialog dialog = new TextInputDialog();
			dialog.setTitle("Remove");
			dialog.setContentText("Enter a course number.");
			Optional<String> result = dialog.showAndWait();
			
			if (result.isPresent()) {
				try {
					String courseNumber = result.get();
					if (theBag.findByCourseNumber(courseNumber) == null) {
						Alerts.noCourseAlert();
						throw new NullPointerException();
					} else {
						theBag.deleteByCourseNumber(courseNumber);
						clearFields();
						Alerts.courseDeletedAlert();
					}
				} catch (NullPointerException e1) {
//					e1.getMessage();
				}
			}
		});
		
		updateBtn.setOnAction(e -> {
			String courseNumber = courseNumberField.getText();
			String courseTitle = courseTitleField.getText();
			String courseDescription = courseDescriptionArea.getText();
			double credits = Double.parseDouble(creditField.getText());
			String isbn = isbnField.getText();
			Course course = theBag.findByCourseNumber(courseNumber);
			course.setCourseNumber(courseNumber);
			course.setTitle(courseTitle);
			course.setCourseDescription(courseDescription);
			course.setCredits(credits);
			course.setIsbn(isbn);
			clearFields();
			Alerts.courseUpdateAlert();
		});
	}
	
	public void clearFields() {
		courseNumberField.clear();
		courseTitleField.clear();
		courseDescriptionArea.clear();
		creditField.clear();
		isbnField.clear();
	}
}
