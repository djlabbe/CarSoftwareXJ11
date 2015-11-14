import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class DecorativePanel extends JPanel {
	
	private Border bevelledBorder = BorderFactory.createRaisedBevelBorder();
	
	public DecorativePanel() {
		setBackground(Color.DARK_GRAY);
		setBorder(bevelledBorder);
	}
}
