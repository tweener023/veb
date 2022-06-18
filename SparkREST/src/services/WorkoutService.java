package services;

import java.util.Collection;

import beans.Workout;
import fileRepository.WorkoutFileRepository;

public class WorkoutService {
	private static WorkoutFileRepository wfr = new WorkoutFileRepository();
	
	public WorkoutService() {
		
	}
	
	public Collection<Workout> getAllWorkouts() {
		return wfr.getAllWorkouts().values();
	}
	
	public boolean saveWorkout(Workout workout) {
		return wfr.saveWorkout(workout);
	}
}
