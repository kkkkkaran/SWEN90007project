package source.dataSource;
import java.sql.Connection;
import java.sql.DriverManager;

public class MyDatabaseConnection implements DatabaseConnection {

	static Connection conn = null;
	
	public static Connection getConn() {
		
		try {
		    Class.forName("org.postgresql.Driver");
		    conn=DriverManager.getConnection(connUrl, username, password);
		}catch(Exception e){
			System.out.println(e);
			
		}
		return conn;
	}
	
}
