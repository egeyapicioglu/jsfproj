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

@ManagedBean(name = "StudentLessonBean")
@RequestScoped

public class StudentLessonBean {

	StudentLessonInsertion studentLessonInsertion;
	List<Lesson> selectedLessons;
	List<StudentLesson> studentLessons;
	List<StudentLesson> filteredStudentLessons;
	Student selectedStudent;

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
	
	public void asd() {
		Student studen = new Student();
		studen = this.selectedStudent;
	}
}
