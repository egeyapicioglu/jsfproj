package bean;

import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import dao.StudentInsertion;
import model.Student;

@ManagedBean(name = "StudentBean")
@RequestScoped

public class StudentBean {
	
	private StudentInsertion studentInsertion;
	

	public void InsertStudent(Student student) throws SQLException
	{
	
		studentInsertion = new StudentInsertion();
		studentInsertion.InsertStudent(student);
		student.clear();
	}
	

	


}
