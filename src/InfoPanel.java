import java.awt.Color;
import java.text.DecimalFormat;

import javax.swing.*;

@SuppressWarnings("serial")
public class InfoPanel extends DecorativePanel {

	private JLabel sessionMileage, totalMileage, currentSpeed, currentFuel;
	private DecimalFormat dfOne = new DecimalFormat("#00");
	private DecimalFormat dfTwo = new DecimalFormat("000");
	private DecimalFormat dfShort = new DecimalFormat("###0.00");

	private Car car;

	public InfoPanel(GuiManager guiManager) {
		this.car = guiManager.getCar();

		sessionMileage = makeInfoLabel(12.0f);
		totalMileage = makeInfoLabel(30.0f);
		currentSpeed = makeInfoLabel(30.0f);
		currentFuel = makeInfoLabel(12.0f);

		refresh();

		add(sessionMileage);
		add(totalMileage);
		add(currentSpeed);
		add(currentFuel);
	}

	public JLabel makeInfoLabel(float size) {
		JLabel newLabel = new JLabel();
		newLabel.setForeground(Color.WHITE);
		newLabel.setFont (newLabel.getFont().deriveFont (size));
		return newLabel;
	}

	public void refresh() {
		sessionMileage.setText("Session: " + dfShort.format(car.currentSession.getDistanceDriven()) + " miles ");
		totalMileage.setText("| " + dfShort.format(car.getOdometer()) + " miles | ");
		currentSpeed.setText(dfTwo.format(car.getCurrentSpeed()) + " MPH | ");
		currentFuel.setText(dfOne.format(car.getFuelPercent()) + "% Fuel ");
	}

}
