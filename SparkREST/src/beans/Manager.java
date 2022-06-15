package beans;

public class Manager extends User {
	private SportObject sportObject;
	
	public Manager() {
		super();
		this.sportObject = new SportObject();
	}
	
	public Manager(User user) {
		super(user);
		this.sportObject = new SportObject();
	}
	
	public SportObject getSportObject() {
		return sportObject;
	}
	
	public void setSportObject(SportObject sportObject) {
		this.sportObject = sportObject;
	}
}
