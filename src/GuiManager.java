/*Timer code structure found on Stack-overflow*/

/*Main screen consists of the full window JFrame which contains a 5 JPanels arranged in Border Layout.
 *The center (main) panel is called the appPanel which itself has a CardLayout meaning it behaves like a stack
 *of cards. The four sub-panels are radioPanel, phonePanel, mapPanel, and analyticsPanel. Each can be made to be
 *the active panel within the appPanel.*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.DecimalFormat;
import java.util.Enumeration;
import java.util.Timer;
import java.util.TimerTask;

public class GuiManager {
	
	private Car car;
	
	private JFrame mainFrame;
	private JPanel labelPanel, navPanel, corePanel, appPanel, emptyPanel;
	private JPanel radioPanel, phonePanel, mapPanel, analyticsPanel, centerRadioPanel;
	
	
	private JLabel sessionMileage, totalMileage, currentSpeed, currentFuel;
	private JLabel stationLabel, modulusLabel, radioVolumeLabel;
	private JLabel driverMiles, driverTime, driverAvgSpeed, driverMaxSpeed, driverFuelUsed, driverRadioTime, driverPhoneTime,
			sessionMiles, sessionTime, sessionAvgSpeed, sessionMaxSpeed, sessionFuelUsed, sessionRadioTime, sessionPhoneTime;
	
	private JButton radioButton, phoneButton, mapButton, 
					statsButton, powerButton, gasButton, brakeButton, refuelButton, loginButton;
	
	private JSlider mapSlider;
	
	private DecimalFormat dfFuel = new DecimalFormat("#,###,##0");
	private DecimalFormat dfMap = new DecimalFormat("###.00");
	private DecimalFormat dfShort = new DecimalFormat("###0.00");

	private Timer timer;
	private double deltaDistance;
	
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
	
	/******************************************/
	/**********  HOMESCREEN SETUP  ************/
	/******************************************/
	
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
		appPanel.setVisible(false);
		
		radioPanel = new JPanel(new BorderLayout());
		radioPanel.setBackground(Color.LIGHT_GRAY);
		
		phonePanel = new JPanel(new BorderLayout());
		phonePanel.setBackground(Color.LIGHT_GRAY);
		
		mapPanel = new JPanel(new BorderLayout());
		mapPanel.setBackground(Color.LIGHT_GRAY);
		
		analyticsPanel = new JPanel(new BorderLayout());
		analyticsPanel.setBackground(Color.LIGHT_GRAY);
		
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
		setupPhonePanel();
		setupMapPanel();
		setupAnalyticsPanel();
		
		sessionMileage.setFont (sessionMileage.getFont().deriveFont (12.0f));
		sessionMileage.setText("Session: " + dfShort.format(car.currentSession.getDistanceDriven()) + " miles ");
		
		totalMileage.setFont (totalMileage.getFont().deriveFont (30.0f));
		totalMileage.setText("| " + dfShort.format(car.getOdometer()) + " miles | ");
		
		currentSpeed.setFont (currentSpeed.getFont().deriveFont (30.0f));
		currentSpeed.setText(car.getCurrentSpeed() + " MPH | ");
		
		currentFuel.setFont (currentFuel.getFont().deriveFont (12.0f));
		currentFuel.setText(dfFuel.format(car.getFuelPercent()) + "% Fuel ");
	
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
	
	/******************************************/
	/*************  NAV PANEL  ****************/
	/******************************************/
	
	private void setupNavPanel() {
		radioButton = new JButton("Radio");
	    radioButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	CardLayout cardLayout = (CardLayout)(appPanel.getLayout());
	        	cardLayout.show(appPanel, "RADIOPANEL");
	        	System.out.println("Show Radio");
	         }          
	      });
	    
	    phoneButton = new JButton("Phone");
	    phoneButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	CardLayout cardLayout = (CardLayout)(appPanel.getLayout());
		        cardLayout.show(appPanel, "PHONEPANEL");
		        System.out.println("Show Phone");
	         }          
	      });

	    mapButton = new JButton("Map");
	    mapButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 CardLayout cardLayout = (CardLayout)(appPanel.getLayout());
			     cardLayout.show(appPanel, "MAPPANEL");
			     System.out.println("Show Map");
	         }          
	      });
	    
	    statsButton = new JButton("Stats");
	    statsButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 CardLayout cardLayout = (CardLayout)(appPanel.getLayout());
			     cardLayout.show(appPanel, "ANALYTICSPANEL");
			     System.out.println("Show Analytics");
	         }          
	      });
	    
	    navPanel.add(radioButton);
	    navPanel.add(phoneButton);
	    navPanel.add(mapButton);
	    navPanel.add(statsButton);
	}
	
	/******************************************/
	/************  CORE PANEL  ***************/
	/******************************************/
	
	// CorePanel displays core car functionality such as Power, Gas, and Brake.
	private void setupCorePanel() {

		loginButton = new JButton("Login");
	    loginButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 // save the current session
	        	 car.currentDriver.saveSession(car.currentSession);
	        	 // login new driver resets current session
	        	 car.login();
	        	 System.out.println("Loggin in new user...");
	         }          
	      });
		
		powerButton = new JButton("On | Off");
	    powerButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 car.togglePower();
	        	 if (car.getIsOn() && car.getCurrentSpeed() == 0) {
	        		 appPanel.setVisible(true);
	        		 System.out.println("Engine turned on.");
	        		 runLoop();
	        	 } else if (car.getCurrentSpeed() == 0) {
	        		appPanel.setVisible(false);
	        		System.out.println("Engine turned off.");
		     		timer.cancel();
		     		timer.purge();
	        	 } else {
	        		 System.out.println("Can't turn off engine while car is moving.");
	        	 }
	         }          
	      });
	    
	    refuelButton = new JButton("Refuel");
	    refuelButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	            car.refuel();
	            currentFuel.setText(dfFuel.format(car.getFuelPercent()) + "% Fuel ");
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
	        	currentFuel.setText(dfFuel.format(car.getFuelPercent()) + "% Fuel ");
	         }          
	      });
	    
	    corePanel.add(loginButton);
	    corePanel.add(powerButton);
	    corePanel.add(refuelButton);
	    corePanel.add(brakeButton);
	    corePanel.add(gasButton);
	}
	
	/******************************************/
	/************  RADIO PANEL  ***************/
	/******************************************/

	private void setupRadioPanel() {
			
		// TOP RADIO PANEL
			
		JPanel topRadioPanel = new JPanel();
		topRadioPanel.setBackground(Color.LIGHT_GRAY);
			
		JButton radioPowerButton = new JButton("On | Off");
		radioPowerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				car.radio.togglePower();
		        if (car.radio.getIsOn()) {
		            centerRadioPanel.setVisible(true);
		            System.out.println("Radio turned on.");
		            
		        } else {
		            centerRadioPanel.setVisible(false);
		            System.out.println("Radio turned off.");
		        }
		        
		    }          
		});
		
		JButton seekDownButton = new JButton(" << ");
	    seekDownButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	if (car.radio.getIsOn()) {
	        		car.radio.seekDown();
		            stationLabel.setText(Double.toString(car.radio.currentStation.getStation()));
	        	}
	         }          
	      });
		
		JButton seekUpButton = new JButton(" >> ");
	    seekUpButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	if (car.radio.getIsOn()) {
	        		car.radio.seekUp();
		            stationLabel.setText(Double.toString(car.radio.currentStation.getStation()));
	        	}
	         }          
	      });
			
		JButton amFmButton = new JButton("AM | FM");
	    amFmButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	if (car.radio.getIsOn()) {
	        		car.radio.toggleMod();
		            stationLabel.setText(Double.toString(car.radio.currentStation.getStation()));
		            modulusLabel.setText(car.radio.getModLabel());
	        	}
	         }          
	      });
	    
	    topRadioPanel.add(radioPowerButton);
	    topRadioPanel.add(seekDownButton);
	    topRadioPanel.add(seekUpButton);
		topRadioPanel.add(amFmButton);
			
		// LEFT RADIO PANEL
		
		JPanel leftRadioPanel = new JPanel();
		leftRadioPanel.setLayout(new BoxLayout(leftRadioPanel, 1));
		leftRadioPanel.setBackground(Color.LIGHT_GRAY);
		
		JLabel radioVolumeTitleLabel = new JLabel("Vol");
		radioVolumeTitleLabel.setFont (radioVolumeTitleLabel.getFont().deriveFont (28.0f));
		
		JButton volumeUpButton = new JButton(" + ");
	    volumeUpButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	if (car.radio.getIsOn()) {
	        		car.radio.volUp();
		            radioVolumeLabel.setText(Integer.toString(car.radio.getVolume()));
	        	} 
	         }          
	     });
		
		radioVolumeLabel = new JLabel(Integer.toString(car.radio.getVolume()));
		radioVolumeLabel.setFont (radioVolumeLabel.getFont().deriveFont (40.0f));
		
		JButton volumeDownButton = new JButton(" - ");
	    volumeDownButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	if (car.radio.getIsOn()) {
		            car.radio.volDown();
		            radioVolumeLabel.setText(Integer.toString(car.radio.getVolume()));	
	        	}
	         }          
	    });
	    
	    leftRadioPanel.add(radioVolumeTitleLabel);
	    leftRadioPanel.add(volumeUpButton);
	    leftRadioPanel.add(radioVolumeLabel);
		leftRadioPanel.add(volumeDownButton);
			
		// CENTER RADIO PANEL
		
		centerRadioPanel = new JPanel();
		centerRadioPanel.setBackground(Color.WHITE);
		centerRadioPanel.setVisible(false);
	
		stationLabel = new JLabel(Double.toString(car.radio.currentStation.getStation()));
		stationLabel.setFont (stationLabel.getFont().deriveFont (55.0f));
		centerRadioPanel.add(stationLabel);
		
		modulusLabel = new JLabel(car.radio.getModLabel());
		modulusLabel.setFont (modulusLabel.getFont().deriveFont (44.0f));
		centerRadioPanel.add(modulusLabel);
	
		// BOTTOM RADIO PANEL
		
		JPanel bottomRadioPanel = new JPanel();
		bottomRadioPanel.setBackground(Color.LIGHT_GRAY);
		
		JButton setButton = new JButton("Set Favorite");
	    setButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	if (car.radio.getIsOn()) {
	        		car.radio.toggleSetIsActive();
	        		if (car.radio.getSetIsActive()) {
	        			System.out.println("Set favorite activated.");
	        		} else {
	        			System.out.println("Set favorite cancelled.");
	        		}
		            
	        	} 
	         }          
	    });
				
		JButton fav1Button = new JButton(" 1 ");
	    fav1Button.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	if (car.radio.getIsOn()) {
	        		if (car.radio.getSetIsActive()) {
		        		car.currentDriver.setFav(car.radio.getIsAm(), 1, car.radio.getCurrentStation());
		        		car.radio.setUserFavorites(car.currentDriver);
		        		car.radio.toggleSetIsActive();
		        		System.out.println("New favorite 1 saved.");
		        	} else {
		        		car.radio.goToFav(1);
			            stationLabel.setText(Double.toString(car.radio.currentStation.getStation()));
			            System.out.println("Select favorite 1.");
		        	} 
	        	}	  
	         }          
	    });
		
		JButton fav2Button = new JButton(" 2 ");
	    fav2Button.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 if (car.radio.getIsOn()) {
	        		 if (car.radio.getSetIsActive()) {
	 		        	car.currentDriver.setFav(car.radio.getIsAm(), 2, car.radio.getCurrentStation());
	 		        	car.radio.setUserFavorites(car.currentDriver);
	 		        	car.radio.toggleSetIsActive();
	 		        	System.out.println("New favorite 2 saved.");
	 		         } else {
	 		        	car.radio.goToFav(2);
	 			        stationLabel.setText(Double.toString(car.radio.currentStation.getStation()));
	 			        System.out.println("Select favorite 2.");
	 		         }  
	        	 }
	         }          
	      });
		
		JButton fav3Button = new JButton(" 3 ");
	    fav3Button.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 if (car.radio.getIsOn()) {
	        		 if (car.radio.getSetIsActive()) {
	 			        car.currentDriver.setFav(car.radio.getIsAm(), 3, car.radio.getCurrentStation());
	 			        car.radio.setUserFavorites(car.currentDriver);
	 			        car.radio.toggleSetIsActive();
	 			        System.out.println("New favorite 3 saved.");
	 			     } else {
	 			    	 car.radio.goToFav(3);
	 				     stationLabel.setText(Double.toString(car.radio.currentStation.getStation()));
	 				     System.out.println("Select favorite 3.");
	 			     } 
	        	 }
	         }          
	      });
	    
	    bottomRadioPanel.add(setButton);
	    bottomRadioPanel.add(fav1Button);
	    bottomRadioPanel.add(fav2Button);
		bottomRadioPanel.add(fav3Button);
		
		//  RIGHT RADIO PANEL
		JPanel rightRadioPanel = new JPanel();
		rightRadioPanel.setBackground(Color.LIGHT_GRAY);
		
		// Add radioPanels to the main radioPanel.
		radioPanel.add("North", topRadioPanel);
		radioPanel.add("West", leftRadioPanel);
		radioPanel.add("South", bottomRadioPanel);
		radioPanel.add("Center", centerRadioPanel);
		radioPanel.add("East", rightRadioPanel);
		
	} // end setupRadioPanel
		
	/******************************************/
	/************  PHONE PANEL  ***************/
	/******************************************/
	
	private void setupPhonePanel()
	{
		// leftPhonePanel is the dial pad
		JPanel dialpadPanel = new JPanel();
		dialpadPanel.setLayout(new GridLayout(4,3));
		
		// The for loop makes buttons 1-9, #, 0, *
		int i;
		for(i = 1; i < 13; i++)
		{
			final String input = Integer.toString(i);
			JButton button;
			
			// creates the * button
			if(i == 10)
			{
				button = new JButton("*");
				button.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						car.phone.dialNumber("*");
					}
				});
			}
			
			// creates the 0 button
			else if(i == 11)
			{
				button = new JButton("0");
				button.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						car.phone.dialNumber("0");
					}
				});
			}
			
			// creates the # button
			else if(i == 12)
			{
				button = new JButton("#");
				button.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						car.phone.dialNumber("#");
					}
				});
			}
			
			// creates buttons 1-9
			else
			{
				button = new JButton(Integer.toString(i));
				button.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						car.phone.dialNumber(input);
					}
				});
			}
			button.setBackground(Color.white);
			dialpadPanel.add(button);
		}
		
		JPanel leftPhonePanel = new JPanel(new BorderLayout());
		JPanel emptyPanel1 = new JPanel();
		emptyPanel1.setBackground(Color.white);
		JPanel emptyPanel2 = new JPanel();
		emptyPanel2.setBackground(Color.white);
		JPanel emptyPanel3= new JPanel();
		emptyPanel3.setBackground(Color.white);
		JPanel emptyPanel4 = new JPanel();
		emptyPanel4.setBackground(Color.white);
		
		leftPhonePanel.add("Center", dialpadPanel);
		leftPhonePanel.add("North", emptyPanel1);
		leftPhonePanel.add("South", emptyPanel2);
		leftPhonePanel.add("West", emptyPanel3);
		leftPhonePanel.add("East", emptyPanel4);
		
		phonePanel.add("West", leftPhonePanel);	
	}
		
	/******************************************/
	/*************  MAP PANEL  ****************/
	/******************************************/
	
	private void setupMapPanel() {
		int routeDistance = (int)car.map.getCurrentRoute().getRouteDistance();
		mapSlider = new JSlider(0, routeDistance, 0);
		setSliderSpacing(routeDistance);
		mapSlider.setPaintLabels(true);
		mapSlider.setEnabled(false);
		mapPanel.add("Center", mapSlider);
		
		
		JComboBox<String> routeSelector = new JComboBox<String>(car.map.getRouteList());
		mapPanel.add("North", routeSelector);
		routeSelector.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
					@SuppressWarnings("unchecked")
					JComboBox<String> cb = (JComboBox<String>)e.getSource();
					int routeIndex = cb.getSelectedIndex();
					if (car.getCurrentSpeed() > 0) {
						routeSelector.setSelectedIndex(car.map.getRouteIndex(car.map.getCurrentRoute()));
						System.out.println("Can not change routes while driving");
					} else {
						car.map.setCurrentRoute(routeIndex);
						int routeDistance = (int)car.map.getCurrentRoute().getRouteDistance();
						mapSlider.setMaximum(routeDistance);
						setSliderSpacing(routeDistance);		
					}
			}
		});
	}
	
	
	private void setSliderSpacing(int routeDistance) {
			mapSlider.setMajorTickSpacing((int)car.map.getCurrentRoute().getRouteDistance() / 4);
			mapSlider.setLabelTable(mapSlider.createStandardLabels(routeDistance / 4));
			
			/* Convert integer labels to decimal -- Snippet adapted from from 
			 * http://stackoverflow.com/questions/1125619/change-displayable-labels-for-a-jslider
			 */
			@SuppressWarnings("unchecked")
			Enumeration<Integer> mapLabels = mapSlider.getLabelTable().keys();
		    while (mapLabels.hasMoreElements()) {
		        Integer i = mapLabels.nextElement();
		        JLabel label = (JLabel) mapSlider.getLabelTable().get(i);
		        label.setText(String.valueOf(dfMap.format((double)i/100.0)));
		        label.setSize(50, 20);
		    }
			
	}
	
	// An executable to be run by the event dispatch thread to update a Swing GUI component.
	Runnable updateSliderPosition = new Runnable () {
		public void run() {
			mapSlider.setValue((int)car.map.getCurrentRoute().getDistanceIntoRoute());
		}
	};	
	
	/******************************************/
	/***********  ANALYTICS PANEL  ************/
	/******************************************/
	
	private void setupAnalyticsPanel() {
		JPanel leftAnalytics = new JPanel();
		leftAnalytics.setLayout(new BoxLayout(leftAnalytics, 1));
		leftAnalytics.setBackground(Color.white);
		
		JPanel centerAnalytics = new JPanel();
		centerAnalytics.setLayout(new BoxLayout(centerAnalytics, 1));
		centerAnalytics.setBackground(Color.LIGHT_GRAY);
		
		JPanel rightAnalytics= new JPanel();
		rightAnalytics.setLayout(new BoxLayout(rightAnalytics, 1));
		rightAnalytics.setBackground(Color.white);
		
		// Left Analytics (Labels)
		
		JLabel categoryTitle = new JLabel("   STATISTICS   ");
		categoryTitle.setFont (categoryTitle.getFont().deriveFont (18.0f));
		leftAnalytics.add(categoryTitle);
		
		JLabel milesLabel = new JLabel("Miles: ");
		leftAnalytics.add(milesLabel);
		
		JLabel timeLabel = new JLabel("Time: ");
		leftAnalytics.add(timeLabel);
		
		JLabel avgSpeedLabel = new JLabel("Avg Speed: ");
		leftAnalytics.add(avgSpeedLabel);
		
		JLabel maxSpeedLabel = new JLabel("Max Speed: ");
		leftAnalytics.add(maxSpeedLabel);
		
		JLabel mpgLabel = new JLabel("Fuel used: ");
		leftAnalytics.add(mpgLabel);
		
		JLabel radioTimeLabel = new JLabel("RadioTime: ");
		leftAnalytics.add(radioTimeLabel);
		
		JLabel phoneTimeLabel = new JLabel("PhoneTime: ");
		leftAnalytics.add(phoneTimeLabel);
		
		// Center Analytics (Driver)
		
		JLabel driverTitle = new JLabel("   " + car.currentDriver.toString().toUpperCase() + "   ");
		driverTitle.setFont (driverTitle.getFont().deriveFont (18.0f));
		centerAnalytics.add(driverTitle);
		
		driverMiles = new JLabel("");
		centerAnalytics.add(driverMiles);
				
		driverTime = new JLabel("");
		centerAnalytics.add(driverTime);
				
		driverAvgSpeed = new JLabel("");
		centerAnalytics.add(driverAvgSpeed);
				
		driverMaxSpeed = new JLabel("");
		centerAnalytics.add(driverMaxSpeed);
				
		driverFuelUsed = new JLabel("");
		centerAnalytics.add(driverFuelUsed);
				
		driverRadioTime = new JLabel("");
		centerAnalytics.add(driverRadioTime);
		
		driverPhoneTime = new JLabel("");
		centerAnalytics.add(driverPhoneTime);
		
		// Right Analytics (Session)
		
		JLabel sessionTitle = new JLabel("   SESSION   ");
		sessionTitle.setFont (driverTitle.getFont().deriveFont (18.0f));
		rightAnalytics.add(sessionTitle);
				
		sessionMiles = new JLabel("");
		rightAnalytics.add(sessionMiles);
				
		sessionTime = new JLabel("");
		rightAnalytics.add(sessionTime);
				
		sessionAvgSpeed = new JLabel("");
		rightAnalytics.add(sessionAvgSpeed);
				
		sessionMaxSpeed = new JLabel("");
		rightAnalytics.add(sessionMaxSpeed);
				
		sessionFuelUsed = new JLabel("");
		rightAnalytics.add(sessionFuelUsed);
				
		sessionRadioTime = new JLabel("");
		rightAnalytics.add(sessionRadioTime);
		
		sessionPhoneTime = new JLabel("");
		rightAnalytics.add(sessionPhoneTime);
	
		analyticsPanel.add("West", leftAnalytics);
		analyticsPanel.add("Center", centerAnalytics);
		analyticsPanel.add("East", rightAnalytics);
	
	}

	public void updateAnalyticsText() {
		driverMiles.setText(" " + dfShort.format(car.currentDriver.getDistanceDriven())  + " Mi");
		driverTime.setText(" " + Integer.toString( car.currentDriver.getTimeDriven() ) + " Sec");
		driverAvgSpeed.setText(" " + dfShort.format( car.currentDriver.getAverageSpeed() ) + " Mph");
		driverMaxSpeed.setText(" " + Integer.toString( car.currentDriver.getMaxSpeed() ) + " Mph");
		driverFuelUsed.setText(" " + dfShort.format( car.currentDriver.getFuelUsed() ) + " Gal");
		driverRadioTime.setText(" " + Integer.toString( car.currentDriver.getTotalRadioTime() ) + " Sec");
		driverPhoneTime.setText(" " + Integer.toString( car.currentDriver.getTotalPhoneTime() ) + " Sec");
		
		sessionMiles.setText(" " + dfShort.format(car.currentSession.getDistanceDriven())  + " Mi");
		sessionTime.setText(" " + Integer.toString( car.currentSession.getTimeDriven() ) + " Sec");
		sessionAvgSpeed.setText(" " + dfShort.format( car.currentSession.getAverageSpeed() ) + " Mph");
		sessionMaxSpeed.setText(" " + Integer.toString( car.currentSession.getMaxSpeed() ) + " Mph");
		sessionFuelUsed.setText(" " + dfShort.format( car.currentSession.getFuelUsed() ) + " Gal");
		sessionRadioTime.setText(" " + Integer.toString( car.currentSession.getRadioTime() ) + " Sec");
		sessionPhoneTime.setText(" " + Integer.toString( car.currentSession.getPhoneTime() ) + " Sec");	
	}

	/******************************************/
	/*********  MAIN CAR CONTROLLER  **********/
	/******************************************/
	
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
			 currentSpeed.setText(car.coast() + " MPH | ");
			 sessionMileage.setText("Session: " + dfShort.format(car.currentSession.getDistanceDriven()) + " miles ");
			 totalMileage.setText("| " + dfShort.format(car.getOdometer()) + " miles | ");
			 updateAnalyticsText();
			 
			 deltaDistance = ((double)car.getCurrentSpeed() / 60 / 60);
			 car.incrementOdometer(deltaDistance);

			 car.map.getCurrentRoute().incrementDistanceIntoRoute(deltaDistance * 100);
			 
			 car.currentDriver.incrementTimeDriven();
			 car.currentSession.incrementTimeDriven();
			 
			 car.currentDriver.incrementDistanceDriven(deltaDistance);
			 car.currentSession.incrementDistanceDriven(deltaDistance);
			 
			 car.currentDriver.updateMaxSpeed(car.getCurrentSpeed());
			 car.currentSession.updateMaxSpeed(car.getCurrentSpeed());
			 
			 if (car.radio.getIsOn()) {
				 car.currentDriver.incrementRadioTime();
				 car.currentSession.incrementRadioTime();
			 }

			 // Asynchronously update the map position slider
			 SwingUtilities.invokeLater(updateSliderPosition);
 
		  }
		},begin, timeinterval);
	}

}
