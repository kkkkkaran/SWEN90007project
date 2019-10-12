package source.domain;

public class Appointment {
	private int appointmentId;
	private int studentId;
	private int tutorId;
	private boolean tutorAccepted;
	private String slot;
	
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public int getTutorId() {
		return tutorId;
	}
	public void setTutorId(int tutorId) {
		this.tutorId = tutorId;
	}
	public boolean isTutorAccepted() {
		return tutorAccepted;
	}
	public void setTutorAccepted(boolean tutorAccepted) {
		this.tutorAccepted = tutorAccepted;
	}
	public String getSlot() {
		return slot;
	}
	public void setSlot(String slot) {
		this.slot = slot;
	}
	public int getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}

}
