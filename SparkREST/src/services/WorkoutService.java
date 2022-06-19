package services;

import java.util.Collection;
import java.util.HashMap;

import beans.Workout;
import fileRepository.WorkoutFileRepository;

public class WorkoutService {
	private static WorkoutFileRepository wfr = new WorkoutFileRepository();
	
	public WorkoutService() {
		
	}
	
	public Collection<Workout> getAllWorkouts() {
		HashMap<String, Workout> validWorkouts = new HashMap<String, Workout>();
		for(Workout workout : wfr.getAllWorkouts().values()) {
			if(!workout.getDeleted()) {
				validWorkouts.put(workout.getId(), workout);
			}
		}
		return validWorkouts.values();
	}
	
	public Workout saveWorkout(Workout workout) {
		return wfr.saveWorkout(workout);
	}
	
	public Workout deleteWorkout(String id) {
		return wfr.deleteWotkout(id);
	}
	
	public Workout changeWorkout(String id, Workout newWorkout) {
		newWorkout.setDeleted(false);
		return wfr.changeWorkout(id, newWorkout);
	}
	
	public Workout findWorkoutById(String id) {
		HashMap<String, Workout> workouts = wfr.getAllWorkouts();
		Workout workout = workouts.get(id);
		if(!workout.getDeleted())
			return workout;
		else
			return null;
	}
}
