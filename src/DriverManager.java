public class DriverManager {
	
	// size of the array
	final int DRIVERCAPACITY = 15;
	
	// array of known drivers with length of size
	Driver[] knownDrivers = new Driver[DRIVERCAPACITY];
	int currentPin;
	
	public boolean login(int pin) {
		
		// #TODO Check pin against knownDrivers pins, return true and login if match.
		
		return false;
	}
	
	public boolean register( ) {
		// #TODO Create new user and add to known drivers, return true if successful, or false if not.
		
		return true;
	}
	

}
