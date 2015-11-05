import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GuiManager {

	private JFrame mainFrame;
	private JPanel labelPanel, navPanel, appPanel, corePanel;
	private JLabel tripMileage, totalMileage, currentSpeed, currentFuel;
	private JButton radioButton, phoneButton, mapButton, 
					dataButton, powerButton, gasButton, brakeButton, refuelButton;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		GuiManager guiManager = new GuiManager();
		guiManager.showScreen();
	}

	/**
	 * Create the application.
	 */
	public GuiManager() {
		prepareGUI();
	}

	/**
	 * prepareGUI the contents of the frame.
	 */
	private void prepareGUI() {
		mainFrame = new JFrame("XJ-11");
		mainFrame.setSize(800, 600);
		mainFrame.addWindowListener(new WindowAdapter() {
	         public void windowClosing(WindowEvent windowEvent){
	            System.exit(0);
	         }        
	      });   
	}
	
	private void showScreen() {
		
		setupLabelPanel();
		setupNavPanel();
		setupCorePanel();
	
		// Need better way to space out the labels. 
		tripMileage.setText("Trip: ___ miles     ");
		totalMileage.setText("     Total: ___ miles");
		currentSpeed.setText("          __ MPH  ");
		currentFuel.setText("           __% Fuel");
		
	    mainFrame.setVisible(true);
	}
	
	private void setupLabelPanel() {
		labelPanel = new JPanel();
		labelPanel.setSize(800,100);
		labelPanel.setLocation(0,0);
		labelPanel.setBackground(Color.GRAY);
		
		navPanel = new JPanel();
		navPanel.setSize(100, 400);
		navPanel.setLocation(0, 100);
		navPanel.setBackground(Color.WHITE);
		
		appPanel = new JPanel();
		appPanel.setSize(700, 600);
		appPanel.setLocation(100,100);
		appPanel.setBackground(Color.BLACK);
		
		corePanel = new JPanel();
		corePanel.setSize(800, 100);
		corePanel.setLocation(0,500);
		corePanel.setBackground(Color.GRAY);
		
		
		mainFrame.add(labelPanel);
		mainFrame.add(navPanel);
		mainFrame.add(corePanel);
		mainFrame.add(appPanel);
		
	
		tripMileage = new JLabel("");  	
		totalMileage = new JLabel("");  
	    currentSpeed = new JLabel("");
	    currentFuel = new JLabel("");
	    
	    labelPanel.add(tripMileage);
	    labelPanel.add(totalMileage);
	    labelPanel.add(currentSpeed);
	    labelPanel.add(currentFuel);
	}
	
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
	            System.out.println("BRAKE pressed.");
	         }          
	      });
	    
	    gasButton = new JButton("GAS");
	    gasButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	            System.out.println("GAS pressed.");
	         }          
	      });
	    
	    corePanel.add(powerButton);
	    corePanel.add(refuelButton);
	    corePanel.add(brakeButton);
	    corePanel.add(gasButton);
	    
	}

}
