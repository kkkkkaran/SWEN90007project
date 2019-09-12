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
			ps.setString(3, s.getName());
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
				s.setName(rs.getString(3));
				
			}
			
		}catch(Exception e){
			System.out.println(e);
			
		}
		
		return s;
	}

}
