/* The DriverManager maintains a list of all known Drivers of the Car as well as the currentDriver.
 * The currentDriver is the set by at login.
 * DriverManager also contains the methods used by the LoginGuiManager for user authentication.*/

/* Basic Login functionality code adapted from "3 Steps to Create Login Dialog in Java Swing"
 * http://www.zentut.com/java-swing/simple-login-dialog/ */

import java.util.ArrayList;

public class DriverManager {

	private ArrayList<Driver> knownDrivers;
	protected Driver currentDriver;
	
	public DriverManager() {
		knownDrivers = new ArrayList<Driver>();
		currentDriver = null;
	}

	public Driver getCurrentDriver() {
		return currentDriver;
	}

	public void setCurrentDriver(Driver newDriver) {
		currentDriver = newDriver;
	}

	public ArrayList<Driver> getKnownDrivers() {
		return knownDrivers;
	}

	/* Authenticate user by searching knownDrivers for matching login credentials, return the matching
	 * driver on success, or null if fail.
	 */
	public Driver authenticate(String usernameInput, String passwordInput) {
		Driver loggedInDriver = null;
		for (int i = 0; i < knownDrivers.size(); i++) {
			if (knownDrivers.get(i).getUsername().equals(usernameInput.toUpperCase()) &&
					knownDrivers.get(i).getPassword().equals(passwordInput) ) {
				loggedInDriver = knownDrivers.get(i);
			} 
		}
		return loggedInDriver;
	}

	// Register a new driver by making a new driver and storing it in knownDrivers.
	public Driver register(String inputUsername, String inputPassword) {
		Driver registeredDriver = new Driver(inputUsername.toUpperCase(), inputPassword);
		knownDrivers.add(registeredDriver);
		System.out.println("Registered new driver - " + inputUsername.toUpperCase());
		return registeredDriver;
	}
	
	// Return a human-readable list of all known drivers.
	public String displayKnownDrivers() {
		String knownDriversDisplay = "";
		for (int i = 0; i < knownDrivers.size(); i++){
			Driver driver = knownDrivers.get(i);
			knownDriversDisplay += ( driver.toListString() + "\n");
		}
		return knownDriversDisplay;		
	}

}