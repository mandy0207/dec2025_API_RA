package basics;

import org.testng.annotations.Test;

public class Client {

	@Test
	public void testMethod() {
		Vehicle carObj = new Vehicle("Skoda", "Car");
		Vehicle CycleObj = new Vehicle("Hero", "Cycle");
		carObj.getVehicleName();
		CycleObj .getVehicleName();
		
		
	}
	
	
}
