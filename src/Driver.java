// driver class is personal for each and every driver
// therefore the variables inside the driver will be initialized using the GUI
public class Driver {
	
	// local variables
	final double FUELRATE = 0.1;
	
	private String username;
	private String password;
	private int totalDriveTime;
	private double totalDriveDistance;
	private double averageSpeed;
	private int maxSpeed;
	private double fuelUsed;
	private double mpg;
	private int totalRadioTime;
	private int totalPhoneTime;
	private double amFav1, amFav2, fmFav1, fmFav2;
	private Contact speedDial1, speedDial2;
	
	public Driver(String newDriverUsername, String newDriverPassword) {
		username = newDriverUsername;
		password = newDriverPassword;
		totalDriveTime = 0;
		totalDriveDistance = 0;
		averageSpeed = 0;
		maxSpeed = 0;
		fuelUsed = 0;
		mpg = 0;
		totalRadioTime = 0;
		totalPhoneTime = 0;
		
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
		averageSpeed = totalDriveDistance / totalDriveTime;
		return averageSpeed;
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
		fuelUsed += FUELRATE;
	}
	
	public double computeMpg() {
		mpg = totalDriveDistance / fuelUsed;
		return mpg;
	}
	
	public void incrementTotalRadioTime() {
		totalRadioTime++;
	}
	
	public void incrementTotalPhoneTime() {
		totalPhoneTime++;
	}
	
	public double getAmFav1() {
		return amFav1;
	}
	
	public void setAmFav1(double newStation) {
		amFav1 = newStation;
	}

	public double getAmFav2() {
		return amFav2;
	}

	public void setAmFav2(double newStation) {
		amFav2 = newStation;
	}
	
	public double getFmFav1() {
		return fmFav1;
	}
	
	public void setFmFav1(double newStation) {
		fmFav1 = newStation;
	}

	public double getFmFav2() {
		return fmFav2;
	}

	public void setFmFav2(double newStation) {
		fmFav2 = newStation;
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
	
	public String toString() {
		return username;
	}

}
