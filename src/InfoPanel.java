import java.awt.Color;
import java.text.DecimalFormat;

import javax.swing.*;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class InfoPanel extends JPanel {
	
	private Border bevelledBorder = BorderFactory.createRaisedBevelBorder();
	
	protected JLabel sessionMileage, totalMileage, currentSpeed, currentFuel;
	private DecimalFormat dfOne = new DecimalFormat("#00");
	private DecimalFormat dfTwo = new DecimalFormat("000");

	private DecimalFormat dfShort = new DecimalFormat("###0.00");
	
	private Car car;
	
	public InfoPanel(Car car) {
		this.car = car;
		setBackground(Color.DARK_GRAY);
		setBorder(bevelledBorder);
		
		sessionMileage = new JLabel("Session: " + dfShort.format(car.currentSession.getDistanceDriven()) + " miles ");
		sessionMileage.setFont (sessionMileage.getFont().deriveFont (12.0f));
		sessionMileage.setForeground(Color.WHITE);
		
		totalMileage = new JLabel("| " + dfShort.format(car.getOdometer()) + " miles | ");
		totalMileage.setFont (totalMileage.getFont().deriveFont (30.0f));
		totalMileage.setForeground(Color.WHITE);
		
		currentSpeed = new JLabel(dfTwo.format(car.getCurrentSpeed()) + " MPH | ");
		currentSpeed.setFont (currentSpeed.getFont().deriveFont (30.0f));
		currentSpeed.setForeground(Color.WHITE);
		
		currentFuel = new JLabel(dfOne.format(car.getFuelPercent()) + "% Fuel ");
		currentFuel.setFont (currentFuel.getFont().deriveFont (12.0f));
		currentFuel.setForeground(Color.WHITE);
		
		add(sessionMileage);
		add(totalMileage);
		add(currentSpeed);
		add(currentFuel);
	}
	
	public void refresh() {
		sessionMileage.setText("Session: " + dfShort.format(car.currentSession.getDistanceDriven()) + " miles ");
		totalMileage.setText("| " + dfShort.format(car.getOdometer()) + " miles | ");
		currentSpeed.setText(dfTwo.format(car.coast()) + " MPH | ");
	}
	
}
