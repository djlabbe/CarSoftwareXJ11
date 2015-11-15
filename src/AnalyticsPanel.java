import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
	
	public AnalyticsPanel(final GuiManager guiManager) {
		this.car = guiManager.getCar();
		setLayout(new BorderLayout());
		setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gb = new GridBagConstraints();
		
		JPanel leftAnalytics = new JPanel();
		leftAnalytics.setLayout(new GridBagLayout());
		leftAnalytics.setBackground(Color.LIGHT_GRAY);
		gb.insets = new Insets(2,5,2,5);

		JPanel centerAnalytics = new JPanel();
		centerAnalytics.setLayout(new GridBagLayout());
		centerAnalytics.setBackground(Color.LIGHT_GRAY);

		JPanel rightAnalytics= new JPanel();
		rightAnalytics.setLayout(new GridBagLayout());
		rightAnalytics.setBackground(Color.LIGHT_GRAY);

		JPanel bottomAnalytics = new JPanel();
		bottomAnalytics.setLayout(new FlowLayout());
		bottomAnalytics.setBackground(Color.LIGHT_GRAY);

		// Left Analytics (Driver)
		driverTitle = makeAnalyticsLabel("");
		driverTitle.setFont (driverTitle.getFont().deriveFont (18.0f));
		gb.gridx=0;
		gb.gridy=0;
		leftAnalytics.add(driverTitle, gb);

		driverMiles = makeAnalyticsLabel("");
		gb.gridy=1;
		leftAnalytics.add(driverMiles, gb);

		driverTime = makeAnalyticsLabel("");
		gb.gridy=2;
		leftAnalytics.add(driverTime, gb);

		driverAvgSpeed = makeAnalyticsLabel("");
		gb.gridy=3;
		leftAnalytics.add(driverAvgSpeed, gb);

		driverMaxSpeed = makeAnalyticsLabel("");
		gb.gridy=4;
		leftAnalytics.add(driverMaxSpeed, gb);

		driverFuelUsed = makeAnalyticsLabel("");
		gb.gridy=5;
		leftAnalytics.add(driverFuelUsed, gb);

		driverRadioTime =makeAnalyticsLabel("");
		
		gb.gridy=6;
		leftAnalytics.add(driverRadioTime, gb);

		driverPhoneTime = makeAnalyticsLabel("");
		gb.gridy=7;
		leftAnalytics.add(driverPhoneTime, gb);
		
		
		driverCallLogBtn = new JButton("Call Log");
		driverCallLogBtn.setBackground(Color.darkGray);
		driverCallLogBtn.setForeground(Color.white);
		driverCallLogBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		driverCallLogBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(guiManager.mainFrame,
						car.driverManager.currentDriver.displayCallHistory());
			}          
		});
		gb.gridy=8;
		leftAnalytics.add(driverCallLogBtn, gb);

		

		// Center Analytics (Labels)

		JLabel categoryTitle = makeAnalyticsLabel("STATS");
		categoryTitle.setFont (categoryTitle.getFont().deriveFont (18.0f));
		gb.gridy=0;
		centerAnalytics.add(categoryTitle, gb);

		JLabel milesLabel = makeAnalyticsLabel("Distance Travelled (Miles)");
		gb.gridy=1;
		centerAnalytics.add(milesLabel, gb);

		JLabel timeLabel = makeAnalyticsLabel("Time Active (Seconds)");
		gb.gridy=2;
		centerAnalytics.add(timeLabel, gb);

		JLabel avgSpeedLabel = makeAnalyticsLabel("Average Speed (Miles / Hour)");
		gb.gridy=3;
		centerAnalytics.add(avgSpeedLabel, gb);

		JLabel maxSpeedLabel = makeAnalyticsLabel("Maximum Speed (Miles / Hour)");
		gb.gridy=4;
		centerAnalytics.add(maxSpeedLabel, gb);

		JLabel mpgLabel = makeAnalyticsLabel("Fuel Used (Gallons)");
		gb.gridy=5;
		centerAnalytics.add(mpgLabel, gb);

		JLabel radioTimeLabel = makeAnalyticsLabel("Radio Time (Seconds)");
		gb.gridy=6;
		centerAnalytics.add(radioTimeLabel, gb);

		JLabel phoneTimeLabel = makeAnalyticsLabel("Phone Time (Seconds)");
		gb.gridy=7;
		centerAnalytics.add(phoneTimeLabel, gb);
		
		viewDriversBtn = new JButton("Driver List");
		viewDriversBtn.setBackground(Color.darkGray);
		viewDriversBtn.setForeground(Color.white);
		viewDriversBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(guiManager.mainFrame,
						car.driverManager.displayKnownDrivers());
			}          
		});
		gb.gridy=8;
		centerAnalytics.add(viewDriversBtn, gb);
		
		

		// Right Analytics (Session)

		JLabel sessionTitle = makeAnalyticsLabel("SESSION");
		sessionTitle.setFont (driverTitle.getFont().deriveFont (18.0f));
		gb.gridy=0;
		rightAnalytics.add(sessionTitle, gb);

		sessionMiles = makeAnalyticsLabel("");
		gb.gridy=1;
		rightAnalytics.add(sessionMiles, gb);

		sessionTime = makeAnalyticsLabel("");
		gb.gridy=2;
		rightAnalytics.add(sessionTime, gb);

		sessionAvgSpeed = makeAnalyticsLabel("");
		gb.gridy=3;
		rightAnalytics.add(sessionAvgSpeed, gb);

		sessionMaxSpeed = makeAnalyticsLabel("");
		gb.gridy=4;
		rightAnalytics.add(sessionMaxSpeed, gb);

		sessionFuelUsed = makeAnalyticsLabel("");
		gb.gridy=5;
		rightAnalytics.add(sessionFuelUsed, gb);

		sessionRadioTime = makeAnalyticsLabel("");
		gb.gridy=6;
		rightAnalytics.add(sessionRadioTime, gb);

		sessionPhoneTime = makeAnalyticsLabel("");
		gb.gridy=7;
		rightAnalytics.add(sessionPhoneTime, gb);

		
		sessionCallLogBtn = new JButton("Call Log");
		sessionCallLogBtn.setBackground(Color.darkGray);
		sessionCallLogBtn.setForeground(Color.white);
		sessionCallLogBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		sessionCallLogBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(guiManager.mainFrame,
						car.currentSession.displayCallHistory());
			}          
		});
		gb.gridy=8;
		rightAnalytics.add(sessionCallLogBtn, gb);

		// Bottom Analytics Panel
		// Display full session history for current driver.
				sessionHistoryBtn = new JButton(car.driverManager.currentDriver + "'s Sessions");
				sessionHistoryBtn.setBackground(Color.darkGray);
				sessionHistoryBtn.setForeground(Color.white);
				sessionHistoryBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JOptionPane.showMessageDialog(guiManager.mainFrame,
								car.driverManager.currentDriver.displaySessionHistory());
					}          
				});
				sessionHistoryBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
				bottomAnalytics.add(sessionHistoryBtn);
		
		
		
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
