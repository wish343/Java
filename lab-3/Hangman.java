/**
 * Hangman.java
 *
 * Version:			1.0
 *     $Id$
 *
 * @author     	 	Vishal Garg(wIsh)
 * @author      	Shobhit Garg
 *
 * Revisions:
 *     $Log$
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Random;
import java.util.Vector;

/**
 * 
 * This class can be used to play Hangman game between any no of people
 * @param	theWords	Stores the words in the provided file
 * @param	tries		Stores the no of tries of a particular person
 * @param 	inp			Scanner variable to for stream system.in
 * @param	noWords		Tells the total no of words in the file
 * @param	playerTry	Tells the no of wrong tries that the current person made while playing
 * @param	pic			2-D array to print the Hangman pic
 */
public class Hangman {
	static Vector<String> theWords=new Vector<String>();
	static Vector<Integer> tries=new Vector<Integer>();
	static int noWords,playerTry;
	static Scanner inp=new Scanner(System.in);
	static int[][] pic={
		{9,3,3,3,3,4,4,4,9},
		{9,3,9,3,9,9,4,9,9},
		{9,3,3,9,9,9,5,9,9},
		{9,3,9,9,9,5,9,5,9},
		{9,2,9,9,9,9,5,9,9},
		{9,2,9,9,8,8,6,8,8},
		{9,2,9,9,9,9,6,9,9},
		{9,2,9,9,9,7,9,7,9},
		{1,1,1,9,7,9,9,9,7}
	};
	
	/**
	 * This is the main function 
	 * @param 		args			Input line arguments 
	 * @param		names			Stores the names of the players sorted according to scores2
	 * @param		scores			Stores unsorted scores of players
	 * @param		scores2			Stores sorted scores of players
	 * @param		lengthString	Length of input line arguments args
	 * @param		wantToPlay		Stores if players want to play more
	 * @throws FileNotFoundException
	 */
	public static void main(String args[]) throws FileNotFoundException{
		fetchWords(args[0]);
		int counter=0;
		Vector<String> names=new Vector<String>();
		Vector<Integer> scores=new Vector<Integer>();
		Vector<Integer> scores2=new Vector<Integer>();
		int lengthString=args.length;
		String wantToPlay="y";
		
		for(int playerNo=0;playerNo<lengthString-1;playerNo++){
			scores.add(0);
			tries.add(0);
		}
		
		do{
			for(int playerNo=0;playerNo<lengthString-1;playerNo++){
				System.out.println("\n\n\n\n\n\n\n\n\n\n");
				System.out.println("Player "+args[playerNo+1]+" get ready");
				int playerScore=play();
				scores.set(playerNo, scores.elementAt(playerNo)+playerScore);
				tries.set(playerNo, tries.elementAt(playerNo)+playerTry);
			}
			System.out.println("\n\n Do you want to play more?(Enter y to play)");
			wantToPlay=inp.next();
		}while(wantToPlay.equals("y")||wantToPlay.equals("Y"));
		
		
		for(int playerNo=0;playerNo<lengthString-1;playerNo++){
			int playerScore=scores.elementAt(playerNo);
			int playertries=tries.elementAt(playerNo);
			if(!names.isEmpty()){
				counter=0;
				while(counter<names.size()&&(playerScore<scores2.elementAt(counter)||(playerScore==scores2.elementAt(counter)&&playertries>tries.elementAt(counter)))){
					counter++;
				}
			
				names.insertElementAt(args[playerNo+1], counter);
				scores2.insertElementAt(playerScore, counter);
			}else{
				names.add(args[playerNo+1]);
				scores2.add(playerScore);
			}
		}
		
		System.out.println("Top players are");
		for(int i=0;i<names.size()&&i<4;i++){
			System.out.print(names.elementAt(i)+":\t\t");
			System.out.println(scores2.elementAt(i));
		}
	}
	
	/**
	 * This function gets all the words from given file name and stores it in a vector
	 * 
	 * @param 		fileName					Takes filename as input to read words from it
	 * @param		path						Stores the path of the file
	 * @param		sc							Scanner to read from file
	 * @param		word						Stores the word fetched from the file
	 * 
	 * @throws 		FileNotFoundException
	 * 			
	 * @return
	 */
	public static void fetchWords(String fileName)throws FileNotFoundException{
		System.out.println("Please input the complete path where the file is saved");
		String path=inp.nextLine();
		File file = new File (path+fileName); 
		Scanner sc = new Scanner(file);
		while(sc.hasNext())
		{
			String word = sc.next();
			theWords.add(word);
			noWords++;
		}
		sc.close();
	}
	
	/**
	 * This function prints the Hangman pic
	 * @param 	fault			Takes no of faults a user has made till now
	 * 
	 * @return
	 */
	public static void drawPic(int fault){
		//System.err.println("Value of fault is "+fault);
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				System.err.print(pic[i][j]<=fault?"#":" ");
			}
			System.err.println("");
		}
	}
	
	/**
	 * This is the function where Hangman game is played once
	 * 
	 * @param		word		Stores a random word fetched from the vector of words
	 * @param		dotString	Stores the string which a user can see
	 * @param		extra		String used to populate a string after a guess
	 * @param		input		Stores input taken from user
	 * 
	 * @return					Point scored
	 */
	public static int play(){
		playerTry=0;
		String word=getWord().toLowerCase();
		String dotString="";
		String extra;
		String input="";
		
		int fault=0,currentIndex;
		int length=word.length();
				
		for(int i=0;i<length;i++){
			dotString+=".";
		}
		
		while(dotString.indexOf(".")!=-1){
			System.out.println(dotString);
			System.out.print("Please enter your guess :\t");
				input=inp.next();
			
			currentIndex=word.indexOf(input);
			if(currentIndex!=-1){
				while(currentIndex!=-1){
					extra="";
					for(int j=0;j<length;j++){
						extra+=(j!=currentIndex)?dotString.charAt(j):word.charAt(j);
					}
					dotString=extra;
					
					if(currentIndex!=length-1){
						currentIndex=word.indexOf(input,currentIndex+1);
					}
					else{
						break;
					}
				}
			}else{
				fault++;
				drawPic(fault);
				if(fault>=8){
					playerTry=fault;
					System.out.println("\n Right word was:\t"+word);
					return -5;
				}
			}
		}
		playerTry=fault;
		return 10;
		
	}
	
	/**
	 * This function return a  random word from the vector of words
	 * @param		rand		Random object to generate a random number
	 * @param		rInt		Stores a randomly generated number
	 * 
	 * @return					Random word
	 */
	public static String getWord(){
		Random rand=new Random();
		int rInt=rand.nextInt(noWords);
		return theWords.elementAt(rInt);
	}

}