package source.services;


import java.util.List;
import source.DataMappers.TutorCourseMapper;
import source.domain.Course;
import source.domain.Tutor;


public class TutorSubjectService implements TutorSubjectInterface{
	
	
	@Override
	public int insertSubject(int tutorId,int subjectId) {
		TutorCourseMapper tcm=new TutorCourseMapper();
		return tcm.insertSubject(tutorId, subjectId);
	}
	
	@Override
	public int deleteSubjectBySubjectId(int subjectId) {
		TutorCourseMapper tcm=new TutorCourseMapper();
		return tcm.deleteSubjectBySubjectId(subjectId);
		
	}
	
	@Override
	public int deleteSubject(int tutorId,int subjectId) {
		TutorCourseMapper tcm=new TutorCourseMapper();
		return tcm.deleteSubject(tutorId, subjectId);
	}
	
	@Override
	public List<Tutor> getTutorForCourse(int courseId) {
		TutorCourseMapper tcm=new TutorCourseMapper();
		return tcm.getTutorForCourse(courseId);
	}
	@Override
	public List<Course> getCoursesNotRegistered(Tutor t){
		TutorCourseMapper tcm=new TutorCourseMapper();
		return tcm.getCoursesNotRegistered(t);
	}
	@Override
	public List<Course> getCoursesRegistered(Tutor t){
		TutorCourseMapper tcm=new TutorCourseMapper();
		return tcm.getCoursesRegistered(t);
	}

}
