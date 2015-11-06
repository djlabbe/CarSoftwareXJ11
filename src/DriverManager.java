/* Most Login functionality code adapted from "3 Steps to Create Login Dialog in Java Swing"
 * http://www.zentut.com/java-swing/simple-login-dialog/ */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;

// #TODO Add user-name functionality

public class DriverManager extends JDialog{
	
	int userEnteredPin;
	Driver currentDriver;
	ArrayList<Driver> knownDrivers;
	
	private JTextField usernameEntry;
    private JPasswordField passwordEntry;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JButton btnLogin;
    private JButton btnRegister;
    private boolean loginSuccessful;
	
	public DriverManager(Frame parent) {
		
		super(parent, "Login", true);
		
		currentDriver = null;
		knownDrivers = new ArrayList<Driver>();
		
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
		 
	        public void actionPerformed(ActionEvent e) {
	        	Driver authResult = authenticate(getUsername(), getPassword());
	            if (authResult != null) {
	                JOptionPane.showMessageDialog(DriverManager.this,
	                        "Welcome to the XJ-11.",
	                        "Login",
	                        JOptionPane.INFORMATION_MESSAGE);
	                loginSuccessful = true;
	                currentDriver = authResult;
	                dispose();
	            } else {
	                JOptionPane.showMessageDialog(DriverManager.this,
	                        "Invalid username or password",
	                        "Login",
	                        JOptionPane.ERROR_MESSAGE);
	                usernameEntry.setText("");
	                passwordEntry.setText("");
	                loginSuccessful = false;
	
	            }
	        }
	    });
	
	    btnRegister = new JButton("Register");
	    btnRegister.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	        	
	    		// #TODO Register new driver
	        	
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
		
		register("aaaa");
		register("1111");
		register("bbbb");
		register("abcd");
	}
	
	 public String getUsername() {
	     return usernameEntry.getText().trim();
	   }
	 
	 public String getPassword() {
	      return new String(passwordEntry.getPassword());
	  }
	 
	 public boolean isSucceeded() {
	        return loginSuccessful;
	    }
	
	public Driver authenticate(String usernameInput, String passwordInput) {
		Driver loggedInDriver = null;
			for (int i = 0; i < knownDrivers.size(); i++) {
				if (knownDrivers.get(i).getPassword().equals(passwordInput)) {
					loggedInDriver = knownDrivers.get(i);
				} 
			}
		return loggedInDriver;
	}
	
	public Driver register(String inputPassword) {
		Driver registeredDriver = new Driver(inputPassword);
		knownDrivers.add(registeredDriver);
		return registeredDriver;
	}
	

}
