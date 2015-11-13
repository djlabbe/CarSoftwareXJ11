import java.util.ArrayList;

public class Phone 
{
	private boolean isActiveCall;
	private String numberBeingDialed;
	private int speakerVol, micVol, currentPhoneTime;
	protected Call currentCall;
	private ArrayList<Contact> contacts;

	public Phone()
	{
		currentPhoneTime = 0;
		isActiveCall = false;
		numberBeingDialed = "";
		speakerVol = 3;
		micVol = 3;
		currentCall = null;
		contacts = new ArrayList<Contact>();
	}

	public void dialNumber(String input)
	{
		this.numberBeingDialed = this.numberBeingDialed.concat(input);
		if(this.numberBeingDialed.length() == 3)
			this.numberBeingDialed = this.numberBeingDialed.concat("-");
		if(this.numberBeingDialed.length() == 7)
			this.numberBeingDialed = this.numberBeingDialed.concat("-");
		// reset if the number is larger than 10
		if(this.numberBeingDialed.length() > 12)
			this.numberBeingDialed = input;

		System.out.println("Number being dialed: " + this.numberBeingDialed);
	}

	public String getNumberDialed()
	{
		return this.numberBeingDialed;
	}

	public boolean checkActiveCall()
	{
		return this.isActiveCall;
	}

	public void activateCall()
	{
		this.isActiveCall = true;
		currentCall = new Call(numberBeingDialed);
		System.out.println("Call being made.");
	}

	public void deactivateCall()
	{
		if (currentCall != null) {
			currentCall.setDuration(currentPhoneTime);
		}
		this.currentPhoneTime = 0;
		this.isActiveCall = false;
		System.out.println("Call ended.");
	}

	public void micVolUp()
	{
		if(this.micVol < 10)
			this.micVol++;
		System.out.println("Microphone volume up.");
	}

	public void micVolDown()
	{
		if(this.micVol > 0)
			this.micVol--;
		System.out.println("Microphone volume down.");
	}

	public String getMicVol()
	{
		return Integer.toString(this.micVol);
	}

	public void speakVolUp()
	{
		if(this.speakerVol < 10)
			this.speakerVol++;
		System.out.println("Speaker volume up.");
	}

	public void speakVolDown()
	{
		if(this.speakerVol > 0)
			this.speakerVol--;
		System.out.println("Speaker volume down");
	}

	public String getSpeakVol()
	{
		return Integer.toString(this.speakerVol);
	}

	public int getCurrentCallTime()
	{
		return this.currentPhoneTime;
	}

	public void incrementCurrentCallTime()
	{
		this.currentPhoneTime++;
	}

	public void resetNumberBeingDialed()
	{
		this.numberBeingDialed = "";
	}

	public void setContacts(Driver driver)
	{
		this.contacts = driver.contacts;
	}

	public ArrayList<Contact> getContacts() {
		return contacts;
	}

}