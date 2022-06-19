package beans;

import java.util.ArrayList;
import java.util.UUID;

public class SportObject {
	private String id;
	private boolean deleted;
	private String name;
	private TypeOfSportObject type;
	private ArrayList<Workout> content;
	private ObjectStatus status;
	private Location location;
	private String logo;
	private double avgGrade;
	private WorkTime workTime;
	
	public SportObject() {
		content = new ArrayList<Workout>();
		this.id = UUID.randomUUID().toString();
		this.deleted = false;
	}
	
	public SportObject(String name, TypeOfSportObject type, ArrayList<Workout> content, ObjectStatus status,
			Location location, String logo, WorkTime workTime) {
		super();
		this.name = name;
		this.type = type;
		this.content = content;
		this.status = status;
		this.location = location;
		this.logo = logo;
		this.workTime = workTime;
	}
	
	public SportObject(String id, boolean deleted, String name, TypeOfSportObject type, ArrayList<Workout> content,
			ObjectStatus status, Location location, String logo, double avgGrade) {
		super();
		this.id = id;
		this.deleted = deleted;
		this.name = name;
		this.type = type;
		this.content = content;
		this.status = status;
		this.location = location;
		this.logo = logo;
		this.avgGrade = avgGrade;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public boolean getDeleted() {
		return deleted;
	}
	
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public TypeOfSportObject getType() {
		return type;
	}
	
	public void setType(TypeOfSportObject type) {
		this.type = type;
	}
	
	public ArrayList<Workout> getContent() {
		return content;
	}
	
	public void setContent(ArrayList<Workout> content) {
		this.content = content;
	}
	
	public ObjectStatus getStatus() {
		return status;
	}
	
	public void setStatus(ObjectStatus status) {
		this.status = status;
	}
	
	public Location getLocation() {
		return location;
	}
	
	public void setLocation(Location location) {
		this.location = location;
	}
	
	public String getLogo() {
		return logo;
	}
	
	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	public double getAvgGrade() {
		return avgGrade;
	}
	
	public void setAvgGrade(double avgGrade) {
		this.avgGrade = avgGrade;
	}
	
	public WorkTime getWorkTime() {
		return workTime;
	}
	
	public void setWorkTime(WorkTime workTime) {
		this.workTime = workTime;
	}
}
