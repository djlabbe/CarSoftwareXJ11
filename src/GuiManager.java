/*Timer code structure found on Stack-overflow*/

/*Main screen consists of the full window JFrame which contains a 5 JPanels arranged in Border Layout.
 *The center (main) panel is called the appPanel which itself has a CardLayout meaning it behaves like a stack
 *of cards. The four sub-panels are radioPanel, phonePanel, mapPanel, and analyticsPanel. Each can be made to be
 *the active panel within the appPanel.*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

public class GuiManager {
	
	private Car car;
	
	private JFrame mainFrame;
	private JPanel labelPanel, navPanel, corePanel, appPanel, emptyPanel;
	private JPanel radioPanel, phonePanel, mapPanel, analyticsPanel;
	private JLabel tripMileage, totalMileage, currentSpeed, currentFuel;
	private JButton radioButton, phoneButton, mapButton, 
					dataButton, powerButton, gasButton, brakeButton, refuelButton, loginButton;
	
	private DecimalFormat df = new DecimalFormat("#,###,##0.00");
	private Timer timer;
	
	public GuiManager(Car car) {
		this.car = car;
		prepareGUI();
	}

	private void prepareGUI() {
		mainFrame = new JFrame("XJ-11");
		mainFrame.setSize(800, 600);
		mainFrame.setLayout(new BorderLayout());
		mainFrame.setResizable(false);
		mainFrame.addWindowListener(new WindowAdapter() {
	         public void windowClosing(WindowEvent windowEvent){
	            System.exit(0);
	         }        
	      });  
		mainFrame.setLocationRelativeTo(null);
	}

	protected void showScreen() {
		
		labelPanel = new JPanel();
		labelPanel.setBackground(Color.GRAY);
		
		navPanel = new JPanel();
		navPanel.setLayout(new BoxLayout(navPanel, 1));
		navPanel.setBackground(Color.WHITE);
		
		appPanel = new JPanel();
		appPanel.setLayout(new CardLayout());
		appPanel.setBackground(Color.DARK_GRAY);
		
		radioPanel = new JPanel();
		radioPanel.setBackground(Color.GREEN);
		
		phonePanel = new JPanel();
		phonePanel.setBackground(Color.RED);
		
		mapPanel = new JPanel();
		mapPanel.setBackground(Color.CYAN);
		
		analyticsPanel = new JPanel();
		analyticsPanel.setBackground(Color.MAGENTA);
		
		appPanel.add(radioPanel, "RADIOPANEL");
		appPanel.add(phonePanel, "PHONEPANEL");
		appPanel.add(mapPanel, "MAPPANEL");
		appPanel.add(analyticsPanel, "ANALYTICSPANEL");
		
		
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
		
		tripMileage.setText("Trip: " + car.getTripOdometer() + " miles | ");
		totalMileage.setText("Total: " + car.getOdometer() + " miles | ");
		currentSpeed.setText(car.getCurrentSpeed() + " MPH | ");
		currentFuel.setText(df.format(car.getFuelPercent()) + "% Fuel ");
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
	
	private void setupNavPanel() {
		radioButton = new JButton("Radio");
	    radioButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	CardLayout cardLayout = (CardLayout)(appPanel.getLayout());
	        	cardLayout.show(appPanel, "RADIOPANEL");
	         }          
	      });
	    
	    phoneButton = new JButton("Phone");
	    phoneButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	CardLayout cardLayout = (CardLayout)(appPanel.getLayout());
		        cardLayout.show(appPanel, "PHONEPANEL");
	         }          
	      });

	    mapButton = new JButton("Map");
	    mapButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 CardLayout cardLayout = (CardLayout)(appPanel.getLayout());
			     cardLayout.show(appPanel, "MAPPANEL");
	         }          
	      });
	    
	    dataButton = new JButton("Data");
	    dataButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 CardLayout cardLayout = (CardLayout)(appPanel.getLayout());
			     cardLayout.show(appPanel, "ANALYTICSPANEL");
	         }          
	      });
	    
	    navPanel.add(radioButton);
	    navPanel.add(phoneButton);
	    navPanel.add(mapButton);
	    navPanel.add(dataButton);
	}
	
	private void setupCorePanel() {
		
		
		loginButton = new JButton("Login");
	    loginButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 
	        	 // #TODO Re-authenticate user.
	        	 car.login();
	         }          
	      });
		
		
		powerButton = new JButton("ON/OFF");
	    powerButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 if (car.togglePower() && car.getCurrentSpeed() == 0) {
	     			runLoop();
	     		} else if (car.getCurrentSpeed() == 0) {
	     			timer.cancel();
	     			timer.purge();
	     		}
	         }          
	      });
	    
	    refuelButton = new JButton("Refuel");
	    refuelButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	            car.refuel();
	            currentFuel.setText(df.format(car.getFuelPercent()) + "% Fuel ");
	         }          
	      });
		
	    brakeButton = new JButton("BRAKE");
	    brakeButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 currentSpeed.setText(car.decelerate() + " MPH | ");
	         }          
	      });
	    
	    gasButton = new JButton("GAS");
	    gasButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	currentSpeed.setText(car.accelerate() + " MPH | ");
	        	currentFuel.setText(df.format(car.getFuelPercent()) + "% Fuel ");
	         }          
	      });
	    
	    corePanel.add(loginButton);
	    corePanel.add(powerButton);
	    corePanel.add(refuelButton);
	    corePanel.add(brakeButton);
	    corePanel.add(gasButton);
	    
	}
	
public void runLoop() {
		int begin = 0; 
		int timeinterval = 1000;
		timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
		  @Override
		  public void run() {
			 currentSpeed.setText(car.coast() + " MPH | ");
			 Driver driver = car.getCurrentDriver();
			 driver.incrementTotalDriveTime();
			 driver.incrementTotalDriveDistance(car.getCurrentSpeed() / 60 / 60);
			 driver.computeAverageSpeed();
		  }
		},begin, timeinterval);
	}

}
