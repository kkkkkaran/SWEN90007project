package source.dataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import source.domain.Student;


public class MyStudentDatabase implements StudentDatabase {

	static Connection conn;
	static PreparedStatement ps;
	static HashMap<Integer, Student> studentIdentityMap = new HashMap<Integer, Student>();
	
	@Override
	public int insertStudent(Student s) {
		
		int status = 0;
		
		try {
			conn=MyDatabaseConnection.getConn();
			ps=conn.prepareStatement("insert into student(username,password,firstname,lastname,yearofbirth,education) values(?,?,?,?,?,?)");
			ps.setString(1, s.getUserName());
			ps.setString(2, s.getPassWord());
			ps.setString(3, s.getFirstName());
			ps.setString(4, s.getLastName());
			ps.setString(5, s.getDateOfBirth());
			ps.setString(6, s.getEducation());
			

			status=ps.executeUpdate();
			conn.close();
			
		}catch(Exception e){
			System.out.println(e);
			
		}
		return status;
	}
	
public int updateStudent(Student s) {
		
		int status = 0;
		
		try {
			conn=MyDatabaseConnection.getConn();
			ps=conn.prepareStatement("update student values(?,?,?,?,?,?,?) WHERE id="+s.getId());
			ps.setInt(1, s.getId());
			ps.setString(2, s.getUserName());
			ps.setString(3, s.getPassWord());
			ps.setString(4, s.getFirstName());
			ps.setString(5, s.getLastName());
			ps.setString(6, s.getDateOfBirth());
			ps.setString(7, s.getEducation());

			status=ps.executeUpdate();
			conn.close();
			
		}catch(Exception e){
			System.out.println(e);
			
		}
		studentIdentityMap.replace(s.getId(), s); //Updated object inserted in identity map
		return status;
	}

	@Override
	public Student getStudent(String username, String password) {
		Student s = new Student();
		
		try {
			conn=MyDatabaseConnection.getConn();
			ps=conn.prepareStatement("select * from student where username=? and password=?");
			ps.setString(1, username);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				s.setId(rs.getInt(1));
				s.setUserName(rs.getString(2));
				s.setPassWord(rs.getString(3));
				/*
				 Fields left out for lazy load
				s.setFirstName(rs.getString(4));
				s.setLastName(rs.getString(5));
				s.setDateOfBirth(rs.getString(6));
				s.setEducation(rs.getString(7));
				*/
			}
			
		}catch(Exception e){
			System.out.println(e);
			
		}
		studentIdentityMap.put(s.getId(), s);
		return s;
	}
	
	@Override
	public List<Student> listAllStudents() throws SQLException{
		List<Student> students = new ArrayList<>();
		try {
			conn=MyDatabaseConnection.getConn();
			ps=conn.prepareStatement("SELECT * FROM student;");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Student s = new Student();
				s.setId(rs.getInt(1));
				s.setUserName(rs.getString(2));
				s.setPassWord(rs.getString(3));
				s.setFirstName(rs.getString(4));
				s.setLastName(rs.getString(5));
				s.setDateOfBirth(rs.getString(6));
				s.setEducation(rs.getString(7));
	
				students.add(s);
				
				
			}
			
		}catch(Exception e){
			System.out.println(e);
			
		}
		return students;
		
		
	}
	
	@Override
	public int deleteStudent(Student s) {
		int status = 0;
		
		try {
			conn=MyDatabaseConnection.getConn();
			ps=conn.prepareStatement("delete from student WHERE id="+s.getId());
			status=ps.executeUpdate();
			conn.close();
			
		}catch(Exception e){
			System.out.println(e);
			
		}
		if(studentIdentityMap.containsKey(s.getId())) {
			studentIdentityMap.remove(s.getId());
		}
		return status;
		
	}
	
	
	public Student lazyLoadedStudent(Student s) {	
		
		try {
			
			conn=MyDatabaseConnection.getConn();
			ps=conn.prepareStatement("select (firstname,lastname,yearofbirth,education) student where id=?;");
			ps.setInt(1, s.getId());
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				s.setFirstName(rs.getString(4));
				s.setLastName(rs.getString(5));
				s.setDateOfBirth(rs.getString(6));
				s.setEducation(rs.getString(7));
			}
			
		}catch(Exception e){
			System.out.println(e);
			
		}
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
				StudentDatabase sd = new MyStudentDatabase();
				return sd.lazyLoadedStudent(s);
			}
		}
		Student s = new Student();
		
		try {
			conn=MyDatabaseConnection.getConn();
			ps=conn.prepareStatement("select * from student where id=?;");
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				s.setId(rs.getInt(1));
				s.setUserName(rs.getString(2));
				s.setPassWord(rs.getString(3));
				s.setFirstName(rs.getString(4));
				s.setLastName(rs.getString(5));
				s.setDateOfBirth(rs.getString(6));
				s.setEducation(rs.getString(7));
				
				
				
			}
			
		}catch(Exception e){
			System.out.println(e);
			
		}
		
		return s;
	}

}
