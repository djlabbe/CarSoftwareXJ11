import java.util.*;

public class Radio {
	
	private boolean isOn;
	private boolean isAm;
	private int volume;

	private int amStationIndex;
	private int fmStationIndex;
	
	private ArrayList<RadioStation> availableAmStations;
	private ArrayList<RadioStation> availableFmStations;

	protected RadioStation amFav1;
	protected RadioStation amFav2;
	protected RadioStation amFav3;
	protected RadioStation fmFav1;
	protected RadioStation fmFav2;
	protected RadioStation fmFav3;
	
	protected RadioStation currentStation;
	
	public Radio () {
		isOn = false;
		isAm = true;
		volume = 5;
		
		availableAmStations = new ArrayList<RadioStation>();
		availableFmStations = new ArrayList<RadioStation>();
		
		
		availableAmStations.add(new RadioStation(550.0));
		availableAmStations.add(new RadioStation(620.0));
		availableAmStations.add(new RadioStation(910.0));
		availableAmStations.add(new RadioStation(1060.0));
		
		availableFmStations.add(new RadioStation(93.9));
		availableFmStations.add(new RadioStation(94.5));
		availableFmStations.add(new RadioStation(100.7));
		availableFmStations.add(new RadioStation(102.5));
		
		currentStation = availableAmStations.get(0);
		amStationIndex = 0;
		fmStationIndex = 0;
	}
	
	public boolean getIsOn() {
		return isOn;
	}
	
	public void togglePower() {
		isOn = !isOn;
	}
	
	public void toggleMod() {
		isAm = !isAm;
		if (isAm) {
			currentStation = availableAmStations.get(amStationIndex);
		} else {
			currentStation = availableFmStations.get(fmStationIndex);
		}
	}
	
	public int getVolume() {
		return volume;
	}
	
	public int volUp() {
		volume++;
		if (volume > 10) {
			volume = 10;
		}
		return volume;
	}
	
	public int volDown() {
		volume--;
		if (volume < 0) {
			volume = 0;
		}
		return volume;
	}
	
	public void seekUp() {
		if (isAm) {
			amStationIndex++;
			if (amStationIndex >= availableAmStations.size()) {
				amStationIndex = 0;
			}
			currentStation = availableAmStations.get(amStationIndex);
		} else if (!isAm) {
			fmStationIndex++;
			if (fmStationIndex >= availableFmStations.size()) {
				fmStationIndex = 0;
			}
			currentStation = availableFmStations.get(fmStationIndex);
		}
	}
	
	public void seekDown() {
		if (isAm) {
			amStationIndex--;
			if (amStationIndex < 0) {
				amStationIndex = availableAmStations.size() - 1;
			}
			currentStation = availableAmStations.get(amStationIndex);
		} else if (!isAm) {
			fmStationIndex--;
			if (fmStationIndex < 0) {
				fmStationIndex = availableFmStations.size() - 1;
			}
			currentStation = availableFmStations.get(fmStationIndex);
		}
	}
	
	public void setUserFavorites(Driver currentDriver) {
		amFav1 = currentDriver.amFav1;
		amFav2 = currentDriver.amFav2;
		amFav3 = currentDriver.amFav3;
		fmFav1 = currentDriver.fmFav1;
		fmFav2 = currentDriver.fmFav2;
		fmFav3 = currentDriver.fmFav3;
	}
	
	public String getModLabel() {
		String mod = isAm ? "AM" : "FM";
		return mod;
	}
	
}
