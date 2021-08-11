package model;

public class MajorTemplate {
	// every major has an array
	// Create arrays, then put them into templates
	private MiniCourse[] miniCourseTemplate; // array of bags
	private int nElems; // number of majors
	
	public MajorTemplate() {
		
	}
	
	public MajorTemplate(MiniCourse[] miniCourseTemplate) {
		this.miniCourseTemplate = miniCourseTemplate;
		nElems = miniCourseTemplate.length;
	}
	
	public MiniCourse[] getMiniCourseTemplate() {
		return miniCourseTemplate;
	}
	
	public int getnElems() {
		return nElems;
	}
	
	public void insert(MiniCourse miniCourse) {
		miniCourseTemplate[nElems++] = miniCourse;
	}
	
	public void display() {
		for (int i = 0; i < nElems; i++) {
			System.out.println(miniCourseTemplate[i]);
		}
		System.out.println();
	}
	
	public void save() {
		
	}
}
