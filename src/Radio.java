import java.util.*;

/*The radio consists of 2 ArrayLists of known RadioStation objects,
 * and methods associated with setting and retrieving the volume,
 * changing the station and retrieving Driver's stored favorites.
 * 
 * The radio keeps track of the station it should be on for both am and fm
 * via the amStationIndex and fmStationIndex fields which should always correspond
 * to a valid index of the matching ArrayList.
 */

public class Radio {

	private boolean isOn, isAm, isSetActive;
	private int volume, amStationIndex, fmStationIndex;
	private ArrayList<RadioStation> availableAmStations, availableFmStations;
	private Double[] amStations = new Double[] {520.0, 620.0, 740.0, 910.0, 1060.0, 1510.0};
	private Double[] fmStations = new Double[] {90.3, 93.3, 93.9, 94.5, 99.9, 100.7, 102.5, 103.9};
	protected RadioStation amFav1, amFav2, amFav3, fmFav1, fmFav2, fmFav3, currentStation;
	
	public Radio () {
		
		availableAmStations = new ArrayList<RadioStation>();
		availableFmStations = new ArrayList<RadioStation>();
		for (int i = 0; i < amStations.length; i++) {
			availableAmStations.add(new RadioStation(amStations[i]));
		}
		for (int i = 0; i < fmStations.length; i++) {
			availableFmStations.add(new RadioStation(fmStations[i]));
		}
		reset();
	}

	public boolean getIsOn() {
		return isOn;
	}

	public void togglePower() {
		isOn = !isOn;
	}

	// Switches between AM and FM while maintaining the current radio station for each
	public void toggleMod() {
		isAm = !isAm;
		if (isAm) {
			currentStation = availableAmStations.get(amStationIndex);
			System.out.println("Radio switched to AM.");
		} else {
			currentStation = availableFmStations.get(fmStationIndex);
			System.out.println("Radio switched to FM.");
		}
	}
	
	public int getVolume() {
		return volume;
	}

	public boolean getIsAm() {
		return isAm;
	}
	
	public boolean getSetIsActive() {
		return isSetActive;
	}

	public void toggleSetIsActive() {
		isSetActive = !isSetActive;
	}

	// Increments the volume, to a max of 10
	public void volUp() {
		if (volume < 10) volume++;
	}

	// Decrements the volume, to a min of 0.
	public void volDown() {
		if (volume > 0) volume--;
	}

	/* Finds the next higher available station in the list of available stations.
	 * Updates the current stationIndex
	 */
	public void seekUp() {
		if (isAm) {
			amStationIndex++;
			if (amStationIndex >= availableAmStations.size()) amStationIndex = 0;
			currentStation = availableAmStations.get(amStationIndex);
		} else if (!isAm) {
			fmStationIndex++;
			if (fmStationIndex >= availableFmStations.size()) fmStationIndex = 0;
			currentStation = availableFmStations.get(fmStationIndex);
		}
		System.out.println("Radio seek forward.");
	}

	/* Finds the next lower available station in the list of available stations.
	 * Updates the current stationIndex
	 */
	public void seekDown() {
		if (isAm) {
			amStationIndex--;
			if (amStationIndex < 0) amStationIndex = availableAmStations.size() - 1;
			currentStation = availableAmStations.get(amStationIndex);
		} else if (!isAm) {
			fmStationIndex--;
			if (fmStationIndex < 0) fmStationIndex = availableFmStations.size() - 1;
			currentStation = availableFmStations.get(fmStationIndex);
		}
		System.out.println("Radio seek back.");
	}

	/* Jump to the selected favorite based on the button pressed.
	 * -Check whether radio is set to AM or FM
	 * -Match the button pressed to the corresponding stored favorite
	 * -Search through the appropriate known station list to find the matching station and update the stationIndex.
	 */
	public void goToFav(int numSelect) {

		if (isAm) {
			switch (numSelect) {
			case 1: 
				if (amFav1 != null) {
					currentStation = amFav1;
				}
				break;
			case 2:
				if (amFav2 != null) {
					currentStation = amFav2;
				}
				break;
			case 3:
				if (amFav3 != null) {
					currentStation = amFav3;
				}
			}

			for (int i = 0; i < availableAmStations.size(); i++) {
				if (currentStation.getStation() == availableAmStations.get(i).getStation()) amStationIndex = i;
			}	
		} else {
			switch (numSelect) {
			case 1: 
				if (fmFav1 != null) {
					currentStation = fmFav1;
				}
				break;
			case 2:
				if (fmFav2 != null) {
					currentStation = fmFav2;
				}
				break;
			case 3:
				if (fmFav3 != null) {
					currentStation = fmFav3;
				}
			}

			for (int i = 0; i < availableFmStations.size(); i++) {
				if (currentStation.getStation() == availableFmStations.get(i).getStation()) fmStationIndex = i;
			}
		}
	}

	/* Refresh the radio favorite stations by retrieving the favorites of the current driver */
	public void setUserFavorites(Driver currentDriver) {
		amFav1 = currentDriver.amFav1;
		amFav2 = currentDriver.amFav2;
		amFav3 = currentDriver.amFav3;
		fmFav1 = currentDriver.fmFav1;
		fmFav2 = currentDriver.fmFav2;
		fmFav3 = currentDriver.fmFav3;
	}

	public RadioStation getCurrentStation() {
		return currentStation;
	}

	public String getModLabel() {
		String mod = isAm ? "AM" : "FM";
		return mod;
	}
	
	public void reset() {
		isOn = false;
		isAm = true;
		volume = 5;
		currentStation = availableAmStations.get(0);
		amStationIndex = 0;
		fmStationIndex = 0;
	}

}
