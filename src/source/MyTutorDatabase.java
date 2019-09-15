package source;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MyTutorDatabase implements TutorDatabase {

	static Connection conn;
	static PreparedStatement ps;
	
	@Override
	public int insertTutor(Tutor t) {
		
		int status = 0;
		
		try {
			conn=MyDatabaseConnection.getConn();
			ps=conn.prepareStatement("insert into tutor values(?,?)");
			ps.setString(2, t.getUserName());
			ps.setString(3, t.getPassWord());
			ps.setString(4, t.getFirstName());
			ps.setString(5, t.getLastName());
			ps.setString(6, t.getDateOfBirth());
			ps.setString(7, t.getAddress());
			ps.setString(8, t.getPrice());
			ps.setString(10, t.getEducation());

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
		
		try {
			conn=MyDatabaseConnection.getConn();
			ps=conn.prepareStatement("update tutor values(?,?) WHERE id="+t.getId());
			ps.setString(2, t.getUserName());
			ps.setString(3, t.getPassWord());
			ps.setString(4, t.getFirstName());
			ps.setString(5, t.getLastName());
			ps.setString(6, t.getDateOfBirth());
			ps.setString(7, t.getAddress());
			ps.setString(8, t.getPrice());
			ps.setString(10, t.getEducation());

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
				t.setFirstName(rs.getString(4));
				t.setLastName(rs.getString(5));
				t.setDateOfBirth(rs.getString(6));
				t.setAddress(rs.getString(7));
				t.setPrice(rs.getString(8));
				t.setEducation(rs.getString(10));
				
				
				
			}
			
		}catch(Exception e){
			System.out.println(e);
			
		}
		
		return t;
	}
	public Tutor getTutorAtId(int id) {
			
	
			Tutor t = new Tutor();
			
			try {
				conn=MyDatabaseConnection.getConn();
				ps=conn.prepareStatement("select * from tutor where id=?");
				ps.setInt(1, id);
				
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
					t.setId(rs.getInt(1));
					t.setUserName(rs.getString(2));
					t.setPassWord(rs.getString(3));
					t.setFirstName(rs.getString(4));
					t.setLastName(rs.getString(5));
					t.setDateOfBirth(rs.getString(6));
					t.setAddress(rs.getString(7));
					t.setPrice(rs.getString(8));
					t.setEducation(rs.getString(10));
					
					
					
				}
				
			}catch(Exception e){
				System.out.println(e);
				
			}
			
			return t;
		}

}
