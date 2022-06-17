package beans;

import java.util.ArrayList;

public class Customer extends User {
	private Membership membership;
	private ArrayList<SportObject> wasIn; 
	private int points;
	private CustomerType type;
	
	public Customer() {
		super();
		this.membership = new Membership();
		this.wasIn = new ArrayList<SportObject>();
		this.points = 0;
		this.type = new CustomerType();
	}
	
	public Customer(Membership membership, ArrayList<SportObject> wasIn, int points, CustomerType type) {
		super();
		this.membership = membership;
		this.wasIn = wasIn;
		this.points = points;
		this.type = type;
	}
	
	public Customer(User user) {
		super(user);
		this.membership = new Membership();
		this.wasIn = new ArrayList<SportObject>();
		this.points = 0;
		this.type = new CustomerType();
	}
	
	public Membership getMembership() {
		return membership;
	}
	
	public void setMembership(Membership membership) {
		this.membership = membership;
	}
	
	public ArrayList<SportObject> getWasIn() {
		return wasIn;
	}
	
	public void setWasIn(ArrayList<SportObject> wasIn) {
		this.wasIn = wasIn;
	}
	
	public int getPoints() {
		return points;
	}
	
	public void setPoints(int points) {
		this.points = points;
	}
	
	public CustomerType getCustomerType() {
		return type;
	}
	
	public void setCustomerType(CustomerType type) {
		this.type = type;
	}
}
