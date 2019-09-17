package source.dataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import source.domain.Tutor;




public class MyTutorDatabase implements TutorDatabase {
	
	static HashMap<Integer, Tutor> tutorIdentityMap = new HashMap<Integer, Tutor>();
	static Connection conn;
	static PreparedStatement ps;
	
	@Override
	public int insertTutor(Tutor t) {
		
		
		int status = 0;
		
		
		try {
			conn=MyDatabaseConnection.getConn();
			ps=conn.prepareStatement("insert into tutor(username,password,first name,lastname,yearofbirth,education,location,rateperhour,approved) values(?,?,?,?,?,?,?,?)");
			ps.setString(1, t.getUserName());
			ps.setString(2, t.getPassWord());
			ps.setString(3, t.getFirstName());
			ps.setString(4, t.getLastName());
			ps.setString(5, t.getDateOfBirth());
			ps.setString(6, t.getEducation());
			ps.setString(7, t.getAddress());
			ps.setString(8, t.getPrice());
			ps.setBoolean(9, t.getApproved());
			

			status=ps.executeUpdate();
			conn.close();
			
		}catch(Exception e){
			System.out.println(e);
			
		}
		return status;
	}
	
	
	@Override
	public int updateTutor(Tutor t) {
		
		int status = 0;
		
		String[] subjects = t.getSubjects();
		TutorSubjectDatabase tsd = new MyTutorSubjectDatabase();
		for(int i=0;i<subjects.length;i++) {
			String[] splitted = subjects[i].split(":"); //format is id:subjectName
			tsd.insertSubject(t.getId(), Integer.parseInt(splitted[0]));
			
		}
		
		try {
			conn=MyDatabaseConnection.getConn();
			ps=conn.prepareStatement("update tutor(id,username,password,first name,lastname,yearofbirth,education,location,rateperhour,approved) values(?,?,?,?,?,?,?,?,?,?) WHERE id="+t.getId());
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

			status=ps.executeUpdate();
			conn.close();
			
		}catch(Exception e){
			System.out.println(e);
			
		}
		return status;
	}
	

	@Override
	public Tutor getTutor(String username, String password) {
		

		Tutor t = new Tutor();
		
		try {
			conn=MyDatabaseConnection.getConn();
			ps=conn.prepareStatement("select * from tutor where username=? and password=?");
			ps.setString(1, username);
			ps.setString(2, password);
			
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
			
		}catch(Exception e){
			System.out.println(e);
			
		}
		tutorIdentityMap.put(t.getId(), t);
		return t;
	}
	public Tutor getTutorAtId(int id) {
			
			//identity map implementation
			if(tutorIdentityMap.containsKey(id)) {
				Tutor t=tutorIdentityMap.get(id);
				if(t.getFirstName() != null) { //if contains full profile, and not partial due to lazy loading
					return t;
				}
				else { //removing from hash map to fetch full item
					tutorIdentityMap.remove(id);
				}
			}
			Tutor t = new Tutor();
			
			try {
				conn=MyDatabaseConnection.getConn();
				ps=conn.prepareStatement("select * from tutor where id=? and approved=TRUE;");
				ps.setInt(1, id);
				
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
					t.setId(rs.getInt(1));
					t.setUserName(rs.getString(2));
					t.setPassWord(rs.getString(3));
					t.setFirstName(rs.getString(4));
					t.setLastName(rs.getString(5));
					t.setDateOfBirth(rs.getString(6));
					t.setEducation(rs.getString(7));
					t.setAddress(rs.getString(8));
					t.setPrice(rs.getString(9));
					t.setApproved(rs.getBoolean(11));
					
					
					
				}
				
			}catch(Exception e){
				System.out.println(e);
				
			}
			tutorIdentityMap.put(id, t);
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
				t.setPrice(rs.getString(9));
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
			ps=conn.prepareStatement("delete from tutor WHERE id="+t.getId());
			status=ps.executeUpdate();
			conn.close();
			int id=t.getId();
			
			//delete from identity map
			if(tutorIdentityMap.containsKey(t.getId())) {
				tutorIdentityMap.remove(id);
			}
			
		}catch(Exception e){
			System.out.println(e);
			
		}
		return status;
		
	}
		
		

}
