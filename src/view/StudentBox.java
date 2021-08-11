package view;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.College;
import model.CourseGrade;
import model.CourseType;
import model.Faculty;
import model.IDMismatchException;
import model.IllegalPhoneException;
import model.IllegalSalaryException;
import model.Major;
import model.MasterCourseBag;
import model.MiniCourse;
import model.MiniCourseBag;
import model.Name;
import model.PeopleBag;
import model.Student;
import utils.Alerts;
import utils.CalculateGPA;
import utils.CourseHelper;
import utils.PeopleHelper;

public class StudentBox {
	private VBox studentBox;
	private HBox box1;
	private HBox box2;
	private HBox box3;
	private HBox buttonBox;

	private TextField firstNameField;
	private TextField lastNameField;
	private TextField idField;
	private TextField majorField;
	private TextField phoneField;
	private TextField gpaField;

	private TextField courseField;
	private TextField statusField;
	private TextField gradeField;

	private Button saveBtn;
	private Button insertBtn;
	private Button searchBtn;
	private Button removeBtn;
	private Button updateBtn;
	private Button courseAttributesBtn;
	private Button setCourseAttributesBtn;
	private Button exportBtn;
	private Button importBtn;
	
	private Label idLbl;
	private Label gpaLbl;
	private Label firstNameLbl;
	private Label lastNameLbl;
	private Label majorLbl;
	private Label phoneLbl;
	private Label listViewLabel;
	
	private Student student;
	private MiniCourse course;
	private double gpa;
	private College college;
	private MasterCourseBag masterCourseBag = CourseHelper.load();
	DecimalFormat df = new DecimalFormat("0.0");

	private ListView<String> coursesToTake;

	private ListView<String> coursesToTakeListView;
	private ComboBox<String> statusBox;
	private ComboBox<String> gradeBox;
	private ObservableList<String> statuses;
	private ObservableList<String> grades;

	private PeopleBag theBag;

	public StudentBox(PeopleBag theBag) {
		this.theBag = theBag;
		studentBox = new VBox(30);
		studentBox.setAlignment(Pos.CENTER);

		idField = new TextField();
		idField.setPromptText("ID");
		idLbl = new Label("ID:");
		gpaField = new TextField();
		gpaField.setPromptText("GPA");
		gpaLbl = new Label("GPA:");
		box1 = new HBox(30);
		box1.setAlignment(Pos.CENTER);
		box1.getChildren().addAll(idLbl, idField, gpaLbl, gpaField);

		firstNameField = new TextField();
		firstNameField.setPromptText("First Name");
		firstNameLbl = new Label("First Name:");
		lastNameField = new TextField();
		lastNameField.setPromptText("Last Name");
		lastNameLbl = new Label("Last Name:");
		majorField = new TextField();
		majorField.setPromptText("Major");
		majorLbl = new Label("Major:");
		phoneField = new TextField();
		phoneField.setPromptText("Phone Number");
		phoneLbl = new Label("Phone:");
		box2 = new HBox(30);
		box2.setAlignment(Pos.CENTER);
		box2.getChildren().addAll(firstNameLbl, firstNameField, lastNameLbl, lastNameField, 
				majorLbl, majorField, phoneLbl, phoneField);

		saveBtn = new Button("Save");
		exportBtn = new Button("Export");
		importBtn = new Button("Import");
		insertBtn = new Button("Insert");
		searchBtn = new Button("Search by ID");
		removeBtn = new Button("Remove");
		updateBtn = new Button("Update");
		buttonBox = new HBox(30);
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.getChildren().addAll(saveBtn, exportBtn, importBtn, insertBtn, searchBtn, removeBtn, updateBtn);

		courseField = new TextField();
		courseField.setPromptText("Course");
		statusField = new TextField();
		statusField.setPromptText("Course Status");
		gradeField = new TextField();
		gradeField.setPromptText("Grade");
		box3 = new HBox(30);
		box3.setAlignment(Pos.CENTER);
		coursesToTakeListView = new ListView<>();
		statusBox = new ComboBox<>();
		gradeBox = new ComboBox<>();
		courseAttributesBtn = new Button("Show Course Attributes");
		setCourseAttributesBtn = new Button("Set Attributes");
		statuses = FXCollections.observableArrayList("TO_TAKE", "TAKING", "TAKEN");
		grades = FXCollections.observableArrayList("A", "B_PLUS", "B", "C_PLUS", "C", "D_PLUS", "D", "F", "NO_GRADE");
		listViewLabel = new Label("Courses:");
		box3.getChildren().addAll(listViewLabel, coursesToTakeListView, statusBox, gradeBox, courseAttributesBtn, setCourseAttributesBtn);
		// box3.getChildren().addAll(courseField, statusField, gradeField);

		// ObservableList<String> coursesToTakeList;

		studentBox.getChildren().addAll(box1, box2, buttonBox, box3);

		setEventListeners();
	}

	public VBox getStudentBox() {
		return studentBox;
	}

	public void setEventListeners() {
		saveBtn.setOnAction(e -> {
			theBag.save(theBag);
		});
		
		exportBtn.setOnAction(e -> {
			PeopleHelper.exportStudents(theBag);
		});
		
		// Work on this at some point
		importBtn.setOnAction(e -> {
			PeopleHelper.importStudents(theBag, "input_data/students_test.txt");
		});
		
		insertBtn.setOnAction(e -> {
			String firstName = firstNameField.getText();
			String lastName = lastNameField.getText();
			Major major = Major.valueOf(majorField.getText());
			String phone = phoneField.getText();
			Name name = new Name(firstName, lastName);
			Student student = new Student(firstName, lastName, phone, major);
			student.setId(String.valueOf(theBag.getnElems() + 1));
			try {
				if (phone.length() != 10){
					Alerts.phoneAlert();
					throw new IllegalPhoneException();
				} else {
					theBag.insert(student);
					clearFields();
					Alerts.insertStudentAlert();
				}
			} catch (IllegalPhoneException e1) {
//				e1.printStackTrace();
			}
		});

		searchBtn.setOnAction(e -> {
			TextInputDialog dialog = new TextInputDialog();
			dialog.setTitle("Search");
			dialog.setContentText("Enter an ID number.");
			Optional<String> result = dialog.showAndWait();
			if (result.isPresent()) {
				try {
					String id = result.get();
					if (theBag.findById(id) == null) {
						Alerts.noPersonAlert();
						throw new NullPointerException();
					} else if (theBag.findById(id) instanceof Faculty) {
						Alerts.isFacultyAlert();
						throw new IDMismatchException();
					} else {
						coursesToTakeListView.getItems().clear();
						idField.setText(id);
						student = (Student) theBag.findById(id);
						firstNameField.setText(student.getFirstName());
						lastNameField.setText(student.getLastName());
						majorField.setText(String.valueOf(student.getMajor()));
						phoneField.setText(student.getPhone());
						gpaField.setText(df.format(CalculateGPA.calculateGPA(student, masterCourseBag)));
						MiniCourseBag miniCourseBag = student.getMiniCourseBag();
//						coursesToTakeListView.setItems(FXCollections.observableArrayList(Arrays.asList(miniCourseBag)));
						ArrayList<String> toTakeList = new ArrayList<>();
						for (int i = 0; i < miniCourseBag.getnElems(); i++) {
							toTakeList.add(miniCourseBag.getMiniCourses(i).getCourseNumber());
						}
						coursesToTakeListView.getItems().addAll(toTakeList);
						statusBox.setItems(statuses);
						gradeBox.setItems(grades);
					}
				} catch (IDMismatchException | NullPointerException e1) {
					// e1.getMessage();
				}
			}
		});

		removeBtn.setOnAction(e -> {
			TextInputDialog dialog = new TextInputDialog();
			dialog.setTitle("Remove");
			dialog.setContentText("Enter an ID.");
			Optional<String> result = dialog.showAndWait();
			
			if (result.isPresent()) {
				try {
					String id = result.get();
					if (theBag.findById(id) == null) {
						Alerts.noPersonAlert();
						throw new NullPointerException();
					} else if (theBag.findById(id) instanceof Faculty) {
						Alerts.isFacultyAlert();
						throw new IDMismatchException();
					} else {
						theBag.deleteById(id);
						Alerts.studentDeletedAlert();
					}
				} catch (IDMismatchException | NullPointerException e1) {
//					e1.getMessage();
				}
			}
		});
		
		updateBtn.setOnAction(e -> {
			String id = idField.getText();
			Major major = Major.valueOf(majorField.getText());
			double gpa = Double.parseDouble(gpaField.getText());
			String firstName = firstNameField.getText();
			String lastName = lastNameField.getText();
			String phone = phoneField.getText();
//			MiniCourseBag miniCourseBag = (MiniCourseBag) coursesToTakeListView.getItems();
			Student student = (Student) theBag.findById(id);
			student.setFirstName(firstName);
			student.setLastName(lastName);
			student.setMajor(major);
			student.setPhone(phone);
			student.setGpa(gpa);
//			student.setMiniCourseBag(miniCourseBag);
			Alerts.personUpdateAlert();
			clearFields();
		});
		
		courseAttributesBtn.setOnAction(e -> {
			String courseNumber = coursesToTakeListView.getSelectionModel().getSelectedItem();
			course = student.getMiniCourseBag().findByCourseNumber(courseNumber);
			statusBox.setValue(String.valueOf(course.getCourseType()));
			gradeBox.setValue(String.valueOf(course.getCourseGrade()));
		});
		
		setCourseAttributesBtn.setOnAction(e -> {
			course.setCourseType(CourseType.valueOf(statusBox.getValue()));
			course.setCourseGrade(CourseGrade.valueOf(gradeBox.getValue()));
			gpa = CalculateGPA.calculateGPA(student, masterCourseBag);
			gpaField.setText(df.format(gpa));
		});
	}

	public void clearFields() {
		idField.clear();
		firstNameField.clear();
		lastNameField.clear();
		phoneField.clear();
		majorField.clear();
		gpaField.clear();
	}
}
