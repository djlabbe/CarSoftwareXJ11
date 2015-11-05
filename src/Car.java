public class Car {
	
	final double FUELCAPACITY = 13.0;
	
	boolean isOn;
	protected int odometer = 0;
	protected int tripOdometer = 0;
	protected int currentSpeed = 0;
	protected double currentFuel = FUELCAPACITY;
	protected double percentFuel = currentFuel / FUELCAPACITY * 100;
	protected Phone phone;
	protected Radio radio;
	protected Map map;
	protected Analytics analytics;
	
	public boolean togglePower()
	{
		// If isOn is true - set it to false, if it is false - set to true
		isOn = isOn ? false : true;
		return false;
	}
	
	public int accelerate() {
		// If current speed is less than max, add 5
		currentSpeed = currentSpeed >= 120 ? 120 : currentSpeed + 5;
		currentFuel -= 0.1;
		updateFuelPercent();
		return currentSpeed;
	}
	
	public int decelerate() {
		// If current speed is more than 0, add subtract 10
		currentSpeed = currentSpeed <= 10 ? 0 : currentSpeed - 10;
		return currentSpeed;
	}
	
	public int coast() {
		// If current speed is more than 0, add subtract 1
		currentSpeed = currentSpeed <= 1 ? 0 : currentSpeed - 1;
		return currentSpeed;
	}
	
	public void refuel() {
		currentFuel = FUELCAPACITY;
		updateFuelPercent();
	}
	
	public void  updateFuelPercent() {
		percentFuel = currentFuel / FUELCAPACITY;
	}
	
	public double getFuelPercent() {
		return percentFuel;
	}

}