
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Font;


import arc.*;
public class CPTSydney{
	public static void main(String[] args){
		Console con = new Console();
		

		//BufferedImage imgLogo = con.loadImage("HangmanLogo.png");
		// set the background colour
	
		CPTSydneytools.drawMainMenu(con);
		
		// Waiting for 33ms before looping around again
		// to show the drawing on the screen
		// before looping back around
		con.sleep(33);
		
		
	}	
	

}
