package source.services;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import source.domain.Tutor;
import source.DataMappers.TutorMapper;



public class TutorService implements TutorInterface {
	
	static HashMap<Integer, Tutor> tutorIdentityMap = new HashMap<Integer, Tutor>();
	
	@Override
	public int insertTutor(Tutor t) {
		TutorMapper tm = new TutorMapper();
		return tm.insertTutor(t);
	}
	
	
	@Override
	public int updateTutor(Tutor t) {
		TutorMapper tm = new TutorMapper();
		String[] subjects = t.getSubjects();
		TutorSubjectInterface tsd = new TutorSubjectService();
		for(int i=0;i<subjects.length;i++) {
			String[] splitted = subjects[i].split(":"); //format is id:subjectName
			tsd.insertSubject(t.getId(), Integer.parseInt(splitted[0]));
		}
		t = tm.updateTutor(t);
		tutorIdentityMap.replace(t.getId(), t);
		return 1;
	}
	

	@Override
	public Tutor getTutor(String username, String password) {
		TutorMapper tm = new TutorMapper();
		Tutor t = tm.getTutor(username, password);
		tutorIdentityMap.put(t.getId(), t);
		return t;
	}
	
	
	@Override
	public Tutor lazyLoadedTutor(Tutor t) {	
		TutorMapper tm = new TutorMapper();
		t = tm.lazyLoadedTutor(t);
		tutorIdentityMap.replace(t.getId(), t); //Updated object inserted in identity map
		return t;
	}
	
	@Override
	public Tutor getTutorAtId(int id) {
			
		//identity map implementation
		if(tutorIdentityMap.containsKey(id)) {
			Tutor t=tutorIdentityMap.get(id);
			if(t.getFirstName() != null) { //if contains full profile, and not partial due to lazy loading
				return t;
			}
			else { //fetching rest of tutor profile, implementing lazy load
				TutorInterface td = new TutorService();
				return td.lazyLoadedTutor(t);
			}
		}
		TutorMapper tm = new TutorMapper();
		Tutor t = tm.getTutorAtId(id);
		tutorIdentityMap.put(id, t);
		return t;
	}
	
	
	public List<Tutor> listUnapprovedTutors() throws SQLException{
		TutorMapper tm = new TutorMapper();
		return tm.listUnapprovedTutors();
	}
	
	
	public List<Tutor> listAllTutors() throws SQLException{
		TutorMapper tm = new TutorMapper();
		return tm.listAllTutors();
	}
	
	
	public int deleteTutor(Tutor t) {
		TutorMapper tm = new TutorMapper();
		int status= tm.deleteTutor(t);
		if(status == 1 && tutorIdentityMap.containsKey(t.getId())) {
			tutorIdentityMap.remove(t.getId());
		}
		return status;
		
	}
		
		

}
