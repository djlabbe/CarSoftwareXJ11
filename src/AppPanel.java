/* The appPanel uses a "CardLayout" which works like a stack of playing cards,
 * appPanel will contain 5 other panels which can individually be made active.
 * */

import java.awt.CardLayout;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class AppPanel extends JPanel {

	protected CardLayout appLayout;
	protected WelcomePanel welcomePanel;
	protected RadioPanel radioPanel;
	protected PhonePanel phonePanel;
	protected MapPanel mapPanel;
	protected AnalyticsPanel statsPanel;

	public AppPanel(GuiManager guiManager) {
		appLayout = new CardLayout();
		setLayout(appLayout);

		welcomePanel = new WelcomePanel();
		radioPanel = new RadioPanel(guiManager);
		phonePanel = new PhonePanel(guiManager);
		mapPanel = new MapPanel(guiManager);
		statsPanel = new AnalyticsPanel(guiManager);

		add(welcomePanel, "WELCOMEPANEL");
		add(radioPanel, "RADIOPANEL");
		add(phonePanel, "PHONEPANEL");
		add(mapPanel, "MAPPANEL");
		add(statsPanel, "STATSPANEL");
	}	
}
