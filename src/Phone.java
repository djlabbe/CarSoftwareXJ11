/* Phone contains all the functionality of a regular telephone.
 * Phone has a dial to dial a number.
 * Phone retrieves a list of user contacts to use as SpeedDial entries.
 * Phone has both speaker and microphone volume and associated controls. */

import java.util.ArrayList;

public class Phone 
{
	private boolean isActiveCall;
	private int speakerVol, micVol, currentPhoneTime;
	private String numberBeingDialed;
	private ArrayList<Contact> contacts;
	protected Call currentCall;
	
	public Phone() {
		currentPhoneTime = 0;
		isActiveCall = false;
		numberBeingDialed = "";
		speakerVol = 3;
		micVol = 3;
		currentCall = null;
		contacts = new ArrayList<Contact>();
	}

	public void dialNumber(String input) {
		numberBeingDialed = numberBeingDialed.concat(input);
		if(numberBeingDialed.length() == 3) numberBeingDialed = numberBeingDialed.concat("-");
		if(numberBeingDialed.length() == 7) numberBeingDialed = numberBeingDialed.concat("-");
		if(numberBeingDialed.length() > 12) numberBeingDialed = input;
	}
		
	public String getNumberBeingDialed() {
		return numberBeingDialed;
	}
	
	public boolean checkActiveCall() {
		return isActiveCall;
	}
	
	public void activateCall() {
		isActiveCall = true;
		currentCall = new Call(numberBeingDialed);
	}
	
	public void deactivateCall() {
		if (currentCall != null) currentCall.setDuration(currentPhoneTime);
		currentPhoneTime = 0;
		isActiveCall = false;
	}
	
	public void micVolUp() {
		if (micVol < 10) micVol++;
	}
	
	public void micVolDown() {
		if (micVol > 0) micVol--;
	}
	
	public String getMicVol() {
		return Integer.toString(micVol);
	}
	
	public void speakVolUp() {
		if (speakerVol < 10) speakerVol++;
	}
	
	public void speakVolDown() {
		if (speakerVol > 0) speakerVol--;
	}
	
	public String getSpeakVol() {
		return Integer.toString(speakerVol);
	}
	
	public int getCurrentCallTime() {
		return currentPhoneTime;
	}
	
	public void incrementCurrentCallTime() {
		currentPhoneTime++;
	}
	
	public void resetNumberBeingDialed() {
		numberBeingDialed = "";
	}
	public void setContactActive(String contactNum) {
		numberBeingDialed = contactNum;
	}
	
	public void setContacts(Driver driver) {
		contacts = driver.getContacts();
	}
	
	public ArrayList<Contact> getContacts() {
		return contacts;
	}
}