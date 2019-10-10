package source.services;

import java.util.List;

import source.domain.Course;
import source.domain.Tutor;

public interface TutorSubjectInterface {
	public int insertSubject(int tutorId,int subjectId);
	public int deleteSubjectBySubjectId(int subjectId);
	public int deleteSubject(int tutorId,int subjectId);
	public List<Tutor> getTutorForCourse(int courseId);
	public List<Course> getCoursesNotRegistered(Tutor t);
	public List<Course> getCoursesRegistered(Tutor t);

}
