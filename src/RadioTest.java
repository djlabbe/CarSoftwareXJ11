import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RadioTest {

	private Radio radio;
	private Driver driver;
	private RadioStation station;
	
	@Before
	public void setUp() throws Exception {
		radio = new Radio();
		driver = new Driver("TestDriver", "password");
	}

	@After
	public void tearDown() throws Exception {
		radio = null;
	}

	@Test
	public void testPower() {
		radio.togglePower();
		assertEquals(radio.getIsOn(), true);
		radio.togglePower();
		assertEquals(radio.getIsOn(), false);
	}
	
	@Test
	public void testMod() {
		assertEquals(radio.getIsAm(), true);
		radio.toggleMod();
		assertEquals(radio.getIsAm(), false);
		radio.toggleMod();
		assertEquals(radio.getIsAm(), true);
	}

	@Test
	public void testSetFav() {
		station = new RadioStation(520.0);
		driver.setFav(true, 1, station);
		radio.toggleSetIsActive();
		driver.setFav(true, 1, station);
		radio.setUserFavorites(driver);
		radio.seekUp();
		radio.goToFav(1);
		assertEquals(radio.currentStation, station);
	}
}
