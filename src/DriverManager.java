import java.util.*;

public class DriverManager {
	
	int userEnteredPin;
	Driver currentDriver;
	ArrayList<Driver> knownDrivers;
	
	public DriverManager() {
		currentDriver = null;
		knownDrivers = new ArrayList<Driver>();
		register("aaaa");
		register("1111");
		register("bbbb");
		register("abcd");
	}
	
	public Driver authenticate(String usernameInput, String passwordInput) {
		Driver loggedInDriver = null;
			for (int i = 0; i < knownDrivers.size(); i++) {
				if (knownDrivers.get(i).getPassword().equals(passwordInput)) {
					loggedInDriver = knownDrivers.get(i);
				} 
			}
		return loggedInDriver;
	}
	
	public Driver register(String inputPassword) {
		Driver registeredDriver = new Driver(inputPassword);
		knownDrivers.add(registeredDriver);
		return registeredDriver;
	}
	

}
