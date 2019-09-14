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
			ps=conn.prepareStatement("insert into student values(?,?)");
			ps.setString(1, t.getUserName());
			ps.setString(2, t.getPassWord());
			ps.setString(3, t.getFirstName());
			ps.setString(4, t.getLastName());
			ps.setString(5, t.getId());
			ps.setString(6, t.getDateOfBirth());
			ps.setString(7, t.getEmail());
			ps.setString(8, t.getEducation());
			ps.setString(9, t.getRating());
			ps.setString(10, t.getBookingHistory());
			ps.setString(11, t.getAddress());

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
			ps=conn.prepareStatement("select * from student where username=? and password=?");
			ps.setString(1, username);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				t.setUserName(rs.getString(1));
				t.setPassWord(rs.getString(2));
				t.setFirstName(rs.getString(3));
				t.setLastName(rs.getString(4));
				t.setId(rs.getString(5));
				t.setDateOfBirth(rs.getString(6));
				t.setEmail(rs.getString(7));
				t.setEducation(rs.getString(8));
				t.setRating(rs.getString(9));
				t.setBookingHistory(rs.getString(10));
				t.setAddress(rs.getString(11));
				
			}
			
		}catch(Exception e){
			System.out.println(e);
			
		}
		
		return t;
	}

}
