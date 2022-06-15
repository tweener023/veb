package beans;

public class WorkTime {
	private double startingAt;
	private double finishingAt;
	
	public WorkTime() {
		
	}
	
	public WorkTime(double startingAt, double finishingAt) {
		super();
		this.startingAt = startingAt;
		this.finishingAt = finishingAt;
	}
	
	public double getStartingAt() {
		return startingAt;
	}
	
	public void setStartingAt(double startingAt) {
		this.startingAt = startingAt;
	}
	
	public double getFinishingAt() {
		return finishingAt;
	}
	
	public void setFinishingAt(double finishingAt) {
		this.finishingAt = finishingAt;
	}
}
