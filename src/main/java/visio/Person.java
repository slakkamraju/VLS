package visio;

public class Person {

	private int creditScore;
	private String state;
	
	public Person(int creditScore, String state) {
		this.creditScore = creditScore;
		this.state = state;
	}
	public int getCreditScore() {
		return this.creditScore;
	}
	public void setCreditScore(int creditScore) {
		this.creditScore = creditScore;
	}
	public String getState() {
		return this.state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
