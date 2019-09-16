package source.dataSource;

import source.domain.Tutor;

public interface TutorDatabase {
	public int insertTutor(Tutor t);
	public Tutor getTutor(String userName, String passWord);
	public Tutor getTutorAtId(int id);
	public int updateTutor(Tutor t);

}
