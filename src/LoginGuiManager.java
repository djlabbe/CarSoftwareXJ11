/* Maintain a list of registered drivers and their associated data and
 * handle the login and registration of drivers.
 */

/* Basic Login functionality code adapted from "3 Steps to Create Login Dialog in Java Swing"
 * http://www.zentut.com/java-swing/simple-login-dialog/ */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

@SuppressWarnings("serial")
public class LoginGuiManager extends JDialog{

	int userEnteredPin;

	private JTextField usernameEntry;
	private JPasswordField passwordEntry;
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	private JButton btnLogin;
	private JButton btnRegister;

	// Create the pop-up login frame
	public LoginGuiManager(Frame parent, DriverManager driverManager) {

		super(parent, "XJ-11 Login", true);

		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints cs = new GridBagConstraints();

		cs.fill = GridBagConstraints.HORIZONTAL;

		usernameLabel = new JLabel("Username: ");
		cs.gridx = 0;
		cs.gridy = 0;
		cs.gridwidth = 1;
		panel.add(usernameLabel, cs);

		usernameEntry = new JTextField(20);
		cs.gridx = 1;
		cs.gridy = 0;
		cs.gridwidth = 2;
		panel.add(usernameEntry, cs);

		passwordLabel = new JLabel("Password: ");
		cs.gridx = 0;
		cs.gridy = 1;
		cs.gridwidth = 1;
		panel.add(passwordLabel, cs);

		passwordEntry = new JPasswordField(20);
		cs.gridx = 1;
		cs.gridy = 1;
		cs.gridwidth = 2;
		panel.add(passwordEntry, cs);
		panel.setBorder(new LineBorder(Color.GRAY));

		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {

			/* When Login is pressed attempt to authenticate entered credentials.
			 * If success set the current driver to the matching driver stored in knownDrivers.
			 * If authentication fails, user can try to login again.
			 */
			public void actionPerformed(ActionEvent e) {
				Driver authResult = driverManager.authenticate(getUsername(), getPassword());
				if (authResult != null) {
					driverManager.setCurrentDriver(authResult);
					dispose();
				} else {
					JOptionPane.showMessageDialog(LoginGuiManager.this,
							"Invalid username or password",
							"Login",
							JOptionPane.ERROR_MESSAGE);
					usernameEntry.setText("");
					passwordEntry.setText("");

				}
			}
		});

		// User can register as a new driver by entering name and password and pressing register.     
		btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Driver registerResult = driverManager.register(getUsername(), getPassword());
				driverManager.setCurrentDriver(registerResult);
				dispose();

			}
		});

		JPanel bp = new JPanel();
		bp.add(btnLogin);
		bp.add(btnRegister);
		getContentPane().add(panel, BorderLayout.CENTER);
		getContentPane().add(bp, BorderLayout.PAGE_END);
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
	}

	public String getUsername() {
		return usernameEntry.getText().trim();
	}

	public String getPassword() {
		return new String(passwordEntry.getPassword());
	}

}
