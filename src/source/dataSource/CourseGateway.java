package source.dataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import source.MyDatabaseConnection;

public class CourseGateway {
	private String name;
	private int id;
	static Connection conn;
	static PreparedStatement ps;
	public int insert() throws SQLException {
		int status=0;
		try {
			conn=MyDatabaseConnection.getConn();
			ps=conn.prepareStatement("INSERT INTO subject VALUES(?,?)");
			ps.setInt(1,id);
			ps.setString(2, name);
			status=ps.executeUpdate();
			conn.close();
		
		}catch(Exception e){
			System.out.println(e);
			
		}
		return status;
	}
	public int delete() throws SQLException {
		int status=0;
		try {
			conn=MyDatabaseConnection.getConn();
			ps=conn.prepareStatement("DELETE FROM subject WHERE name=?;");
			ps.setString(1, name);
			status=ps.executeUpdate();
			conn.close();
		
		}catch(Exception e){
			System.out.println(e);
			
		}
		return status;
	}
	public List<Course> listCourses() throws SQLException{
		List<Course> courses = new ArrayList<>();
		try {
			conn=MyDatabaseConnection.getConn();
			ps=conn.prepareStatement("SELECT * FROM subject;");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Course c = new Course();
				c.setCourseId(rs.getInt(1));
				c.setCourseName(rs.getString(2));
				courses.add(c);
			}
			
		}catch(Exception e){
			System.out.println(e);
			
		}
		return courses;
		
		
	}
	
	public static String listCoursesText() throws SQLException {
		CourseGateway cg=new CourseGateway();
		List<Course> courses=cg.listCourses();
		Iterator iterator = courses.iterator();
		int i=0;
		String courseListString="";
		while(iterator.hasNext()) {
			Course c=courses.get(i);
			courseListString=courseListString+c.getCourseId()+":"+c.getCourseName()+",";
			i++;		
		}
		return courseListString;
		
	}
	
	

}
