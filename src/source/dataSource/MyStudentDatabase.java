package source.dataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import source.domain.Student;
import source.domain.Tutor;

public class MyStudentDatabase implements StudentDatabase {

	static Connection conn;
	static PreparedStatement ps;
	
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
		return status;
		
	}
	
	@Override
	public Student getStudentAtId(int id) {
		
		
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
