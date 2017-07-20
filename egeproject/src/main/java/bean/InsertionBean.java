package bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import egeproject.ConnectionClass;
import model.Lesson;
import model.Student;

@ManagedBean(name = "InsertionBean")
@RequestScoped
public class InsertionBean {

	ConnectionClass connectionClass = new ConnectionClass();
	
	public Connection getDBConnection() {
		Connection connection = null;
		connectionClass.setConnection();
		connection = connectionClass.getConnection();
		return connection;
	}
	
	public void InsertStudent(Student student)
	{
		Connection connection = getDBConnection();
		String insertionSql = "INSERT INTO public.\"Student_Table\"(\r\n" + 
				"            \"Student_Number\", \"Student_Name\", \"Student_Class\")\r\n" + 
				"    VALUES (?, ?, ?);";
		MsgBean msgbean = new MsgBean();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(insertionSql);
		   
			preparedStatement.setInt(1, student.getStudentNumber());
			preparedStatement.setString(2, student.getStudentName());
			preparedStatement.setString(3, student.getStudentClass());
		    
			preparedStatement.executeUpdate();
			
			connection.close();
		} 
		catch (SQLException e) 
		{

			e.printStackTrace();
			System.out.println(e);
		}
		
		msgbean.showMessage("You have succesfully added " + student.getStudentName() + " to your students.");
	}
	
	
	public void InsertLesson(Lesson lesson)
	{
		Connection connection = getDBConnection();
		String insertionSql = "INSERT INTO public.\"Lesson_Table\"(\r\n" + 
				"            \"Lesson_Name\")\r\n" + 
				"    VALUES (?);";
		MsgBean msgbean = new MsgBean();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(insertionSql);

			preparedStatement.setString(1, lesson.getLessonName());
		    
			preparedStatement.executeUpdate();
			
			connection.close();
		} 
		catch (SQLException e) 
		{

			e.printStackTrace();
			System.out.println(e);
		}
		
		msgbean.showMessage("You have succesfully added " + lesson.getLessonName() + " to your lessons.");
	}
	
	
	

}
