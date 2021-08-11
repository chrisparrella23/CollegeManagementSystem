package model;

public class MajorTemplateCourse {
	private Course course;
	private MiniCourse miniCourse;

	public MajorTemplateCourse(Course course, MiniCourse miniCourse) {
		this.course = course;
		this.miniCourse = miniCourse;
	}

	public MajorTemplateCourse(Course course) {
		this.course = course;
	}

	public MajorTemplateCourse(MiniCourse miniCourse) {
		this.miniCourse = miniCourse;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public MiniCourse getMiniCourse() {
		return miniCourse;
	}

	public void setMiniCourse(MiniCourse miniCourse) {
		this.miniCourse = miniCourse;
	}

}
