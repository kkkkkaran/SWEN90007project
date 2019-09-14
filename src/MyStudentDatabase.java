import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MyStudentDatabase implements StudentDatabase {

	static Connection conn;
	static PreparedStatement ps;
	
	@Override
	public int insertStudent(Student s) {
		
		int status = 0;
		
		try {
			conn=MyDatabaseConnection.getConn();
			ps=conn.prepareStatement("insert into student values(?,?)");
			ps.setString(1, s.getUserName());
			ps.setString(2, s.getPassWord());
			ps.setString(3, s.getFirstName());
			ps.setString(4, s.getLastName());
			ps.setString(5, s.getId());
			ps.setString(6, s.getDateOfBirth());
			ps.setString(7, s.getEmail());
			ps.setString(8, s.getEducation());
			ps.setString(9, s.getRating());
			ps.setString(10, s.getBookingHistory());
			ps.setString(11, s.getAddress());

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
				s.setUserName(rs.getString(1));
				s.setPassWord(rs.getString(2));
				s.setFirstName(rs.getString(3));
				s.setLastName(rs.getString(4));
				s.setId(rs.getString(5));
				s.setDateOfBirth(rs.getString(6));
				s.setEmail(rs.getString(7));
				s.setEducation(rs.getString(8));
				s.setRating(rs.getString(9));
				s.setBookingHistory(rs.getString(10));
				s.setAddress(rs.getString(11));
				
			}
			
		}catch(Exception e){
			System.out.println(e);
			
		}
		
		return s;
	}

}
