package source.services;


import java.util.List;
import source.DataMappers.TutorCourseMapper;
import source.domain.Tutor;


public class MyTutorSubjectDatabase implements TutorSubjectDatabase{
	
	
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

}
