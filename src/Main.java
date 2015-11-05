public class Main {

	protected Car car;
	
	public static void main(String[] args) {
		Car car = new Car();
		GuiManager guiManager = new GuiManager(car);
		guiManager.showScreen();
	}
}
