import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class NavPanel extends DecorativePanel {

	private Border bevelledBorder = BorderFactory.createRaisedBevelBorder();
	private AppPanel controlledPanel;
	private CardLayout cardLayout;
	private Car car;

	public NavPanel(GuiManager guiManager) {
		this.car = guiManager.getCar();
		controlledPanel = guiManager.appPanel;
		setLayout(new GridLayout(4, 1));
		cardLayout = (CardLayout)(controlledPanel.getLayout());

		add(makeNavButton("Radio"));
		add(makeNavButton("Phone"));
		add(makeNavButton("Map"));
		add(makeNavButton("Stats"));
	}

	private JButton makeNavButton(String label) {
		JButton button = new JButton(label);
		button.setFont(button.getFont().deriveFont(18.0f));
		button.setBackground(Color.DARK_GRAY);
		button.setForeground(Color.WHITE);
		button.setBorder(bevelledBorder);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (car.getIsOn()) cardLayout.show(controlledPanel, label.toUpperCase() + "PANEL");
			}
		});
		return button;
	}

}
