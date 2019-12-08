package RuleEngine;



import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;


import visio.Person;
import visio.Product;
import visio.RuleEngine;

public class RuleEngineTests {

	Person person;
	Product product;
	RuleEngine ruleEngine;
	String rulesFile;
	double initialRate;
	
	@BeforeMethod
	public void setup() {
		person = new Person(720,"Florida");
		product = new Product("7-1 ARM", 5.0);
		ruleEngine = new RuleEngine();
		rulesFile = System.getProperty("user.dir")+"/src/main/java/config/rules.properties";
		ruleEngine.loadRules(rulesFile);
		initialRate = 5.0;
	}
	@Test(groups={"productAvailable","regression"})
	public void verifyProductionAvailabilityFalse() {
		//person.setState("North Dakota");
		ruleEngine.runRules(person, product, rulesFile);
		Assert.assertEquals(true, product.getDisqualified());
		
	}
	@Test(groups={"productAvailable","regression"})
	public void verifyProductionAvailabilityTrue() {
		person.setState("California");
		ruleEngine.runRules(person, product, rulesFile);
		Assert.assertEquals(false, product.getDisqualified());
	}
	@Test(groups={"productRate","regression"})
	public void verifyWithSpecialProductRate() {
		product.setName("7-1 ARM");
		product.setInterestRate(initialRate);
		person.setState("Florida");
		person.setCreditScore(720);
		double finalrunRulesInterestRate = ruleEngine.runRules(person, product, rulesFile);
		double finalInterest = initialRate + 0.5 + (-0.3);
		Assert.assertEquals(finalInterest, finalrunRulesInterestRate);
	}
	@Test(groups={"productRate","regression"})
	public void verifyWithSpecialProductRateForDifferentProductRate() {
		product.setName("15-1 ARM");
		product.setInterestRate(initialRate);
		person.setState("Florida");
		person.setCreditScore(720);
		double finalrunRulesInterestRate = ruleEngine.runRules(person, product, rulesFile);
		double finalInterest = initialRate + 0.7 + (-0.3);
		Assert.assertEquals(finalInterest, finalrunRulesInterestRate);
	}
	@Test(groups={"productRate","regression"})
	public void verifyWithoutSpecialProductRate() {
		product.setName("7-2 ARM");
		product.setInterestRate(initialRate);
		person.setState("Florida");
		person.setCreditScore(720);
		double finalrunRulesInterestRate = ruleEngine.runRules(person, product, rulesFile);
		double finalInterest = initialRate + 0.0 + (-0.3);
		Assert.assertEquals(finalInterest, finalrunRulesInterestRate);
	}
	@Test(groups={"CreditScore","regression"})
	public void verifyWithSpecialProductRateWithNegetiveCreditScore() {
		product.setName("7-1 ARM");
		product.setInterestRate(initialRate);
		person.setState("Florida");
		person.setCreditScore(-200);
		double finalrunRulesInterestRate = ruleEngine.runRules(person, product, rulesFile);
		double finalInterest = initialRate + 0.5 + (0.9);
		Assert.assertEquals(finalrunRulesInterestRate, finalInterest);
	}
	@Test(groups={"CreditScore","regression"})
	public void verifyWithSpecialProductRateWithLowCreditScore() {
		product.setName("7-1 ARM");
		product.setInterestRate(initialRate);
		person.setState("Florida");
		person.setCreditScore(399);
		double finalrunRulesInterestRate = ruleEngine.runRules(person, product, rulesFile);
		double finalInterest = initialRate + 0.5 + (0.9);
		Assert.assertEquals(finalrunRulesInterestRate, finalInterest);
	}
	@Test(groups={"CreditScore","regression"})
	public void verifyWithSpecialProductRateWithMediumCreditScore() {
		product.setName("7-1 ARM");
		product.setInterestRate(initialRate);
		person.setState("Florida");
		person.setCreditScore(650);
		double finalrunRulesInterestRate = ruleEngine.runRules(person, product, rulesFile);
		double finalInterest = initialRate + 0.5 + (0.5);
		Assert.assertEquals(finalrunRulesInterestRate, finalInterest);
	}
	@Test(groups={"CreditScore","regression"})
	public void verifyWithSpecialProductRateWithMaximumCreditScore() {
		product.setName("7-1 ARM");
		product.setInterestRate(initialRate);
		person.setState("Florida");
		person.setCreditScore(900);
		double finalrunRulesInterestRate = ruleEngine.runRules(person, product, rulesFile);
		double finalInterest = initialRate + 0.5 + (-0.3);
		Assert.assertEquals(finalrunRulesInterestRate, finalInterest);
	}
	@Test(groups={"CreditScore","regression"})
	public void verifyWithSpecialProductRateWithExceedingMaxCreditScore() {
		product.setName("7-1 ARM");
		product.setInterestRate(initialRate);
		person.setState("Florida");
		person.setCreditScore(999);
		double finalrunRulesInterestRate = ruleEngine.runRules(person, product, rulesFile);
		double finalInterest = initialRate + 0.5 + (-0.3);
		Assert.assertEquals(finalrunRulesInterestRate, finalInterest);
	}
}
