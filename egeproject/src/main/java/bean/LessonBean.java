package bean;

import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import dao.LessonInsertion;
import model.Lesson;

@ManagedBean(name = "LessonBean")
@RequestScoped

public class LessonBean {
	private LessonInsertion lessonInsertion;

	public void InsertLesson(Lesson lesson) throws SQLException
	{
		lessonInsertion = new LessonInsertion();
		lessonInsertion.InsertLesson(lesson);
		lesson.clear();
	}
}
