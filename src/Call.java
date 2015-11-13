import java.util.Date;

public class Call {

	private int duration;
	private String number;
	private Date timeStamp;

	public Call(String callNumber)
	{
		number = callNumber;
		duration = 0;
		timeStamp = new Date();
	}

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