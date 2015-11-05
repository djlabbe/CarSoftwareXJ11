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
	
	
	public boolean togglePower()
	{
		isOn = (isOn && currentSpeed == 0) ? false : true;
		if (isOn) {
			System.out.println("Car on");
		} else {
			System.out.println("Car off");
		}
		return isOn;
	}
	
	
	public int accelerate() {
		// If current speed is less than max, add 5
		if (isOn) {
			currentSpeed = currentSpeed >= 120 ? 120 : currentSpeed + 5;
			currentFuel -= 0.1;
			updateFuelPercent();
		}
		return currentSpeed;
	}
	
	public int decelerate() {
		// If current speed is more than 0, add subtract 10
		currentSpeed = currentSpeed <= 10 ? 0 : currentSpeed - 10;
		return currentSpeed;
	}
	
	public int coast() {
		// If current speed is more than 0, subtract 1
		currentSpeed = currentSpeed <= 1 ? 0 : (currentSpeed - 1);
		return currentSpeed;
	}
	
	public void refuel() {
		if (currentSpeed == 0 && !isOn) {
			currentFuel = FUELCAPACITY;
			updateFuelPercent();
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