package source.DataMappers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import source.domain.TutorAvailability;
import source.utils.LockManager;


public class TutorAvailabilityMapper {
	static Connection conn;
	static PreparedStatement ps;
	
	
	public int setAvailability(TutorAvailability t) {
		int status = 0;
		
		try {
			conn=MyDatabaseConnection.getConn();
			String[] availList = t.getAvailability();
			ps=conn.prepareStatement("DELETE FROM tutor_availability WHERE tutor_id = ?");
			ps.setInt(1, t.getId());
			status=ps.executeUpdate();
			for(int i=0;i<availList.length;i++) {
				ps=conn.prepareStatement("INSERT INTO tutor_availability(tutor_id,availability,booked) VALUES (?,?,?)");
				ps.setInt(1, t.getId());
				ps.setString(2, availList[i]);
				ps.setBoolean(3, t.isBooked()[i]);
				status=ps.executeUpdate();
			}
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
	public TutorAvailability getAvailability(int tutorId) {
		TutorAvailability t = new TutorAvailability();
		try {
			conn=MyDatabaseConnection.getConn();
			ps=conn.prepareStatement("select * from tutor_availability where tutor_id=(?)");
			ps.setInt(1, tutorId);
			ResultSet rs = ps.executeQuery();
			ArrayList<String> availStr = new ArrayList<String>(); 
			ArrayList<Boolean> booked = new ArrayList<Boolean>();
			boolean empty = true;
			while(rs.next()) {
				empty = false;
				availStr.add(rs.getString(3));
				boolean temp = rs.getBoolean(4);
				booked.add(temp);
				System.out.println(temp);
			}
			t.setId(tutorId);
			if(empty == false) {
				t.setAvailability(availStr.toArray(new String[availStr.size()]));
				Boolean[] bookedarr = new Boolean[booked.size()];
				for(int i=0;i<booked.size();i++) {
					bookedarr[i]=booked.get(i);
				}
				System.out.println(booked);
				t.setBooked(bookedarr);
			}
			else {
				t.setAvailability(new String[0]);
				t.setBooked(new Boolean[0]);
			}
			conn.close();
		}catch(Exception e){
			System.out.println(e);
			
		}
		return t;
	}

}
