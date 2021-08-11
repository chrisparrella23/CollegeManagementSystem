package view;

import java.util.Optional;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Faculty;
import model.IDMismatchException;
import model.IllegalPhoneException;
import model.IllegalSalaryException;
import model.Name;
import model.PeopleBag;
import model.Person;
import model.Student;
import model.Title;
import utils.Alerts;
import utils.PeopleHelper;

public class FacultyBox {
	private VBox facultyBox;
	private HBox box1;
	private HBox box2;
	private HBox buttonBox;
	
	private TextField firstNameField;
	private TextField lastNameField;
	private TextField idField;
	private TextField salaryField;
	private TextField titleField;
	private TextField phoneField;
	
	private Button saveBtn;
	private Button exportBtn;
	private Button importBtn;
	private Button insertBtn;
	private Button searchBtn;
	private Button removeBtn;
	private Button updateBtn;
	
	private Label firstNameLbl;
	private Label lastNameLbl;
	private Label idLbl;
	private Label salaryLbl;
	private Label titleLbl;
	private Label phoneLbl;
	
	private PeopleBag theBag;
	
	public FacultyBox(PeopleBag theBag) {
		this.theBag = theBag;
		facultyBox = new VBox(30);
		facultyBox.setAlignment(Pos.CENTER);
		
		idField = new TextField();
		idField.setPromptText("ID");
		idLbl = new Label("ID:");
		titleField = new TextField();
		titleField.setPromptText("Title");
		titleLbl = new Label("Title:");
		box1 = new HBox(30);
		box1.setAlignment(Pos.CENTER);
		box1.getChildren().addAll(idLbl, idField, titleLbl, titleField);
		
		firstNameField = new TextField();
		firstNameField.setPromptText("First Name");
		firstNameLbl = new Label("First Name:");
		
		lastNameField = new TextField();
		lastNameField.setPromptText("Last Name");
		lastNameLbl = new Label("Last Name:");
		
		salaryField = new TextField();
		salaryField.setPromptText("Salary");
		salaryLbl = new Label("Salary:");
		
		phoneField = new TextField();
		phoneField.setPromptText("Phone Number");
		phoneLbl = new Label("Phone:");
		box2 = new HBox(30);
		box2.setAlignment(Pos.CENTER);
		box2.getChildren().addAll(firstNameLbl, firstNameField, lastNameLbl, lastNameField, 
				salaryLbl, salaryField, phoneLbl, phoneField);

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
	
		facultyBox.getChildren().addAll(box1, box2, buttonBox);
		
		setEventListeners();
	}
	
	public VBox getFacultyBox() {
		return facultyBox;
	}
	
	public void setEventListeners() {
		saveBtn.setOnAction(e ->{
			theBag.save(theBag);
			Alerts.saveAlert();
		});
		
		exportBtn.setOnAction(e -> {
			PeopleHelper.exportFaculty(theBag);
		});
		
		importBtn.setOnAction(e -> {
			PeopleHelper.importFaculty(theBag, "input_data/faculty_test.txt");
//			Alerts.textLoadAlert();
		});
		
		//Fix the ID Problem!
		insertBtn.setOnAction(e -> {
			String firstName = firstNameField.getText();
			String lastName = lastNameField.getText();
			String phone = phoneField.getText();
			Title title = Title.valueOf(titleField.getText());
			int salary = Integer.parseInt(salaryField.getText());
			Name name = new Name(firstName, lastName);
			Faculty faculty = new Faculty(name, phone, title, salary);
			faculty.setId(String.valueOf(theBag.getnElems() + 1));
			try {
				if (salary < 10000 || salary > 100000) {
					Alerts.salaryAlert();
					throw new IllegalSalaryException();
				} else if (phone.length() != 10){
					Alerts.phoneAlert();
					throw new IllegalPhoneException();
				} else {
					theBag.insert(faculty);
					clearFields();
					Alerts.insertFacultyAlert();
				}
			} catch (IllegalSalaryException | IllegalPhoneException e1) {
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
					} else if (theBag.findById(id) instanceof Student) {
						Alerts.isStudentAlert();
						throw new IDMismatchException();
					} else {
						idField.setText(id);
						Person faculty = theBag.findById(id);
						firstNameField.setText(faculty.getFirstName());
						lastNameField.setText(faculty.getLastName());
						titleField.setText(String.valueOf(((Faculty) faculty).getTitle()));
						phoneField.setText(faculty.getPhone());
						salaryField.setText(String.valueOf(((Faculty) faculty).getSalary()));
					}
				} catch (IDMismatchException | NullPointerException e1) {
//					e1.getMessage();
//					e1.printStackTrace();
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
					} else if (theBag.findById(id) instanceof Student) {
						Alerts.isStudentAlert();
						throw new IDMismatchException();
					} else {
						theBag.deleteById(id);
						Alerts.facultyDeletedAlert();
					}
				} catch (IDMismatchException | NullPointerException e1) {
//					e1.getMessage();
				}
			}
		});
		
		updateBtn.setOnAction(e -> {
			String id = idField.getText();
			Title title = Title.valueOf(titleField.getText());
			int salary = Integer.parseInt(salaryField.getText());
			String firstName = firstNameField.getText();
			String lastName = lastNameField.getText();
			String phone = phoneField.getText();
			Name name = new Name(firstName, lastName);
			Faculty faculty = (Faculty) theBag.findById(id);
			faculty.setFirstName(firstName);
			faculty.setLastName(lastName);
			faculty.setPhone(phone);
			faculty.setSalary(salary);
			faculty.setTitle(title);
			clearFields();
		});
	}
	
	public void clearFields() {
		idField.clear();
		firstNameField.clear();
		lastNameField.clear();
		salaryField.clear();
		titleField.clear();
		phoneField.clear();
	}
}
