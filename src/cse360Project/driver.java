package cse360Project;

// driver class is personal for each and every driver
// therefore the variables inside the driver will be initialized using the GUI
public class driver {
	
	// local variables
	int pin, totalDriveTime, totalDriveDistance, averageSpeed, maxSpeed, fuelUsed, mpg, totalRadioTime, totalPhoneTime;
	String name;
	double amFav1, amFav2, fmFav1, fmFav2;
	// contact speedDial1, speedDial2;
	public void setName(String input)
	{
		name = input;
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

}
