package model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import egeproject.StudentView;

@ManagedBean(name = "Student")
@RequestScoped

public class Student {

	private Integer studentId;
	private Integer studentNumber;
	private String studentName;
	private String studentClass;

	public Integer getStudentNumber() {
		return studentNumber;
	}
	public void setStudentNumber(Integer studentNumber) {
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
	
	public Student returnStudent()
	{
		return this;
	}

	public Integer getStudentId() {
		return studentId;
	}
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	
	public void clear()
	{
		this.studentId = null;
		this.studentClass = null;
		this.studentName = null;
		this.studentNumber = null;
	}
}
