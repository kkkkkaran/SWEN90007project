package source.DataMappers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import source.domain.Appointment;
import source.utils.LockManager;


public class AppointmentsMapper {
	
	static Connection conn;
	static PreparedStatement ps;
	
	public int insertAppointment(Appointment a) {
		int status = 0;
		try {
			conn=MyDatabaseConnection.getConn();
			ps=conn.prepareStatement("INSERT INTO appointment(studentid,tutorid,slot,accepted) VALUES(?,?,?,?)");
			ps.setInt(1, a.getStudentId());
			ps.setInt(2, a.getTutorId());
			ps.setString(3, a.getSlot());
			ps.setBoolean(4, a.isTutorAccepted());
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
	
	public int deleteAppointment(int id, String role) {
		int status = 0;
		try {
			conn=MyDatabaseConnection.getConn();
			if(role.equalsIgnoreCase("student")){
				ps=conn.prepareStatement("DELETE FROM appointment WHERE studentid = ? ");
			}
			else {
				ps=conn.prepareStatement("DELETE FROM appointment WHERE tutorid = ? ");
			}
			
			ps.setInt(1,id);
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
	public List<Appointment> getAppointmentsForTutor(int tutorid) {
		List<Appointment> appointments = new ArrayList<>();
		try {
			conn=MyDatabaseConnection.getConn();
			ps=conn.prepareStatement("SELECT * FROM appointment WHERE tutorid = ?;");
			ps.setInt(1,tutorid);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Appointment a = new Appointment();
				a.setAppointmentId(rs.getInt(1));
				a.setStudentId(rs.getInt(2));
				a.setTutorId(rs.getInt(3));
				a.setSlot(rs.getString(4));
				a.setTutorAccepted(rs.getBoolean(5));
				appointments.add(a);
			}
			
			conn.close();
			
		}catch(Exception e){
			System.out.println(e);
			
		}
		return appointments;
	}
	public List<Appointment> getAppointmentsForStudent(int studentId) {
		List<Appointment> appointments = new ArrayList<>();
		try {
			conn=MyDatabaseConnection.getConn();
			ps=conn.prepareStatement("SELECT * FROM appointment WHERE studentid = ?;");
			ps.setInt(1,studentId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Appointment a = new Appointment();
				a.setAppointmentId(rs.getInt(1));
				a.setStudentId(rs.getInt(2));
				a.setTutorId(rs.getInt(3));
				a.setSlot(rs.getString(4));
				a.setTutorAccepted(rs.getBoolean(5));
				appointments.add(a);
			}
			
			conn.close();
			
		}catch(Exception e){
			System.out.println(e);
			
			
		}
		return appointments;
	}
	
	public Appointment getAppointment(int appointmentId) {
		Appointment a = new Appointment();	
		try {
			conn=MyDatabaseConnection.getConn();
			ps=conn.prepareStatement("SELECT * FROM appointment WHERE id_appointment = ?;");
			ps.setInt(1,appointmentId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				a.setAppointmentId(rs.getInt(1));
				a.setStudentId(rs.getInt(2));
				a.setTutorId(rs.getInt(3));
				a.setSlot(rs.getString(4));
				a.setTutorAccepted(rs.getBoolean(5));
				
			}	
			
			conn.close();
		}catch(Exception e){
			System.out.println(e);
			
			
		}
		return a;
	}
	
	public int updateAppointment(Appointment a) {
		int status = 0;
		try {
			conn=MyDatabaseConnection.getConn();
			ps=conn.prepareStatement("INSERT INTO appointment(studentid,tutorid,slot,accepted) VALUES(?,?,?,?)");
			ps.setInt(1, a.getStudentId());
			ps.setInt(2, a.getTutorId());
			ps.setString(3, a.getSlot());
			ps.setBoolean(4, a.isTutorAccepted());
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
