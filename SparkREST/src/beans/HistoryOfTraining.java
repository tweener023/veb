package beans;

import java.util.Date;

public class HistoryOfTraining {
	private Date startOfWorkout;
	private Workout workout;
	private Customer customer;
	private Trainer trainer;
	
	public HistoryOfTraining() {
		
	}
	
	public HistoryOfTraining(Date startOfWorkout, Workout workout, Customer customer, Trainer trainer) {
		super();
		this.startOfWorkout = startOfWorkout;
		this.workout = workout;
		this.customer = customer;
		this.trainer = trainer;
	}
	
	public Date getStartOfWorkout() {
		return startOfWorkout;
	}
	
	public void setStartOfWorkout(Date startOfWorkout) {
		this.startOfWorkout = startOfWorkout;
	}
	
	public Workout getWorkout() {
		return workout;
	}
	
	public void setWorkout(Workout workout) {
		this.workout = workout;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public Trainer getTrainer() {
		return trainer;
	}
	
	public void setTrainer(Trainer trainer) {
		this.trainer = trainer;
	}
}
