package source.services;

import source.domain.TutorAvailability;

public interface TutorAvailabilityInterface {

	public int setAvailability(TutorAvailability t);

	public TutorAvailability getAvailability(int tutorId);

}
