package source.domain;

public class TutorAvailability {
	private int id;
	private String[] availability;
	private boolean[] booked;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String[] getAvailability() {
		return availability;
	}
	public void setAvailability(String[] availability) {
		this.availability = availability;
	}
	public boolean[] isBooked() {
		return booked;
	}
	public void setBooked(boolean[] booked) {
		this.booked = booked;
	}

}
