import java.awt.*;
import javax.swing.*;

public class Main {
	
	public static void main(String[] args) {
		
		Car car = new Car();
		GuiManager guiManager = new GuiManager(car);
		guiManager.showScreen();
		
		/*
		final JFrame frame = new JFrame("Login");
        frame.setSize(300, 100);
        frame.setLayout(new FlowLayout());
		DriverManager driverManager = new DriverManager(frame);
        driverManager.setVisible(true);*/
		
		
	}
}
