/* Most Login functionality code adapted from "3 Steps to Create Login Dialog in Java Swing"
 * http://www.zentut.com/java-swing/simple-login-dialog/ */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class LoginGuiManager extends JDialog {

	private JTextField usernameEntry;
    private JPasswordField passwordEntry;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JButton btnLogin;
    private boolean loginSuccessful;
	
	public LoginGuiManager(Frame parent) {
		super(parent, "Login", true);
		
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
        	DriverManager testManager = new DriverManager();
            if (testManager.authenticate(getUsername(), getPassword()) != null) {
                JOptionPane.showMessageDialog(LoginGuiManager.this,
                        "Hi " + getUsername() + "! You have successfully logged in.",
                        "Login",
                        JOptionPane.INFORMATION_MESSAGE);
                loginSuccessful = true;
                dispose();
            } else {
                JOptionPane.showMessageDialog(LoginGuiManager.this,
                        "Invalid username or password",
                        "Login",
                        JOptionPane.ERROR_MESSAGE);
                usernameEntry.setText("");
                passwordEntry.setText("");
                loginSuccessful = false;

            	}
        	}
    	});
	
		JPanel bp = new JPanel();
	    bp.add(btnLogin);
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
	 
	 public boolean isSucceeded() {
	        return loginSuccessful;
	    }
	
}
