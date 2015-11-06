import java.awt.FlowLayout;

import javax.swing.JFrame;

public class Car {
	
	protected static final double FUELCAPACITY = 13.0;
	protected static final double FUELRATE = 0.02;
	
	boolean isOn;
	private double odometer;
	private double sessionOdometer;
	private int currentSpeed;
	private double currentFuel;
	private double percentFuel;
	private Phone phone;
	private Radio radio;
	private Map map;
	private Analytics analytics;
	private Driver currentDriver;
	
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
		login();
	}
	
	public void login() {
		final JFrame frame = new JFrame("Login");
        frame.setSize(300, 100);
        frame.setLayout(new FlowLayout());
		DriverManager driverManager = new DriverManager(frame);
        driverManager.setVisible(true);
        currentDriver = driverManager.currentDriver;
        System.out.println(currentDriver + " logged in.");
	}
	
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
	
	public int decelerate() {
		currentSpeed = currentSpeed <= 10 ? 0 : currentSpeed - 10;
		System.out.println("Brake applied.");
		return currentSpeed;
	}
	
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
	
	public Driver getCurrentDriver() {
		return currentDriver;
	}

	
	
	public void  updateFuel() {
		percentFuel = currentFuel / FUELCAPACITY * 100;
		currentFuel -= FUELRATE;
		currentDriver.incrementFuelUsed();
	}
	
	public double getFuelPercent() {
		return percentFuel;
	}

	public double getCurrentSpeed() {
		return currentSpeed;
	}


	public void setCurrentSpeed(int currentSpeed) {
		this.currentSpeed = currentSpeed;
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


	public void setTripOdometer(double tripOdometer) {
		this.sessionOdometer = tripOdometer;
	}
	
	public void setCurrentDriver(Driver newDriver) {
		currentDriver = newDriver;
	}
	
	
}