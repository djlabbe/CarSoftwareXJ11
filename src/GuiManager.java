
/*Main screen consists of the full window JFrame which contains a 5 JPanels arranged in Border Layout.
 *The center (main) panel is called the appPanel which itself has a CardLayout meaning it behaves like a stack
 *of cards. The four sub-panels are radioPanel, phonePanel, mapPanel, and analyticsPanel. Each can be made to be
 *the active panel within the appPanel.*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GuiManager {

	private Car car;

	protected JFrame mainFrame;
	protected InfoPanel infoPanel;
	protected NavPanel navPanel;
	protected AppPanel appPanel;
	protected CorePanel corePanel;

	public GuiManager() {
		try {
			UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		car = new Car();
		prepareGUI();
	}

	private void prepareGUI() {
		mainFrame = new JFrame("XJ-11");
		mainFrame.setBackground(Color.WHITE);
		mainFrame.setSize(600, 400);
		mainFrame.setLayout(new BorderLayout());
		mainFrame.setResizable(false);
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent){
				System.exit(0);
			}        
		});  
		mainFrame.setLocationRelativeTo(null);
	}

	protected void showScreen() {

		infoPanel = new InfoPanel(this);
		corePanel = new CorePanel(this);
		appPanel = new AppPanel(this);
		navPanel = new NavPanel(this);

		mainFrame.add("North", infoPanel);
		mainFrame.add("West", navPanel);
		mainFrame.add("South", corePanel);
		mainFrame.add("Center", appPanel);
		mainFrame.add("East", new DecorativePanel());

		mainFrame.setVisible(true);
	}

	public Car getCar() {
		return car;
	}
}