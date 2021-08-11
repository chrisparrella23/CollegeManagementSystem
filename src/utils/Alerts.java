package utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.IllegalSalaryException;

public class Alerts {
	public static void saveAlert() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Info");
		alert.setHeaderText("Saved");
		alert.setContentText("File saved.");
		alert.showAndWait();
	}
	
	public static void textSaveAlert() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Info");
		alert.setHeaderText("Saved");
		alert.setContentText("Text file saved successfully.");
		alert.showAndWait();
	}
	
	public static void textLoadAlert() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Info");
		alert.setHeaderText("Import");
		alert.setContentText("Text file loaded successfully.");
		alert.showAndWait();
	}
	
	public static void salaryAlert() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Invalid Input");
		alert.setHeaderText("Invalid Salary");
		alert.setContentText("Salary must be between 10000 and 100000");
		alert.showAndWait();
	}
	
	public static void phoneAlert() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Invalid Input");
		alert.setHeaderText("Invalid Phone Number");
		alert.setContentText("Phone number must be 10 digits long.");
		alert.showAndWait();
	}
	
	public static void isbnAlert() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Invalid Input");
		alert.setHeaderText("Invalid ISBN");
		alert.setContentText("ISBN must be 17 characters long.");
		alert.showAndWait();
	}
	
	public static void creditsAlert() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Invalid Input");
		alert.setHeaderText("Invalid Credits");
		alert.setContentText("Course must have at least 1 credit.");
		alert.showAndWait();
	}
	
	public static void insertFacultyAlert() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Info");
		alert.setHeaderText("Person added");
		alert.setContentText("The faculty member was added.");
		alert.showAndWait();
	}
	
	public static void insertStudentAlert() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Info");
		alert.setHeaderText("Person added");
		alert.setContentText("The student was added.");
		alert.showAndWait();
	}
	
	public static void insertBookAlert() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Info");
		alert.setHeaderText("Book added");
		alert.setContentText("The textbook was added.");
		alert.showAndWait();
	}
	
	public static void insertCourseAlert() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Info");
		alert.setHeaderText("Course added");
		alert.setContentText("The course was added.");
		alert.showAndWait();
	}
	
	public static void noPersonAlert() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText("No Person Found");
		alert.setContentText("No person was found to have this ID.");
		alert.showAndWait();
	}
	
	public static void noBookAlert() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText("ISBN not found");
		alert.setContentText("The given ISBN could not be found.");
		alert.showAndWait();
	}
	
	public static void noCourseAlert() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText("Course not found");
		alert.setContentText("The given course could not be found.");
		alert.showAndWait();
	}
	
	public static void isStudentAlert() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText("ID Mismatch");
		alert.setContentText("This ID belongs to a student.");
		alert.showAndWait();
	}
	
	public static void isFacultyAlert() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText("ID Mismatch");
		alert.setContentText("This ID belongs to a faculty member.");
		alert.showAndWait();
	}
	
	public static void facultyDeletedAlert() {
		Alert alert1 = new Alert(AlertType.INFORMATION);
		alert1.setTitle("Info");
		alert1.setHeaderText("Person deleted");
		alert1.setContentText("The faculty member was deleted.");
		alert1.showAndWait();
	}
	
	public static void studentDeletedAlert() {
		Alert alert1 = new Alert(AlertType.INFORMATION);
		alert1.setTitle("Info");
		alert1.setHeaderText("Person deleted");
		alert1.setContentText("The student was deleted.");
		alert1.showAndWait();
	}
	
	public static void bookDeletedAlert() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Info");
		alert.setHeaderText("Book deleted");
		alert.setContentText("The textbook was deleted.");
		alert.showAndWait();
	}
	
	public static void courseDeletedAlert() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Info");
		alert.setHeaderText("Course deleted");
		alert.setContentText("The course was deleted.");
		alert.showAndWait();
	}
	
	public static void personUpdateAlert() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Info");
		alert.setHeaderText("Person updated");
		alert.setContentText("The person was updated.");
		alert.showAndWait();
	}
	
	public static void bookUpdateAlert() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Info");
		alert.setHeaderText("Book updated");
		alert.setContentText("The textbook was updated.");
		alert.showAndWait();
	}
	
	public static void courseUpdateAlert() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Info");
		alert.setHeaderText("Update");
		alert.setContentText("The course was updated.");
		alert.showAndWait();
	}
	
	public static void addAuthorAlert() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Info");
		alert.setHeaderText("Author added");
		alert.setContentText("The author was added.");
		alert.showAndWait();
	}
	
	public static void noFileAlert() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText("File not Found");
		alert.setContentText("The specified file could not be found.");
		alert.showAndWait();
	}
	
	public static void saveFailAlert() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText("IO Exception");
		alert.setContentText("The file could not be saved.");
		alert.showAndWait();
	}
	
	public static void loadFailAlert() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText("IO Exception");
		alert.setContentText("The file could not be loaded.");
		alert.showAndWait();
	}
}
