package source.services;

import java.util.List;

import source.domain.Tutor;

public interface TutorSubjectDatabase {
	public int insertSubject(int tutorId,int subjectId);
	public int deleteSubjectBySubjectId(int subjectId);
	public int deleteSubject(int tutorId,int subjectId);
	public List<Tutor> getTutorForCourse(int courseId);

}
