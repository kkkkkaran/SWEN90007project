package source.services;

import java.sql.SQLException;
import java.util.List;

import source.domain.Tutor;

public interface TutorDatabase {
	public int insertTutor(Tutor t);
	public Tutor getTutor(String userName, String passWord);
	public Tutor getTutorAtId(int id);
	public int updateTutor(Tutor t);
	public List<Tutor> listUnapprovedTutors() throws SQLException;
	public List<Tutor> listAllTutors() throws SQLException;
	int deleteTutor(Tutor t);
	Tutor lazyLoadedTutor(Tutor t);


}
