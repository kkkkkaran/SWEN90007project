package source.services;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import source.domain.Student;
import source.DataMappers.StudentMapper;

public class StudentService implements StudentInterface {

	static HashMap<Integer, Student> studentIdentityMap = new HashMap<Integer, Student>();
	
	@Override
	public int insertStudent(Student s) {
		StudentMapper sm=new StudentMapper();
		return sm.insertStudent(s);
	}
	
	
	@Override
	public int updateStudent(Student s) {
		StudentMapper sm=new StudentMapper();	
		s=sm.updateStudent(s);
		studentIdentityMap.replace(s.getId(), s); //Updated object inserted in identity map
		return 1;
	}
	


	public Student getStudent(String username) {
		Student s = new Student();
		StudentMapper sm=new StudentMapper();
		s = sm.getStudent(username);
		studentIdentityMap.put(s.getId(), s);
		return s;
	}
	
	
	@Override
	public List<Student> listAllStudents() throws SQLException{
		StudentMapper sm=new StudentMapper();
		return sm.listAllStudents();
	}
	
	@Override
	public int deleteStudent(Student s) {
		StudentMapper sm=new StudentMapper();
		int status= sm.deleteStudent(s);
		if(status == 1 && studentIdentityMap.containsKey(s.getId())) {
			studentIdentityMap.remove(s.getId());
		}
		return status;
		
	}
	
	@Override
	public Student lazyLoadedStudent(Student s) {	
		StudentMapper sm=new StudentMapper();
		s = sm.lazyLoadedStudent(s);
		studentIdentityMap.replace(s.getId(), s); //Updated object inserted in identity map
		return s;
	}
	
	@Override
	public Student getStudentAtId(int id) {
		//identity map implementation
		if(studentIdentityMap.containsKey(id)) {
			Student s=studentIdentityMap.get(id);
			if(s.getFirstName() != null) { //if contains full profile, and not partial due to lazy loading
				return s;
			}
			else { //fetching rest of student profile, implementing lazy load
				StudentInterface sd = new StudentService();
				return sd.lazyLoadedStudent(s);
			}
		}
		StudentMapper sm=new StudentMapper();
		return sm.getStudentAtId(id);
	}

}
