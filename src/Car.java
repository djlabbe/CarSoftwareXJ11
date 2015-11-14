/* Car models functions related to the engine and movement of the vehicle.
 * A Car has DriverManager, a Session, a Radio, a Phone, and a Map.
 * Each component is responsible for managing its' own data and operations.
 */

import java.awt.FlowLayout;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Car {

	protected static final double FUELCAPACITY = 13.0;
	protected static final double FUELRATE = 0.02;
	protected static final int MAXSPEED = 220;


	private boolean isOn;
	private int currentSpeed;
	private double odometer, currentFuel;
	protected DriverManager driverManager;
	protected Session currentSession;
	protected Radio radio;
	protected Phone phone;
	protected Map map;
	protected Timer timer;
	private double deltaDistance;

	public Car() {
		isOn = false;
		currentSpeed = 0;
		odometer = 0.0;
		currentFuel = FUELCAPACITY;
		driverManager = new DriverManager();
		radio = new Radio();
		phone = new Phone();
		map = new Map();
		login();
	}

	// Call the login pop-up window and activate the newly logged in or registered driver. 
	public void login() {
		final JFrame frame = new JFrame("Login");
		frame.setSize(300, 100);
		frame.setLayout(new FlowLayout());
		LoginGuiManager loginGuiManager = new LoginGuiManager(frame, driverManager);
		loginGuiManager.setVisible(true);
		System.out.println(driverManager.currentDriver + " logged in.");
		if (driverManager.currentDriver != null) {
			currentSession = new Session(driverManager.currentDriver);
			setUserFavorites();
			refreshContacts();
		}
	}

	// Retrieve saved driver settings for radio (and phone?)
	public void setUserFavorites() {
		radio.setUserFavorites(driverManager.currentDriver);
	}

	public void refreshContacts() {
		phone.setContacts(driverManager.currentDriver);
	}

	public boolean getIsOn() {
		return isOn;
	}

	/* Turn the car on and off.
	 * Car can only be turned off is it is currently running and is not moving. 
	 */
	public void togglePower() {
		if (isOn && currentSpeed == 0) {
			isOn = false;
		} else if (!isOn && currentSpeed == 0){
			isOn = true;
		}
	}

	public double getOdometer() {
		return odometer;
	}

	public void incrementOdometer(double increment) {
		odometer += increment;
	}

	public int getCurrentSpeed() {
		return currentSpeed;
	}

	/* Accelerates the car. Called when driver presses gas.
	 * Only works if the car is on.
	 * Car is capped at MAXSPEED mph.
	 */
	public int accelerate() {
		if (isOn) {
			currentSpeed += 5;
			if (currentSpeed > MAXSPEED) {
				currentSpeed = MAXSPEED;
			}
			updateFuel();
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
		currentSpeed = currentSpeed <= 1 ? 0 : currentSpeed - 1;
		return currentSpeed;
	}

	public void  updateFuel() {
		currentFuel -= FUELRATE;
		driverManager.currentDriver.incrementFuelUsed();
		currentSession.incrementFuelUsed();
	}

	public double getFuelPercent() {
		return currentFuel / FUELCAPACITY * 100;
	}

	/* Sets the car's fuel to 100%. Called when driver presses refuel button
	 * Only works when the car is stopped and turned off.
	 */
	public boolean refuel() {
		boolean success = false;
		if (currentSpeed == 0 && !isOn) {
			currentFuel = FUELCAPACITY;
			success = true;
			System.out.println("Refueled successfully.");
		}
		else {
			success = false;
			System.out.println("Unable to refuel, car must be off.");
		}
		return success;
	}

	/* The main loop and timing mechanism for driving,
	 * A TimerTask is scheduled to run every 1 second which then updates the 
	 * speed and position of the car while logging associated data.
	 */
	public void runLoop(GuiManager guiManager) {
		int begin = 0; // start immediately 
		int timeinterval = 1000; // tick every 1 second
		timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				guiManager.infoPanel.refresh();
				guiManager.appPanel.analyticsPanel.refresh();
				guiManager.appPanel.phonePanel.refresh();

				deltaDistance = ((double)getCurrentSpeed() / 60 / 60);
				incrementOdometer(deltaDistance);
				map.getCurrentRoute().incrementDistanceIntoRoute(deltaDistance * 100);
				driverManager.currentDriver.incrementTimeDriven();
				currentSession.incrementTimeDriven();
				driverManager.currentDriver.incrementDistanceDriven(deltaDistance);
				currentSession.incrementDistanceDriven(deltaDistance);
				driverManager.currentDriver.updateMaxSpeed(getCurrentSpeed());
				currentSession.updateMaxSpeed(getCurrentSpeed());
				if (radio.getIsOn()) {
					driverManager.currentDriver.incrementRadioTime();
					currentSession.incrementRadioTime();
				}

				// Asynchronously update the map position slider
				SwingUtilities.invokeLater(guiManager.appPanel.mapPanel.updateSliderPosition);

			}
		},begin, timeinterval);
	}




}