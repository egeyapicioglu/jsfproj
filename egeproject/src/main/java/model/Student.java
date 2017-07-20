package model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "Student")
@RequestScoped

public class Student {

	private int studentNumber;
	private String studentName;
	private String studentClass;

	public int getStudentNumber() {
		return studentNumber;
	}
	public void setStudentNumber(int studentNumber) {
		this.studentNumber = studentNumber;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getStudentClass() {
		return studentClass;
	}
	public void setStudentClass(String studentClass) {
		this.studentClass = studentClass;
	}
	
	public Student createStudent(int studentNumber, String studentName, String studentClass)
	{
		this.setStudentNumber(studentNumber);
		this.setStudentName(studentName);
		this.setStudentClass(studentClass);
		return this;
	}
}
