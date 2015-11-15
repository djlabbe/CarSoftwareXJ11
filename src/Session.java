/* Session represents the currentDrivers current activity in the Car since the last time a user logged in.
 * A new session is created every time a driver logs in. 
 * Each driver contains a history of sessions belonging to that driver.*/


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

public class Session {

	private Driver driver;
	private Date timeStamp;
	private double distanceDriven;
	private int timeDriven;
	private int maxSpeed;
	private double fuelUsed;
	private int radioTime;
	private int phoneTime;
	private ArrayList<Call> callHistory;

	private DecimalFormat dfShort = new DecimalFormat("###0.00");

	public Session(Driver driver) {	
		this.driver = driver;
		timeStamp = new Date();
		distanceDriven = 0;
		maxSpeed = 0;
		maxSpeed = 0;
		fuelUsed = 0;
		phoneTime = 0;
		radioTime = 0;
		callHistory = new ArrayList<Call>();
	}

	public Driver getDriver() {
		return driver;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public int getTimeDriven() {
		return timeDriven;
	}

	public void incrementTimeDriven() {
		timeDriven++;
	}

	public double getDistanceDriven() {
		return distanceDriven;
	}

	public void incrementDistanceDriven(double increment) {
		distanceDriven += increment;
	}

	public int getMaxSpeed() {
		return maxSpeed;
	}

	public void updateMaxSpeed(int currentSpeed) {
		if (currentSpeed > maxSpeed) maxSpeed = currentSpeed;
		
	}

	public double getAverageSpeed() {
		if (timeDriven != 0) return distanceDriven / timeDriven * 60 * 60;
		else return 0;
	}	

	public double getFuelUsed() {
		return fuelUsed;
	}

	public void incrementFuelUsed() {
		fuelUsed += Car.FUELRATE;
	}

	public int getRadioTime() {
		return radioTime;
	}

	public void incrementRadioTime() {
		radioTime++;
	}

	public int getPhoneTime() {
		return phoneTime;
	}

	public void incrementPhoneTime() {
		phoneTime++;
	}

	public void saveCall(Call currentCall) {
		callHistory.add(currentCall);
	}

	public String displayCallHistory() {
		String callHistoryDisplay = "";
		if (callHistory.size() == 0) callHistoryDisplay = "No completed calls to display.";
		for (int i = 0; i < callHistory.size(); i++){
			Call call = callHistory.get(i);
			callHistoryDisplay += ( call.toString() + "\n");
		}
		return callHistoryDisplay;		
	}

	public String toString() {
		String result = "";
		result += ( timeStamp.toString() + " | ");
		result += ( "Time: " + timeDriven + "sec | ");
		result += ( "Distance: " + dfShort.format(distanceDriven) + "mi | ");
		result += ( "MaxSpeed: " + maxSpeed + "mph | ");
		result += ( "AvgSpeed: " + dfShort.format(getAverageSpeed()) + "mph | ");
		result += ( "FuelUsed: " + dfShort.format(getFuelUsed()) + "gal | ");
		result += ( "RadioTime: " + radioTime + "sec | ");
		result += ( "PhoneTime: " + phoneTime + "sec");
		return result;
	}


}
