import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.text.DecimalFormat;

public class GuiManager {
	
	private JFrame mainFrame;
	private JPanel labelPanel, navPanel, corePanel, appPanel, emptyPanel;
	private JLabel tripMileage, totalMileage, currentSpeed, currentFuel;
	private JButton radioButton, phoneButton, mapButton, 
					dataButton, powerButton, gasButton, brakeButton, refuelButton;
	
	private Car car;
	
	DecimalFormat df = new DecimalFormat("#,###,##0.00");
	
	// Run the GUI
	public GuiManager(Car car) {
		this.car = car;
		prepareGUI();
	}

	// Prepare for contents of the frame
	private void prepareGUI() {
		mainFrame = new JFrame("XJ-11");
		mainFrame.setSize(800, 600);
		mainFrame.setLayout(new BorderLayout());
		mainFrame.addWindowListener(new WindowAdapter() {
	         public void windowClosing(WindowEvent windowEvent){
	            System.exit(0);
	         }        
	      });   
	}

	//Display the contents of the GUI
	protected void showScreen() {
		
		labelPanel = new JPanel();
		labelPanel.setBackground(Color.GRAY);
		
		navPanel = new JPanel();
		navPanel.setLayout(new BoxLayout(navPanel, 1));
		navPanel.setBackground(Color.WHITE);
		
		appPanel = new JPanel(new CardLayout());
		appPanel.setBackground(Color.DARK_GRAY);
		
		corePanel = new JPanel();
		corePanel.setBackground(Color.GRAY);
		
		emptyPanel = new JPanel();
		emptyPanel.setBackground(Color.GRAY);
		
		mainFrame.add("North", labelPanel);
		mainFrame.add("West", navPanel);
		mainFrame.add("South", corePanel);
		mainFrame.add("Center", appPanel);
		mainFrame.add("East", emptyPanel);

		setupLabelPanel();
		setupNavPanel();
		setupCorePanel();
		
		tripMileage.setText("Trip: " + car.tripOdometer + " miles | ");
		totalMileage.setText("Total: " + car.odometer + " miles | ");
		currentSpeed.setText(car.currentSpeed + " MPH | ");
		currentFuel.setText(df.format(car.percentFuel) + "% Fuel ");
		
	    mainFrame.setVisible(true);
	}
	
	private void setupLabelPanel() {
		
		tripMileage = new JLabel("");  	
		totalMileage = new JLabel("");  
	    currentSpeed = new JLabel("");
	    currentFuel = new JLabel("");
	    
	    labelPanel.add(tripMileage);
	    labelPanel.add(totalMileage);
	    labelPanel.add(currentSpeed);
	    labelPanel.add(currentFuel);
	}
	
	// Prepare the navigation Panel buttons.
	private void setupNavPanel() {
		radioButton = new JButton("Radio");
	    radioButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	            System.out.println("Radio pressed.");
	         }          
	      });
	    
	    phoneButton = new JButton("Phone");
	    phoneButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	            System.out.println("Phone pressed.");
	         }          
	      });

	    mapButton = new JButton("Map");
	    mapButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	             System.out.println("Map pressed.");
	         }          
	      });
	    
	    dataButton = new JButton("Data");
	    dataButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	            System.out.println("Data pressed.");
	         }          
	      });
	    
	    navPanel.add(radioButton);
	    navPanel.add(phoneButton);
	    navPanel.add(mapButton);
	    navPanel.add(dataButton);
	}
	
	// Prepare the core Panel buttons.
	private void setupCorePanel() {
		powerButton = new JButton("ON/OFF");
	    powerButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	            System.out.println("ON/OFF pressed.");
	         }          
	      });
	    
	    refuelButton = new JButton("Refuel");
	    refuelButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	            System.out.println("Refuel pressed.");
	         }          
	      });
		
	    brakeButton = new JButton("BRAKE");
	    brakeButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 car.decelerate();
	        	 currentSpeed.setText(car.currentSpeed + " MPH | ");
	         }          
	      });
	    
	    gasButton = new JButton("GAS");
	    gasButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	car.accelerate();
	        	currentSpeed.setText(car.currentSpeed + " MPH | ");
	        	currentFuel.setText(df.format(car.percentFuel) + "% Fuel ");
	         }          
	      });
	    
	    corePanel.add(powerButton);
	    corePanel.add(refuelButton);
	    corePanel.add(brakeButton);
	    corePanel.add(gasButton);
	    
	}

}
