/* Phone contains all the functionality of a regular telephone.
 * Phone has a dial to dial a number.
 * Phone retrieves a list of user contacts to use as SpeedDial entries.
 * Phone has both speaker and microphone volume and associated controls. */

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
		numberBeingDialed = numberBeingDialed.concat(input);
		if(numberBeingDialed.length() == 3)
			numberBeingDialed = numberBeingDialed.concat("-");
		if(numberBeingDialed.length() == 7)
			numberBeingDialed = numberBeingDialed.concat("-");
		// reset if the number is larger than 10
		if(numberBeingDialed.length() > 12)
			numberBeingDialed = input;

		System.out.println("Number being dialed: " + numberBeingDialed);
	}
		
	public String getNumberDialed()
	{
		return numberBeingDialed;
	}
	
	public boolean checkActiveCall()
	{
		return isActiveCall;
	}
	
	public void activateCall()
	{
		isActiveCall = true;
		currentCall = new Call(numberBeingDialed);
		System.out.println("Call being made.");
	}
	
	public void deactivateCall()
	{
		if (currentCall != null) {
			currentCall.setDuration(currentPhoneTime);
		}
		currentPhoneTime = 0;
		isActiveCall = false;
		System.out.println("Call ended.");
	}
	
	public void micVolUp()
	{
		if(micVol < 10)
			micVol++;
		System.out.println("Microphone volume up.");
	}
	
	public void micVolDown()
	{
		if(micVol > 0)
			micVol--;
		System.out.println("Microphone volume down.");
	}
	
	public String getMicVol()
	{
		return Integer.toString(micVol);
	}
	
	public void speakVolUp()
	{
		if(speakerVol < 10)
			speakerVol++;
		System.out.println("Speaker volume up.");
	}
	
	public void speakVolDown()
	{
		if(speakerVol > 0)
			speakerVol--;
		System.out.println("Speaker volume down");
	}
	
	public String getSpeakVol()
	{
		return Integer.toString(speakerVol);
	}
	
	public int getCurrentCallTime()
	{
		return currentPhoneTime;
	}
	
	public void incrementCurrentCallTime()
	{
		currentPhoneTime++;
	}
	
	public void resetNumberBeingDialed()
	{
		numberBeingDialed = "";
	}
	public void setContactActive(String contactNum) {
		numberBeingDialed = contactNum;
	}
	
	
	public void setContacts(Driver driver)
	{
		contacts = driver.getContacts();
	}
	
	public ArrayList<Contact> getContacts() {
		return contacts;
	}
	
	
	

}