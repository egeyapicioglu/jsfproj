package bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import dao.StudentLessonInsertion;
import egeproject.ConnectionClass;
import model.Lesson;
import model.Student;
import model.StudentLesson;
import model.StudentLessonElement;

@ManagedBean(name = "StudentLessonBean")
@RequestScoped

public class StudentLessonBean {

	private StudentLessonInsertion studentLessonInsertion;
	private List<Lesson> selectedLessons;
	private List<StudentLesson> studentLessons;
	private List<StudentLesson> filteredStudentLessons;
	private Student selectedStudent;
	private StudentLessonElement selectedStudentLessonElement;
	private List<StudentLessonElement> studentLessonElements;
	private List<StudentLessonElement> filteredStudentLessonElements;
	private Integer score;


	public List<Lesson> getSelectedLessons() {
		return selectedLessons;
	}

	public void setSelectedLessons(List<Lesson> selectedLessons) {
		this.selectedLessons = selectedLessons;
	}

	public Student getSelectedStudent() {
		return selectedStudent;
	}
	
	public void setSelectedStudent(Student selectedStudent) {
		this.selectedStudent = selectedStudent;
	}
	
	public StudentLessonElement getSelectedStudentLessonElement() {
		return selectedStudentLessonElement	;
	}
	
	public void setSelectedStudentLessonElement(StudentLessonElement selectedStudentLessonElement) {
		this.selectedStudentLessonElement = selectedStudentLessonElement;
	}

	public void InsertStudentLesson() throws SQLException {
		studentLessonInsertion = new StudentLessonInsertion();
		StudentLesson studentLesson = new StudentLesson();
		for (int i = 0; i < selectedLessons.size(); i++) {
			studentLesson.setLessonId(selectedLessons.get(i).getLessonId());
			studentLesson.setStudentId(selectedStudent.getStudentId());
			studentLessonInsertion.InsertStudentLesson(studentLesson);
			studentLesson.clear();
		}

	}

	public List<StudentLesson> getFilteredStudentLessons() {
		return filteredStudentLessons;
	}

	public void setFilteredStudentLessons(List<StudentLesson> filteredStudentLessons) {
		this.filteredStudentLessons = filteredStudentLessons;
	}

	public List<StudentLesson> getStudentLessons() throws SQLException {
		studentLessons = new ArrayList<StudentLesson>();
		ConnectionClass connectionClass = new ConnectionClass();
		Connection connection = connectionClass.getDBConnection();

		String selectionSql = "SELECT \"Student_Id\", \"Lesson_Id\", \"Score\", \"Student_Lesson_Id\"\r\n"
				+ "  FROM public.\"Student_Lesson_Table\";\r\n" + "";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(selectionSql);
			ResultSet results = preparedStatement.executeQuery();
			while (results.next()) {
				StudentLesson newStudentLesson = new StudentLesson();
				newStudentLesson.setStudentId(results.getInt("Student_Id"));
				newStudentLesson.setLessonId(results.getInt("Lesson_Id"));
				newStudentLesson.setScore(results.getInt("Score"));
				newStudentLesson.setStudentLessonId(results.getInt("Student_Lesson_Id"));
				studentLessons.add(newStudentLesson);
			}
		} catch (SQLException e) {

			e.printStackTrace();
			System.out.println(e);
		} finally {
			connection.close();
		}

		return studentLessons;

	}
	
	public List<StudentLessonElement> getStudentLessonElements() throws SQLException
	{
		studentLessonElements = new ArrayList<StudentLessonElement>();
		this.getStudentLessons();
		ConnectionClass connectionClass = new ConnectionClass();
		Connection connection = connectionClass.getDBConnection();
		ResultSet studentResults;
		ResultSet lessonResults;
		String studentSelectionSql = "SELECT \"Student_Name\"\r\n" + 
				"  FROM public.\"Student_Table\" WHERE \"Student_Id\"=?;\r\n" + 
				"";
		
		String lessonSelectionSql = "SELECT \"Lesson_Id\", \"Lesson_Name\"\r\n" + 
				"  FROM public.\"Lesson_Table\" WHERE \"Lesson_Id\"=?;";
		StudentLesson currStudentLesson = new StudentLesson();
		StudentLessonElement currStudentLessonElement;
		
		for(int i=0; i<studentLessons.size(); i++)
		{
			
			currStudentLesson = studentLessons.get(i);
			currStudentLessonElement = new StudentLessonElement();
			try 
			{
				PreparedStatement preparedStatement = connection.prepareStatement(studentSelectionSql);

				preparedStatement.setInt(1, currStudentLesson.getStudentId());
			    
				studentResults = preparedStatement.executeQuery();
				
				preparedStatement = connection.prepareStatement(lessonSelectionSql);
				
				preparedStatement.setInt(1, currStudentLesson.getLessonId());
				
				lessonResults = preparedStatement.executeQuery();		
				
				currStudentLessonElement.setStudentLessonId(currStudentLesson.getStudentLessonId());
			
				if(studentResults.next())
					currStudentLessonElement.setStudentName(studentResults.getString("Student_Name"));
				if(lessonResults.next())
					currStudentLessonElement.setLessonName(lessonResults.getString("Lesson_Name"));
				
				
				currStudentLessonElement.setScore(currStudentLesson.getScore());
				studentLessonElements.add(currStudentLessonElement);
				
			} 
			catch (SQLException e) 
			{

				e.printStackTrace();
				System.out.println(e);
			}
			
			
		}
		
		return studentLessonElements;
		}
	
	public List<StudentLessonElement> getFilteredStudentLessonElements() {
		return filteredStudentLessonElements;
	}

	public void setFilteredStudentLessonElements(List<StudentLessonElement> filteredStudentLessonElements) {
		this.filteredStudentLessonElements = filteredStudentLessonElements;
	}

	public void InsertGrade()
	{
		ConnectionClass connectionClass = new ConnectionClass();
		Connection connection = connectionClass.getDBConnection();
		
		String updateSql = "UPDATE public.\"Student_Lesson_Table\"\r\n" + 
				"   SET \"Score\"=?\r\n" + 
				" WHERE \"Student_Lesson_Id\" =?;\r\n" + 
				"";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(updateSql);
			preparedStatement.setInt(1, score);
			preparedStatement.setInt(2, selectedStudentLessonElement.getStudentLessonId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		score=null;

	}
	
	
	public void asd() {
		Student studen = new Student();
		studen = this.selectedStudent;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}
}
