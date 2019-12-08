package visio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class RuleEngine {

	//To hold Rules
	static Properties rulesProp;

	//Created main to call the rule engine and test the implementation
	public static void main(String[] args) {
		
		RuleEngine re = new RuleEngine();
		String rulesFile = System.getProperty("user.dir")+"/src/main/java/config/rules.properties";
		re.loadRules(rulesFile);
		double AllProductsInitialRate = Double.parseDouble(rulesProp.getProperty("AllProductsInitialRate"));
		Person person = new Person(720,"Florida");
		Person person1 = new Person(600,"New Jersey");
		Person person2 = new Person(400,"Chicago");
		Product product = new Product("7-1 ARM", AllProductsInitialRate);
		Product product1 = new Product("12-1 ARM", AllProductsInitialRate);
		Product product2 = new Product("30-1 ARM", AllProductsInitialRate);
		//Product product = new Product("70 ARM", 5.0);

		
		
		System.out.println("Final Product interest Rate for " + product.getName() + " is : " + re.runRules(person, product, rulesFile));
		System.out.println("Final Product interest Rate for " + product1.getName() + " is : " + re.runRules(person1, product1, rulesFile));
		System.out.println("Final Product interest Rate for " + product2.getName() + " is : " + re.runRules(person2, product2, rulesFile));
//		re.runRules(person1, product1, rulesFile);
//		re.runRules(person2, product2, rulesFile);
	}

	//Main Rule Engine which determines the final interest by dynamically reading all the rules
	//from file each person/product and return the final interest for the product
	public double runRules(Person person, Product product, String rules) {

		//Load the rules file
		loadRules(rules);
		
		//Reading the rules configuration file for the initial/common interest rate for 
		//all the products, this rate can be configurable in the rules.properties
		//and it takes affects without changing the code
		double startInterestRate = Double.valueOf(rulesProp.getProperty("AllProductsInitialRate"));
		
		//To hold Final Product Interest rate
		double finalProductInterest=0.0;
		
		//To hold CreditScore interest incentive
		double creditInterestRateDiscount=0.0;
		
		//To hold special interest based on the product
		double specialRateForProduct=0.0;

		

		//Set the boolean value for 'disqualified' member of the Product based on the 
		//Person's State
		setProuctQualification(product, person);

		//Get the Interest rate based on the Person's Credit Score
		//Based on the credit score interest rate could be more or less
		creditInterestRateDiscount = getInterestRateBasedOnCreditScore(person);

		//Some special products may have more or less interest rate
		//Getting the product related interest from the Product class
		//to decide the final interest rate
		specialRateForProduct = getSpecialProductRate(product);

		

		//Printing the required output
		//System.out.println("startInterestRate : " + startInterestRate + " CreditInterestRate: " + creditInterestRateDiscount + " ProductInterestRate: " + specialRateForProduct);
		
		//Calculate final Product Interest Rate
		finalProductInterest = finalProductInterestRate(startInterestRate,creditInterestRateDiscount,specialRateForProduct);
		//finalProductInterest = startInterestRate + creditInterestRateDiscount + specialRateForProduct;
		
		//Printing the Final Product Interest Rate and availability of the product to the person
		System.out.println("Product Interest Rate: " + finalProductInterest + " Product Disqualified: " + product.getDisqualified());
		//Printing a empty line for good indentation
		System.out.println();
		return finalProductInterest;
	}
	//Calculate returns final product Rate 
	public double finalProductInterestRate(double startInterestRate, double creditInterestRateDiscount, double specialRateForProduct ) {
		return startInterestRate + creditInterestRateDiscount + specialRateForProduct;
	}
	
	//Get the special rate of the product
	public double getSpecialProductRate(Product product) {
		return getSpecialRate(product.getName());
	}
	//Read the rule from the file and find out the special interest
	public double getSpecialRate(String productName) {
		//Read Product related rule from the rules file
		String prodString = rulesProp.getProperty("Product");
		//Getting List of special products and rates
		String[] productsWithRates = prodString.split(",");
		//Returning the special rate if a product name matches 
		for(String product : productsWithRates) {
			String[] productsWithRate = product.split(":");
			if(productsWithRate[0].equalsIgnoreCase(productName)) {
				//Return the special rate that is matched in the rules
				return Double.valueOf(productsWithRate[1]);
			}		 
		}
		//No special rate available for the product then return 0.0
		return 0.0;
	}

	//Get the rate based on the Person's credit score
	public double getInterestRateBasedOnCreditScore(Person person) {
		return getCreditBasedRate(person.getCreditScore());
	}
	//Read the 'CreditScoreRules' from the file and finds out the interest rate
	//and return the matched interest
	public double getCreditBasedRate(int creditScore) {
		//Read the Credit score related rule from the rules file
		String score = rulesProp.getProperty("CreditScoreRules");
		//Getting List of rates based on the Credit Score
		String[] scoreValue = score.split(",");
		//Store highest available credit score from rules file
		double highest = 0.0;
		//Returning the interest rate based on the credit score 
		for(String s : scoreValue) {
			String[] credits = s.split(":");
			if(creditScore <= Integer.valueOf(credits[0])) {
				//Return the interest rate based on the rate
				return Double.valueOf(credits[1]);
			}		 
			highest = Double.valueOf(credits[1]);
		}
		//Returns the following value if rules doesn't match, this condition may not occur
		return highest;
	}

	//Set the product is available for the person based on the State the person lives
	public void setProuctQualification(Product product, Person person) {
		//Read the disqualified states from the rules
		String disqualifyStates = rulesProp.getProperty("DisqualifiedStates");
		//Check and set appropriate value based on the rule
		if(disqualifyStates.toLowerCase().contains(person.getState().toLowerCase())){
			product.setDisqualified(true);
		} else {
			product.setDisqualified(false);
		}

	}

	//Reading the rules from file and loading into the memory
	public  void loadRules(String rules) {
		FileInputStream file = null;
		try {
			rulesProp = new Properties();
			file = new FileInputStream (rules);
			rulesProp.load(file);
			file.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

}
