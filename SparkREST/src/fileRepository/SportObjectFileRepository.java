package fileRepository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;

import beans.Adress;
import beans.Location;
import beans.ObjectStatus;
import beans.SportObject;
import beans.Status;
import beans.TypeOfSportObject;
import beans.WorkTime;
import beans.Workout;
import services.WorkoutService;

import java.io.IOException;
import java.io.PrintWriter;

public class SportObjectFileRepository {
	
	private static String fileLocation = "files/sportObjects.txt";
	
	public SportObjectFileRepository() {
		
	}
	
	public HashMap<String, SportObject> getAllSportObjects() {
		HashMap<String, SportObject> sportObjects = new HashMap<String, SportObject>();
		BufferedReader in = null;
		String line;
		try {
			in = new BufferedReader(new FileReader(new File(fileLocation)));
			while ((line = in.readLine()) != null) {
				SportObject sportObject = makeSportObjectFromLine(line);
				sportObjects.put(sportObject.getId(), sportObject);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
					
				}
			}
		}
		return sportObjects;
	}
	
	public SportObject saveSportObject(SportObject sportObject) {
		try(FileWriter f = new FileWriter(fileLocation, true);
				BufferedWriter b = new BufferedWriter(f);
				PrintWriter p = new PrintWriter(b);) {
			p.println(getStringFromSportObject(sportObject));
			return sportObject;
		} catch (IOException i) {
			i.printStackTrace();
			return null;
		}
	}
	
	public SportObject changeSportObject(String id, SportObject newSportObject) {
		HashMap<String, SportObject> sportObjects = this.getAllSportObjects();
		
		SportObject oldSportObject = sportObjects.get(id);
		sportObjects.replace(id, oldSportObject, newSportObject);
		writeAllSportObjectsInFile(sportObjects);
		
		return newSportObject;
	}
	
	public SportObject deleteSportObject(String id) {
		HashMap<String, SportObject> sportObjects = this.getAllSportObjects();
		
		SportObject sportObjectForDeleting = sportObjects.get(id);
		sportObjectForDeleting.setDeleted(true);
		
		writeAllSportObjectsInFile(sportObjects);
		
		return sportObjectForDeleting;
	}
	
	private void writeAllSportObjectsInFile(HashMap<String, SportObject> sportObjects) {
		FileWriter fileWriter = null;
		ArrayList<String> sportObjectsForWriting = getAllSportObjectsForWriting(sportObjects);
		try {
			fileWriter = new FileWriter(fileLocation);
			for(String sportObject : sportObjectsForWriting) {
				fileWriter.write(sportObject);
				fileWriter.write("\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(fileWriter != null) {
				try {
					fileWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private ArrayList<String> getAllSportObjectsForWriting(HashMap<String, SportObject> sportObjects){
		ArrayList<String> sportObjectsForWriting = new ArrayList<String>();
		
		for(SportObject sportObject : sportObjects.values()) {
			sportObjectsForWriting.add(this.getStringFromSportObject(sportObject));
		}
		
		return sportObjectsForWriting;
	}
	
	private String getStringFromSportObject(SportObject sportObject) {
		return sportObject.getId() + "," + Boolean.toString(sportObject.getDeleted()) + "," + sportObject.getName() + ","
				+ sportObject.getType().toString() + "," + sportObject.getStatus().toString() + "," + getStringFromLocation(sportObject.getLocation())
				+ "," + "logo" + Double.toString(sportObject.getAvgGrade()) + "," + getStringFromWorkTime(sportObject.getWorkTime());
	}
	
	private String getStringFromLocation(Location location) {
		return Double.toString(location.getLongitude()) + "," + Double.toString(location.getLatitude()) + ","
				+ location.getAdress();
	}
	
	private String getStringFromWorkTime(WorkTime workTime) {
		return Double.toString(workTime.getStartingAt()) + "," + Double.toString(workTime.getFinishingAt());
	}
	
	private SportObject makeSportObjectFromLine(String line) {
		String[] lineItems = line.split(",");
		
		String id = lineItems[0];
		boolean deleted = Boolean.parseBoolean(lineItems[1]);
		String name = lineItems[2];
		TypeOfSportObject type = this.getTypeOfSportObjectFromString(lineItems[3]);
		ArrayList<Workout> workouts = addAllWokoutsInSportObject(id);
		ObjectStatus status = this.getStatusOfSportObject(lineItems[4]);
		Location location = new Location(Double.parseDouble(lineItems[5]), Double.parseDouble(lineItems[6]),
				new Adress(lineItems[7],lineItems[8],lineItems[9], Integer.parseInt(lineItems[10])));
		//Image logo = this.getImageFromString(lineItems[11]);
		double avgGrade = Double.parseDouble(lineItems[12]);
		
		return new SportObject(id, deleted, name, type, workouts, status, location, null, avgGrade);
	}
	
	private TypeOfSportObject getTypeOfSportObjectFromString(String type) {
		if (type.equals("teretana"))
			return TypeOfSportObject.teretana;
		else if(type.equals("bazen"))
			return TypeOfSportObject.bazen;
		else if(type.equals("sportski centar"))
			return TypeOfSportObject.sportski_centar;
		else
			return TypeOfSportObject.plesni_studio;
	}
	
	private ObjectStatus getStatusOfSportObject(String status) {
		if (status.equals("radi"))
			return ObjectStatus.radi;
		else
			return ObjectStatus.ne_radi;
	}
	
	private ArrayList<Workout> addAllWokoutsInSportObject(String sportObjectId){
		WorkoutService ws = new WorkoutService();
		
		ArrayList<Workout> workouts = new ArrayList<Workout>();
		for(Workout workout : ws.getAllWorkouts()) {
			if(sportObjectId.equals(workout.getSportObject().getId()))
				workouts.add(workout);
		}
		
		return workouts;
	}

}
