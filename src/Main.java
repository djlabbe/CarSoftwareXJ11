import java.awt.*;
import javax.swing.*;

public class Main {
	
	public static void main(String[] args) {
		
		Car car = new Car();
		GuiManager guiManager = new GuiManager(car);
		guiManager.showScreen();
		
	}
}
