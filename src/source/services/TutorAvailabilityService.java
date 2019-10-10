package source.services;

import source.domain.TutorAvailability;
import source.DataMappers.TutorAvailabilityMapper;

public class TutorAvailabilityService implements TutorAvailabilityInterface{
	
	@Override
	public TutorAvailability getAvailability(int tutorId) {
		TutorAvailabilityMapper tam = new TutorAvailabilityMapper();
		return tam.getAvailability(tutorId);
	}
	
	@Override
	public int setAvailability(TutorAvailability t) {
		TutorAvailabilityMapper tam = new TutorAvailabilityMapper();
		return tam.setAvailability(t);
	}
}
