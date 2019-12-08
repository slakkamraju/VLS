package Product;



import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

import visio.Product;

public class ProductTests {

	Product product;
	
	@BeforeMethod
	public void setup() {
		product = new Product("7-1 ARM", 5.0);
	}
	@Test
	public void checkProduct() {
		product.setName("Amazon product");
		Assert.assertEquals("Amazon product", product.getName());
		
	}
	@Test
	public void checkLongProductName() {
		product.setName("Abcdefghijklmnopqrstuvwxyz Abcdefghijklmnopqrstuvwxyz");
		Assert.assertEquals("Abcdefghijklmnopqrstuvwxyz Abcdefghijklmnopqrstuvwxyz", product.getName());
		
	}
	@Test
	public void checkSpecialCharacterProductName() {
		product.setName("Abcdefghijklmnopqrstuvwxyz !@#$%^&*()_+");
		Assert.assertEquals("Abcdefghijklmnopqrstuvwxyz !@#$%^&*()_+", product.getName());
		
	}
	@Test
	public void checkProductInterest() {
		product.setInterestRate(2.0);
		Assert.assertEquals(2.0, product.getInterestRate());
		
	}
	@Test
	public void checkProductSpecialInterest() {
		product.setInterestRate(5.78932456);
		Assert.assertEquals(5.78932456, product.getInterestRate());
	}
	@Test
	public void setProductQualificationToTrue() {
		product.setDisqualified(true);
		Assert.assertEquals(true, product.getDisqualified());
	}
	@Test
	public void setProductQualificationToFalse() {
		product.setDisqualified(false);
		Assert.assertEquals(false, product.getDisqualified());
	}
}
