public class Contact {
	
	private String name;
	private int phoneNumber;

	public Contact(String nameToAdd, int numberToAdd) {
		name = nameToAdd;
		phoneNumber = numberToAdd;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public String getName() {
		return name;
	}
}
