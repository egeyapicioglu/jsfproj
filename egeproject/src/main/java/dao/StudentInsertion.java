package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import bean.MsgBean;
import egeproject.ConnectionClass;
import model.Student;

public class StudentInsertion {
	
	public void InsertStudent(Student student) throws SQLException
	{
		ConnectionClass connectionClass = new ConnectionClass();
		Connection connection = connectionClass.getDBConnection();

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
			
			msgbean.showMessage("You have succesfully added " + student.getStudentName() + " to your students.");
			
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
		
		
		
	}
}
