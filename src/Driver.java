/* Represent a user of the Car. Has a username, password, and multiple data tracking fields. 
 * Stores favorites for each user that are loaded by the Radio and Phone when the driver logs into the Car.*/
public class Driver {

	private String username;
	private String password;
	private int totalDriveTime;
	private double totalDriveDistance;
	private int maxSpeed;
	private double fuelUsed;
	private double mpg;
	private int totalRadioTime;
	private int totalPhoneTime;
	protected RadioStation amFav1, amFav2, amFav3, fmFav1, fmFav2, fmFav3;
	protected Contact speedDial1, speedDial2;
	
	public Driver(String newDriverUsername, String newDriverPassword) {
		username = newDriverUsername;
		password = newDriverPassword;
		totalDriveTime = 0;
		totalDriveDistance = 0;
		maxSpeed = 0;
		fuelUsed = 0;
		mpg = 0;
		totalRadioTime = 0;
		totalPhoneTime = 0;
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
	
	public int getTotalDriveTime() {
		return totalDriveTime;
	}

	public void incrementTotalDriveTime() {
		totalDriveTime++;
	}
	
	public double getTotalDriveDistance() {
		return totalDriveDistance;
	}
	
	public void incrementTotalDriveDistance(double increment) {
		totalDriveDistance += increment;
	}
	
	public double computeAverageSpeed() {
		return totalDriveDistance / totalDriveTime;
	}
	
	public int getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public double getFuelUsed() {
		return fuelUsed;
	}

	public void incrementFuelUsed() {
		fuelUsed += Car.FUELRATE;
	}
	
	public double computeMpg() {
		mpg = totalDriveDistance / fuelUsed;
		return mpg;
	}
	
	public int getTotalRadioTime() {
		return totalRadioTime;
	}
	
	public void incrementTotalRadioTime() {
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
	
	public String toString() {
		return username;
	}

}
