package services;

import java.util.Collection;
import java.util.HashMap;

import beans.SportObject;
import fileRepository.SportObjectFileRepository;

public class SportObjectService {
	public static SportObjectFileRepository sofr = new SportObjectFileRepository();
	
	public SportObjectService() {
		
	}
	
	public Iterable<SportObject> getAllSportObjects() {
		HashMap<String, SportObject> validSportObjects = new HashMap<String, SportObject>();
		for(SportObject sportObject : sofr.getAllSportObjects().values()) {
			if(!sportObject.getDeleted())
				validSportObjects.put(sportObject.getId(), sportObject);
		}
		return validSportObjects.values();
	}
	
	public SportObject saveSportObject(SportObject sportObject) {
		return sofr.saveSportObject(sportObject);
	}
	
	public SportObject deleteSportObject(String id) {
		return sofr.deleteSportObject(id);
	}
	
	public SportObject changeSportObject(String id, SportObject newSportObject) {
		newSportObject.setDeleted(false);
		return sofr.changeSportObject(id, newSportObject);
	}
	
	public SportObject findSportObject(String id) {
		HashMap<String, SportObject> sportObjects = sofr.getAllSportObjects();
		SportObject sportObject = sportObjects.get(id);
		if(!sportObject.getDeleted())
			return sportObject;
		else
			return null;
	}
}
