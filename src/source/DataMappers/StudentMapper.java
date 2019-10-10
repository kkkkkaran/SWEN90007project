package source.DataMappers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

import source.domain.Student;

public class StudentMapper {
	static Connection conn;
	static PreparedStatement ps;
	
	
	
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
	
public Student updateStudent(Student s) {
		
		
		
		try {
			conn=MyDatabaseConnection.getConn();
			ps=conn.prepareStatement("update student values(?,?,?,?,?,?,?) WHERE idstudent="+s.getId());
			ps.setInt(3, s.getId());
			ps.setString(1, s.getUserName());
			ps.setString(2, s.getPassWord());
			ps.setString(4, s.getFirstName());
			ps.setString(5, s.getLastName());
			ps.setString(6, s.getDateOfBirth());
			ps.setString(7, s.getEducation());

			ps.executeUpdate();
			conn.close();
			
		}catch(Exception e){
			System.out.println(e);
			
		}
		
		return s;
	}

	
	public Student getStudent(String username) {
		Student s = new Student();
		
		try {
			conn=MyDatabaseConnection.getConn();
			ps=conn.prepareStatement("select * from student where username=?");
			ps.setString(1, username);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				s.setId(rs.getInt(3));
				s.setUserName(rs.getString(1));
				s.setPassWord(rs.getString(2));
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
		
		return s;
	}
	
	
	public List<Student> listAllStudents() throws SQLException{
		List<Student> students = new ArrayList<>();
		try {
			conn=MyDatabaseConnection.getConn();
			ps=conn.prepareStatement("SELECT * FROM student;");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Student s = new Student();
				s.setId(rs.getInt(3));
				s.setUserName(rs.getString(1));
				s.setPassWord(rs.getString(2));
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
	
	
	public int deleteStudent(Student s) {
		int status = 0;
		
		try {
			conn=MyDatabaseConnection.getConn();
			ps=conn.prepareStatement("delete from student WHERE idstudent="+s.getId());
			status=ps.executeUpdate();
			conn.close();
			
		}catch(Exception e){
			System.out.println(e);
			
		}
		
		return status;
		
	}
	
	
	public Student lazyLoadedStudent(Student s) {	
		
		try {
			
			conn=MyDatabaseConnection.getConn();
			ps=conn.prepareStatement("select firstname,lastname,yearofbirth,education from student where idstudent=?;");
			ps.setInt(1, s.getId());
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				s.setFirstName(rs.getString(1));
				s.setLastName(rs.getString(2));
				s.setDateOfBirth(rs.getString(3));
				s.setEducation(rs.getString(4));
			}
			
		}catch(Exception e){
			System.out.println(e);
			
		}
		
		return s;
		
		
	}
	
	
	public Student getStudentAtId(int id) {
		Student s = new Student();
		
		try {
			conn=MyDatabaseConnection.getConn();
			ps=conn.prepareStatement("select * from student where idstudent=?;");
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				s.setId(rs.getInt(3));
				s.setUserName(rs.getString(1));
				s.setPassWord(rs.getString(2));
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
