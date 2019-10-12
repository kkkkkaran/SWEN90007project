package source.services;

import java.util.List;

import source.domain.Appointment;

public interface AppointmentsInterface {
	public int insertAppointment(Appointment a);
	public int deleteAppointment(int id, String role);
	public List<Appointment> getAppointmentsForTutor(int tutorid);
	public List<Appointment> getAppointmentsForStudent(int studentId);
	public int updateAppointment(Appointment a);
	public Appointment getAppointment(int appoitnmentId);
	
}
