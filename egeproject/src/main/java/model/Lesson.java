package model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "Lesson")
@RequestScoped

public class Lesson {

	private String lessonName;
	

	public String getLessonName() {
		return lessonName;
	}
	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}
	
	public Lesson createLesson(String lessonName) {
		this.setLessonName(lessonName);
		return this;
	}
}
