
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;
import arc.*;
public class CPTSydney{
	public static void main(String[] args){
		Console con = new Console();
		
		JFrame frame = new JFrame("Hangmanzz");
		frame.addKeyListener(CPTSydneytools.PlayonPKeyListener(con));
		frame.addKeyListener(CPTSydneytools.QuitonQKeyListener(con));
		//BufferedImage imgLogo = con.loadImage("HangmanLogo.png");
		// set the background colour
		CPTSydneytools.drawMainMenu(con);
		CPTSydneytools.QuitonQKeyListener(con);
		// Setting a predefined color
		//con.setDrawColor(Color.RED);
		//con.fillOval(400, 10, 100, 75);
	
		//con.drawImage(imgLogo, 0, 0);
	
		con.setDrawColor(new Color(200, 30, 30));
		con.drawString("Hangman Menu", 75,0);
		
		// repainting ensures that all of the drawing commands above appear
		con.repaint();
		// Waiting for 33ms before looping around again
		// to show the drawing on the screen
		// before looping back around
		con.sleep(33);
		
		
	}	
	

}
