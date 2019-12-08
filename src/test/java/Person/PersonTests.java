package Person;

import org.testng.annotations.Test;
import org.testng.Assert;


import visio.Person;


public class PersonTests {

	@Test
	public void checkState() {
		Person person = new Person(720,"Florida");
		Assert.assertEquals("Florida", person.getState());
		
	}
	@Test
	public void checkScore() {
		Person person = new Person(720,"Florida");
		Assert.assertEquals(720, person.getCreditScore());
		
	}
	@Test
	public void checkLongState() {
		Person person = new Person(720,"Abcdefghijklmnopqrstuvwxyz Abcdefghijklmnopqrstuvwxyz");
		Assert.assertEquals("Abcdefghijklmnopqrstuvwxyz Abcdefghijklmnopqrstuvwxyz", person.getState());
		
	}
	@Test
	public void checkSpecialCharacterInput() {
		Person person = new Person(1,"Abcdefghijklmnopqrstuvwxyz !@#$%^&*()_+");
		Assert.assertEquals("Abcdefghijklmnopqrstuvwxyz !@#$%^&*()_+", person.getState());
		
	}
	@Test
	public void setState() {
		Person person = new Person(720,"Florida");
		person.setState("California");
		Assert.assertEquals("California", person.getState());
	}
	@Test
	public void setCreditScore() {
		Person person = new Person(720,"Florida");
		person.setCreditScore(620);
		Assert.assertEquals(620, person.getCreditScore());
	}
}
