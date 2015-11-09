
public class Phone 
{
	private boolean isActiveCall;
	private String numberBeingDialed;
	private int speakerVol;
	private int micVol;
	private Contact speed1, speed2;
	
	public Phone()
	{
		isActiveCall = false;
		numberBeingDialed = "";
		speakerVol = 3;
		micVol = 3;
		
	}
	
	public void dialNumber(String input)
	{
		this.numberBeingDialed = this.numberBeingDialed.concat(input);
		
		// reset if the number is larger than 10
		if(this.numberBeingDialed.length() > 10)
			this.numberBeingDialed = input;
		
		System.out.println("Number being dialed: " + this.numberBeingDialed);
	}
	
	public void micVolUp()
	{
		
	}
	
	public void micVolDown()
	{
		
	}

}
