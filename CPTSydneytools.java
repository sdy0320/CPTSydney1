
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
			CPTSydneytools.PlayGame(con);
		}
		
		public static void PlayGame(Console con){
			String strUserName;
			String strTest = "";
			String strTheme = "";
			String strWord;
			String strUserChoice;
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
			strUserChoice = con.readLine();
			strTheme = CPTSydneytools.Themechosen(strUserChoice);
			strWord = CPTSydneytools.ChooseWord(strTheme);
			//WORKS THEME & WORD
			//con.println(strTheme);
			//con.println(strWord);
			
		}
		
		
		
		public static String Themechosen(String strUserChoice){
			TextInputFile themes = new TextInputFile("themes.txt");
			String strThemeName = "";
			int intCount  = 0;
			String strThemes [] = new String [5];
			String strChosenTheme = "";
			while(themes.eof() == false){
				strThemeName = themes.readLine();
				if(strThemeName.equalsIgnoreCase(strUserChoice)){
					strChosenTheme = strThemeName;
				}
			}
			
			return strChosenTheme;
		}	
		
		public static String ChooseWord(String strTheme){
			TextInputFile currentTheme = new TextInputFile(strTheme);
			//make the code that will choose a random word from the text. Will need to use bubble sort and put the data from the text file into a 2D array
			int intCount = 0;
			String strWord = "";
			int intRand;
			String strThemeWords [][];
			strThemeWords = new String [10][2];
			while (currentTheme.eof() == false){
				strWord = currentTheme.readLine();
				strThemeWords[intCount][0] = strWord;
				intRand = (int)(Math.random()*100+1);
				strThemeWords[intCount][1] = intRand + "";
				intCount = intCount+1;
			}
			
			//Bubble sorting
			int intCount2;
			String strWordTemp;
			String strRandNum;
			
			for(intCount2 = 0; intCount2 < 10-1; intCount2++){
				for (intCount = 0; intCount < 10-1; intCount++){
					//Convert the people's score to integer and compare
					if(Integer.parseInt(strThemeWords[intCount][1]) > Integer.parseInt(strThemeWords[intCount+1][1])){
						//Swap here
						//swap name
						strWordTemp = strThemeWords[intCount][0];
						strThemeWords[intCount][0] = strThemeWords[intCount+1][0];
						strThemeWords[intCount+1][0] = strWordTemp;
						//swap critics
						strRandNum = strThemeWords[intCount][1];
						strThemeWords[intCount][1] = strThemeWords[intCount+1][1];
						strThemeWords[intCount+1][1] = strRandNum;
				
					}
				}
			}
			strWord = strThemeWords[0][0];
			return strWord;
		}
		
		
		public static String UserGuess(String strUserChoice){
			TextInputFile themes = new TextInputFile("themes.txt");
			String strThemeName = "";
			int intCount  = 0;
			//String strThemes [] = new String [5];
			String strChosenTheme = "";
			while(themes.eof() == false){
				strThemeName = themes.readLine();
				if(strThemeName.equalsIgnoreCase(strUserChoice)){
					strChosenTheme = strThemeName;
				}
				
				//strThemes[intCount] = strThemeName;
				//intCount = intCount+1;
			}
			
			//for(intCount = 0; intCount <= intUserChoice; intCount++){
				//if(intUserChoice == intCount){
					//strChosenTheme = strThemes[intCount];
				//}
			//}
			
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
