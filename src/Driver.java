/* Represent a user of the Car. Has a user-name, password, and multiple data tracking fields. 
 * Stores favorites for each user that are loaded by the Radio and Phone when the driver logs into the Car.*/

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Driver {

	private String username;
	private String password;
	private int timeDriven; // seconds
	private double distanceDriven; // miles
	private int maxSpeed;
	private double fuelUsed;
	private int totalRadioTime;
	private int totalPhoneTime;
	protected RadioStation amFav1, amFav2, amFav3, fmFav1, fmFav2, fmFav3;
	private ArrayList<Session> sessionHistory;
	private ArrayList<Contact> contacts;
	private ArrayList<Call> callHistory;

	private DecimalFormat dfShort = new DecimalFormat("###0.00");

	public Driver(String newDriverUsername, String newDriverPassword) {
		username = newDriverUsername;
		password = newDriverPassword;
		timeDriven = 0;
		distanceDriven = 0;
		maxSpeed = 0;
		fuelUsed = 0;
		totalRadioTime = 0;
		totalPhoneTime = 0;
		sessionHistory = new ArrayList<Session>();
		callHistory = new ArrayList<Call>();
		amFav1 = null;
		amFav2 = null;
		amFav3 = null;
		fmFav1 = null;
		fmFav2 = null;
		fmFav3 = null;
		contacts = new ArrayList<Contact>();

	}

	public String getUsername()
	{
		return username;
	}

	public String getPassword() {
		return password;
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

	public double getAverageSpeed() {
		if (timeDriven != 0) {
			return distanceDriven / timeDriven * 60 * 60;
		} else {
			return 0;
		}
	}

	public int getMaxSpeed() {
		return maxSpeed;
	}

	public void updateMaxSpeed(int currentSpeed) {
		if (currentSpeed > maxSpeed) {
			maxSpeed = currentSpeed;
		}
	}

	public double getFuelUsed() {
		return fuelUsed;
	}

	public void incrementFuelUsed() {
		fuelUsed += Car.FUELRATE;
	}


	public int getTotalRadioTime() {
		return totalRadioTime;
	}

	public void incrementRadioTime() {
		totalRadioTime++;
	}

	public int getTotalPhoneTime() {
		return totalPhoneTime;
	}

	public void incrementTotalPhoneTime() {
		totalPhoneTime++;
	}

	public Contact getSpeedDial(int i) {
		return contacts.get(i);
	}

	public void setFav(boolean isAm, int favIndex, RadioStation station) {
		if (isAm) {
			switch (favIndex) {
			case 1:
				amFav1 = station;
				break;
			case 2:
				amFav2 = station;
				break;
			case 3:
				amFav3 = station;
				break;
			default:
				break;
			}
		} else {
			switch (favIndex) {
			case 1:
				fmFav1 = station;
				break;
			case 2:
				fmFav2 = station;
				break;
			case 3:
				fmFav3 = station;
				break;
			default:
				break;
			}
		}
	}

	public void addContact(Contact newContact) {
		contacts.add(newContact);
	}

	public ArrayList<Contact> getContacts()
	{
		return contacts;
	}

	public void saveSession(Session currentSession) {
		sessionHistory.add(currentSession);
	}

	public void saveCall(Call currentCall) {
		callHistory.add(currentCall);
	}

	public String displayCallHistory() {
		String callHistoryDisplay = "";
		if (callHistory.size() == 0) {
			callHistoryDisplay = "No completed calls to display.";
		}
		for (int i = 0; i < callHistory.size(); i++){
			Call call = callHistory.get(i);
			callHistoryDisplay += ( call.toString() + "\n");
		}
		return callHistoryDisplay;		
	}

	public String displaySessionHistory() {
		String sessionHistoryDisplay = "";
		if (sessionHistory.size() == 0) {
			sessionHistoryDisplay = "No completed sessions to display.";
		}
		for (int i = 0; i < sessionHistory.size(); i++){
			Session session = sessionHistory.get(i);
			sessionHistoryDisplay += ( session.toString() + "\n");
		}
		return sessionHistoryDisplay;		
	}
	
	public String toListString() {
		String result = "";
		result += (username + " | ");
		result += ("Distance driven: "  + dfShort.format(distanceDriven) + "mi | ");
		result += ("Time driven: "  + timeDriven + "sec");
		return result;
	}

	public String toString() {
		return username;
	}

	
}
