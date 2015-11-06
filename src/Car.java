public class Car {
	
	final double FUELCAPACITY = 13.0;
	
	boolean isOn;
	protected double odometer = 0;
	protected double tripOdometer = 0;
	protected int currentSpeed = 0;
	protected double currentFuel = FUELCAPACITY;
	protected double percentFuel = currentFuel / FUELCAPACITY * 100;
	protected Phone phone;
	protected Radio radio;
	protected Map map;
	protected Analytics analytics;
	protected Driver currentDriver;
	
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
			currentFuel -= 0.1;
			updateFuelPercent();
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
			updateFuelPercent();
			System.out.println("Refueled successfully.");
		}
		else {
			System.out.println("Unable to refuel, car must be off.");
		}
	}
	
	public void  updateFuelPercent() {
		percentFuel = currentFuel / FUELCAPACITY;
	}
	
	public double getFuelPercent() {
		return percentFuel;
	}

}