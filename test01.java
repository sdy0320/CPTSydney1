import arc.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Font;

public class test01{
	public static void main(String[] args){
		Console con = new Console("Fun!", 700,700);
		int intX = 400;
		String strWord = "perseus";
		String strUserInput = "arachne";
		int intGuesses = 0;
		int intWinScore =0;
		boolean blnCorrect = false;
		int intY = 200;
		//String strUserHint = CPTSydneytools.UserGuess(strWord,strUserInput,intGuesses, intWinScore, strCorrect,con);
		boolean blncorrect = CPTSydneytools.UserGuess(strWord,strUserInput,intGuesses, intWinScore, blnCorrect,con);
		//con.println(strUserHint);
		
	}
}
