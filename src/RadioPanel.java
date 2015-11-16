import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class RadioPanel extends JPanel {
	
	protected DecorativePanel topRadioPanel, centerRadioPanel;
	protected JLabel stationLabel, modulusLabel, radioVolumeLabel;
	protected DecorativeButton radioPowerButton, seekDownButton, seekUpButton, amFmButton, volumeUpButton, volumeDownButton, setButton, favButton1,
	favButton2, favButton3;
	private DecimalFormat dfOne = new DecimalFormat("#00");
	private Car car;

	public RadioPanel(GuiManager guiManager) {
		this.car = guiManager.getCar();
		setLayout(new BorderLayout());
		setBackground(Color.LIGHT_GRAY);
	
		// TOP RADIO PANEL
		
		topRadioPanel = new DecorativePanel();
		topRadioPanel.setBackground(Color.DARK_GRAY);
		radioPowerButton = new DecorativeButton("ON ");
		radioPowerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				car.radio.togglePower();
				if (car.radio.getIsOn()) {
					modulusLabel.setVisible(true);
					updateStationLabel(car);
					radioVolumeLabel.setText(Integer.toString(car.radio.getVolume()));
					radioPowerButton.setText("OFF");
				} else {
					modulusLabel.setVisible(false);
					stationLabel.setText("OsirusXM");
					radioVolumeLabel.setText("-");
					radioPowerButton.setText("ON");
				}
			}          
		});
		
		seekDownButton = new DecorativeButton(" << ");
		seekDownButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (car.radio.getIsOn()) {
					car.radio.seekDown();
					updateStationLabel(car);
				}
			}          
		});
	
		seekUpButton = new DecorativeButton(" >> ");
		seekUpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (car.radio.getIsOn()) {
					car.radio.seekUp();
					updateStationLabel(car);
				}
			}          
		});
		
		amFmButton = new DecorativeButton("AM | FM");
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

		DecorativePanel leftRadioPanel = new DecorativePanel();
		leftRadioPanel.setLayout(new GridBagLayout());
		leftRadioPanel.setBackground(Color.DARK_GRAY);
		GridBagConstraints gb = new GridBagConstraints();
		gb.gridx = 0;
		gb.gridy = 0;
		gb.insets = new Insets(0,5,0,5);
		JLabel radioVolumeTitleLabel = new JLabel("Vol");
		radioVolumeTitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		radioVolumeTitleLabel.setFont (radioVolumeTitleLabel.getFont().deriveFont (28.0f));
		radioVolumeTitleLabel.setForeground(Color.WHITE);

		volumeUpButton = new DecorativeButton(" + ");
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
		radioVolumeLabel.setForeground(Color.WHITE);

		volumeDownButton = new DecorativeButton(" - ");
		volumeDownButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		volumeDownButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (car.radio.getIsOn()) {
					car.radio.volDown();
					radioVolumeLabel.setText(Integer.toString(car.radio.getVolume()));	
				}
			}          
		});
		
		leftRadioPanel.add(radioVolumeTitleLabel, gb); gb.gridy++;
		leftRadioPanel.add(volumeUpButton, gb); gb.gridy++;
		leftRadioPanel.add(radioVolumeLabel, gb); gb.gridy++;
		leftRadioPanel.add(volumeDownButton, gb);

		// CENTER RADIO PANEL

		centerRadioPanel = new DecorativePanel();
		centerRadioPanel.setLayout(new GridBagLayout());
		centerRadioPanel.setBackground(Color.WHITE);
		centerRadioPanel.setVisible(true);

		stationLabel = new JLabel("OsirusXM");
		stationLabel.setFont (stationLabel.getFont().deriveFont (60.0f));
		centerRadioPanel.add(stationLabel);
		
		modulusLabel = new JLabel(car.radio.getModLabel());
		modulusLabel.setFont (modulusLabel.getFont().deriveFont (44.0f));
		modulusLabel.setVisible(false);
		centerRadioPanel.add(modulusLabel);

		// BOTTOM RADIO PANEL

		DecorativePanel bottomRadioPanel = new DecorativePanel();
		bottomRadioPanel.setBackground(Color.DARK_GRAY);

		setButton = new DecorativeButton("Set Favorite");
		setButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (car.radio.getIsOn()) {
					car.radio.toggleSetIsActive();
					if (car.radio.getSetIsActive()) {
					}
				} 
			}          
		});
		
		bottomRadioPanel.add(setButton);
		bottomRadioPanel.add(makeFavButton(car, 1));
		bottomRadioPanel.add(makeFavButton(car, 2));
		bottomRadioPanel.add(makeFavButton(car, 3));
		
		//  RIGHT RADIO PANEL
		
		DecorativePanel rightRadioPanel = new DecorativePanel();
		rightRadioPanel.setBackground(Color.DARK_GRAY);
		
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
	
	private DecorativeButton makeFavButton(final Car car, final int favNum) {
		DecorativeButton newButton = new DecorativeButton(" " + favNum + " ");
		newButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (car.radio.getIsOn()) {
					if (car.radio.getSetIsActive()) {
						car.driverManager.currentDriver.setFav(car.radio.getIsAm(), favNum, car.radio.getCurrentStation());
						car.radio.setUserFavorites(car.driverManager.currentDriver);
						car.radio.toggleSetIsActive();
					} else {
						car.radio.goToFav(favNum);
						updateStationLabel(car);
					}  
				}
			}          
		});
		return newButton;
	}
}
