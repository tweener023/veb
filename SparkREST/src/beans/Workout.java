package beans;

import java.util.UUID;

public class Workout {
	private String id;
	private boolean deleted;
	private String name;
	private TypeOfWorkout type;
	private SportObject sportObject;
	private int length; // u minituma
	private Trainer trainer;
	private String description;
	private String image;
	
	public Workout() {
		this.id = UUID.randomUUID().toString();
		this.deleted = false;
		this.description = " ";
	}
	
	public Workout(String name, TypeOfWorkout type, SportObject sportObject, int length, Trainer trainer,
			String description, String image) {
		super();
		this.name = name;
		this.type = type;
		this.sportObject = sportObject;
		this.length = length;
		this.trainer = trainer;
		this.description = description;
		this.image = image;
	}
	
	public Workout(String id, boolean deleted, String name, TypeOfWorkout type, SportObject sportObject,
			int length, Trainer trainer, String description, String image) {
		super();
		this.id = id;
		this.deleted = deleted;
		this.name = name;
		this.type = type;
		this.sportObject = sportObject;
		this.length = length;
		this.trainer = trainer;
		this.description = description;
		this.image = image;
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
	
	public TypeOfWorkout getType() {
		return type;
	}
	
	public void setType(TypeOfWorkout type) {
		this.type = type;
	}
	
	public SportObject getSportObject() {
		return sportObject;
	}
	
	public void setSportObject(SportObject sportObject) {
		this.sportObject = sportObject;
	}
	
	public int getLength() {
		return length;
	}
	
	public void setLenget(int length) {
		this.length = length;
	}
	
	public Trainer getTrainer() {
		return trainer;
	}
	
	public void setTrainer(Trainer trainer) {
		this.trainer = trainer;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getImage() {
		return image;
	}
	
	public void setImage(String image) {
		this.image = image;
	}
}
