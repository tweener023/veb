package fileRepository;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.HashMap;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import beans.SportObject;
import beans.Trainer;
import beans.TypeOfWorkout;
import beans.Workout;


public class WorkoutFileRepository {
	private static String fileLocation = "files/workouts.txt";
	
	public WorkoutFileRepository() {
	}
	
	public HashMap<String, Workout> getAllWorkouts() {
		HashMap<String, Workout> workouts = new HashMap<String, Workout>();
		BufferedReader in = null;
		String line;
		try {
			in = new BufferedReader(new FileReader(new File(fileLocation)));
			while((line = in.readLine()) != null) {
				Workout workout = makeWorkoutFromLine(line.trim());
				if(!workout.getDeleted())
					workouts.put(workout.getId(), workout);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(in != null) {
				try {
					in.close();
				} catch (Exception e) {
					
				}
			}
		}
		return workouts;
	}
	
	public boolean saveWorkout(Workout workout) {
		try(FileWriter f = new FileWriter(fileLocation, true);
				BufferedWriter b = new BufferedWriter(f);
				PrintWriter p = new PrintWriter(b);) {
			p.println(getStringFromWorkout(workout));
			return true;
		} catch (IOException i) {
			i.printStackTrace();
			return false;
		}
	}
	
	private String getStringFromWorkout(Workout workout) {
		return workout.getId() + "," + Boolean.toString(workout.getDeleted()) + "," + workout.getName() + ","
				+ getStringFromTypeOfWorkout(workout) + "," + workout.getSportObject().getId() + "," 
				+ Integer.toString(workout.getLength()) + "," + workout.getTrainer().getId() + "," + workout.getDescription()
				+ "," + "slika";
	}
	
	private String getStringFromTypeOfWorkout(Workout workout) {
		if(workout.getType().equals(TypeOfWorkout.grupni))
			return "grupni";
		else if(workout.getType().equals(TypeOfWorkout.personalni))
			return "personalni";
		else
			return "teretana";
	}
	
	private Workout makeWorkoutFromLine(String line) {
		String[] lineItems = line.split(",");
		
		String id = lineItems[0];
		boolean deleted = Boolean.parseBoolean(lineItems[1]);
		String name = lineItems[2];
		TypeOfWorkout type = this.getTypeOfWorkoutFromString(lineItems[3]);
		SportObject sportObject = new SportObject();
		sportObject.setId(lineItems[4]);
		int length = Integer.parseInt(lineItems[5]);
		Trainer trainer = new Trainer();
		trainer.setId(lineItems[6]);
		String description = lineItems[7];
		//Image image = this.getImageFromString(lineItems[8]);
		
		return new Workout(id, deleted, name, type, sportObject, length, trainer, description, null);
	}
	
	private TypeOfWorkout getTypeOfWorkoutFromString(String type) {
		if(type.equals("grupni"))
			return TypeOfWorkout.grupni;
		else if(type.equals("personalni"))
			return TypeOfWorkout.personalni;
		else
			return TypeOfWorkout.teretana;
	}

}
