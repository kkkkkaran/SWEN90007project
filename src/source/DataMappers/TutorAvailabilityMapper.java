package source.DataMappers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


import source.domain.TutorAvailability;


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
			for(int i=0;i<availList.length;i++) {
				ps=conn.prepareStatement("INSERT INTO tutor_availability(tutor_id,availability,booked) VALUES (?,?,?)");
				ps.setInt(1, t.getId());
				ps.setString(2, availList[i]);
				ps.setBoolean(3, t.isBooked()[i]);
				status=ps.executeUpdate();
			}
			conn.close();
			
		}catch(Exception e){
			System.out.println(e);
			
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
				booked.add(rs.getBoolean(4));
			}
			t.setId(tutorId);
			if(empty == false) {
				t.setAvailability(availStr.toArray(new String[availStr.size()]));
				boolean[] bookedarr = new boolean[booked.size()];
				for(int i=0;i<booked.size();i++) {
					bookedarr[i]=booked.get(i);
				}
				t.setBooked(bookedarr);
			}
			else {
				t.setAvailability(new String[0]);
				t.setBooked(new boolean[0]);
			}
			
		}catch(Exception e){
			System.out.println(e);
			
		}
		return t;
	}

}
