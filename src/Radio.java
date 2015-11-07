import java.util.*;

public class Radio {
	
	private boolean isOn;
	private boolean isAm;
	private int volume;
	
	private double amFav1;
	private double amFav2;
	private double fmFav1;
	private double fmFav2;
	
	private ArrayList<Double> availableAmStations;
	private ArrayList<Double> availableFmStations;
	
	private double currentStation;
	public int amStationIndex;
	public int fmStationIndex;
	
	public Radio () {
		isOn = false;
		isAm = true;
		volume = 5;
		
		availableAmStations = new ArrayList<Double>();
		availableFmStations = new ArrayList<Double>();
		
		availableAmStations.add(550.0);
		availableAmStations.add(620.0);
		availableAmStations.add(910.0);
		availableAmStations.add(1060.0);
		
		availableFmStations.add(93.9);
		availableFmStations.add(94.5);
		availableFmStations.add(100.7);
		availableFmStations.add(102.5);
		
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
		return volume;
	}
	
	public int volDown() {
		volume--;
		return volume;
	}
	
	public double getCurrentStation() {
		return currentStation;
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
	
	public double getAmFav1() {
		return amFav1;
	}
	
	public double getAmFav2() {
		return amFav2;
	}

	public double getFmFav1() {
		return fmFav1;
	}
	
	public double getFmFav2() {
		return fmFav2;
	}
	
	public void setUserFavorites(Driver currentDriver) {
		amFav1 = currentDriver.getAmFav1();
		amFav2 = currentDriver.getAmFav2();
		fmFav1 = currentDriver.getFmFav1();
		fmFav2 = currentDriver.getFmFav2();
	}
	
	public String getModLabel() {
		String mod = isAm ? "AM" : "FM";
		return mod;
	}
	
}
