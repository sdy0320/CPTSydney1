
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Font;
import java.awt.event.KeyListener;

import arc.*;
public class CPTSydneytools{
	//methods 1
		public static void drawMainMenu(Console con){
			boolean blnwaiting = true;
		
		
			con.setDrawColor(Color.WHITE);
			con.fillRect(0,0,300,800);
			int intCount = 0;
			int intY = 90;
			// Start drawing stuff on that background
			for(intCount = 0; intCount<4; intCount++){
				con.setDrawColor(new Color(166, 227, 245));
				con.fillRect(50, intY, 200, 50);
				intY = intY+70;
	
			}
			
			Font fntMenu = con.loadFont("AmericanTypewriter.ttc", 25);
			con.setDrawFont(fntMenu);
			con.setDrawColor(new Color (32,91,110));
			con.drawString("Play", 125,95);
			con.drawString("Leaderboard", 75,165);
			con.drawString("Rules", 116,235);
			con.drawString("Quit", 120,305);
	
			
			while(blnwaiting == true){
				char chrControl = con.getChar();
			
				if (chrControl == 'p' || chrControl == 'P'){
					blnwaiting = false;
				
					CPTSydneytools.UserNameInfo(con);
					CPTSydneytools.Themes(con);
					CPTSydneytools.PlayGame(con);
				}
			}
			
			
		
			
		
			//CPTSydneytools.PlayGame(con);
		}
		
		
		public static String UserNameInfo(Console con){
			String strUsername = "";
			con.setDrawColor(Color.WHITE);
			con.fillRect(0,0,300,800);
			
			con.setDrawColor(new Color(166, 227, 245));
			con.fillRect(50, 200, 200, 50);
			
			con.setDrawColor(new Color (32,91,110));
			con.drawString("Enter name: ", 80,95);
			strUsername = con.readLine();
			con.drawString(strUsername, 100,200);
			con.sleep(33);
			return strUsername;
		}
		
		
		
		public static String Themes(Console con){
			con.clear();
			String strTheme = "";
			String strUserChoice = "";
			con.setDrawColor(Color.BLACK);
			con.fillRect(0,0,300,800);
			BufferedImage imgthemes = con.loadImage("newthemebackground.png");
			con.drawImage(imgthemes, 0, -30);
			
			
			
			// Start drawing stuff on that background
			
			TextInputFile themeNames = new TextInputFile("themes.txt");
			int inty = 120;
			con.setDrawColor(new Color (32,91,110));
			con.drawString("Choose a theme: ", 40,80);
			while(themeNames.eof() == false){
				strTheme = themeNames.readLine();
				
				con.drawString(strTheme,40,inty);
				inty = inty+50;
			}
			themeNames.close();
			strUserChoice = con.readLine();
			con.drawString(strUserChoice,90,400);
			//con.repaint();
			return strUserChoice;
		}
		
		
		public static boolean QuitControl(Console con){
			boolean blnQuit = false;
			char chrQuit = con.getChar();
			if(chrQuit == 'q' || chrQuit == 'Q'){
				blnQuit = true;
				CPTSydneytools.PlayGame(con);
			}
			return blnQuit;
		}
		
		public static void PlayGame(Console con){
			String strUserName;
			String strTest = "";
			String strTheme = "";
			String strWord;
			String strUserChoice;
			String strCorrect = "false";
			int intGuesses = 0;
			int intCount = 0;
			int intWinScore = 0;
			String strUserHint = "";
			//Getting user's name
			strUserName = CPTSydneytools.UserNameInfo(con);
			//Printing Themes
			//TextInputFile themeNames = new TextInputFile("themes.txt");
			
			//con.println("Themes: ");
			//while(themeNames.eof() == false){
				//strTheme = themeNames.readLine();
				//con.println(strTheme);
			//}
			//themeNames.close();
			//Using ThemeChosen method to get the theme the user wanted.
			//con.println("Choose a theme: ");
			//strUserChoice = con.readLine();
			strUserChoice = CPTSydneytools.Themes(con);
			
			strTheme = CPTSydneytools.Themechosen(strUserChoice);
			strWord = CPTSydneytools.ChooseWord(strTheme);
			strUserHint = CPTSydneytools.UserGuess(strWord,intGuesses, intWinScore, strCorrect,con);
			//WORKS THEME & WOD
			//con.println(strTheme);
			//con.println(strWord);
			
			
			for(intCount = 0; intCount<= strWord.length(); intCount++){
				if(strUserHint.equalsIgnoreCase (strWord.substring(intCount, intCount+1))){
					con.println(strUserHint);
				}
			}
			
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
		
		
		public static String UserGuess(String strWord, int intGuesses, int intWins,String strCorrect, Console con){
			String strGuess = con.readLine();
			String strWordSplit [][];
			int intRand = 0;
			int intCount = 0;
			int intCount2 = 0;
			String strLetterTemp;
			String strNumTemp;
			String strUserHint = "";
			strWordSplit = new String[strWord.length()][2];
			while(!strWord.equalsIgnoreCase(strGuess)){
				if (!strGuess.equalsIgnoreCase(strWord)){
					intGuesses = intGuesses + 1;
					for (intCount = 0; intCount <= strWord.length(); intCount++){
						intRand = (int) (Math.random()*100+1);
						strWordSplit[intCount][0] = strWord.substring(intCount,intCount+1);
						strWordSplit[intCount][1] = intRand + "";
					}
					for(intCount2 = 0; intCount2 < strWord.length()-1; intCount2++){
						for (intCount = 0; intCount < strWord.length()-1; intCount++){
							//Convert the people's score to integer and compare
							if(Integer.parseInt(strWordSplit[intCount][1]) > Integer.parseInt(strWordSplit[intCount+1][1])){
								strLetterTemp = strWordSplit[intCount][0];
								strWordSplit[intCount][0] = strWordSplit[intCount+1][0];
								strWordSplit[intCount+1][0] = strLetterTemp;
								//swap critics
								strNumTemp = strWordSplit[intCount][1];
								strWordSplit[intCount][1] = strWordSplit[intCount+1][1];
								strWordSplit[intCount+1][1] = strNumTemp;
						
							}
						}
					}
				
					strUserHint = strWordSplit[intGuesses][0];
				}else{
					intWins = intWins+1;
					strCorrect = "true";
					return strCorrect;
				}
			}
			return strUserHint;
	
		}
		

		
}
