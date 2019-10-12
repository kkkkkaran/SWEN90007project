package source.services;

import java.util.List;

import source.DataMappers.AppointmentsMapper;
import source.domain.Appointment;

public class AppointmentsService implements AppointmentsInterface {

	@Override
	public int insertAppointment(Appointment a) {
		AppointmentsMapper am = new AppointmentsMapper();
		return am.insertAppointment(a);
	}

	@Override
	public int deleteAppointment(int id, String role) {
		AppointmentsMapper am = new AppointmentsMapper();
		return am.deleteAppointment(id,role);
	}

	@Override
	public List<Appointment> getAppointmentsForTutor(int tutorid) {
		AppointmentsMapper am = new AppointmentsMapper();
		return am.getAppointmentsForTutor(tutorid);
	}

	@Override
	public List<Appointment> getAppointmentsForStudent(int studentId) {
		AppointmentsMapper am = new AppointmentsMapper();
		return am.getAppointmentsForStudent(studentId);
	}

	@Override
	public int updateAppointment(Appointment a) {
		AppointmentsMapper am = new AppointmentsMapper();
		return am.updateAppointment(a);
	}
	
	@Override
	public Appointment getAppointment(int appointmentId) {
		AppointmentsMapper am = new AppointmentsMapper();
		return am.getAppointment(appointmentId);
	}

}
