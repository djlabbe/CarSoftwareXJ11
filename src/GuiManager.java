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
	private JLabel sessionMileage, totalMileage, currentSpeed, currentFuel;
	
	private JLabel stationLabel, modulusLabel, radioVolumeLabel;
	
	private JButton radioButton, phoneButton, mapButton, 
					statsButton, powerButton, gasButton, brakeButton, refuelButton, loginButton;
	
	private DecimalFormat df = new DecimalFormat("#,###,##0.00");
	private Timer timer;
	
	// GUI operates for a specific car object which is passed in on GUI initialization.
	public GuiManager(Car car) {
		this.car = car;
		prepareGUI();
	}

	// Make a new JFrame (main window) and center it.
	private void prepareGUI() {
		mainFrame = new JFrame("XJ-11");
		mainFrame.setSize(600, 400);
		mainFrame.setLayout(new BorderLayout());
		mainFrame.setResizable(false);
		mainFrame.addWindowListener(new WindowAdapter() {
	         public void windowClosing(WindowEvent windowEvent){
	            System.exit(0);
	         }        
	      });  
		mainFrame.setLocationRelativeTo(null);
	}
	
	// Build the elements inside the frame and make visible.
	protected void showScreen() {
		
		labelPanel = new JPanel();
		labelPanel.setBackground(Color.GRAY);
		
		navPanel = new JPanel();
		navPanel.setLayout(new BoxLayout(navPanel, 1));
		navPanel.setBackground(Color.GRAY);
		
		/* The appPanel uses a "CardLayout" which works like a stack of playing cards,
		 * appPanel will contain 4 other panels which can individually be made active.
		 */
		
		appPanel = new JPanel();
		appPanel.setLayout(new CardLayout());
		appPanel.setBackground(Color.DARK_GRAY);
		
		radioPanel = new JPanel(new BorderLayout());
		radioPanel.setBackground(Color.LIGHT_GRAY);
		
		phonePanel = new JPanel();
		phonePanel.setBackground(Color.RED);
		
		mapPanel = new JPanel();
		mapPanel.setBackground(Color.CYAN);
		
		analyticsPanel = new JPanel();
		analyticsPanel.setBackground(Color.MAGENTA);
		
		// Add four panels to appPanel.
		appPanel.add(radioPanel, "RADIOPANEL");
		appPanel.add(phonePanel, "PHONEPANEL");
		appPanel.add(mapPanel, "MAPPANEL");
		appPanel.add(analyticsPanel, "ANALYTICSPANEL");
		
		corePanel = new JPanel();
		corePanel.setBackground(Color.GRAY);
		
		emptyPanel = new JPanel();
		emptyPanel.setBackground(Color.GRAY);
		
		// Add all panels to the main frame
		mainFrame.add("North", labelPanel);
		mainFrame.add("West", navPanel);
		mainFrame.add("South", corePanel);
		mainFrame.add("Center", appPanel);
		mainFrame.add("East", emptyPanel);

		// Setup the contents of each panel.
		setupLabelPanel();
		setupNavPanel();
		setupCorePanel();
		setupRadioPanel();
		
		sessionMileage.setFont (sessionMileage.getFont().deriveFont (12.0f));
		sessionMileage.setText("Session: " + car.getSessionOdometer() + " miles ");
		
		totalMileage.setFont (totalMileage.getFont().deriveFont (24.0f));
		totalMileage.setText("| " + df.format(car.getOdometer()) + " miles | ");
		
		currentSpeed.setFont (currentSpeed.getFont().deriveFont (24.0f));
		currentSpeed.setText(car.coast() + " MPH | ");
		
		currentFuel.setFont (currentFuel.getFont().deriveFont (12.0f));
		currentFuel.setText(df.format(car.getFuelPercent()) + "% Fuel ");
	
	    mainFrame.setVisible(true);
	}
	
	// LabelPanel is a HeadsUpDisplay of necessary information for the driver.
	private void setupLabelPanel() {
		
		sessionMileage = new JLabel("");  	
		totalMileage = new JLabel("");  
	    currentSpeed = new JLabel("");
	    currentFuel = new JLabel("");
	    
	    labelPanel.add(sessionMileage);
	    labelPanel.add(totalMileage);
	    labelPanel.add(currentSpeed);
	    labelPanel.add(currentFuel);
	}
	
	// NavPanel contains buttons that toggle the display of appPanel.
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
	    
	    statsButton = new JButton("Stats");
	    statsButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 CardLayout cardLayout = (CardLayout)(appPanel.getLayout());
			     cardLayout.show(appPanel, "ANALYTICSPANEL");
	         }          
	      });
	    
	    navPanel.add(radioButton);
	    navPanel.add(phoneButton);
	    navPanel.add(mapButton);
	    navPanel.add(statsButton);
	}
	
	// CorePanel displays core car functionality such as Power, Gas, and Brake.
	private void setupCorePanel() {

		loginButton = new JButton("Login");
	    loginButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 car.login();
	         }          
	      });
		
		powerButton = new JButton("On | Off");
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
	
	// RADIOPANEL PLAYS RADIO
		private void setupRadioPanel() {
			
			// TOP RADIO PANEL
			
			JPanel topRadioPanel = new JPanel();
			topRadioPanel.setBackground(Color.LIGHT_GRAY);
			
			JButton radioPowerButton = new JButton("On | Off");
		    radioPowerButton.addActionListener(new ActionListener() {
		         public void actionPerformed(ActionEvent e) {
		            car.radio.togglePower();
		            System.out.println("Radio power toggled");
		         }          
		      });
			topRadioPanel.add(radioPowerButton);
			
			JButton seekDownButton = new JButton(" << ");
		    seekDownButton.addActionListener(new ActionListener() {
		         public void actionPerformed(ActionEvent e) {
		            car.radio.seekDown();
		            stationLabel.setText(Double.toString(car.radio.currentStation.getStation()));
		         }          
		      });
			topRadioPanel.add(seekDownButton);
			
			JButton seekUpButton = new JButton(" >> ");
		    seekUpButton.addActionListener(new ActionListener() {
		         public void actionPerformed(ActionEvent e) {
		            car.radio.seekUp();
		            stationLabel.setText(Double.toString(car.radio.currentStation.getStation()));
		         }          
		      });
			topRadioPanel.add(seekUpButton);
			
			JButton amFmButton = new JButton("AM | FM");
		    amFmButton.addActionListener(new ActionListener() {
		         public void actionPerformed(ActionEvent e) {
		            car.radio.toggleMod();
		            stationLabel.setText(Double.toString(car.radio.currentStation.getStation()));
		            modulusLabel.setText(car.radio.getModLabel());
		         }          
		      });
			topRadioPanel.add(amFmButton);
			
			// LEFT RADIO PANEL
			
			JPanel leftRadioPanel = new JPanel();
			leftRadioPanel.setLayout(new BoxLayout(leftRadioPanel, 1));
			leftRadioPanel.setBackground(Color.LIGHT_GRAY);
			
			JLabel radioVolumeTitleLabel = new JLabel("Vol");
			radioVolumeTitleLabel.setFont (radioVolumeTitleLabel.getFont().deriveFont (28.0f));
			leftRadioPanel.add(radioVolumeTitleLabel);
		
			
			JButton volumeUpButton = new JButton(" + ");
		    volumeUpButton.addActionListener(new ActionListener() {
		         public void actionPerformed(ActionEvent e) {
		            car.radio.volUp();
		            radioVolumeLabel.setText(Integer.toString(car.radio.getVolume()));
		         }          
		      });
			leftRadioPanel.add(volumeUpButton);
			
			radioVolumeLabel = new JLabel(Integer.toString(car.radio.getVolume()));
			radioVolumeLabel.setFont (radioVolumeLabel.getFont().deriveFont (40.0f));
			leftRadioPanel.add(radioVolumeLabel);
			
			
			JButton volumeDownButton = new JButton(" - ");
		    volumeDownButton.addActionListener(new ActionListener() {
		         public void actionPerformed(ActionEvent e) {
		            car.radio.volDown();
		            radioVolumeLabel.setText(Integer.toString(car.radio.getVolume()));
		         }          
		      });
			leftRadioPanel.add(volumeDownButton);
			
			// CENTER RADIO PANEL
			
			JPanel centerRadioPanel = new JPanel();
			centerRadioPanel.setBackground(Color.WHITE);
		
			stationLabel = new JLabel(Double.toString(car.radio.currentStation.getStation()));
			stationLabel.setFont (stationLabel.getFont().deriveFont (55.0f));
			centerRadioPanel.add(stationLabel);
			
			modulusLabel = new JLabel(car.radio.getModLabel());
			modulusLabel.setFont (modulusLabel.getFont().deriveFont (44.0f));
			centerRadioPanel.add(modulusLabel);
		
			// Bottom Radio Panel
			
			JPanel bottomRadioPanel = new JPanel();
			bottomRadioPanel.setBackground(Color.LIGHT_GRAY);
			
			JButton setButton = new JButton("Set Favorite");
		    setButton.addActionListener(new ActionListener() {
		         public void actionPerformed(ActionEvent e) {
		            // Set Favorite
		         }          
		      });
			bottomRadioPanel.add(setButton);
			
			JButton fav1Button = new JButton(" 1 ");
		    fav1Button.addActionListener(new ActionListener() {
		         public void actionPerformed(ActionEvent e) {
		            // Favorite 1
		         }          
		      });
			bottomRadioPanel.add(fav1Button);
			
			JButton fav2Button = new JButton(" 2 ");
		    fav2Button.addActionListener(new ActionListener() {
		         public void actionPerformed(ActionEvent e) {
		            // Favorite 2
		         }          
		      });
			bottomRadioPanel.add(fav2Button);
			
			JButton fav3Button = new JButton(" 3 ");
		    fav3Button.addActionListener(new ActionListener() {
		         public void actionPerformed(ActionEvent e) {
		            // Favorite 3
		         }          
		      });
			bottomRadioPanel.add(fav3Button);
			
			//  RIGHT RADIO PANEL
			JPanel rightRadioPanel = new JPanel();
			rightRadioPanel.setBackground(Color.LIGHT_GRAY);
			
			//setup 4 panels
			radioPanel.add("North", topRadioPanel);
			radioPanel.add("West", leftRadioPanel);
			radioPanel.add("South", bottomRadioPanel);
			radioPanel.add("Center", centerRadioPanel);
			radioPanel.add("East", rightRadioPanel);
			
		}
	
	/* The main loop and timing mechanism for driving,
	 * A TimerTask is scheduled to run every 1 second which then updates the 
	 * speed and position of the car while logging associated data.
	 */
	
	public void runLoop() {
		int begin = 0; // start immediately 
		int timeinterval = 1000; // tick every 1 second
		timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
		  @Override
		  public void run() {
			  
			 double deltaDistance = (car.getCurrentSpeed() / 60 / 60);
	
			 car.incrementOdometer(deltaDistance);
			 
			 car.currentDriver.incrementTotalDriveTime();
			 car.currentDriver.incrementTotalDriveDistance(deltaDistance);
			 car.currentDriver.computeAverageSpeed();
			 if (car.radio.getIsOn()) {
				 car.currentDriver.incrementTotalRadioTime();
			 }
			 currentSpeed.setText(car.coast() + " MPH | ");
			 totalMileage.setText("| " + df.format(car.getOdometer()) + " miles | ");
		  }
		},begin, timeinterval);
	}

}
