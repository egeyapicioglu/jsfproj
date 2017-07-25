package model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "Lesson")
@RequestScoped

public class Lesson {

	private Integer lessonId;
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
	public Integer getLessonId() {
		return lessonId;
	}
	public void setLessonId(int lessonId) {
		this.lessonId = lessonId;
	}
	
	public Lesson returnLesson()
	{
		return this;
	}
	
	public void clear()
	{
		lessonId = null;
		lessonName = null;
	}
}
