/* Represent stations used by the Radio */

public class RadioStation {

	private double station;

	public RadioStation (double station) {
		this.station = station;
	}

	public double getStation() {
		return station;
	}

	public String toString() {
		return Double.toString(station);
	}
}
