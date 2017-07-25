package model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "StudentLesson")
@RequestScoped

public class StudentLesson {
	
	private Integer studentLessonId;
	private Integer studentId;
	private Integer lessonId;
	private Integer score;
	
	
	public Integer getStudentLessonId() {
		return studentLessonId;
	}
	public void setStudentLessonId(Integer studentLessonId) {
		this.studentLessonId = studentLessonId;
	}
	public Integer getStudentId() {
		return studentId;
	}
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	public Integer getLessonId() {
		return lessonId;
	}
	public void setLessonId(Integer lessonId) {
		this.lessonId = lessonId;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	
	public StudentLesson createReturn(Student student, Lesson lesson)
	{
		this.lessonId = lesson.getLessonId();
		this.studentId = student.getStudentId();
		return this;
	}
	
	public void clear()
	{
		this.lessonId=null;
		this.score=null;
		this.studentId=null;
		this.studentLessonId=null;
	}
	
}
