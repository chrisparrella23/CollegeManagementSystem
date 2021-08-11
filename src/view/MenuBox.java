package view;

import java.io.FileNotFoundException;


import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import model.College;
import model.MasterCourseBag;
import model.NameBag;
import model.PeopleBag;
import model.Person;
import model.Textbook;
import model.TextbookBag;
import utils.CourseHelper;
import utils.PeopleHelper;
import utils.TextbookHelper;

public class MenuBox {
	private MenuBar menuBar;
	private Button saveBtn;

	public MenuBox(BorderPane pane, College college) throws FileNotFoundException {
		menuBar = new MenuBar();
		menuBar.prefWidthProperty().bind(pane.widthProperty());

//		College college = new College(10000, 40000, 40000);
		
		final TextbookBag[] textbookBagz = new TextbookBag[1];
		TextbookBag textbookBag = new TextbookBag(40000);
		textbookBagz[0] = textbookBag;
//		Textbook.importPublishers("input_data/Publishers.txt");
//		TextbookHelper.importData(textbookBag, "input_data/textbook_titles.txt", "input_data/textbook_isbns.txt", Textbook.getPublisherArray());
		final MasterCourseBag[] masterCourseBagz = new MasterCourseBag[1];
		MasterCourseBag masterCourseBag = new MasterCourseBag(2000);
		masterCourseBagz[0] = masterCourseBag;
		NameBag nameBag = new NameBag(2000);
		final PeopleBag[] peopleBagz = new PeopleBag[1];
		PeopleBag peopleBag = new PeopleBag(1000);
		peopleBagz[0] = peopleBag;
		NameBag.fillBag(nameBag);
		masterCourseBag = CourseHelper.load();
		textbookBag = TextbookHelper.load();
		peopleBag = PeopleHelper.load();
		
//		PeopleHelper.importData(peopleBag, nameBag);
//		peopleBag.save(peopleBag);
		
//		CourseHelper.importCourses(masterCourseBag, textbookBag, "input_data/Course_Inventory.txt", "input_data/textbook_isbns.txt");
//		masterCourseBag.save(masterCourseBag);
		college.setTextbookBag(textbookBag);
		college.setMasterCourseBag(masterCourseBag);
		college.setPeopleBag(peopleBag);
		
		
//		TextbookHelper.importData(textbookBag, "input_data/textbook_titles.txt", "input_data/textbook_isbns.txt", Textbook.getPublisherArray());
//		TextbookBag.load();
//		textbookBag.save(textbookBag);
//		
		TextbookBox textbookBox = new TextbookBox(college.getTextbookBag());
		CourseBox masterCourseBox = new CourseBox(college.getMasterCourseBag(), college.getTextbookBag());
		StudentBox bigStudentBox = new StudentBox(college.getPeopleBag());
		FacultyBox bigFacultyBox = new FacultyBox(college.getPeopleBag());
		
		VBox bookBox = textbookBox.getTextbookBox();
		VBox courseBox = masterCourseBox.getCourseBox();
		VBox studentBox = bigStudentBox.getStudentBox();
		VBox facultyBox = bigFacultyBox.getFacultyBox();
		
		Menu fileMenu = new Menu("File");
		MenuItem newMenuItem = new MenuItem("New");
		MenuItem saveMenuItem = new MenuItem("Save All");
		MenuItem exitMenuItem = new MenuItem("Exit");
		
		Menu viewMenu = new Menu("View");
		MenuItem textbookItem = new MenuItem("Textbooks");
		MenuItem courseItem = new MenuItem("Courses");
		MenuItem studentItem = new MenuItem("Students");
		MenuItem facultyItem = new MenuItem("Faculty");
		
//		pane.setCenter(courseBox);
		pane.setTop(this.getMenuBar());
		
		saveMenuItem.setOnAction(e -> {
//			masterCourseBagz[0].save(masterCourseBagz[0]);
//			peopleBagz[0].save(peopleBagz[0]);
//			textbookBagz[0].save(textbookBagz[0]);
//			textbookBag.save(textbookBag);
//			masterCourseBag.save(masterCourseBag);
//			peopleBag.save(peopleBag);
		});
		
		exitMenuItem.setOnAction(e -> {
//			masterCourseBagz[0].save(masterCourseBagz[0]);
//			peopleBagz[0].save(peopleBagz[0]);
//			textbookBagz[0].save(textbookBagz[0]);
			Platform.exit();
		});
		
		textbookItem.setOnAction(e -> {
			pane.setCenter(bookBox);
		});
		
		courseItem.setOnAction(e -> {
			pane.setCenter(courseBox);
		});
		
		studentItem.setOnAction(e -> {
			pane.setCenter(studentBox);
		});
		
		facultyItem.setOnAction(e -> {
			pane.setCenter(facultyBox);
		});
		
		fileMenu.getItems().addAll(newMenuItem,
				new SeparatorMenuItem(), saveMenuItem,
				new SeparatorMenuItem(),
				exitMenuItem);

		viewMenu.getItems().addAll(textbookItem, new SeparatorMenuItem(),
				courseItem, new SeparatorMenuItem(),
				studentItem, new SeparatorMenuItem(),
				facultyItem);
		
		
		// add fileMenu to the menuBar:
		menuBar.getMenus().addAll(fileMenu, viewMenu);
	}

	public MenuBar getMenuBar() {
		return menuBar;
	}
	
}
