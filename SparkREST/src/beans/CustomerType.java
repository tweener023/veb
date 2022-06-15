package beans;

public class CustomerType {
	private String typeName;
	private int discountPercentage;
	private int pointsRequired;
	
	public CustomerType() {
		
	}
	
	public CustomerType(String typeName, int discountPercentage, int pointsRequired) {
		super();
		this.typeName = typeName;
		this.discountPercentage = discountPercentage;
		this.pointsRequired = pointsRequired;
	}
	
	public String getType() {
		return typeName;
	}
	
	public void setType(String typeName) {
		this.typeName = typeName;
	}
	
	public int getDiscountPercentage() {
		return discountPercentage;
	}
	
	public void setDiscountPercentage(int discountPercentage) {
		this.discountPercentage = discountPercentage;
	}
	
	public int getPointsRequired() {
		return pointsRequired;
	}
	
	public void setPointsRequired(int pointsRequired) {
		this.pointsRequired = pointsRequired;
	}
}
