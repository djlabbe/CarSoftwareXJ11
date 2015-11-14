/* A contact object is used by the phone in order to store a list of saved speed-dial entries
 * Each contact has a phone-number and the name of the contact.
 * */

public class Contact {
	private String name;
	private String phoneNumber;
	
	public Contact(String nameToAdd, String numberToAdd) {
		name = nameToAdd;
		phoneNumber = numberToAdd;
	}
	public Contact() {
		name = "";
		phoneNumber = "";
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public String getName() {
		return name;
	}
	
	public String toString() {
		return name;
	}
}
