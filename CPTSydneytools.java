
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;
import arc.*;
public class CPTSydneytools{
	//methods 1
		public static void drawMainMenu(Console con){
			con.setDrawColor(Color.WHITE);
			con.fillRect(0,0,300,800);
			int intCount = 0;
			int intY = 90;
			// Start drawing stuff on that background
			for(intCount = 0; intCount<4; intCount++){
				con.setDrawColor(Color.GRAY);
				con.fillRect(50, intY, 200, 50);
				intY = intY+70;
			}
		
		}
		
		public static String PlayGame(Console con, String strWord){
			String strUserName;
			String strTest = "";
			String strTheme = "";
			int intUserChoice;
			//Getting user's name
			con.println("Enter User Name: ");
			//Printing Themes
			TextInputFile themeNames = new TextInputFile("themes.txt");
			con.println("Themes: ");
			while(themeNames.eof() == false){
				strTheme = themeNames.readLine();
				con.println(strTheme);
			}
			themeNames.close();
			//Using ThemeChosen method to get the theme the user wanted.
			con.println("Choose a theme: ");
			intUserChoice = con.readInt();
			strTheme = CPTSydneytools.Themechosen(intUserChoice);
			
			TextInputFile currentTheme = new TextInputFile(strTheme);
			
			
			return strTest;
		}
		
		
		
		public static String Themechosen(int intUserChoice){
			TextInputFile themes = new TextInputFile("themes.txt");
			String strThemeName = "";
			int intCount  = 0;
			String strThemes [] = new String [5];
			String strChosenTheme = "";
			while(themes.eof() == false){
				strThemeName = themes.readLine();
				strThemes[intCount] = strThemeName;
				intCount = intCount+1;
			}
			
			for(intCount = 0; intCount <= intUserChoice; intCount++){
				if(intUserChoice == intCount){
					strChosenTheme = strThemes[intCount];
				}
			}
			
			return strChosenTheme;
		}	
	
		public static KeyListener QuitonQKeyListener(Console con){
			return new KeyAdapter(){
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode() == KeyEvent.VK_Q) {
						con.println("Quitting Game");
						System.exit(0);
					}
				}
			};
					
		}	
		public static KeyListener PlayonPKeyListener(Console con){
			return new KeyAdapter(){
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode() == KeyEvent.VK_P) {
						con.println("Starting Game");
						
					}
				}
			};
					
		}
		
}
