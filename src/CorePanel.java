import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class CorePanel extends JPanel{
	
	private JButton powerButton, gasButton, brakeButton, 
	refuelButton, loginButton;
	private Border bevelledBorder = BorderFactory.createRaisedBevelBorder();
	private final float coreFontSize = 16.0f;
	private Car car;
	private GuiManager parentGuiManager;
	
	public CorePanel(Car car, GuiManager guiManager) {
		this.car = car;
		parentGuiManager = guiManager;
		setLayout(new GridLayout(1,6));
		setBackground(Color.DARK_GRAY);
		
		loginButton = makeCoreButton("Login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!car.getIsOn()) {
					car.driverManager.currentDriver.saveSession(car.currentSession);
					car.login();
					resetApps();
					guiManager.appPanel.phonePanel.refreshContactList(car);
				}
				else {
					JOptionPane.showMessageDialog(guiManager.mainFrame,
							"Car must be turned off to change drivers.",
							"Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}          
		});
		
		powerButton = makeCoreButton("ON");
		powerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				car.togglePower();
				if (car.getIsOn() && car.getCurrentSpeed() == 0) {
					guiManager.appPanel.appLayout.show(guiManager.appPanel, "RADIOPANEL");
					powerButton.setText("OFF");
					System.out.println("Engine turned on.");
					car.runLoop(guiManager);
				} else if (car.getCurrentSpeed() == 0) {
					car.timer.cancel();
					car.timer.purge();
					resetApps();
					guiManager.appPanel.appLayout.show(guiManager.appPanel, "WELCOMEPANEL");
					powerButton.setText("ON");
					System.out.println("Engine turned off.");
				} else {
					JOptionPane.showMessageDialog(guiManager.mainFrame,
							"Can't turn off engine while moving.",
							"Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}          
		});
		
		refuelButton = makeCoreButton("Refuel");
		refuelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!car.refuel()) {
					JOptionPane.showMessageDialog(guiManager.mainFrame,
							"Engine must be off to refuel.",
							"Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}          
		});
		
		brakeButton = makeCoreButton("BRAKE");
		brakeButton.setForeground(Color.RED);
		brakeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				car.decelerate();
			}          
		});
		
		gasButton = makeCoreButton("GAS");
		gasButton.setForeground(Color.GREEN);
		gasButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				car.accelerate();	
			}          
		});
		
		add(loginButton);
		add(powerButton);
		add(refuelButton);
		add(brakeButton);
		add(gasButton);
	}
	
	private JButton makeCoreButton(String label) {
		JButton button = new JButton(label);
		button.setFont(button.getFont().deriveFont(coreFontSize));
		button.setBackground(Color.DARK_GRAY);
		button.setForeground(Color.WHITE);
		button.setBorder(bevelledBorder);
		return button;
	}
	
	public void resetApps() {
		car.phone.resetNumberBeingDialed();
		parentGuiManager.appPanel.phonePanel.dialNumField.setText("");
	}

}
