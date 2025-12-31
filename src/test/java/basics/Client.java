package basics;

import org.testng.annotations.Test;

public class Client {

	@Test
	public void testMethod() {
		printName("martin");
	}
	
	public void printName(String name) {
		System.out.println("my name is "+ name);
	}
}
