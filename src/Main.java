public class Main {
	
	public static void main(String[] args) {
		
		// Make a car, login a driver, and display the GUI
		
		Car car = new Car();
		car.login();
		GuiManager guiManager = new GuiManager(car);
		guiManager.showScreen();
		
	}
}
