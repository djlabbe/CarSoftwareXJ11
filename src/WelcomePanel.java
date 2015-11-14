import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class WelcomePanel extends JPanel{
	
	public WelcomePanel() {
		setLayout(new GridBagLayout());
		setBackground(Color.WHITE);
		
		JPanel welcomeMessageHolder = new JPanel(new GridLayout(3,1));
		welcomeMessageHolder.setBackground(Color.WHITE);
		add(welcomeMessageHolder);

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
	}
}
