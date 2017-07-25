package egeproject;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import model.Lesson;



@ManagedBean(name="lessonView")
@ViewScoped


public class LessonView implements Serializable{
	
	private List<Lesson> lessons;
	private List<Lesson> filteredLessons;

	public List<Lesson> getLessons() throws SQLException
	{
		lessons = new ArrayList<Lesson>();
		ConnectionClass connectionClass = new ConnectionClass();
		Connection connection = connectionClass.getDBConnection();
		
		String selectionSql = "SELECT \"Lesson_Id\", \"Lesson_Name\"\r\n" + 
				"  FROM public.\"Lesson_Table\";\r\n" + 
				"";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(selectionSql);	    
			ResultSet results = preparedStatement.executeQuery();
			while(results.next())
			{
				Lesson newLesson = new Lesson();
				newLesson.setLessonId(results.getInt("Lesson_Id"));
				newLesson.setLessonName(results.getString("Lesson_Name"));
				lessons.add(newLesson);
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
		
		
		return lessons;
		
	}

	public List<Lesson> getFilteredLessons() {
		return filteredLessons;
	}

	public void setFilteredLessons(List<Lesson> filterLessons) {
		this.filteredLessons = filterLessons;
	}
}
