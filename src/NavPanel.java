import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class NavPanel extends JPanel {

	private Border bevelledBorder = BorderFactory.createRaisedBevelBorder();
	private JButton radioButton, phoneButton, mapButton, statsButton;

	public NavPanel(Car car, AppPanel controlledPanel) {
		setLayout(new GridLayout(4, 1));
		setBackground(Color.DARK_GRAY);
		setBorder(bevelledBorder);

		radioButton = makeNavButton("Radio");
		radioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (car.getIsOn()) {
					CardLayout cardLayout = (CardLayout)(controlledPanel.getLayout());
					cardLayout.show(controlledPanel, "RADIOPANEL");
					System.out.println("Show Radio");
				}			
			}          
		});
		phoneButton = makeNavButton("Phone");
		phoneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (car.getIsOn()) {
					CardLayout cardLayout = (CardLayout)(controlledPanel.getLayout());
					cardLayout.show(controlledPanel, "PHONEPANEL");
					System.out.println("Show Phone");
				}
			}          
		});
		mapButton = makeNavButton("Map");
		mapButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (car.getIsOn()) {
					CardLayout cardLayout = (CardLayout)(controlledPanel.getLayout());
					cardLayout.show(controlledPanel, "MAPPANEL");
					System.out.println("Show Map");
				}

			}          
		});
		statsButton = makeNavButton("Stats");
		statsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (car.getIsOn()) {
					CardLayout cardLayout = (CardLayout)(controlledPanel.getLayout());
					cardLayout.show(controlledPanel, "ANALYTICSPANEL");
					System.out.println("Show Analytics");
				}

			}          
		});
		
		add(radioButton);
		add(phoneButton);
		add(mapButton);
		add(statsButton);
	}

	private JButton makeNavButton(String label) {
		JButton button = new JButton(label);
		button.setFont(button.getFont().deriveFont(18.0f));
		button.setBackground(Color.DARK_GRAY);
		button.setForeground(Color.WHITE);
		button.setBorder(bevelledBorder);
		return button;
	}

}
