package beans;

import java.util.ArrayList;

public class Trainer extends User {
	private ArrayList<HistoryOfTraining> upcomingWorkouts;
	
	public Trainer() {
		super();
		this.upcomingWorkouts = new ArrayList<HistoryOfTraining>();
	}
	
	public Trainer(User user) {
		super(user);
		this.upcomingWorkouts = new ArrayList<HistoryOfTraining>();
	}
	
	public ArrayList<HistoryOfTraining> getUpcmonigWorkouts() {
		return upcomingWorkouts;
	}
	
	public void setUpcomingWokrouts(ArrayList<HistoryOfTraining> upcomingWorkouts) {
		this.upcomingWorkouts= upcomingWorkouts;
	}
}
