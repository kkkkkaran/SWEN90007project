package source.domain;
public class Tutor extends Member{
	

	private String[] subjects;
	private String price;
	private boolean approved;
	public String[] getSubjects() {
		return subjects;
	}
	public void setSubjects(String[] subjects) {
		this.subjects = subjects;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public boolean getApproved() {
		return approved;
	}
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	

}
