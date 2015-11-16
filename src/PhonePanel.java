import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.Border;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class PhonePanel extends JPanel {

	private JPanel dialpadPanel;
	private JLabel	phoneTimeLabel;
	private JScrollPane scrollContact;
	private DefaultListModel<Contact> contactList;
	private ArrayList<Contact> contactArray;
	protected JTextField dialNumField;
	protected JList<Contact> contactText;
	protected JButton endButton, callButton;
	protected DecorativeButton addContactButton;
	private JDialog contactDialog;
	private JTextField nameText, numText;
	private Car car;

	public PhonePanel(final GuiManager guiManager) {
		this.car = guiManager.getCar();
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);

		// Phone panels
		DecorativePanel leftPhonePanel = new DecorativePanel();
		leftPhonePanel.setLayout(new BorderLayout());
		DecorativePanel centerPhonePanel = new DecorativePanel();
		centerPhonePanel.setLayout(new GridBagLayout());
		DecorativePanel rightPhonePanel = new DecorativePanel();
		rightPhonePanel.setLayout(new GridBagLayout());
		
		dialpadPanel = new JPanel(new GridLayout(4,3));
		GridBagConstraints gb = new GridBagConstraints();

		final JLabel speakVolLabel;
		final JLabel micVolLabel;

		// TextField for the dialed number in the center panel
		dialNumField = new JTextField("", 8);
		dialNumField.setBackground(Color.WHITE);
		dialNumField.setFont(new Font("Arial", Font.PLAIN, 23));
		dialNumField.setHorizontalAlignment(SwingConstants.CENTER);
		dialNumField.setEditable(false);
		gb.weightx = 1;
		gb.weighty = 1;
		gb.gridx = 0;
		gb.gridy = 0;
		gb.insets = new Insets(10,10,10,10);
		gb.fill = GridBagConstraints.BOTH;
		centerPhonePanel.add(dialNumField, gb);

		// Putting contacts into a list for the Jlist
		contactList = new DefaultListModel<Contact>();

		contactArray = car.phone.getContacts();

		if(contactArray.size() != 0){
			for(int i = 0; i < contactArray.size(); i++)
				contactList.addElement(contactArray.get(i));
		} else {
			contactList.addElement(new Contact());
		}

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
		

		scrollContact = new JScrollPane(contactText);
		centerPhonePanel.add(scrollContact, gb);

		// Call Button
		callButton = new JButton("Call");
		callButton.setBackground(Color.green);
		gb.insets = new Insets(10,10,0,10);
		gb.gridheight = 1;
		gb.gridx = 0;
		gb.gridy = 0;
		callButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(car.phone.checkActiveCall() != true){
					if (car.phone.getNumberBeingDialed() != ""){
						car.phone.activateCall();
					} else {
						JOptionPane.showMessageDialog(guiManager.mainFrame,
								"Enter a phone number.",
								"Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		rightPhonePanel.add(callButton, gb);

		// End Button
		endButton = new JButton("End");
		endButton.setBackground(Color.red);
		endButton.setVisible(false);
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
		addContactButton = new DecorativeButton("Add Contact");
		gb.insets = new Insets(10,10,0,10);
		gb.fill = 0;
		gb.gridwidth = 2;
		gb.gridx = 0;
		gb.gridy = 1;
		addContactButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){

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
				gb.insets = new Insets(0,0,0,0);
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
				enterButton.setForeground(Color.white);
				enterButton.setBackground(Color.darkGray);
				gb.ipadx = 0;
				gb.gridx = 0;
				gb.gridy = 2;
				gb.gridwidth = 3;
				enterButton.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						String numToSave = "";
						String enteredNum = numText.getText().trim();
						if (enteredNum.length() > 7) {
							for (int i = 0; i < enteredNum.length(); i++) {
								if ((i == 3 || i ==6 ) && enteredNum.charAt(3) != '-' && enteredNum.charAt(7) != '-') {
									numToSave += "-";
								}
								numToSave += enteredNum.charAt(i);
							}
						} else if (enteredNum.length() > 2) {
							for (int i = 0; i < enteredNum.length(); i++) {
								if ((i == 3) && enteredNum.charAt(3) != '-') {
									numToSave += "-";
								}
								numToSave += enteredNum.charAt(i);
							}
						} else {
							numToSave = enteredNum;
						}
						if(numToSave.isEmpty() || nameText.getText().isEmpty()){
							JOptionPane.showMessageDialog(guiManager.mainFrame,
									"Contact cannot be empty.",
									"Error",
									JOptionPane.ERROR_MESSAGE);
							contactDialog.dispose();
						} else {
							Contact newContact = new Contact (nameText.getText().trim(), numToSave);
							car.driverManager.currentDriver.addContact(newContact);
							car.refreshContacts();
							contactDialog.dispose();
							refreshContactList();
						}
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
		speakerPanel.setBackground(Color.DARK_GRAY);
		JPanel micPanel = new JPanel(new GridBagLayout());
		micPanel.setBackground(Color.DARK_GRAY);

		// Speaker volume buttons	
		speakVolLabel = new JLabel("Speaker Vol: " + car.phone.getSpeakVol());
		speakVolLabel.setForeground(Color.WHITE);
		speakVolLabel.setHorizontalAlignment(SwingConstants.CENTER);
		gb.gridx = 0;
		gb.gridy = 1;
		gb.gridwidth = 2;
		speakerPanel.add(speakVolLabel, gb);

		DecorativeButton speakUpBut = new DecorativeButton("+");
		gb.fill = GridBagConstraints.HORIZONTAL;
		gb.insets = new Insets(0,0,0,0);
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

		DecorativeButton speakDownBut = new DecorativeButton("-");
		gb.insets = new Insets(0,0,0,0);
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
		micVolLabel.setForeground(Color.WHITE);
		micVolLabel.setHorizontalAlignment(SwingConstants.CENTER);
		gb.insets = new Insets(0,0,0,0);
		gb.gridx = 0;
		gb.gridy = 1;
		gb.gridwidth = 2;
		micPanel.add(micVolLabel, gb);

		DecorativeButton micVolUpBut = new DecorativeButton("+");
		gb.insets = new Insets(0,0,0,0);
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

		DecorativeButton micVolDownBut = new DecorativeButton("-");
		gb.insets = new Insets(0,0,0,0);
		gb.gridx = 0;
		micVolDownBut.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				car.phone.micVolDown();
				micVolLabel.setText("Mic Vol: " + car.phone.getMicVol());
			}
		});
		micPanel.add(micVolDownBut, gb);

		gb.insets = new Insets(10,10,0,10);
		gb.gridwidth = 1;
		gb.gridy = 2;
		gb.gridx = 0;
		gb.gridwidth = 2;
		rightPhonePanel.add(micPanel, gb);		

		gb.insets = new Insets(0,10,0,10);
		gb.gridx = 0;
		gb.gridy = 3;
		rightPhonePanel.add(speakerPanel, gb);		

		// Time Label
		phoneTimeLabel = new JLabel("");
		phoneTimeLabel.setForeground(Color.WHITE);
		gb.gridy = 4;
		gb.insets = new Insets(0,25,0,0);
		rightPhonePanel.add(phoneTimeLabel, gb);

		for (int i = 1; i <= 9; i++) {
			makePhoneButton(car, Integer.toString(i));
		}
		makePhoneButton(car, "*");
		makePhoneButton(car, "0");
		makePhoneButton(car, "#");

		// empty panels to insert into the leftPhonePanel
		DecorativePanel emptyPanel1 = new DecorativePanel();

		DecorativePanel bottomPadPanel = new DecorativePanel();
		bottomPadPanel.setLayout(new GridBagLayout());

		DecorativeButton clearButton = new DecorativeButton("Clear");
		clearButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				car.phone.resetNumberBeingDialed();
				dialNumField.setText("");
			}
		});
		bottomPadPanel.add(clearButton);

		DecorativePanel emptyPanel2= new DecorativePanel();
		DecorativePanel emptyPanel3 = new DecorativePanel();

		// adding the border panels to leftphonepanel
		leftPhonePanel.add("Center", dialpadPanel);
		leftPhonePanel.add("North", emptyPanel1);
		leftPhonePanel.add("South", bottomPadPanel);
		leftPhonePanel.add("West", emptyPanel2);
		leftPhonePanel.add("East", emptyPanel3);			

		// adding all the different phone panels to main phone panel
		add(leftPhonePanel, BorderLayout.WEST);
		add(centerPhonePanel, BorderLayout.CENTER);
		add(rightPhonePanel, BorderLayout.EAST);
	}
	
	private JButton makePhoneButton(final Car car, final String btnLabel) {
		JButton newButton = new JButton(btnLabel);
		newButton.setBackground(Color.DARK_GRAY);
		newButton.setForeground(Color.WHITE);
		newButton.setFont(new Font("Courier", Font.PLAIN, 15));
		newButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				car.phone.dialNumber(btnLabel);
				dialNumField.setText(car.phone.getNumberBeingDialed());
			}
		});
		dialpadPanel.add(newButton);
		return newButton;
	}
	
	public void refresh() {

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

	public void refreshContactList() {

		contactList.clear();
		contactList.addElement(new Contact());
		contactArray = car.phone.getContacts();

		if (contactArray.size() > 0){
			for(int i = 0; i < contactArray.size(); i++)
				contactList.addElement(contactArray.get(i));
		}

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
		contactText.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				car.phone.resetNumberBeingDialed();
				String contactNumber = contactText.getSelectedValue().getPhoneNumber();
				dialNumField.setText(contactNumber);
				car.phone.setContactActive(contactNumber);
			}
		});
		scrollContact.setViewportView(contactText);
	}
	
}
