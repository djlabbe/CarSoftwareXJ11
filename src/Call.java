/* A call object models a call made from the car's phone.
 * The Call object stores the date of the call, the number dialed, and the call duration.
 * Driver and Session both contain ArrayLists of Call objects representing call histories.
 */

import java.util.Date;

public class Call {

	private int duration;
	private String number;
	private Date timeStamp;

	public Call(String callNumber) {
		number = callNumber;
		duration = 0;
		timeStamp = new Date();
	}

	/* When the call is completed the phone updates the call's duration using this method.*/
	public void setDuration(int callDuration) {
		duration = callDuration;
	}

	public String toString() {
		String result = "";
		result += ( "Called: " + number + " on ");
		result += ( timeStamp.toString() + ". ");
		result += ( "Duration: " + duration + " seconds.");
		return result;
	}
}