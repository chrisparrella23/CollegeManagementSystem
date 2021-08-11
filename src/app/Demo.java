package app;

import java.io.FileNotFoundException;

import model.CourseGrade;
import model.CourseType;
import model.Major;
import model.MajorBag;
import model.MasterCourseBag;
import model.MiniCourse;
import model.MiniCourseBag;
import model.NameBag;
import model.PeopleBag;
import model.Person;
import model.Student;
import model.TextbookBag;
import utils.NameFactory;

public class Demo {

	public static void main(String[] args) throws FileNotFoundException {
//		College college = new College()
		NameBag nameBag = new NameBag(2000);
		TextbookBag textbookBag = new TextbookBag(40000);
		MasterCourseBag masterCourseBag = new MasterCourseBag(2000);
		
//		NameBag.fillBag(nameBag);
//		Textbook.importPublishers("input_data/Publishers.txt");
//		TextbookHelper.importData(textbookBag, "input_data/textbook_titles.txt", "input_data/textbook_isbns.txt", Textbook.getPublisherArray());
//		CourseHelper.importCourses(masterCourseBag, textbookBag, "input_data/Course_Inventory.txt");
		
//		NameFactory.displayFirstNames();
//		NameFactory.displayLastNames();
//		nameBag.displayNames();
//		masterCourseBag.display();
		
		
//		Person s1 = new Student("John", "Smith", "6314959800", Major.CSE, null);
//		System.out.println(s1);
//		Person s2 = new Student();
//		System.out.println(s2);
//		Person f1 = new Faculty(Title.PROFESSOR);
//		System.out.println(f1);
//		MiniCourseBag mcb1 = new MiniCourseBag(10);
//		MiniCourse mc1 = new MiniCourse("CSE148", CourseType.TAKEN, CourseGrade.B_PLUS);
//		MiniCourse mc2 = new MiniCourse("CSE118", CourseType.TAKEN, CourseGrade.A);
//		MiniCourse mc3 = new MiniCourse("MAT142", CourseType.TAKEN, CourseGrade.B_PLUS);
//		MiniCourse mc4 = new MiniCourse("MAT205", CourseType.TAKING, CourseGrade.NO_GRADE);
//		MiniCourse mc5 = new MiniCourse("MAT210", CourseType.TO_TAKE, CourseGrade.NO_GRADE);
//		
//		mcb1.insert(mc1);
//		mcb1.insert(mc2);
//		mcb1.insert(mc3);
//		mcb1.insert(mc4);
//		mcb1.insert(mc5);
//		Person s3 = new Student(Major.CSE, mcb1);
//      mcb1.display();
//		System.out.println(CalculateGPA.calculateGPA(s3, masterCourseBag));
		
//		textbookBag.save(textbookBag);
//		TextbookBag.load();
//		textbookBag.display();
//		nameBag.save(nameBag);
//		NameBag.load();
//		MasterCourseBag temp = new MasterCourseBag(1);
//		Course c1 = new Course();
//		temp.insert(c1);
//		temp.save(temp);
//		masterCourseBag.save(masterCourseBag);
//		MasterCourseBag.load();
//		textbookBag.exportData(textbookBag);
		
		MajorBag majorBag = new MajorBag(26);
		MiniCourseBag cseTemplate = new MiniCourseBag(21, Major.CSE);
		MiniCourse cse110 = new MiniCourse("CSE110", CourseType.TO_TAKE, CourseGrade.NO_GRADE);
		MiniCourse cse118 = new MiniCourse("CSE118", CourseType.TO_TAKE, CourseGrade.NO_GRADE);
		MiniCourse cse148 = new MiniCourse("CSE148", CourseType.TO_TAKE, CourseGrade.NO_GRADE);
		MiniCourse cse218 = new MiniCourse("CSE218", CourseType.TO_TAKE, CourseGrade.NO_GRADE);
		MiniCourse cse222 = new MiniCourse("CSE222", CourseType.TO_TAKE, CourseGrade.NO_GRADE);
		MiniCourse cse248 = new MiniCourse("CSE248", CourseType.TO_TAKE, CourseGrade.NO_GRADE);
		MiniCourse eng101 = new MiniCourse("ENG101", CourseType.TO_TAKE, CourseGrade.NO_GRADE);
		MiniCourse eng102 = new MiniCourse("ENG102", CourseType.TO_TAKE, CourseGrade.NO_GRADE);
		MiniCourse mat141 = new MiniCourse("MAT141", CourseType.TO_TAKE, CourseGrade.NO_GRADE);
		MiniCourse mat142 = new MiniCourse("MAT142", CourseType.TO_TAKE, CourseGrade.NO_GRADE);
		MiniCourse mat205 = new MiniCourse("MAT205", CourseType.TO_TAKE, CourseGrade.NO_GRADE);
		MiniCourse mat210 = new MiniCourse("MAT210", CourseType.TO_TAKE, CourseGrade.NO_GRADE);
		MiniCourse env101 = new MiniCourse("ENV101", CourseType.TO_TAKE, CourseGrade.NO_GRADE);
		MiniCourse env115 = new MiniCourse("ENV115", CourseType.TO_TAKE, CourseGrade.NO_GRADE);
		MiniCourse env128 = new MiniCourse("ENV128", CourseType.TO_TAKE, CourseGrade.NO_GRADE);
		MiniCourse ped145 = new MiniCourse("PED145", CourseType.TO_TAKE, CourseGrade.NO_GRADE);
		MiniCourse ped146 = new MiniCourse("PED146", CourseType.TO_TAKE, CourseGrade.NO_GRADE);
		MiniCourse his101 = new MiniCourse("HIS101", CourseType.TO_TAKE, CourseGrade.NO_GRADE);
		MiniCourse hum114 = new MiniCourse("HUM114", CourseType.TO_TAKE, CourseGrade.NO_GRADE);
		MiniCourse itl101 = new MiniCourse("ITL101", CourseType.TO_TAKE, CourseGrade.NO_GRADE);
		MiniCourse his106 = new MiniCourse("HIS106", CourseType.TO_TAKE, CourseGrade.NO_GRADE);
		cseTemplate.insert(cse110);
		cseTemplate.insert(cse118);
		cseTemplate.insert(cse148);
		cseTemplate.insert(cse218);
		cseTemplate.insert(cse222);
		cseTemplate.insert(cse248);
		cseTemplate.insert(eng101);
		cseTemplate.insert(eng102);
		cseTemplate.insert(mat141);
		cseTemplate.insert(mat142);
		cseTemplate.insert(mat205);
		cseTemplate.insert(mat210);
		cseTemplate.insert(env101);
		cseTemplate.insert(env115);
		cseTemplate.insert(env128);
		cseTemplate.insert(ped145);
		cseTemplate.insert(ped146);
		cseTemplate.insert(his101);
		cseTemplate.insert(hum114);
		cseTemplate.insert(itl101);
		cseTemplate.insert(his106);
		majorBag.insert(cseTemplate);
		
		MiniCourseBag matTemplate = new MiniCourseBag(20, Major.MAT);
		MiniCourse col101 = new MiniCourse("COL101", CourseType.TO_TAKE, CourseGrade.NO_GRADE);
		MiniCourse mat200 = new MiniCourse("MAT200", CourseType.TO_TAKE, CourseGrade.NO_GRADE);
		MiniCourse mat203 = new MiniCourse("MAT203", CourseType.TO_TAKE, CourseGrade.NO_GRADE);
		MiniCourse mat204 = new MiniCourse("MAT204", CourseType.TO_TAKE, CourseGrade.NO_GRADE);
		MiniCourse mat206 = new MiniCourse("MAT206", CourseType.TO_TAKE, CourseGrade.NO_GRADE);
		MiniCourse his103 = new MiniCourse("HIS103", CourseType.TO_TAKE, CourseGrade.NO_GRADE);
		MiniCourse hum120 = new MiniCourse("HUM120", CourseType.TO_TAKE, CourseGrade.NO_GRADE);
		MiniCourse his104 = new MiniCourse("HIS104", CourseType.TO_TAKE, CourseGrade.NO_GRADE);
		matTemplate.insert(col101);
		matTemplate.insert(eng101);
		matTemplate.insert(eng102);
		matTemplate.insert(mat141);
		matTemplate.insert(mat142);
		matTemplate.insert(mat200);
		matTemplate.insert(mat203);
		matTemplate.insert(mat204);
		matTemplate.insert(mat206);
		matTemplate.insert(cse118);
		matTemplate.insert(ped145);
		matTemplate.insert(ped146);
		matTemplate.insert(his101);
		matTemplate.insert(his103);
		matTemplate.insert(itl101);
		matTemplate.insert(env101);
		matTemplate.insert(env128);
		matTemplate.insert(his104);
		matTemplate.insert(hum114);
		matTemplate.insert(hum120);
		majorBag.insert(matTemplate);
		
//		cseTemplate.display();
//		MajorTemplateCourse cse110 = new MajorTemplateCourse(masterCourseBag.getCourses()[252], mc1);
//		MajorTemplateCourse cse118 = new MajorTemplateCourse(masterCourseBag.getCourses()[253], mc2);
//		MajorTemplateCourse cse148 = new MajorTemplateCourse(masterCourseBag.getCourses()[254], mc3);
//		MajorTemplateCourse cse218 = new MajorTemplateCourse(masterCourseBag.getCourses()[255], mc4);
//		MajorTemplateCourse cse222 = new MajorTemplateCourse(masterCourseBag.getCourses()[256], mc5);
//		MajorTemplateCourse cse248 = new MajorTemplateCourse(masterCourseBag.getCourses()[257], mc6);
	
		PeopleBag peopleBag = new PeopleBag(100);
		NameBag.fillBag(nameBag);
//		PeopleHelper.importData(peopleBag, nameBag);
//		peopleBag.save(peopleBag);
//		peopleBag = PeopleHelper.load();
//		peopleBag.display();
//		masterCourseBag = CourseHelper.load();
		
//		majorBag.display();
		Person s1 = new Student(NameFactory.emitName(), Major.MAT);
		System.out.println(s1);
		Person s2 = new Student(NameFactory.emitName(), Major.CSE);
//		System.out.println(s2.getMiniCourseBag());
	}

}
