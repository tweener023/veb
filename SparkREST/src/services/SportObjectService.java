package services;

import java.util.Collection;

import beans.SportObject;
import fileRepository.SportObjectFileRepository;

public class SportObjectService {
	public static SportObjectFileRepository sofr = new SportObjectFileRepository();
	
	public SportObjectService() {
		
	}
	
	public Collection<SportObject> getAllSportObjects() {
		return sofr.getAllSportObjects().values();
	}
	
	public boolean saveSportObject(SportObject sportObject) {
		return sofr.saveSportObject(sportObject);
	}
}
