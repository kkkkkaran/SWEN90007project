package source.services;

import java.sql.SQLException;
import java.util.List;
import source.DataMappers.CourseMapper;
import source.domain.Course;

public class CourseService {
	
	public int insert(String name) throws SQLException {
		CourseMapper cm = new CourseMapper();
		return cm.insert(name);
	}
	
	
	public int delete(String name) throws SQLException {
		CourseMapper cm = new CourseMapper();
		return cm.delete(name);
	}
	
	
	public List<Course> listCourses() throws SQLException{
		CourseMapper cm = new CourseMapper();
		return cm.listCourses();	
	}
	
	
	public static String listCoursesText() throws SQLException {
		return CourseMapper.listCoursesText();
	}
	
}
