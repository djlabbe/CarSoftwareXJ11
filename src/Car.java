/* Car models functions related to the engine and movement of the vehicle.
 * A Car has Radio, Phone, Map, and Analytics
 * A Car has a currentDriver which is set by the DriverManger through the driver login functionality.
 */

import java.awt.FlowLayout;

import javax.swing.JFrame;

public class Car {
	
	protected static final double FUELCAPACITY = 13.0;
	protected static final double FUELRATE = 0.02;
	
	private boolean isOn;
	private double odometer;
	private double sessionOdometer;
	private int currentSpeed;
	private double currentFuel;
	private double percentFuel;
	protected Radio radio;
	protected Phone phone;
	protected Map map;
	protected Analytics analytics;
	protected Driver currentDriver;
	
	public Car() {
		isOn = false;
		odometer = 0;
		sessionOdometer = 0;
		currentSpeed = 0;
		currentFuel = FUELCAPACITY;
		percentFuel = currentFuel / FUELCAPACITY * 100;
		phone = new Phone();
		radio = new Radio();
		map = new Map();
		analytics = new Analytics();
		radio = new Radio();
	}
	
	// Call the login pop-up window and activate the new driver. 
	public void login() {
		final JFrame frame = new JFrame("Login");
        frame.setSize(300, 100);
        frame.setLayout(new FlowLayout());
		DriverManager driverManager = new DriverManager(frame);
        driverManager.setVisible(true);
        currentDriver = driverManager.currentDriver;
        System.out.println(currentDriver + " logged in.");
        if (currentDriver != null) {
        	setUserFavorites();
        }
	}
	
	// Retrieve saved driver settings for radio and phone.
	public void setUserFavorites() {
		radio.setUserFavorites(currentDriver);
	}
	
	/* Turn the car on and off.
	 * Car can only be turned off is it is currently running and is not moving. 
	 */
	public boolean togglePower() {
		if (isOn && currentSpeed == 0) {
			isOn = false;
			System.out.println("Car turned off.");
		} else if (isOn && currentSpeed > 0){
			System.out.println("Can't turn off while driving.");
		} else {
			isOn = true;
			System.out.println("Car turned on.");
		}
		return isOn;
	}
	
	/* Accelerates the car. Called when driver presses gas.
	 * Only works if the car is on.
	 * Car can go up to a max speed of 120 MPH.
	 */
	public int accelerate() {
		if (isOn) {
			currentSpeed = currentSpeed >= 120 ? 120 : currentSpeed + 5;
			updateFuel();
			
			if (currentSpeed > currentDriver.getMaxSpeed()) {
				currentDriver.setMaxSpeed(currentSpeed);
			}
			
			System.out.println("Speed increased by 5 MPH.");
		} else {
			System.out.println("Can't accelerate -- car is off.");
		}
		return currentSpeed;
	}
	
	/* Decelerates the car. Called when driver applies the brake.
	 * Car can not go below 0 MPH.
	 */
	public int decelerate() {
		currentSpeed = currentSpeed <= 10 ? 0 : currentSpeed - 10;
		System.out.println("Brake applied.");
		return currentSpeed;
	}
	
	/* Car is coasting - is called once per second while the car is on.
	 * Car loses 1 MPH per second, can not go below 0 MPH.
	 */
	public int coast() {
		if (currentSpeed <= 1) {
			currentSpeed = 0;
			System.out.println("Idle...");
		} else {
			currentSpeed--;
			System.out.println("Coasting...");
		}
		return currentSpeed;
	}
	
	/* Sets the car's fuel to 100%. Called when driver presses refuel button
	 * Only works when the car is stopped and turned off.
	 */
	public void refuel() {
		if (currentSpeed == 0 && !isOn) {
			currentFuel = FUELCAPACITY;
			percentFuel = currentFuel / FUELCAPACITY;
			System.out.println("Refueled successfully.");
		}
		else {
			System.out.println("Unable to refuel, car must be off.");
		}
	}
	
	/* Calculates the percent fuel remaining, used for the GUI display 
	 * Also updates the associated driver statistic.
	 */
	
	public boolean getIsOn() {
		return isOn;
	}
	
	public void  updateFuel() {
		percentFuel = currentFuel / FUELCAPACITY * 100;
		currentFuel -= FUELRATE;
		currentDriver.incrementFuelUsed();
	}
	
	public double getFuelPercent() {
		return percentFuel;
	}
	
	public Driver getCurrentDriver() {
		return currentDriver;
	}
	
	public void setCurrentDriver(Driver newDriver) {
		currentDriver = newDriver;
	}

	public double getCurrentSpeed() {
		return currentSpeed;
	}

	public double getOdometer() {
		return odometer;
	}

	public void incrementOdometer(double increment) {
		odometer += increment;
	}

	public double getSessionOdometer() {
		return sessionOdometer;
	}
	public void incrementSessionOdometer(double increment) {
		sessionOdometer += increment;
	}
	
}