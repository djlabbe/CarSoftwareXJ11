public class Main {
	
	public static void main(String[] args) {
		
		// Make a car and display the GUI
		
		Car car = new Car();
		GuiManager guiManager = new GuiManager(car);
		guiManager.showScreen();
		
	}
}
