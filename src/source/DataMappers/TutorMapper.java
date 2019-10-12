package source.DataMappers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import source.domain.Tutor;


public class TutorMapper {
	static Connection conn;
	static PreparedStatement ps;
	

	public int insertTutor(Tutor t) {
		
		
		int status = 0;
		
		
		try {
			conn=MyDatabaseConnection.getConn();
			ps=conn.prepareStatement("insert into tutor(username,password,firstname,lastname,yearofbirth,education,location,rateperhour,approved) values(?,?,?,?,?,?,?,?,?)");
			ps.setString(1, t.getUserName());
			ps.setString(2, t.getPassWord());
			ps.setString(3, t.getFirstName());
			ps.setString(4, t.getLastName());
			ps.setString(5, t.getDateOfBirth());
			ps.setString(6, t.getEducation());
			ps.setString(7, t.getAddress());
			ps.setString(8, t.getPrice());
			ps.setBoolean(9, false);
			

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
	
	
	
	public Tutor updateTutor(Tutor t) {
		
		String[] subjects = t.getSubjects();
		TutorCourseMapper tc = new TutorCourseMapper();
		for(int i=0;i<subjects.length;i++) {
			String[] splitted = subjects[i].split(":"); //format is id:subjectName
			tc.insertSubject(t.getId(), Integer.parseInt(splitted[0]));
			
		}
		try {
			conn=MyDatabaseConnection.getConn();
			ps=conn.prepareStatement("update tutor(idtutor,username,password,firstname,lastname,yearofbirth,education,location,rateperhour,approved) values(?,?,?,?,?,?,?,?,?,?) WHERE idtutor="+t.getId());
			ps.setInt(1, t.getId());
			ps.setString(2, t.getUserName());
			ps.setString(3, t.getPassWord());
			ps.setString(4, t.getFirstName());
			ps.setString(5, t.getLastName());
			ps.setString(6, t.getDateOfBirth());
			ps.setString(7, t.getEducation());
			ps.setString(8, t.getAddress());
			ps.setString(9, t.getPrice());
			ps.setBoolean(10, t.getApproved());

			ps.executeUpdate();
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
		
		return t;
	}
	


	public Tutor getTutor(String username) {
		

		Tutor t = new Tutor();
		
		try {
			conn=MyDatabaseConnection.getConn();
			ps=conn.prepareStatement("select * from tutor where username=?");
			ps.setString(1, username);
			
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				t.setId(rs.getInt(1));
				t.setUserName(rs.getString(2));
				t.setPassWord(rs.getString(3));
				
				/*
				t.setFirstName(rs.getString(4));
				t.setLastName(rs.getString(5));
				t.setDateOfBirth(rs.getString(6));
				t.setEducation(rs.getString(7));
				t.setAddress(rs.getString(8));
				t.setPrice(rs.getString(9));
				t.setApproved(rs.getBoolean(11));
				*/
				
				
				
			}
			
			conn.close();
			
		}catch(Exception e){
			System.out.println(e);
			
		}
		
		return t;
	}
	
	
	
	public Tutor lazyLoadedTutor(Tutor t) {	
		try {
			
			conn=MyDatabaseConnection.getConn();
			ps=conn.prepareStatement("select firstname,lastname,yearofbirth,education,location,rateperhour,approved from tutor where idtutor=?;");
			ps.setInt(1, t.getId());
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				t.setFirstName(rs.getString(1));
				t.setLastName(rs.getString(2));
				t.setDateOfBirth(rs.getString(3));
				t.setEducation(rs.getString(4));
				t.setAddress(rs.getString(5));
				t.setPrice(rs.getString(6));
				t.setApproved(rs.getBoolean(7));
				
				
				
			}
			
		}catch(Exception e){
			System.out.println(e);
			
		}
		
		return t;
		
		
	}
	public int approveTutor(int id) {
		int status=0;
		try {
			conn=MyDatabaseConnection.getConn();
			ps=conn.prepareStatement("update tutor set approved=TRUE where idtutor=?");
			ps.setInt(1, id);
			status = ps.executeUpdate();
		}catch(Exception e){
			System.out.println(e);
			
		}
		return status;
	}
	public Tutor getTutorAtId(int id) {
			
			Tutor t = new Tutor();
			
			try {
				conn=MyDatabaseConnection.getConn();
				ps=conn.prepareStatement("select * from tutor where idtutor=? and approved=TRUE;");
				ps.setInt(1, id);
				
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
					t.setId(rs.getInt(1));
					t.setUserName(rs.getString(2));
					t.setPassWord(rs.getString(3));
					t.setFirstName(rs.getString(4));
					t.setLastName(rs.getString(5));
					t.setEducation(rs.getString(6));
					t.setDateOfBirth(rs.getString(7));
					t.setAddress(rs.getString(8));
					t.setApproved(rs.getBoolean(9));
					t.setPrice(rs.getString(10));
					
					
					
					
				}
				
			}catch(Exception e){
				System.out.println(e);
				
			}
			
			return t;
		}
	
	public List<Tutor> listUnapprovedTutors() throws SQLException{
		List<Tutor> tutors = new ArrayList<>();
		try {
			conn=MyDatabaseConnection.getConn();
			ps=conn.prepareStatement("SELECT * FROM tutor where approved=FALSE;");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Tutor t = new Tutor();
				t.setId(rs.getInt(1));
				t.setUserName(rs.getString(2));
				t.setPassWord(rs.getString(3));
				t.setFirstName(rs.getString(4));
				t.setLastName(rs.getString(5));
				t.setDateOfBirth(rs.getString(6));
				t.setEducation(rs.getString(7));
				t.setAddress(rs.getString(8));
				t.setPrice(rs.getString(10));
				t.setApproved(false);
				tutors.add(t);
				
				
			}
			
		}catch(Exception e){
			System.out.println(e);
			
		}
		return tutors;
		
		
	}
	public List<Tutor> listAllTutors() throws SQLException{
		List<Tutor> tutors = new ArrayList<>();
		try {
			conn=MyDatabaseConnection.getConn();
			ps=conn.prepareStatement("SELECT * FROM tutor;");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Tutor t = new Tutor();
				t.setId(rs.getInt(1));
				t.setUserName(rs.getString(2));
				t.setPassWord(rs.getString(3));
				t.setFirstName(rs.getString(4));
				t.setLastName(rs.getString(5));
				t.setDateOfBirth(rs.getString(6));
				t.setEducation(rs.getString(7));
				t.setAddress(rs.getString(8));
				t.setPrice(rs.getString(9));
				tutors.add(t);
				
				
			}
			
		}catch(Exception e){
			System.out.println(e);
			
		}
		return tutors;
		
		
	}
	
	public int deleteTutor(Tutor t) {
		int status = 0;
		
		try {
			conn=MyDatabaseConnection.getConn();
			ps=conn.prepareStatement("delete from tutor WHERE idtutor="+t.getId());
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
	

}
