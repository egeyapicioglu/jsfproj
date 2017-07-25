package egeproject;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;


import model.Student;

@ManagedBean(name="studentView")
@ViewScoped

public class StudentView implements Serializable{
	
	private List<Student> students;
	private List<Student> filteredStudents;
	

	public List<Student> getStudents() throws SQLException
	{
		students = new ArrayList<Student>();
		ConnectionClass connectionClass = new ConnectionClass();
		Connection connection = connectionClass.getDBConnection();
		
		String selectionSql = "SELECT \"Student_Number\", \"Student_Name\", \"Student_Class\", \"Student_Id\"\r\n" + 
				"  FROM public.\"Student_Table\";\r\n" + "";
		
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(selectionSql);	    
			ResultSet results = preparedStatement.executeQuery();
			
			while(results.next())
			{
				Student newStudent = new Student();
				newStudent.setStudentId(results.getInt("Student_Id"));
				newStudent.setStudentNumber(results.getInt("Student_Number"));
				newStudent.setStudentName(results.getString("Student_Name"));
				newStudent.setStudentClass(results.getString("Student_Class"));
				students.add(newStudent);
			}
		} 
		catch (SQLException e) 
		{

			e.printStackTrace();
			System.out.println(e);
		}
		finally
		{
			connection.close();
		}
		
		
		return students;
	}


	public List<Student> getFilteredStudents() {
		return filteredStudents;
	}


	public void setFilteredStudents(List<Student> filteredStudents) {
		this.filteredStudents = filteredStudents;
	}
}

