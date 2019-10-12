package source.domain;

public class TutorAvailability {
	private int id;
	private String[] availability;
	private Boolean[] booked;
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
	public Boolean[] isBooked() {
		return booked;
	}
	public void setBooked(Boolean[] bookedarr) {
		this.booked = bookedarr;
	}

}
