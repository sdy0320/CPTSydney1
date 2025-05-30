
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Font;
import arc.*;
public class CPTSydney{
	public static void main(String[] args){
		Console con = new Console();
		
		int intX = 400;
		int intY = 200;
		BufferedImage imgLogo = con.loadImage("HangmanLogo.png");
		Font fntTest = con.loadFont("JourneyWhiskers-Regular.ttf", 40);
		while(intX > 0){
			// set the background
			con.setDrawColor(Color.BLACK);
			con.fillRect(0,0,700,700);
			// Start drawing stuff on that background
			con.setDrawColor(Color.WHITE);
			con.fillOval(100, 100, 50, 200);
			// Setting a predefined color
			con.setDrawColor(Color.RED);
			con.fillOval(400, 10, 100, 75);
			// Setting a custom Red Blue Green rgb color 0-255
			con.setDrawColor(new Color(3, 100, 230));
			con.fillRoundRect(300, intY, 200, 200, 50, 50);
			intY = intY + 3;
			con.drawImage(imgLogo, intX, 0);
			intX = intX - 1;
			con.setDrawFont(fntTest);
			con.setDrawColor(new Color(200, 30, 30));
			con.drawString("Mmmmm doughnuts", 0,0);
			
			// repainting ensures that all of the drawing commands above appear
			con.repaint();
			// Waiting for 33ms before looping around again
			// to show the drawing on the screen
			// before looping back around
			con.sleep(33);	

		
		
	}	
	
	}
}
