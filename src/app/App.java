package app;

import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.College;
import model.CourseGrade;
import model.CourseType;
import model.MajorTemplate;
import model.MajorTemplateCourse;
import model.MasterCourseBag;
import model.MiniCourse;
import model.NameBag;
import model.PeopleBag;
import model.Person;
import model.Textbook;
import model.TextbookBag;
import utils.CourseHelper;
import utils.PeopleHelper;
import utils.TextbookHelper;
import view.CourseBox;
import view.MenuBox;
import view.TextbookBox;

public class App extends Application {

	College college = new College(10000, 40000, 40000);
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void init() throws FileNotFoundException {
		
		
		NameBag nameBag = new NameBag(2000);
		NameBag.fillBag(nameBag);
		PeopleBag peopleBag = new PeopleBag(1000);
//		PeopleHelper.importData(peopleBag, nameBag);
//		college.setPeopleBag(peopleBag);
//		peopleBag.save(peopleBag);
		
		TextbookBag textbookBag = new TextbookBag(40000);
		Textbook.importPublishers("input_data/Publishers.txt");
//		TextbookHelper.importData(textbookBag, "input_data/textbook_titles.txt", "input_data/textbook_isbns.txt", Textbook.getPublisherArray());
//		textbookBag.save(textbookBag);
//		TextbookBag.load();
//		masterCourseBag = CourseHelper.load();
		long time = (System.currentTimeMillis());
		textbookBag = TextbookHelper.load();
		System.out.println((System.currentTimeMillis() - time) / 1000);
		college.setTextbookBag(textbookBag);
		
		
		
//		TextbookHelper.importData(textbookBag, "input_data/textbook_titles.txt", "input_data/textbook_isbns.txt", Textbook.getPublisherArray());
//		TextbookBag.load();
		
		MasterCourseBag masterCourseBag = new MasterCourseBag(2000);
//		CourseHelper.importCourses(masterCourseBag, textbookBag, "input_data/Course_Inventory.txt", "input_data/textbook_isbns.txt");
		college.setMasterCourseBag(masterCourseBag);
//		masterCourseBag.save(masterCourseBag);
		
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
//		College college = new College(10000, 20000, 20000);
		TextbookBox textbookBox = new TextbookBox(college.getTextbookBag());
//		CourseBox masterCourseBox = new CourseBox(college.getMasterCourseBag(), college.getTextbookBag());
//		StudentBox studentBox = new StudentBox();
		VBox bookBox = textbookBox.getTextbookBox();
//		VBox courseBox = masterCourseBox.getCourseBox();
		
		BorderPane root = new BorderPane();
		MenuBox menuBox = new MenuBox(root, college);
		
		
		root.setCenter(bookBox);
		root.setTop(menuBox.getMenuBar());
		
		Scene scene = new Scene(root, 1100, 500);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
}
