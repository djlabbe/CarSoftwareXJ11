import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class RadioPanel extends JPanel {
	
	protected JPanel topRadioPanel, centerRadioPanel;
	protected JLabel stationLabel, modulusLabel, radioVolumeLabel;
	protected JButton radioPowerButton, seekDownButton, seekUpButton, amFmButton, volumeUpButton, volumeDownButton, setButton, favButton1,
	favButton2, favButton3;
	private DecimalFormat dfOne = new DecimalFormat("#00");
	private Car car;

	public RadioPanel(GuiManager guiManager) {
		this.car = guiManager.getCar();
		setLayout(new BorderLayout());
		setBackground(Color.LIGHT_GRAY);
	
		// TOP RADIO PANEL
		
		topRadioPanel = new JPanel();
		topRadioPanel.setBackground(Color.LIGHT_GRAY);

		radioPowerButton = new JButton("ON");
		radioPowerButton.setBackground(Color.WHITE);
		radioPowerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				car.radio.togglePower();
				if (car.radio.getIsOn()) {
					modulusLabel.setVisible(true);
					updateStationLabel(car);
					radioVolumeLabel.setText(Integer.toString(car.radio.getVolume()));
					radioPowerButton.setText("OFF");
					System.out.println("Radio turned on.");
				} else {
					modulusLabel.setVisible(false);
					stationLabel.setText("OsirusXM");
					radioVolumeLabel.setText("-");
					radioPowerButton.setText("ON");
					System.out.println("Radio turned off.");
				}
			}          
		});
		
		seekDownButton = new JButton(" << ");
		seekDownButton.setBackground(Color.WHITE);
		seekDownButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (car.radio.getIsOn()) {
					car.radio.seekDown();
					updateStationLabel(car);
				}
			}          
		});
	
		seekUpButton = new JButton(" >> ");
		seekUpButton.setBackground(Color.WHITE);
		seekUpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (car.radio.getIsOn()) {
					car.radio.seekUp();
					updateStationLabel(car);
				}
			}          
		});
		
		amFmButton = new JButton("AM | FM");
		amFmButton.setBackground(Color.WHITE);
		amFmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (car.radio.getIsOn()) {
					car.radio.toggleMod();
					updateStationLabel(car);
					modulusLabel.setText(car.radio.getModLabel());
				}
			}          
		});
		
		topRadioPanel.add(radioPowerButton);
		topRadioPanel.add(seekDownButton);
		topRadioPanel.add(seekUpButton);
		topRadioPanel.add(amFmButton);

		// LEFT RADIO PANEL

		JPanel leftRadioPanel = new JPanel();
		leftRadioPanel.setLayout(new BoxLayout(leftRadioPanel, 1));
		leftRadioPanel.setBackground(Color.LIGHT_GRAY);

		JLabel radioVolumeTitleLabel = new JLabel("Vol");
		radioVolumeTitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		radioVolumeTitleLabel.setFont (radioVolumeTitleLabel.getFont().deriveFont (28.0f));

		volumeUpButton = new JButton(" + ");
		volumeUpButton.setBackground(Color.WHITE);
		volumeUpButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		volumeUpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (car.radio.getIsOn()) {
					car.radio.volUp();
					radioVolumeLabel.setText(Integer.toString(car.radio.getVolume()));
				} 
			}          
		});

		radioVolumeLabel = new JLabel("-");
		radioVolumeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		radioVolumeLabel.setFont (radioVolumeLabel.getFont().deriveFont (40.0f));

		volumeDownButton = new JButton(" - ");
		volumeDownButton.setBackground(Color.WHITE);
		volumeDownButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		volumeDownButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (car.radio.getIsOn()) {
					car.radio.volDown();
					radioVolumeLabel.setText(Integer.toString(car.radio.getVolume()));	
				}
			}          
		});
		
		leftRadioPanel.add(radioVolumeTitleLabel);
		leftRadioPanel.add(volumeUpButton);
		leftRadioPanel.add(radioVolumeLabel);
		leftRadioPanel.add(volumeDownButton);

		// CENTER RADIO PANEL

		centerRadioPanel = new JPanel(new GridBagLayout());
		centerRadioPanel.setBackground(Color.LIGHT_GRAY);
		centerRadioPanel.setVisible(true);

		stationLabel = new JLabel("OsirusXM");
		stationLabel.setFont (stationLabel.getFont().deriveFont (60.0f));
		centerRadioPanel.add(stationLabel);
		
		modulusLabel = new JLabel(car.radio.getModLabel());
		modulusLabel.setFont (modulusLabel.getFont().deriveFont (44.0f));
		modulusLabel.setVisible(false);
		centerRadioPanel.add(modulusLabel);

		// BOTTOM RADIO PANEL

		JPanel bottomRadioPanel = new JPanel();
		bottomRadioPanel.setBackground(Color.LIGHT_GRAY);

		setButton = new JButton("Set Favorite");
		setButton.setBackground(Color.WHITE);
		setButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (car.radio.getIsOn()) {
					car.radio.toggleSetIsActive();
					if (car.radio.getSetIsActive()) {
						System.out.println("Set favorite activated.");
					} else {
						System.out.println("Set favorite cancelled.");
					}
				} 
			}          
		});
		
		bottomRadioPanel.add(setButton);
		bottomRadioPanel.add(makeFavButton(car, 1));
		bottomRadioPanel.add(makeFavButton(car, 2));
		bottomRadioPanel.add(makeFavButton(car, 3));
		
		//  RIGHT RADIO PANEL
		
		JPanel rightRadioPanel = new JPanel();
		rightRadioPanel.setBackground(Color.LIGHT_GRAY);
		
		// COMBINE TO MAIN PANEL
		
		add("North", topRadioPanel);
		add("West", leftRadioPanel);
		add("South", bottomRadioPanel);
		add("Center", centerRadioPanel);
		add("East", rightRadioPanel);
	}
	
	public void updateStationLabel(Car car) {
		if (car.radio.getIsAm()) {
			stationLabel.setText(dfOne.format(car.radio.currentStation.getStation()));
		} else {
			stationLabel.setText(Double.toString(car.radio.currentStation.getStation()));
		}
	}
	
	private JButton makeFavButton(Car car, int favNum) {
		JButton newButton = new JButton(" " + favNum + " ");
		newButton.setBackground(Color.WHITE);
		newButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (car.radio.getIsOn()) {
					if (car.radio.getSetIsActive()) {
						car.driverManager.currentDriver.setFav(car.radio.getIsAm(), favNum, car.radio.getCurrentStation());
						car.radio.setUserFavorites(car.driverManager.currentDriver);
						car.radio.toggleSetIsActive();
						System.out.println("New favorite " + favNum + " saved.");
					} else {
						car.radio.goToFav(favNum);
						updateStationLabel(car);
						System.out.println("Select favorite " + favNum +".");
					}  
				}
			}          
		});
		return newButton;
	}
}
