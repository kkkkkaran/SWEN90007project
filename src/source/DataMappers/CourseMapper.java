package source.DataMappers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

import source.domain.Course;
import source.services.CourseService;

public class CourseMapper {
	static Connection conn;
	static PreparedStatement ps;
	public int insert(String name) throws SQLException {
		int status=0;
		try {
			conn=MyDatabaseConnection.getConn();
			ps=conn.prepareStatement("INSERT INTO course(name) VALUES(?)");
			ps.setString(1, name);
			status=ps.executeUpdate();
			conn.commit();
			conn.close();
		
		}catch(Exception e){
			try {
				conn.rollback();
			}
			catch (SQLException ignored) {
				System.out.println("Rollback failed");
			}
			System.out.println(e);
			
		}
		return status;
	}
	public int delete(String name) throws SQLException {
		int status=0;
		try {
			conn=MyDatabaseConnection.getConn();
			ps=conn.prepareStatement("DELETE FROM course WHERE name=?;");
			ps.setString(1, name);
			status=ps.executeUpdate();
			conn.commit();
			conn.close();
		
		}catch(Exception e){
			System.out.println(e);
			try {
				conn.rollback();
			}
			catch (SQLException ignored) {
				System.out.println("Rollback failed");
			}
			
		}
		return status;
	}
	
	public List<Course> listCourses() throws SQLException{
		List<Course> courses = new ArrayList<>();
		try {
			conn=MyDatabaseConnection.getConn();
			ps=conn.prepareStatement("SELECT * FROM course;");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Course c = new Course();
				c.setCourseId(rs.getInt(1));
				c.setCourseName(rs.getString(2));
				courses.add(c);
			}
			
			conn.close();
			
		}catch(Exception e){
			System.out.println(e);
			
		}
		return courses;
		
		
	}
	
	public static String listCoursesText() throws SQLException {
		CourseService cg=new CourseService();
		List<Course> courses=cg.listCourses();
		
		
		String courseListString="";
		for(int i=0;i<courses.size();i++) {
			Course c=courses.get(i);
			courseListString=courseListString+c.getCourseId()+":"+c.getCourseName()+",";
					
		}
		return courseListString;
		
	}

}
