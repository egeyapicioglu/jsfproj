package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import bean.MsgBean;
import egeproject.ConnectionClass;
import model.Lesson;

public class LessonInsertion {

	
	public void InsertLesson(Lesson lesson) throws SQLException
	{
		ConnectionClass connectionClass = new ConnectionClass();
		Connection connection = connectionClass.getDBConnection();
		
		String insertionSql = "INSERT INTO public.\"Lesson_Table\"(\r\n" + 
				"            \"Lesson_Name\")\r\n" + 
				"    VALUES (?);";
		MsgBean msgbean = new MsgBean();
		try 
		{
			PreparedStatement preparedStatement = connection.prepareStatement(insertionSql);

			preparedStatement.setString(1, lesson.getLessonName());
		    
			preparedStatement.executeUpdate();
			
			
		} 
		catch (SQLException e) 
		{

			e.printStackTrace();
			System.out.println(e);
		}finally {
			connection.close();
		}
		
		msgbean.showMessage("You have succesfully added " + lesson.getLessonName() + " to your lessons.");
	}
}
