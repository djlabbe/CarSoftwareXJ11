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
}