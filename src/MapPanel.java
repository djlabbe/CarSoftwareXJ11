import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Enumeration;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;

@SuppressWarnings("serial")
public class MapPanel extends JPanel {
	
	private JSlider mapSlider;
	private DecimalFormat dfMap = new DecimalFormat("###.00");
	private JComboBox<String> routeSelector;
	private Car car;

	public MapPanel(Car car, GuiManager guiManager) {
		this.car = car;
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);
		
		int routeDistance = (int)car.map.getCurrentRoute().getRouteDistance();
		mapSlider = new JSlider(0, routeDistance, 0);
		setSliderSpacing(car, routeDistance);
		mapSlider.setBackground(Color.WHITE);
		mapSlider.setPaintLabels(true);
		mapSlider.setEnabled(false);
		add("Center", mapSlider);

		routeSelector = new JComboBox<String>(car.map.getRouteList());
		routeSelector.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				@SuppressWarnings("unchecked")
				JComboBox<String> cb = (JComboBox<String>)e.getSource();
				int routeIndex = cb.getSelectedIndex();
				if (car.getCurrentSpeed() > 0) {
					routeSelector.setSelectedIndex(car.map.getRouteIndex(car.map.getCurrentRoute()));
					JOptionPane.showMessageDialog(guiManager.mainFrame,
							"Can not change routes while moving.",
							"Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					car.map.setCurrentRoute(routeIndex);
					int routeDistance = (int)car.map.getCurrentRoute().getRouteDistance();
					mapSlider.setMaximum(routeDistance);
					setSliderSpacing(car, routeDistance);		
				}
			}
		});
		add("North", routeSelector);
	}
	
	public void setSliderSpacing(Car car, int routeDistance) {
		mapSlider.setMajorTickSpacing((int)car.map.getCurrentRoute().getRouteDistance() / 4);
		mapSlider.setLabelTable(mapSlider.createStandardLabels(routeDistance / 4));

		/* Convert integer labels to decimal -- Snippet adapted from from 
		 * http://stackoverflow.com/questions/1125619/change-displayable-labels-for-a-jslider
		 */
		@SuppressWarnings("unchecked")
		Enumeration<Integer> mapLabels = mapSlider.getLabelTable().keys();
		while (mapLabels.hasMoreElements()) {
			Integer i = mapLabels.nextElement();
			JLabel label = (JLabel) mapSlider.getLabelTable().get(i);
			label.setText(String.valueOf(dfMap.format((double)i/100.0)));
			label.setSize(50, 20);
		}
				
	}
	
	// An executable to be run by the event dispatch thread to update a Swing GUI component.
	Runnable updateSliderPosition = new Runnable () {
		public void run() {
			mapSlider.setValue((int)car.map.getCurrentRoute().getDistanceIntoRoute());
		}
	};

}
