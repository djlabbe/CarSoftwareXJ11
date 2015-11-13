/*Timer code structure found on Stack-overflow*/

/*Main screen consists of the full window JFrame which contains a 5 JPanels arranged in Border Layout.
 *The center (main) panel is called the appPanel which itself has a CardLayout meaning it behaves like a stack
 *of cards. The four sub-panels are radioPanel, phonePanel, mapPanel, and analyticsPanel. Each can be made to be
 *the active panel within the appPanel.*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Timer;
import java.util.TimerTask;

public class GuiManager {

	private Car car;
	private JFrame 	mainFrame;

	private JPanel 	labelPanel, navPanel, corePanel, appPanel, emptyPanel,
	welcomePanel, radioPanel, phonePanel, mapPanel, analyticsPanel, centerRadioPanel;

	private JLabel 	sessionMileage, totalMileage, currentSpeed, currentFuel, stationLabel, modulusLabel, 
	radioVolumeLabel, driverMiles, driverTime, driverAvgSpeed, driverMaxSpeed, driverFuelUsed, 
	driverRadioTime, driverPhoneTime, sessionMiles, sessionTime, sessionAvgSpeed, sessionMaxSpeed, 
	sessionFuelUsed, sessionRadioTime, sessionPhoneTime, phoneTimeLabel;

	private JButton radioButton, phoneButton, mapButton, statsButton, powerButton, gasButton, brakeButton, 
	refuelButton, loginButton, endButton, callButton;
	
	private JTextField dialNumField;

	private JList<Contact> contactText;
	private JTextField nameText, numText;
	private JDialog contactDialog;
	private DefaultListModel<Contact> contactList;
	private ArrayList<Contact> contactArray;

	private JSlider mapSlider;

	private DecimalFormat dfOne = new DecimalFormat("#00");
	private DecimalFormat dfTwo = new DecimalFormat("000");
	private DecimalFormat dfMap = new DecimalFormat("###.00");
	private DecimalFormat dfShort = new DecimalFormat("###0.00");

	private Timer timer;
	private double deltaDistance;
	private final float coreFontSize = 16.0f;
	
	private CardLayout appLayout = new CardLayout();
	Border bevelledBorder = BorderFactory.createRaisedBevelBorder();

	// GUI operates for a specific car object which is passed in on GUI initialization.
	public GuiManager() {
		try {
			UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		car = new Car();
		car.login();
		prepareGUI();
	}

	// Make a new JFrame (main window) and center it.
	private void prepareGUI() {

		mainFrame = new JFrame("XJ-11");
		mainFrame.setBackground(Color.WHITE);
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
		labelPanel.setBackground(Color.DARK_GRAY);
		labelPanel.setBorder(bevelledBorder);

		navPanel = new JPanel();
		navPanel.setLayout(new GridLayout(4, 1));
		navPanel.setBackground(Color.DARK_GRAY);

		appPanel = new JPanel();
		appPanel.setLayout(appLayout);
		appPanel.setVisible(true);

		corePanel = new JPanel(new GridLayout(1,6));
		corePanel.setBackground(Color.DARK_GRAY);

		emptyPanel = new JPanel();
		emptyPanel.setBackground(Color.DARK_GRAY);
		emptyPanel.setBorder(bevelledBorder);

		// Add all panels to the main frame
		mainFrame.add("North", labelPanel);
		mainFrame.add("West", navPanel);
		mainFrame.add("South", corePanel);
		mainFrame.add("Center", appPanel);
		mainFrame.add("East", emptyPanel);

		/* The appPanel uses a "CardLayout" which works like a stack of playing cards,
		 * appPanel will contain 5 other panels which can individually be made active.
		 */
		
		welcomePanel = new JPanel(new GridBagLayout());
		welcomePanel.setBackground(Color.WHITE);
		
		radioPanel = new JPanel(new BorderLayout());
		radioPanel.setBackground(Color.LIGHT_GRAY);
		

		phonePanel = new JPanel(new BorderLayout());
		phonePanel.setBackground(Color.WHITE);

		mapPanel = new JPanel(new BorderLayout());

		analyticsPanel = new JPanel(new BorderLayout());
		analyticsPanel.setBackground(Color.LIGHT_GRAY);
		
		appPanel.add(welcomePanel, "WELCOMEPANEL");
		appPanel.add(radioPanel, "RADIOPANEL");
		appPanel.add(phonePanel, "PHONEPANEL");
		appPanel.add(mapPanel, "MAPPANEL");
		appPanel.add(analyticsPanel, "ANALYTICSPANEL");
		
		// Setup the contents of each panel.
		setupLabelPanel();
		setupNavPanel();
		setupCorePanel();
		setupWelcomePanel();
		setupRadioPanel();
		setupPhonePanel();
		setupMapPanel();
		setupAnalyticsPanel();

		sessionMileage.setFont (sessionMileage.getFont().deriveFont (12.0f));
		sessionMileage.setText("Session: " + dfShort.format(car.currentSession.getDistanceDriven()) + " miles ");

		totalMileage.setFont (totalMileage.getFont().deriveFont (30.0f));
		totalMileage.setText("| " + dfShort.format(car.getOdometer()) + " miles | ");

		currentSpeed.setFont (currentSpeed.getFont().deriveFont (30.0f));
		currentSpeed.setText(dfTwo.format(car.getCurrentSpeed()) + " MPH | ");

		currentFuel.setFont (currentFuel.getFont().deriveFont (12.0f));
		currentFuel.setText(dfOne.format(car.getFuelPercent()) + "% Fuel ");

		mainFrame.setVisible(true);
	}
	
	

	/******************************************/
	/*************  LABEL PANEL  ****************/
	/******************************************/
	
	private void setupLabelPanel() {

		sessionMileage = new JLabel("");
		sessionMileage.setForeground(Color.WHITE);
		totalMileage = new JLabel("");
		totalMileage.setForeground(Color.WHITE);
		currentSpeed = new JLabel("");
		currentSpeed.setForeground(Color.WHITE);
		currentFuel = new JLabel("");
		currentFuel.setForeground(Color.WHITE);

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
		radioButton.setFont(radioButton.getFont().deriveFont (18.0f));
		radioButton.setBackground(Color.DARK_GRAY);
		radioButton.setForeground(Color.WHITE);
		radioButton.setBorder(bevelledBorder);
		radioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cardLayout = (CardLayout)(appPanel.getLayout());
				cardLayout.show(appPanel, "RADIOPANEL");
				System.out.println("Show Radio");
			}          
		});

		phoneButton = new JButton("Phone");
		phoneButton.setFont(phoneButton.getFont().deriveFont (18.0f));
		phoneButton.setBackground(Color.DARK_GRAY);
		phoneButton.setForeground(Color.WHITE);
		phoneButton.setBorder(bevelledBorder);
		phoneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cardLayout = (CardLayout)(appPanel.getLayout());
				cardLayout.show(appPanel, "PHONEPANEL");
				System.out.println("Show Phone");
			}          
		});

		mapButton = new JButton("Map");
		mapButton.setFont(mapButton.getFont().deriveFont (18.0f));
		mapButton.setBackground(Color.DARK_GRAY);
		mapButton.setForeground(Color.WHITE);
		mapButton.setBorder(bevelledBorder);
		mapButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cardLayout = (CardLayout)(appPanel.getLayout());
				cardLayout.show(appPanel, "MAPPANEL");
				System.out.println("Show Map");
			}          
		});

		statsButton = new JButton("Stats");
		statsButton.setFont(statsButton.getFont().deriveFont (18.0f));
		statsButton.setBackground(Color.DARK_GRAY);
		statsButton.setForeground(Color.WHITE);
		statsButton.setBorder(bevelledBorder);
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
		loginButton.setFont(loginButton.getFont().deriveFont (coreFontSize));
		loginButton.setBorder(bevelledBorder);
		loginButton.setBackground(Color.DARK_GRAY);
		loginButton.setForeground(Color.WHITE);
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!car.getIsOn()) {
					// save the current session
					car.driverManager.currentDriver.saveSession(car.currentSession);
					// login new driver resets current session
					car.login();
					System.out.println("Loggin in new user...");
				}
				else {
					System.out.println("Car must be turned off to login new driver");
				}

			}          
		});

		powerButton = new JButton("ON");
		powerButton.setFont(powerButton.getFont().deriveFont (coreFontSize));
		powerButton.setBorder(bevelledBorder);
		powerButton.setBackground(Color.DARK_GRAY);
		powerButton.setForeground(Color.WHITE);
		powerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				car.togglePower();
				if (car.getIsOn() && car.getCurrentSpeed() == 0) {
					appLayout.show(appPanel, "RADIOPANEL");
					powerButton.setText("OFF");
					System.out.println("Engine turned on.");
					runLoop();
				} else if (car.getCurrentSpeed() == 0) {
					timer.cancel();
					timer.purge();
					appLayout.show(appPanel, "WELCOMEPANEL");
					powerButton.setText("ON");
					System.out.println("Engine turned off.");
				} else {
					System.out.println("Can't turn off engine while car is moving.");
				}
			}          
		});

		refuelButton = new JButton("Refuel");
		refuelButton.setFont(refuelButton.getFont().deriveFont (coreFontSize));
		refuelButton.setBorder(bevelledBorder);
		refuelButton.setBackground(Color.DARK_GRAY);
		refuelButton.setForeground(Color.WHITE);
		refuelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				car.refuel();
				currentFuel.setText(dfOne.format(car.getFuelPercent()) + "% Fuel ");
			}          
		});

		brakeButton = new JButton("BRAKE");
		brakeButton.setFont(brakeButton.getFont().deriveFont (coreFontSize));
		brakeButton.setBorder(bevelledBorder);
		brakeButton.setBackground(Color.DARK_GRAY);
		brakeButton.setForeground(Color.WHITE);
		brakeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentSpeed.setText(dfTwo.format(car.decelerate()) + " MPH | ");
			}          
		});

		gasButton = new JButton("GAS");
		gasButton.setFont(gasButton.getFont().deriveFont (coreFontSize));
		gasButton.setBorder(bevelledBorder);
		gasButton.setBackground(Color.DARK_GRAY);
		gasButton.setForeground(Color.WHITE);
		gasButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentSpeed.setText(dfTwo.format(car.accelerate()) + " MPH | ");
				currentFuel.setText(dfOne.format(car.getFuelPercent()) + "% Fuel ");
			}          
		});

		corePanel.add(loginButton);
		corePanel.add(powerButton);
		corePanel.add(refuelButton);
		corePanel.add(brakeButton);
		corePanel.add(gasButton);
	}
	
	/******************************************/
	/*************  MAP PANEL  ****************/
	/******************************************/

	private void setupWelcomePanel() {
		
		JPanel welcomeMessageHolder = new JPanel(new GridLayout(3,1));
		welcomeMessageHolder.setBackground(Color.WHITE);
		welcomePanel.add(welcomeMessageHolder);
		
		JLabel welcomeLabel = new JLabel ("WELCOME");
		welcomeLabel.setFont (welcomeLabel.getFont().deriveFont (45.0f));
		welcomeMessageHolder.add(welcomeLabel);
		
		JLabel filler = new JLabel ("TO THE");
		filler.setHorizontalAlignment(SwingConstants.CENTER);
		filler.setFont (filler.getFont().deriveFont (18.0f));
		welcomeMessageHolder.add(filler);
		
		JLabel xj11Label = new JLabel ("XJ-11");
		xj11Label.setHorizontalAlignment(SwingConstants.CENTER);
		xj11Label.setFont (xj11Label.getFont().deriveFont (35.0f));
		welcomeMessageHolder.add(xj11Label);
		
		
		
	};	

	/******************************************/
	/************  RADIO PANEL  ***************/
	/******************************************/

	private void setupRadioPanel() {

		// TOP RADIO PANEL

		JPanel topRadioPanel = new JPanel();
		topRadioPanel.setBackground(Color.LIGHT_GRAY);

		JButton radioPowerButton = new JButton("ON");
		radioPowerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				car.radio.togglePower();
				if (car.radio.getIsOn()) {
					modulusLabel.setVisible(true);
					updateStationLabel();
					radioVolumeLabel.setText(Integer.toString(car.radio.getVolume()));
					radioPowerButton.setText("OFF");
					System.out.println("Radio turned on.");
				} else {
					modulusLabel.setVisible(false);
					stationLabel.setText("OsirusXM");
					radioVolumeLabel.setText("-");
					radioPowerButton.setText("ON");
					System.out.println("Radio turned off.");
				}

			}          
		});

		JButton seekDownButton = new JButton(" << ");
		seekDownButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (car.radio.getIsOn()) {
					car.radio.seekDown();
					updateStationLabel();
				}
			}          
		});

		JButton seekUpButton = new JButton(" >> ");
		seekUpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (car.radio.getIsOn()) {
					car.radio.seekUp();
					updateStationLabel();
				}
			}          
		});

		JButton amFmButton = new JButton("AM | FM");
		amFmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (car.radio.getIsOn()) {
					car.radio.toggleMod();
					updateStationLabel();
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
		radioVolumeTitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		radioVolumeTitleLabel.setFont (radioVolumeTitleLabel.getFont().deriveFont (28.0f));

		JButton volumeUpButton = new JButton(" + ");
		volumeUpButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		volumeUpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (car.radio.getIsOn()) {
					car.radio.volUp();
					radioVolumeLabel.setText(Integer.toString(car.radio.getVolume()));
				} 
			}          
		});

		radioVolumeLabel = new JLabel("-");
		radioVolumeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		radioVolumeLabel.setFont (radioVolumeLabel.getFont().deriveFont (40.0f));

		JButton volumeDownButton = new JButton(" - ");
		volumeDownButton.setAlignmentX(Component.CENTER_ALIGNMENT);
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

		centerRadioPanel = new JPanel(new GridBagLayout());
		centerRadioPanel.setBackground(Color.LIGHT_GRAY);
		centerRadioPanel.setVisible(true);

		stationLabel = new JLabel();
		stationLabel.setFont (stationLabel.getFont().deriveFont (60.0f));
		stationLabel.setText("OsirusXM");
		centerRadioPanel.add(stationLabel);

		modulusLabel = new JLabel(car.radio.getModLabel());
		modulusLabel.setFont (modulusLabel.getFont().deriveFont (44.0f));
		modulusLabel.setVisible(false);
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
						car.driverManager.currentDriver.setFav(car.radio.getIsAm(), 1, car.radio.getCurrentStation());
						car.radio.setUserFavorites(car.driverManager.currentDriver);
						car.radio.toggleSetIsActive();
						System.out.println("New favorite 1 saved.");
					} else {
						car.radio.goToFav(1);
						updateStationLabel();
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
						car.driverManager.currentDriver.setFav(car.radio.getIsAm(), 2, car.radio.getCurrentStation());
						car.radio.setUserFavorites(car.driverManager.currentDriver);
						car.radio.toggleSetIsActive();
						System.out.println("New favorite 2 saved.");
					} else {
						car.radio.goToFav(2);
						updateStationLabel();
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
						car.driverManager.currentDriver.setFav(car.radio.getIsAm(), 3, car.radio.getCurrentStation());
						car.radio.setUserFavorites(car.driverManager.currentDriver);
						car.radio.toggleSetIsActive();
						System.out.println("New favorite 3 saved.");
					} else {
						car.radio.goToFav(3);
						updateStationLabel();
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

	public void updateStationLabel() {
		if (car.radio.getIsAm()) {
			stationLabel.setText(dfOne.format(car.radio.currentStation.getStation()));
		} else {
			stationLabel.setText(Double.toString(car.radio.currentStation.getStation()));
		}
	}

	/******************************************/
	/************  PHONE PANEL  ***************/
	/******************************************/

	private void setupPhonePanel()
	{
		// Phone panels
		JPanel leftPhonePanel = new JPanel(new BorderLayout());
		JPanel centerPhonePanel = new JPanel(new GridBagLayout());
		JPanel rightPhonePanel = new JPanel(new GridBagLayout());
		JPanel dialpadPanel = new JPanel(new GridLayout(4,3));
		GridBagConstraints gb = new GridBagConstraints();

		leftPhonePanel.setBackground(Color.LIGHT_GRAY);
		centerPhonePanel.setBackground(Color.LIGHT_GRAY);
		rightPhonePanel.setBackground(Color.LIGHT_GRAY);
		dialpadPanel.setBackground(Color.LIGHT_GRAY);
		
		final JLabel speakVolLabel;
		final JLabel micVolLabel;

		// TextField for the dialed number in the center panel
		dialNumField = new JTextField("", 8);
		dialNumField.setBackground(Color.WHITE);
		dialNumField.setFont(new Font("Arial", Font.PLAIN, 30));
		dialNumField.setHorizontalAlignment(SwingConstants.CENTER);
		dialNumField.setEditable(false);
		gb.weightx = 1;
		gb.weighty = 1;
		gb.gridx = 0;
		gb.gridy = 0;
		gb.insets = new Insets(10,10,10,10);
		gb.fill = GridBagConstraints.BOTH;
		centerPhonePanel.add(dialNumField, gb);


		// TextArea for all the contacts

		// Putting contacts into a list for the Jlist
		contactList = new DefaultListModel<Contact>();
		contactArray = car.driverManager.currentDriver.getContacts();
		if(contactArray != null){
			for(int i = 0; i < contactArray.size(); i++)
				contactList.addElement(contactArray.get(i));
		}
		contactList.addElement(new Contact());
		contactText = new JList<Contact>(contactList);
		contactText.setCellRenderer(new ListCellRenderer<Contact>(){
			public Component getListCellRendererComponent(JList<? extends Contact> list, Contact contact, int index, boolean isSelected, boolean cellHasFocus){
				String name = contact.getName();
				JLabel renderer = (JLabel) new DefaultListCellRenderer().getListCellRendererComponent(list, contact, index, isSelected, cellHasFocus);
				renderer.setText(name);
				return renderer;			
			}

		});
		contactText.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		contactText.setLayoutOrientation(JList.VERTICAL);
		gb.insets = new Insets(0,10,10,10);
		gb.gridy = 1;	
		gb.fill = GridBagConstraints.BOTH;
		contactText.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				car.phone.resetNumberBeingDialed();
				String contactNumber = contactText.getSelectedValue().getPhoneNumber();
				dialNumField.setText(contactNumber);
				car.phone.setContactActive(contactNumber);
			}
		});
		JScrollPane scrollContact = new JScrollPane(contactText);
		centerPhonePanel.add(scrollContact, gb);

		// Call Button
		callButton = new JButton("Call");
		callButton.setBackground(Color.green);
		gb.insets = new Insets(0,0,0,0);
		gb.gridheight = 1;
		gb.gridx = 0;
		gb.gridy = 0;
		callButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(car.phone.checkActiveCall() != true){
					car.phone.activateCall();
				}
			}
		});
		rightPhonePanel.add(callButton, gb);

		// End Button
		endButton = new JButton("End");
		endButton.setBackground(Color.red);
		gb.gridx = 1;
		gb.gridy = 0;
		endButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				car.phone.deactivateCall();
				car.driverManager.currentDriver.saveCall(car.phone.currentCall);
				car.currentSession.saveCall(car.phone.currentCall);
				car.phone.currentCall = null;
				car.phone.resetNumberBeingDialed();
				dialNumField.setText("");
			}
		});
		rightPhonePanel.add(endButton, gb);

		// Add Contact Button
		JButton addContactButton = new JButton("Add Contact");
		addContactButton.setBackground(Color.white);
		gb.fill = 0;
		gb.gridwidth = 2;
		gb.gridx = 0;
		gb.gridy = 1;
		addContactButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){

				System.out.println("Creating a contact.");

				// opens a window to create a new contact
				contactDialog = new JDialog();
				contactDialog.setResizable(false);
				contactDialog.setLocationRelativeTo(null);
				contactDialog.setTitle("Create a new Contact");
				contactDialog.setSize(300, 100);

				// Panel to fit inside dialog box
				JPanel contactPanel = new JPanel(new GridBagLayout());

				GridBagConstraints gb = new GridBagConstraints();

				JLabel nameLabel = new JLabel("Name:");
				nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
				gb.fill = GridBagConstraints.HORIZONTAL;
				gb.weightx = 1;
				gb.weighty = 1;
				gb.gridx = 0;
				gb.gridy = 0;
				contactPanel.add(nameLabel, gb);

				nameText = new JTextField("", 8);
				gb.gridx = 1;
				gb.ipadx = 150;
				contactPanel.add(nameText, gb);

				JLabel numLabel = new JLabel("Number: ");
				numLabel.setHorizontalAlignment(SwingConstants.CENTER);
				gb.ipadx = 0;
				gb.gridx = 0;
				gb.gridy = 1;
				gb.gridwidth = 1;
				contactPanel.add(numLabel, gb);

				numText = new JTextField("", 8);
				gb.ipadx = 150;
				gb.gridx = 1;
				contactPanel.add(numText, gb);

				JButton enterButton = new JButton("Done");
				gb.ipadx = 0;
				gb.gridx = 0;
				gb.gridy = 2;
				gb.gridwidth = 3;
				enterButton.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						String numToSave = "";
						String enteredNum = numText.getText().trim();
						for (int i = 0; i < enteredNum.length(); i++) {
							if ((i == 3 || i == 6) && enteredNum.charAt(3) != '-' && enteredNum.charAt(7) != '-') {
								numToSave += "-";
							}
							numToSave += enteredNum.charAt(i);
						}
						contactArray.add(car.driverManager.currentDriver.createContact(nameText.getText().trim(), numToSave));
						contactList.addElement(contactArray.get(contactArray.size()-1));
						contactDialog.dispose();
						System.out.println("Contact created.");
					}
				});
				contactPanel.add(enterButton, gb);
				contactDialog.add(contactPanel);
				contactDialog.setVisible(true);

			}
		});
		rightPhonePanel.add(addContactButton, gb);


		// Panels for the volume controls
		JPanel speakerPanel = new JPanel(new GridBagLayout());
		speakerPanel.setBackground(Color.LIGHT_GRAY);
		JPanel micPanel = new JPanel(new GridBagLayout());
		micPanel.setBackground(Color.LIGHT_GRAY);

		// Speaker volume buttons	
		speakVolLabel = new JLabel("Speaker Vol: " + car.phone.getSpeakVol());
		gb.gridx = 0;
		gb.gridy = 1;
		gb.gridwidth = 2;
		speakerPanel.add(speakVolLabel, gb);

		JButton speakUpBut = new JButton("+");
		speakUpBut.setBackground(Color.white);
		gb.gridwidth = 1;
		gb.gridy = 0;
		gb.gridx = 1;
		speakUpBut.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				car.phone.speakVolUp();
				speakVolLabel.setText("Speaker Vol: " + car.phone.getSpeakVol());
			}
		});
		speakerPanel.add(speakUpBut, gb);

		JButton speakDownBut = new JButton("-");
		speakDownBut.setBackground(Color.white);
		gb.gridx = 0;
		speakDownBut.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				car.phone.speakVolDown();
				speakVolLabel.setText("Speaker Vol: " + car.phone.getSpeakVol());
			}
		});
		speakerPanel.add(speakDownBut, gb);

		// Microphone volume buttons
		micVolLabel = new JLabel("Mic Vol: " + car.phone.getMicVol());
		gb.gridx = 0;
		gb.gridy = 1;
		gb.gridwidth = 2;
		micPanel.add(micVolLabel, gb);

		JButton micVolUpBut = new JButton("+");
		micVolUpBut.setBackground(Color.white);
		gb.gridwidth = 1;
		gb.gridx = 1;
		gb.gridy = 0;
		micVolUpBut.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				car.phone.micVolUp();
				micVolLabel.setText("Mic Vol: " + car.phone.getMicVol());
			}
		});
		micPanel.add(micVolUpBut, gb);

		JButton micVolDownBut = new JButton("-");
		micVolDownBut.setBackground(Color.white);
		gb.gridx = 0;
		micVolDownBut.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				car.phone.micVolDown();
				micVolLabel.setText("Mic Vol: " + car.phone.getMicVol());
			}
		});
		micPanel.add(micVolDownBut, gb);

		gb.gridwidth = 1;
		gb.gridy = 2;
		gb.gridx = 0;
		gb.gridwidth = 2;
		rightPhonePanel.add(micPanel, gb);


		gb.gridx = 0;
		gb.gridy = 3;
		rightPhonePanel.add(speakerPanel, gb);


		// Time Label
		phoneTimeLabel = new JLabel("Time: 0:00");
		gb.gridy = 4;
		rightPhonePanel.add(phoneTimeLabel, gb);

		// The for loop makes buttons 1-9, #, 0, *
		int j;
		for(j = 1; j < 13; j++)
		{
			final String input = Integer.toString(j);
			JButton button;

			// creates the * button
			if(j == 10)
			{
				button = new JButton("*");
				button.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						car.phone.dialNumber("*");
						dialNumField.setText(car.phone.getNumberDialed());
					}
				});
			}

			// creates the 0 button
			else if(j == 11)
			{
				button = new JButton("0");
				button.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						car.phone.dialNumber("0");
						dialNumField.setText(car.phone.getNumberDialed());
					}
				});
			}

			// creates the # button
			else if(j == 12)
			{
				button = new JButton("#");
				button.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						car.phone.dialNumber("#");
						dialNumField.setText(car.phone.getNumberDialed());
					}
				});
			}

			// creates buttons 1-9
			else
			{
				button = new JButton(Integer.toString(j));
				button.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						car.phone.dialNumber(input);
						dialNumField.setText(car.phone.getNumberDialed());
					}
				});
			}
			button.setBackground(Color.WHITE);
			button.setFont(new Font("Courier", Font.PLAIN, 15));
			dialpadPanel.add(button);
		}

		// empty panels to insert into the leftPhonePanel
		JPanel emptyPanel1 = new JPanel();
		emptyPanel1.setBackground(Color.LIGHT_GRAY);
		JPanel emptyPanel2 = new JPanel();
		emptyPanel2.setBackground(Color.LIGHT_GRAY);
		JPanel emptyPanel3= new JPanel();
		emptyPanel3.setBackground(Color.LIGHT_GRAY);
		JPanel emptyPanel4 = new JPanel();
		emptyPanel4.setBackground(Color.LIGHT_GRAY);

		// adding the empty panels to leftphonepanel
		leftPhonePanel.add("Center", dialpadPanel);
		leftPhonePanel.add("North", emptyPanel1);
		leftPhonePanel.add("South", emptyPanel2);
		leftPhonePanel.add("West", emptyPanel3);
		leftPhonePanel.add("East", emptyPanel4);			

		// adding all the different phone panels to main phone panel
		phonePanel.add(leftPhonePanel, BorderLayout.WEST);
		phonePanel.add(centerPhonePanel, BorderLayout.CENTER);
		phonePanel.add(rightPhonePanel, BorderLayout.EAST);

	}

	/******************************************/
	/*************  MAP PANEL  ****************/
	/******************************************/

	private void setupMapPanel() {
		int routeDistance = (int)car.map.getCurrentRoute().getRouteDistance();
		mapSlider = new JSlider(0, routeDistance, 0);
		setSliderSpacing(routeDistance);
		mapSlider.setBackground(Color.WHITE);
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
		JLabel driverTitle = new JLabel("   " + car.driverManager.currentDriver.toString().toUpperCase() + "   ");
		driverTitle.setFont (driverTitle.getFont().deriveFont (18.0f));
		driverTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		leftAnalytics.add(driverTitle);

		driverMiles = new JLabel("");
		driverMiles.setAlignmentX(Component.CENTER_ALIGNMENT);
		leftAnalytics.add(driverMiles);

		driverTime = new JLabel("");
		driverTime.setAlignmentX(Component.CENTER_ALIGNMENT);
		leftAnalytics.add(driverTime);

		driverAvgSpeed = new JLabel("");
		driverAvgSpeed.setAlignmentX(Component.CENTER_ALIGNMENT);
		leftAnalytics.add(driverAvgSpeed);

		driverMaxSpeed = new JLabel("");
		driverMaxSpeed.setAlignmentX(Component.CENTER_ALIGNMENT);
		leftAnalytics.add(driverMaxSpeed);

		driverFuelUsed = new JLabel("");
		driverFuelUsed.setAlignmentX(Component.CENTER_ALIGNMENT);
		leftAnalytics.add(driverFuelUsed);

		driverRadioTime = new JLabel("");
		driverRadioTime.setAlignmentX(Component.CENTER_ALIGNMENT);
		leftAnalytics.add(driverRadioTime);

		driverPhoneTime = new JLabel("");
		driverPhoneTime.setAlignmentX(Component.CENTER_ALIGNMENT);
		leftAnalytics.add(driverPhoneTime);

		JButton driverCallLogBtn = new JButton("Call Log");
		driverCallLogBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		driverCallLogBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(mainFrame,
						car.driverManager.currentDriver.displayCallHistory());
			}          
		});
		leftAnalytics.add(driverCallLogBtn);

		// Display full session history for current driver.
		JButton sessionHistoryBtn = new JButton("Session History");
		sessionHistoryBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		sessionHistoryBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(mainFrame,
						car.driverManager.currentDriver.displaySessionHistory());
			}          
		});
		leftAnalytics.add(sessionHistoryBtn);


		// Center Analytics (Labels)

		JLabel categoryTitle = new JLabel("STATS");
		categoryTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		categoryTitle.setFont (categoryTitle.getFont().deriveFont (18.0f));
		centerAnalytics.add(categoryTitle);

		JLabel milesLabel = new JLabel("Distance Travelled (Miles)");
		milesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		centerAnalytics.add(milesLabel);

		JLabel timeLabel = new JLabel("Time Active (Seconds)");
		timeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		centerAnalytics.add(timeLabel);

		JLabel avgSpeedLabel = new JLabel("Average Speed (Miles / Hour)");
		avgSpeedLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		centerAnalytics.add(avgSpeedLabel);

		JLabel maxSpeedLabel = new JLabel("Maximum Speed (Miles / Hour)");
		maxSpeedLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		centerAnalytics.add(maxSpeedLabel);

		JLabel mpgLabel = new JLabel("Fuel Used (Gallons)");
		mpgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		centerAnalytics.add(mpgLabel);

		JLabel radioTimeLabel = new JLabel("Radio Time (Seconds)");
		radioTimeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		centerAnalytics.add(radioTimeLabel);

		JLabel phoneTimeLabel = new JLabel("Phone Time (Seconds)");
		phoneTimeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		centerAnalytics.add(phoneTimeLabel);


		// Right Analytics (Session)

		JLabel sessionTitle = new JLabel("SESSION");
		sessionTitle.setFont (driverTitle.getFont().deriveFont (18.0f));
		sessionTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		rightAnalytics.add(sessionTitle);

		sessionMiles = new JLabel("");
		sessionMiles.setAlignmentX(Component.CENTER_ALIGNMENT);
		rightAnalytics.add(sessionMiles);

		sessionTime = new JLabel("");
		sessionTime.setAlignmentX(Component.CENTER_ALIGNMENT);
		rightAnalytics.add(sessionTime);

		sessionAvgSpeed = new JLabel("");
		sessionAvgSpeed.setAlignmentX(Component.CENTER_ALIGNMENT);
		rightAnalytics.add(sessionAvgSpeed);

		sessionMaxSpeed = new JLabel("");
		sessionMaxSpeed.setAlignmentX(Component.CENTER_ALIGNMENT);
		rightAnalytics.add(sessionMaxSpeed);

		sessionFuelUsed = new JLabel("");
		sessionFuelUsed.setAlignmentX(Component.CENTER_ALIGNMENT);
		rightAnalytics.add(sessionFuelUsed);

		sessionRadioTime = new JLabel("");
		sessionRadioTime.setAlignmentX(Component.CENTER_ALIGNMENT);
		rightAnalytics.add(sessionRadioTime);

		sessionPhoneTime = new JLabel("");
		sessionPhoneTime.setAlignmentX(Component.CENTER_ALIGNMENT);
		rightAnalytics.add(sessionPhoneTime);

		JButton sessionCallLogBtn = new JButton("Call Log");
		sessionCallLogBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		sessionCallLogBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(mainFrame,
						car.currentSession.displayCallHistory());
			}          
		});
		rightAnalytics.add(sessionCallLogBtn);

		// Bottom Analytics Panel
		JButton viewDriversBtn = new JButton("Driver List");
		viewDriversBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(mainFrame,
						car.driverManager.displayKnownDrivers());
			}          
		});
		bottomAnalytics.add(viewDriversBtn);

		analyticsPanel.add("West", leftAnalytics);
		analyticsPanel.add("Center", centerAnalytics);
		analyticsPanel.add("East", rightAnalytics);
		analyticsPanel.add("South", bottomAnalytics);

	}

	public void updatePhone() {
		if (car.phone.checkActiveCall()) {
			endButton.setVisible(true);
			callButton.setVisible(false);
			car.driverManager.currentDriver.incrementTotalPhoneTime();
			car.currentSession.incrementPhoneTime();
			car.phone.incrementCurrentCallTime();
		} else {
			endButton.setVisible(false);
			callButton.setVisible(true);
		}
		phoneTimeLabel.setText("Duration: " + Integer.toString(car.phone.getCurrentCallTime()) + " sec");
	}


	public void updateAnalyticsPanel() {
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
				currentSpeed.setText(dfTwo.format(car.coast()) + " MPH | ");
				sessionMileage.setText("Session: " + dfShort.format(car.currentSession.getDistanceDriven()) + " miles ");
				totalMileage.setText("| " + dfShort.format(car.getOdometer()) + " miles | ");
				updateAnalyticsPanel();

				deltaDistance = ((double)car.getCurrentSpeed() / 60 / 60);

				car.incrementOdometer(deltaDistance);

				car.map.getCurrentRoute().incrementDistanceIntoRoute(deltaDistance * 100);

				car.driverManager.currentDriver.incrementTimeDriven();
				car.currentSession.incrementTimeDriven();

				car.driverManager.currentDriver.incrementDistanceDriven(deltaDistance);
				car.currentSession.incrementDistanceDriven(deltaDistance);

				car.driverManager.currentDriver.updateMaxSpeed(car.getCurrentSpeed());
				car.currentSession.updateMaxSpeed(car.getCurrentSpeed());

				updatePhone();

				if (car.radio.getIsOn()) {
					car.driverManager.currentDriver.incrementRadioTime();
					car.currentSession.incrementRadioTime();
				}

				// Asynchronously update the map position slider
				SwingUtilities.invokeLater(updateSliderPosition);

			}
		},begin, timeinterval);
	}

}
