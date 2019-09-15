package source;
public interface TutorDatabase {
	public int insertTutor(Tutor t);
	public Tutor getTutor(String userName, String passWord);
	public int updateTutor(Tutor t);

}
