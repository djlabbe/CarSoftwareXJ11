// driver class is personal for each and every driver
// therefore the variables inside the driver will be initialized using the GUI
public class Driver {
	
	// local variables
	private String password;
	private int totalDriveTime;
	private int totalDriveDistance;
	private int averageSpeed;
	private int maxSpeed;
	private int fuelUsed;
	private int mpg;
	private int totalRadioTime;
	private int totalPhoneTime;
	private String name;
	private double amFav1, amFav2, fmFav1, fmFav2;
	private Contact speedDial1, speedDial2;
	
	public Driver(String newDriverPassword) {
		password = newDriverPassword;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setAmFav1(double freq)
	{
		amFav1 = freq;
	}
	
	public void setAmFav2(double freq)
	{
		amFav2 = freq;
	}
	
	public void setFmFav1(double freq)
	{
		fmFav1 = freq;
	}
	public void setFmFav2(double freq)
	{
		fmFav2 = freq;
	}
	public void setSpeedDial1(Contact contact) {
		speedDial1 = contact;
	}
	public void setSpeedDial2(Contact contact) {
		speedDial2 = contact;
	}
	
	public String getPassword() {
		return password;
	}

}
