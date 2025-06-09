
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Font;


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
			
			con.repaint();
			while(blnwaiting == true){
				char chrControl = con.getChar();
			
				if (chrControl == 'p' || chrControl == 'P'){
					blnwaiting = false;
				
					String strUserName = CPTSydneytools.UserNameInfo(con);
					String strUserChoice = CPTSydneytools.Themes(con);
					
					CPTSydneytools.PlayGame(con, strUserName, strUserChoice);
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
			con.sleep(44);
			return strUsername;
		}
		
		
		
		public static String Themes(Console con){
			boolean blnUserChoice = false;
			con.clear();
			String strTheme = "";
			String strUserChoice = "";
			String strThemes [][];
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
			
		//	while(blnUserChoice == false){
				//strUserChoice = con.readLine();
				//con.drawString(strUserChoice,90,400);
				//if(!strUserChoice.equalsIgnoreCase()){
					//con.drawString("Please type again", 200,80);
				//}
			//}
			strUserChoice = con.readLine();
			con.drawString(strUserChoice,90,400);
			con.sleep(44);
			
			//con.repaint();
			return strUserChoice;
			
		}
		
		public static void drawmaingame(Console con, String strWord){
			con.clear();
			int intCount = 0;
			String strGuess = strWord;
			int intWordLength = strWord.length();
			String strTheme = "";
			String strUserChoice = "";
			con.setDrawColor(Color.WHITE);
			con.fillRect(300,0,10,800);
			int intx = 400;
			int intgap = 14;
			for(intCount = 0; intCount < intWordLength; intCount++){
				con.fillRect(intx,350,40,2);
				intx= intx+40+intgap;
			
			}
			con.repaint();
			
			
	
		}
		
		
		public static boolean QuitControl(Console con){
			boolean blnQuit = false;
			char chrQuit = con.getChar();
			if(chrQuit == 'q' || chrQuit == 'Q'){
				blnQuit = true;
	
			}
			return blnQuit;
		}
		
		public static void PlayGame(Console con, String strUserName, String strUserChoice){
			
			con.clear();
			
			String strTheme = "";
			String strWord;
			
			boolean blnCorrect = false;
			int intGuesses = 0;
			int intCount = 0;
			int intWinScore = 0;
			int intx = 408;
			int intrevealedIndexes [];
			intrevealedIndexes = new int[5];
			String strUserHint = "";
			String strUserInput;
			String strHintLetters [][];
			
			strTheme = CPTSydneytools.Themechosen(strUserChoice);
			strWord = CPTSydneytools.ChooseWord(strTheme);
			CPTSydneytools.drawmaingame(con,strWord);
			strUserInput = CPTSydneytools.getUserInput(strWord,intrevealedIndexes,intGuesses,con);
			strHintLetters = CPTSydneytools.UserGuess(strWord,strUserInput,intGuesses, intWinScore, blnCorrect,con);
			intrevealedIndexes[intGuesses] = Integer.parseInt(strHintLetters[intGuesses][1]);
			
			while(blnCorrect == false && intGuesses < 5){
				CPTSydneytools.drawmaingame(con,strWord);
				if(strUserInput.equalsIgnoreCase(strWord)){
					blnCorrect = true;
					break;
				}else{
				
					for(intCount = 0; intCount < intGuesses; intCount++){
					CPTSydneytools.drawUserhint(strHintLetters[intGuesses][0],Integer.parseInt( strHintLetters[intGuesses][1]),intGuesses, con);
					
					}
					strUserInput = CPTSydneytools.getUserInput(strWord,intrevealedIndexes,intGuesses,con);
				strHintLetters = CPTSydneytools.UserGuess(strWord,strUserInput,intGuesses, intWinScore, blnCorrect,con);
				}
				
			}
	
			
			//WORKS THEME & WORD
			
			
			
		}
		
		public static String getUserInput(String strWord, int intIndexes[], int intGuesses,Console con){
			String strUserInput = "";
			
			int intCount = 0;
			int intx = 408;
			int intspacing = 0;
			boolean blntyping = true;
			char chrletter;
			String strletter = "";
			
			Font fntWord = con.loadFont("AmericanTypewriter.ttc", 25);
			con.setDrawFont(fntWord);
			
			
			for (intCount = 0 ; intCount < strWord.length(); intCount++){
				if(intIndexes[intCount] > 0 && intCount != intIndexes[intCount]){
					con.setDrawColor(Color.BLACK);
					con.fillRect(intx-8+(54*intCount),290,40,50);
				}
				
			}
			con.setDrawColor(Color.WHITE);
			while(blntyping == true){
				chrletter = con.getChar();
				if(chrletter == '\n'){
					if(strUserInput.length() == strWord.length()){
						blntyping = false;
						intGuesses = intGuesses+1;
					}
				}else if(chrletter == 8 && strUserInput.length() >= 0){
					strUserInput = strUserInput.substring(0,strUserInput.length()-1);
					intCount = intCount-1;
					intx = intx-54;
					con.setDrawColor(Color.BLACK);
					
					con.fillRect(intx,295,40,50);
				}else if((chrletter >= 97 && chrletter <= 122) || (chrletter >=65 && chrletter <=90)){
					if(strUserInput.length() < strWord.length()){
						strletter = String.valueOf(chrletter).toUpperCase();
						strUserInput = strUserInput+strletter;
						con.setDrawColor(Color.WHITE);
						con.drawString(strletter,intx,295);
						intx = intx +54;
						intCount = intCount+1;
					}
				}
				
				
				con.repaint();
			}
			
			return strUserInput;
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
		
		
		public static String [][] UserGuess(String strWord, String strGuess, int intGuesses, int intWins,boolean blnCorrect, Console con){
			//String strGuess = con.readLine();
			String strWordSplit [][];
			int intRand = 0;
			int intCount = 0;
			int intCount2 = 0;
			int intCount3 =0;
			int intindex = 0;
			String strLetterTemp;
			String strNumTemp;
			String strUserHint = "";
			String strOrigWordSplit [][];
			String strSortedWordSplit[][];
			String strHintLetters [][];
			strOrigWordSplit = new String[strWord.length()][2];
			strSortedWordSplit = new String[strWord.length()][2];
			
			
			if(intGuesses < 5 && !strWord.equalsIgnoreCase(strGuess)){
				for (intCount = 0; intCount < strWord.length(); intCount++){
					intRand = (int) (Math.random()*100+1);
					strOrigWordSplit[intCount][0] = strWord.substring(intCount,intCount+1);
					strOrigWordSplit[intCount][1] = intRand + "";
					strSortedWordSplit[intCount][0] = strWord.substring(intCount,intCount+1);
					strSortedWordSplit[intCount][1] = intRand + "";
				}
				
				//Bubble sort
				for(intCount2 = 0; intCount2 < strWord.length()-1; intCount2++){
					for (intCount = 0; intCount < strWord.length()-1 - intCount2; intCount++){
						//Convert the people's score to integer and compare
						if(Integer.parseInt(strSortedWordSplit[intCount][1]) > Integer.parseInt(strSortedWordSplit[intCount+1][1])){
							strLetterTemp = strSortedWordSplit[intCount][0];
							strSortedWordSplit[intCount][0] = strSortedWordSplit[intCount+1][0];
							strSortedWordSplit[intCount+1][0] = strLetterTemp;
							//swap critics
							strNumTemp = strSortedWordSplit[intCount][1];
							strSortedWordSplit[intCount][1] = strSortedWordSplit[intCount+1][1];
							strSortedWordSplit[intCount+1][1] = strNumTemp;
					
						}
					}
				}
				strUserHint = strSortedWordSplit[intGuesses][0];
				String strHintIndex = strSortedWordSplit[intGuesses][1];
				
				for (intCount = 0; intCount <strWord.length(); intCount++){
					if(strOrigWordSplit[intCount][0].equalsIgnoreCase(strUserHint) && strOrigWordSplit[intCount][1].equals(strHintIndex)){
						intindex = intCount;
						break;
					}
				}
				

			}else if(strWord.equalsIgnoreCase(strGuess)) {
				blnCorrect = true;
			}
			strHintLetters = CPTSydneytools.drawUserhint(strUserHint, intindex, intGuesses, con);
			return strHintLetters;
			
		}
		
		
		public static String[][] drawUserhint(String strUserHint, int intIndex, int intGuesses, Console con){
			Font fntWord = con.loadFont("AmericanTypewriter.ttc", 25);
			con.setDrawFont(fntWord);
			con.setDrawColor(Color.BLACK);
			boolean blnDrawhint = false;
			
			int intx = 408;
			String strHintLetters[][];
			strHintLetters = new String [5][2];
			strHintLetters [intGuesses][0] = strUserHint;
			strHintLetters [intGuesses][1] = intIndex+"";
			
			con.fillRect(intx-8+(54*intIndex),290,40,50);
			con.setDrawColor(Color.BLUE);
			con.drawString(strUserHint.toUpperCase(), intx+(54*intIndex),295);
			con.repaint();
			return strHintLetters;
		}
		
		
}
	
