package beans;

import java.util.Date;

public class Membership {
	private String id;
	private boolean deleted;
	private TypeOfMembership type;
	private Date dateOfPaying;
	private Date validUntil;
	private int price;
	private Customer customer;
	private Status status;
	private NumberOfWorkouts numberOfWorkouts;
	
	public Membership() {
		this.deleted = false;
	}
	
	public Membership(String id, boolean deleted, TypeOfMembership type, Date dateOfPaying, Date validUntil,
			int price, Customer customer, Status status, NumberOfWorkouts numberOfWorkouts) {
		super();
		this.id = id;
		this.deleted = deleted;
		this.type = type;
		this.dateOfPaying = dateOfPaying;
		this.validUntil = validUntil;
		this.price = price;
		this.customer = customer;
		this.status = status;
		this.numberOfWorkouts = numberOfWorkouts;
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
	
	public TypeOfMembership getTypeOfMembership() {
		return type;
	}
	
	public void setTypeOfMembership(TypeOfMembership type) {
		this.type = type;
	}
	
	public Date getDateOfPaying() {
		return dateOfPaying;
	}
	
	public void setDateOfPaying(Date dateOfPaying) {
		this.dateOfPaying = dateOfPaying;
	}
	
	public Date getValidUntil() {
		return validUntil;
	}
	
	public void setValitUntil(Date validUntil) {
		this.validUntil = validUntil;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public Status getStatus() {
		return status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}
	
	public NumberOfWorkouts getNumberOfWorkouts() {
		return numberOfWorkouts;
	}
	
	public void setNumberOfWorkouts(NumberOfWorkouts numberOfWorkouts) {
		this.numberOfWorkouts = numberOfWorkouts;
	}
}
