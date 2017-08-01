package model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "StudentLessonElement")
@RequestScoped
public class StudentLessonElement {
	private Integer studentLessonId;
	private String studentName;
	private String lessonName;
	private Integer score;
	
	
	public Integer getStudentLessonId() {
		return studentLessonId;
	}
	public void setStudentLessonId(Integer studentLessonId) {
		this.studentLessonId = studentLessonId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getLessonName() {
		return lessonName;
	}
	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	
	public void clear()
	{
		this.lessonName = null;
		this.score = null;
		this.studentLessonId = null;
		this.studentName = null;
	}

}
