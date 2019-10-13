package source.DataMappers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import source.utils.LockManagerInterface;



public class ExclusiveWriteLockManager implements LockManagerInterface {
	private static ExclusiveWriteLockManager lm;
	private final int lock_been_taken = -1;
	private final int lock_can_take = 0;
	private final int you_hold_lock = 1;
	
	static Connection conn;
	static PreparedStatement ps;
	
	private ExclusiveWriteLockManager() {
		
	}
	
	public static ExclusiveWriteLockManager getInstance() {
		if (lm == null) {
			lm = new ExclusiveWriteLockManager();
		}
		
		return lm;
	}

	@Override
	public boolean acquireLock(String type, int id, int sessionId) throws Exception {
		
		boolean result = false;
		
		int lockStatus = hasLock(type, id, sessionId);
		
		// if lock is unavailable now, retry 3 times
		int i = 0;
		while (lockStatus == lock_been_taken && i<3) {
			if (i!=0) {
				// wait for 1s
				Thread.sleep(1000);
			}
			lockStatus = hasLock(type, id, sessionId);
			i++;
			System.out.println("[RETRY]   "+ i +" retrying..........");
		}
		
		// if current session can take the lock
		if (lockStatus == lock_can_take) {
			String acquireSQL = "insert into Lock (Id, sessionId, tableName)"
					+ " values (?,?,?);"; 
			Connection conn = MyDatabaseConnection.getConn();
			PreparedStatement pStatement = (PreparedStatement) conn.prepareStatement(acquireSQL);
			pStatement.setInt(1, id);
			pStatement.setInt(2, sessionId);
			pStatement.setString(3, type);
			int sqlResult = pStatement.executeUpdate();
			
			conn.close();
			
			if (sqlResult == 1)
				result = true;
		}
		
		else if (lockStatus == you_hold_lock) {
			result = true;
		}
		
		return result;
	}

	@Override
	public boolean releaseLock(String type, int id, int sessionId) throws Exception {
		String releaseSQL = "delete from Lock where tableName=? AND Id=?"
				+" AND sessionId=?";
		Connection conn = MyDatabaseConnection.getConn();
		PreparedStatement pStatement = (PreparedStatement) conn.prepareStatement(releaseSQL);
		pStatement.setString(1, type);
		pStatement.setInt(2, id);
		pStatement.setInt(3, sessionId);
		
		int result = pStatement.executeUpdate();
		
		if (result ==0)
			return false;
		else
			return true;
	}
	
	
	private int hasLock(String type, int id, int sessionId) {
		String hasLockSQL = "SELECT sessionId FROM Lock WHERE id =?" +
				"AND tableName=?";
		int result = 0;
		Connection conn;
		try {
			conn = MyDatabaseConnection.getConn();
			PreparedStatement pStatement = (PreparedStatement) conn.prepareStatement(hasLockSQL);
			pStatement.setInt(1, id);
			pStatement.setString(2, type);
			
			ResultSet resultSet = pStatement.executeQuery();
			// if current object has been locked
			while (resultSet.next()) {
				int session_in_DB = resultSet.getInt(1);
				// if current session has the lock
				if (session_in_DB == sessionId) {
					result = 1;
				}
				// if the lock has been taken by others
				else {
					result = -1;
				}
				
			}
			
			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
		
	}

}
