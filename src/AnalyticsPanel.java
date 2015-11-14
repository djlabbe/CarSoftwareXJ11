import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.*;

@SuppressWarnings("serial")
public class AnalyticsPanel extends JPanel {

	private JLabel driverMiles, driverTime, driverAvgSpeed, driverMaxSpeed, driverFuelUsed, 
	driverRadioTime, driverPhoneTime, sessionMiles, sessionTime, sessionAvgSpeed, sessionMaxSpeed, 
	sessionFuelUsed, sessionRadioTime, sessionPhoneTime, driverTitle;
	
	private JButton sessionCallLogBtn, driverCallLogBtn, sessionHistoryBtn, viewDriversBtn;
	
	private DecimalFormat dfShort = new DecimalFormat("###0.00");
	private Car car;
	
	public AnalyticsPanel(Car car, GuiManager guiManager) {
		this.car = car;
		setLayout(new BorderLayout());
		setBackground(Color.LIGHT_GRAY);
		
		JPanel leftAnalytics = new JPanel();
		leftAnalytics.setLayout(new BoxLayout(leftAnalytics, BoxLayout.Y_AXIS));
		leftAnalytics.setBackground(Color.LIGHT_GRAY);

		JPanel centerAnalytics = new JPanel();
		centerAnalytics.setLayout(new BoxLayout(centerAnalytics, 1));
		centerAnalytics.setBackground(Color.LIGHT_GRAY);

		JPanel rightAnalytics= new JPanel();
		rightAnalytics.setLayout(new BoxLayout(rightAnalytics, 1));
		rightAnalytics.setBackground(Color.LIGHT_GRAY);

		JPanel bottomAnalytics = new JPanel();
		bottomAnalytics.setLayout(new FlowLayout());
		bottomAnalytics.setBackground(Color.LIGHT_GRAY);

		// Left Analytics (Driver)
		driverTitle = makeAnalyticsLabel("");
		driverTitle.setFont (driverTitle.getFont().deriveFont (18.0f));
		leftAnalytics.add(driverTitle);

		driverMiles = makeAnalyticsLabel("");
		leftAnalytics.add(driverMiles);

		driverTime = makeAnalyticsLabel("");
		leftAnalytics.add(driverTime);

		driverAvgSpeed = makeAnalyticsLabel("");
		leftAnalytics.add(driverAvgSpeed);

		driverMaxSpeed = makeAnalyticsLabel("");
		leftAnalytics.add(driverMaxSpeed);

		driverFuelUsed = makeAnalyticsLabel("");
		leftAnalytics.add(driverFuelUsed);

		driverRadioTime =makeAnalyticsLabel("");
		leftAnalytics.add(driverRadioTime);

		driverPhoneTime = makeAnalyticsLabel("");
		leftAnalytics.add(driverPhoneTime);

		driverCallLogBtn = new JButton("Call Log");
		driverCallLogBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		driverCallLogBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(guiManager.mainFrame,
						car.driverManager.currentDriver.displayCallHistory());
			}          
		});
		
		leftAnalytics.add(driverCallLogBtn);

		// Display full session history for current driver.
		sessionHistoryBtn = new JButton("Session History");
		sessionHistoryBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(guiManager.mainFrame,
						car.driverManager.currentDriver.displaySessionHistory());
			}          
		});
		sessionHistoryBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		leftAnalytics.add(sessionHistoryBtn);


		// Center Analytics (Labels)

		JLabel categoryTitle = makeAnalyticsLabel("STATS");
		categoryTitle.setFont (categoryTitle.getFont().deriveFont (18.0f));
		centerAnalytics.add(categoryTitle);

		JLabel milesLabel = makeAnalyticsLabel("Distance Travelled (Miles)");
		centerAnalytics.add(milesLabel);

		JLabel timeLabel = makeAnalyticsLabel("Time Active (Seconds)");
		centerAnalytics.add(timeLabel);

		JLabel avgSpeedLabel = makeAnalyticsLabel("Average Speed (Miles / Hour)");
		centerAnalytics.add(avgSpeedLabel);

		JLabel maxSpeedLabel = makeAnalyticsLabel("Maximum Speed (Miles / Hour)");
		centerAnalytics.add(maxSpeedLabel);

		JLabel mpgLabel = makeAnalyticsLabel("Fuel Used (Gallons)");
		centerAnalytics.add(mpgLabel);

		JLabel radioTimeLabel = makeAnalyticsLabel("Radio Time (Seconds)");
		centerAnalytics.add(radioTimeLabel);

		JLabel phoneTimeLabel = makeAnalyticsLabel("Phone Time (Seconds)");
		centerAnalytics.add(phoneTimeLabel);

		// Right Analytics (Session)

		JLabel sessionTitle = makeAnalyticsLabel("SESSION");
		sessionTitle.setFont (driverTitle.getFont().deriveFont (18.0f));
		rightAnalytics.add(sessionTitle);

		sessionMiles = makeAnalyticsLabel("");
		rightAnalytics.add(sessionMiles);

		sessionTime = makeAnalyticsLabel("");
		rightAnalytics.add(sessionTime);

		sessionAvgSpeed = makeAnalyticsLabel("");
		rightAnalytics.add(sessionAvgSpeed);

		sessionMaxSpeed = makeAnalyticsLabel("");
		rightAnalytics.add(sessionMaxSpeed);

		sessionFuelUsed = makeAnalyticsLabel("");;
		rightAnalytics.add(sessionFuelUsed);

		sessionRadioTime = makeAnalyticsLabel("");
		rightAnalytics.add(sessionRadioTime);

		sessionPhoneTime = makeAnalyticsLabel("");
		rightAnalytics.add(sessionPhoneTime);

		sessionCallLogBtn = new JButton("Call Log");
		sessionCallLogBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		sessionCallLogBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(guiManager.mainFrame,
						car.currentSession.displayCallHistory());
			}          
		});
		
		rightAnalytics.add(sessionCallLogBtn);

		// Bottom Analytics Panel
		viewDriversBtn = new JButton("Driver List");
		viewDriversBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(guiManager.mainFrame,
						car.driverManager.displayKnownDrivers());
			}          
		});
		
		bottomAnalytics.add(viewDriversBtn);
		
		add("West", leftAnalytics);
		add("Center", centerAnalytics);
		add("East", rightAnalytics);
		add("South", bottomAnalytics);

	}
	
	public void refresh() {
		driverTitle.setText(car.driverManager.currentDriver.toString().toUpperCase());
		driverMiles.setText(dfShort.format(car.driverManager.currentDriver.getDistanceDriven()));
		driverTime.setText(Integer.toString( car.driverManager.currentDriver.getTimeDriven()));
		driverAvgSpeed.setText(dfShort.format( car.driverManager.currentDriver.getAverageSpeed()));
		driverMaxSpeed.setText(Integer.toString( car.driverManager.currentDriver.getMaxSpeed()));
		driverFuelUsed.setText(dfShort.format( car.driverManager.currentDriver.getFuelUsed()));
		driverRadioTime.setText(Integer.toString( car.driverManager.currentDriver.getTotalRadioTime()));
		driverPhoneTime.setText(Integer.toString( car.driverManager.currentDriver.getTotalPhoneTime()));

		sessionMiles.setText(dfShort.format(car.currentSession.getDistanceDriven()));
		sessionTime.setText(Integer.toString( car.currentSession.getTimeDriven()));
		sessionAvgSpeed.setText(dfShort.format( car.currentSession.getAverageSpeed()));
		sessionMaxSpeed.setText(Integer.toString( car.currentSession.getMaxSpeed()));
		sessionFuelUsed.setText(dfShort.format( car.currentSession.getFuelUsed()));
		sessionRadioTime.setText(Integer.toString( car.currentSession.getRadioTime()));
		sessionPhoneTime.setText(Integer.toString( car.currentSession.getPhoneTime()));	
	}
	
	
	private JLabel makeAnalyticsLabel(String label) {
		JLabel newJLabel = new JLabel(label);
		newJLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		return newJLabel;
	}


}
