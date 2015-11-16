import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class DecorativeButton extends JButton {
	
	private Border bevelledBorder = BorderFactory.createRaisedBevelBorder();
	
	public DecorativeButton(String text) {
		this.setText(text);
		this.setFont(this.getFont().deriveFont (20.0f));
		setBackground(Color.DARK_GRAY);
		setForeground(Color.WHITE);
		setBorder(bevelledBorder);
	}

}
