package source.services;

import java.sql.SQLException;
import java.util.List;

import source.domain.Student;

public interface StudentInterface {

	public int insertStudent(Student s);
	public int updateStudent(Student s);
	public Student getStudent(String userName, String passWord);
	List<Student> listAllStudents() throws SQLException;
	public int deleteStudent(Student s);
	Student getStudentAtId(int id);
	public Student lazyLoadedStudent(Student s);
	
}
