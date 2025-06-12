
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Font;


import arc.*;
public class CPTSydneytools{
	//Drawing the main menu using con.draw
		public static void drawMainMenu(Console con){
			boolean blnwaiting = true;
		
			BufferedImage imgLogo = con.loadImage("hangmanLogo.png");
			con.setDrawColor(Color.WHITE);
			con.fillRect(0,0,1100,800);
			int intCount = 0;
			int intY = 90;
			con.drawImage(imgLogo, 300,0);
			con.repaint();
			for(intCount = 0; intCount<5; intCount++){
				con.setDrawColor(new Color(166, 227, 245));
				con.fillRect(50, intY, 200, 50);
				intY = intY+70;
	
			}
			
			
			Font fntMenu = con.loadFont("AmericanTypewriter.ttc", 25);
			con.setDrawFont(fntMenu);
			con.setDrawColor(new Color (32,91,110));
			con.drawString("Play", 125,95);
			con.drawString("Add Theme", 85,165);
			con.drawString("Leaderboard", 75,235);
			con.drawString("Rules", 116,305);
			con.drawString("Quit", 120,375);
			
			con.repaint();
			//Creating key functions using con.getChar()
			while(blnwaiting == true){
				char chrControl = con.getChar();
			
				if (chrControl == 'p' || chrControl == 'P'){
					blnwaiting = false;
				
					String strUserName = CPTSydneytools.UserNameInfo(con);
					String strUserChoice = CPTSydneytools.Themes(con);
					
					CPTSydneytools.PlayGame(con, strUserName, strUserChoice);
				}else if (chrControl == 'q' || chrControl == 'Q'){
					blnwaiting = false;
				
					System.exit(0);
				}else if (chrControl == 'v' || chrControl == 'V'){
					blnwaiting = false;
					CPTSydneytools.drawLeaderBoard(con);
		
				}else if (chrControl == 'a' || chrControl == 'A'){
					blnwaiting = false;
					CPTSydneytools.addTheme(con);
					drawMainMenu(con);
				}
			}
			con.sleep(33);
		
		}
		//Method for if user wants to add a new theme
		public static void addTheme(Console con) {
        con.clear();
        con.setDrawColor(Color.WHITE);
        con.fillRect(0, 0, 1100, 800);

        Font fntWord = con.loadFont("AmericanTypewriter.ttc", 25);
        con.setDrawFont(fntWord);
        con.setDrawColor(Color.BLACK);

        con.drawString("Enter new theme name (e.g. Teachers):", 50, 100);
        String strThemeName = con.readLine();
        String strFilename = strThemeName + ".txt";

        TextOutputFile newTheme = new TextOutputFile(strFilename);
        con.drawString("Enter words for the theme. Type 'stop' to finish.", 50, 150);
		
        while (true) {
            String strWord = con.readLine();
            if (strWord.equalsIgnoreCase("stop")) break;
            if (strWord.length() >= 7) {
                newTheme.println(strWord);
            } else {
                con.drawString("Word must be 7+ letters.", 50, 300);
            }
        }
        newTheme.close();

        // Adding new theme into master file themes.txt
        TextOutputFile themeList = new TextOutputFile("themes.txt", true);
        themeList.println(strFilename);
        themeList.close();

        con.drawString("Theme added successfully!", 50, 400);
        con.sleep(1000);
    }
		//Getting username for user for leaderboard later
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
		
		//Printing all the themes to screen and asking user to choose a theme
		
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
			themeNames.close();
	
			strUserChoice = con.readLine();
			con.drawString(strUserChoice,90,400);
			con.sleep(44);
			
			return strUserChoice;
			
		}
		//drawing main game, including hangman sign, and word underlines
		public static void drawmaingame(Console con, String strWord, int intGuesses){
			con.clear();
			if(intGuesses > 0){
				if(intGuesses == 1 ){
					BufferedImage imghangman1 = con.loadImage("hangman1.jpg");
					con.drawImage(imghangman1, 0,0);
					
				}else if(intGuesses == 2){
					BufferedImage imghangman2 = con.loadImage("hangman2.jpg");
					con.drawImage(imghangman2, 0,0);

				}else if(intGuesses == 3 ){
					BufferedImage imghangman3 = con.loadImage("hangman3.jpg");
					con.drawImage(imghangman3, 0,0);

				}else if(intGuesses == 4){
					BufferedImage imghangman4 = con.loadImage("hangman4.jpg");
					con.drawImage(imghangman4, 0,0);

				}else if(intGuesses ==5){
					BufferedImage imghangman5 = con.loadImage("hangman5.jpg");
					con.drawImage(imghangman5, 0,0);

				}else if(intGuesses == 6 ){
					BufferedImage imghangman6 = con.loadImage("hangman6.jpg");
					con.drawImage(imghangman6, 0,0);

				}
			}else{
				BufferedImage imghangman0 = con.loadImage("hangman0.jpg");
				con.drawImage(imghangman0, 0,0);
			}
			
			int intCount = 0;
			String strGuess = strWord;
			int intWordLength = strWord.length();
			String strTheme = "";
			String strUserChoice = "";
			
			con.setDrawColor(Color.BLACK);
			con.fillRect(300,0,800,800);
			con.setDrawColor(Color.WHITE);
			con.fillRect(300,0,10,800);
			int intx = 400;
			int intgap = 14;
			for(intCount = 0; intCount < intWordLength; intCount++){
				con.fillRect(intx,350,30,2);
				intx= intx+30+intgap;
			
			}
			con.repaint();
		}
		
		
		//Main game method, where all other methods will be accessed from 
		public static void PlayGame(Console con, String strUserName, String strUserChoice){
			
			con.clear();
			boolean blnPlayAgain = false;
			String strTheme = "";
			String strWord;
			int intRounds = 0;
			boolean blnCorrect = false;
			int intGuesses = 0;
			int intCount = 0;
			int intWinScore = 0;
			int intx = 408;
			int intRevealedIndexes [];
			intRevealedIndexes = new int[5];
			String strUserHint = "";
			String strUserInput = "";
			String strHintLetters [][] = new String [5][2];
			Font fntWord = con.loadFont("AmericanTypewriter.ttc", 30);

			con.setDrawFont(fntWord);
			
			strTheme = CPTSydneytools.Themechosen(strUserChoice);
			strWord = CPTSydneytools.ChooseWord(strTheme, intRounds);
			//creating a loop for how many times user can guess
			
			while(intGuesses < 6){
				CPTSydneytools.drawmaingame(con,strWord, intGuesses);
			//
				for (intCount = 0; intCount < intGuesses; intCount++){
					if(strHintLetters[intCount][0] != null && !strHintLetters[intCount][0].isEmpty()){
						CPTSydneytools.drawUserhint(strHintLetters[intCount][0], Integer.parseInt(strHintLetters[intCount][1]),intCount, con);
					}
				}
				
				strUserInput = CPTSydneytools.getUserInput(strWord,strHintLetters,intGuesses,con);
			
				if(strUserInput.equalsIgnoreCase(strWord)){
					blnCorrect = true;
					
					break;
				
				}else{
					strHintLetters = CPTSydneytools.UserGuess(strWord, strUserInput, intGuesses, intWinScore, blnCorrect, con);
					CPTSydneytools.drawUserhint(strHintLetters[intGuesses][0], Integer.parseInt(strHintLetters[intGuesses][1]), intGuesses, con);

					intGuesses = intGuesses +1;
				}
				
					
			
			}
			
			if(intGuesses == 6 && !strUserInput.equalsIgnoreCase(strWord)){
				blnPlayAgain = CPTSydneytools.playerWinorLose(blnCorrect, strUserName, intWinScore, con);
			}
			if(blnPlayAgain == true){
				intGuesses = 0;
				CPTSydneytools.drawMainMenu(con);
			}
			
			
		}
		//Getting users input using con.getChar
		public static String getUserInput(String strWord, String [][] strHintLetters, int intGuesses,Console con){
			String strUserInput = "";
			con.clear();
			int intCount = 0;
			int intCount2 = 0;
			int intCount3 =0;
			int intx = 408;
			int intx2 = 408;
			int intspacing = 0;
			boolean blntyping = true;
			char chrletter;
			String strletter = "";
			
			Font fntWord = con.loadFont("AmericanTypewriter.ttc", 25);

			con.setDrawFont(fntWord);
			con.setDrawColor(Color.WHITE);
			for(intCount2 =0; intCount2 < strWord.length(); intCount2++){
				boolean blnhintspace = false;
				for(intCount3 =0; intCount3 < intGuesses; intCount3++){
					if(strHintLetters[intCount3][1] == null){
						break;
					}
					if(intCount2 == Integer.parseInt(strHintLetters[intCount3][1])){
						blnhintspace = true;
						con.setDrawColor(Color.BLUE);
						con.drawString(strHintLetters[intCount3][0], 400*(42*intCount2),315);
		
						break;
					}
				
				}
				if(!blnhintspace){
					con.setDrawColor(Color.BLACK);
					con.fillRect(408 + (42*intCount2), 290,40,50);
					
				}
				
			}
			while(blntyping == true){

				chrletter = con.getChar();
				if(chrletter == '\n'){
					if(strUserInput.length() == strWord.length()){
						blntyping = false;
						
					}
				}else if(chrletter == 8 && strUserInput.length() > 0){
					strUserInput = strUserInput.substring(0,strUserInput.length()-1);
					intCount = intCount-1;
					intx = intx-42;
					con.setDrawColor(Color.BLACK);
					con.fillRect(intx,295,40,50);
					
				}else if((chrletter >= 97 && chrletter <= 122) || (chrletter >=65 && chrletter <=90)){
					if(strUserInput.length() < strWord.length()){
						strletter = String.valueOf(chrletter).toUpperCase();
						strUserInput = strUserInput+strletter;
						con.setDrawColor(Color.BLACK);
						con.fillRect(intx,290, 40, 50);
						con.setDrawColor(Color.WHITE);
						con.drawString(strletter,intx,295);
						intx = intx +42;
						intCount = intCount+1;
					}
						
				}
				
				con.repaint();
				
			}
							
			System.out.println(intGuesses);
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
		//Using Bubble sort to choose a randomized word, and sort each word after that.
		public static String ChooseWord(String strTheme, int intRounds){
			TextInputFile currentTheme = new TextInputFile(strTheme);
			
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
					
					if(Integer.parseInt(strThemeWords[intCount][1]) > Integer.parseInt(strThemeWords[intCount+1][1])){
					
						strWordTemp = strThemeWords[intCount][0];
						strThemeWords[intCount][0] = strThemeWords[intCount+1][0];
						strThemeWords[intCount+1][0] = strWordTemp;
				
						strRandNum = strThemeWords[intCount][1];
						strThemeWords[intCount][1] = strThemeWords[intCount+1][1];
						strThemeWords[intCount+1][1] = strRandNum;
				
					}
				}
			}
			strWord = strThemeWords[intRounds][0];
			return strWord;
		}
		
		//Taking users guess and if its wrong will turn the randomized word into a series of letters to give one as a hint
		public static String[][] UserGuess(String strWord, String strGuess, int intGuesses, int intWins,boolean blnCorrect, Console con){
			
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
			strHintLetters = new String[5][2];
			strOrigWordSplit = new String[strWord.length()][2];
			strSortedWordSplit = new String[strWord.length()][2];
			
			
			if(intGuesses < 6 && !strWord.equalsIgnoreCase(strGuess)){
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
						
						if(Integer.parseInt(strSortedWordSplit[intCount][1]) > Integer.parseInt(strSortedWordSplit[intCount+1][1])){
							strLetterTemp = strSortedWordSplit[intCount][0];
							strSortedWordSplit[intCount][0] = strSortedWordSplit[intCount+1][0];
							strSortedWordSplit[intCount+1][0] = strLetterTemp;
					
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
			CPTSydneytools.drawUserhint(strUserHint, intindex, intGuesses, con);
			for(intCount = 0; intCount < intGuesses; intCount++){
				strHintLetters [intGuesses][0] = strUserHint;
				strHintLetters[intGuesses][1] = String.valueOf(intindex);
			}
		
			return strHintLetters;
			
		}
		//Drawing the randomized hint onto screen
		
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
			
			con.fillRect(intx+(42*intIndex),290,30,50);
			con.setDrawColor(Color.BLUE);
			con.drawString(strUserHint.toUpperCase(), intx+(42*intIndex),295);
			con.repaint();
			return strHintLetters;
		}
		
		//Saving Username and winscore onto leaderboard
		public static void saveToLeaderboard(String strUserName, int intWinScore){
			TextOutputFile savetoBoard = new TextOutputFile("LeaderBoard,txt", true);
			savetoBoard.println(strUserName);
			savetoBoard.println(intWinScore);
			
			savetoBoard.close();
		}
		
		//drawing leaderboard on screen
		public static void drawLeaderBoard(Console con){
			String strUserName;
			int intWinScore;
			TextInputFile savetoBoard = new TextInputFile("LeaderBoard,txt");
			Font fntWord = con.loadFont("AmericanTypewriter.ttc", 25);
			
			con.setDrawFont(fntWord);
			con.setDrawColor(Color.BLACK);
			con.fillRect(0,0,1100,900);
			con.setDrawColor(Color.WHITE);
			int inty = 230;
			con.drawString("LEADERBOARD RANKING", 200, 10);
			fntWord = con.loadFont("AmericanTypewriter.ttc", 20);
			while(savetoBoard.eof() == false){
				con.setDrawColor(Color.WHITE);

				strUserName = savetoBoard.readLine();
				intWinScore = savetoBoard.readInt();
				
				con.drawString(strUserName,230, inty);
				con.drawString(String.valueOf(intWinScore),600, inty);
				inty += 40;
			}
			con.repaint();
			
			char chrControl = con.getChar();
			if(chrControl == 'q' || chrControl == 'Q'){
				System.exit(0);
			}
			savetoBoard.close();
		}
		//seeing if user wants to play again or not
		public static boolean playerWinorLose(boolean blnCorrect,String strUserName, int intWinScore, Console con){
			char chrResponse;
			char chrControl;
			String strletter;
			boolean blnPlayAgain = false;
					
			String strResponse = "";
			Font fntWord = con.loadFont("AmericanTypewriter.ttc", 25);
			con.setDrawFont(fntWord);
			
			con.setDrawColor(Color.WHITE);
			if(blnCorrect == false){
				con.setDrawColor(Color.RED);
				con.drawString("You Lose", 570, 50);
				con.drawString("Would you like to play again? (Y/N) ",470, 140);
				con.repaint();
				chrControl = con.getChar();
				if (chrControl == 'Y' || chrControl == 'y'){
					blnPlayAgain = true;
					
				}else if  (chrControl == 'N' || chrControl == 'n'){
					con.setDrawColor(Color.BLUE);
					con.fillRect(400,60, 500, 200);
					con.setDrawColor(Color.WHITE);
					con.drawString("View Leaderboard (v)",500, 130);
					con.drawString("Quit Game (q)",500, 180);
					
					con.repaint();
					chrControl = con.getChar();
					if (chrControl == 'v' || chrControl == 'V'){
						CPTSydneytools.saveToLeaderboard(strUserName,intWinScore);
						CPTSydneytools.drawLeaderBoard(con);
					}else if(chrControl == 'q' || chrControl == 'Q'){
						System.exit(0);
					}
				}
				
				

				
			}else if(blnCorrect == true){
				con.setDrawColor(Color.GREEN);
				con.drawString("You Win!", 570, 50);
				con.drawString("Would you like to play again? (Y/N)",470, 230);
				con.repaint();
				chrControl = con.getChar();
				if (chrControl == 'Y' || chrControl == 'y'){
						blnPlayAgain = true;
				}else if(chrControl == 'N' || chrControl == 'n'){
						con.setDrawColor(Color.BLUE);
						con.fillRect(400,60, 500, 100);
						con.setDrawColor(Color.WHITE);
						con.drawString("View Leaderboard (v)",500, 230);
						con.drawString("Quit Game (q)",500, 240);
						con.repaint();

						chrControl = con.getChar();
						if (chrControl == 'v' || chrControl == 'V'){
							CPTSydneytools.saveToLeaderboard(strUserName,intWinScore);
							CPTSydneytools.drawLeaderBoard(con);
							
						}else if(chrControl == 'q' || chrControl == 'Q'){
							System.exit(0);
						}
				}
				
			}
			return blnPlayAgain;
		}
		
		
		
			
}
	
