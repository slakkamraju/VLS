package visio;

public class Product {
	private String name;
	private double interestRate;
	private boolean disqualified;
	
	public Product(String name, double interestRate){
		this.name = name;
		this.interestRate = interestRate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}
	public boolean getDisqualified() {
		return this.disqualified;
	}
	public void setDisqualified(boolean disqualified) {
		this.disqualified = disqualified;
	}
	
}
