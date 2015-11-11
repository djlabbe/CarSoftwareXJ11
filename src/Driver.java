import java.text.DecimalFormat;
import java.util.ArrayList;

/* Represent a user of the Car. Has a username, password, and multiple data tracking fields. 
 * Stores favorites for each user that are loaded by the Radio and Phone when the driver logs into the Car.*/

public class Driver {

	private String username;
	private String password;
	private int timeDriven; // seconds
	private double distanceDriven; // miles
	private int maxSpeed;
	private double fuelUsed;
	private int totalRadioTime;
	private int totalPhoneTime;
	private ArrayList<Session> sessionHistory;
	private DecimalFormat dfShort = new DecimalFormat("###0.00");

	protected RadioStation amFav1, amFav2, amFav3, fmFav1, fmFav2, fmFav3;
	protected Contact speedDial1, speedDial2;


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
		amFav1 = null;
		amFav2 = null;
		amFav3 = null;
		fmFav1 = null;
		fmFav2 = null;
		fmFav3 = null;
		speedDial1 = null;
		speedDial2 = null;

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

	public Contact getSpeedDial1() {
		return speedDial1;
	}

	public void setSpeedDial1(Contact contact) {
		speedDial1 = contact;
	}

	public Contact getSpeedDial2() {
		return speedDial2;
	}

	public void setSpeedDial2(Contact contact) {
		speedDial2 = contact;
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
				System.out.println("Error setting driver radio am favorite");
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
				System.out.println("Error setting driver radio fm favorite");
				break;
			}
		}
	}

	public void saveSession(Session currentSession) {
		sessionHistory.add(currentSession);
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

	public String toString() {
		return username;
	}

	public String toListString() {
		String result = "";
		result += (username + " | ");
		result += ("Distance driven: "  + dfShort.format(distanceDriven) + "mi | ");
		result += ("Time driven: "  + timeDriven + "sec");
		return result;

	}
}
