package source;
public interface StudentDatabase {

	public int insertStudent(Student s);
	public int updateStudent(Student s);
	public Student getStudent(String userName, String passWord);
	
}
