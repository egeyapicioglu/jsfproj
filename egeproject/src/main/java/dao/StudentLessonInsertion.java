package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import bean.MsgBean;
import egeproject.ConnectionClass;
import model.StudentLesson;

public class StudentLessonInsertion {


	public void InsertStudentLesson(StudentLesson studentLesson) throws SQLException
	{

		ConnectionClass connectionClass = new ConnectionClass();
		Connection connection = connectionClass.getDBConnection();

		String insertionSql = "INSERT INTO public.\"Student_Lesson_Table\"(\r\n" + 
				"            \"Student_Id\", \"Lesson_Id\")\r\n" + 
				"    VALUES (?, ?);\r\n" + 
				"";
		MsgBean msgbean = new MsgBean();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(insertionSql);
		   
			preparedStatement.setInt(1, studentLesson.getStudentId());
			preparedStatement.setInt(2, studentLesson.getLessonId());
	
		    
			preparedStatement.executeUpdate();
			
			msgbean.showMessage("You have succesfully connected the student with id: " + studentLesson.getStudentId() + " to the lesson with id: " + studentLesson.getLessonId());
			
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
