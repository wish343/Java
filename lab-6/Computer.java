
/**
 * Computer class extends Player class and implements Functioning of a computer
 * 
 * @param	gamePiece	Character being used by Computer to play the game
 * @param	Name		Name of the computer
 * @param	row		Stores the row of the last move
 * @param	column		Stores the column of the last move
 * @param	BoardField	Object of modelConnect to play the game on
 */
public class Computer extends Player{
	char gamePiece;
	String Name;
	public int row,column;
	public int oldRow,oldColumn;
	modelConnect BoardField;
	/**
	* This is the constructor of the class which initilizes the variables according to the parameters provided
	* @param	Field		Object of modelConnect to play the game on
	* @param	nameIs		Name of the computer
	* @param	Piece		character to be used for playing
	*
	* @return			None
	*/
	public Computer(modelConnect Field,String nameIs,char Piece){
		Name=nameIs;
		gamePiece=Piece;
		BoardField=Field;
	}
	/**
	* Returns the gamePiece being used by the Computer
	*/
	public char getGamePiece(){
		return gamePiece;
	}
	/**
	* Returns the name of the Computer
	*/
	public String getName(){
		return Name;
	}
	/**
	* Returns the value of row of the last move
	*/
	public int getX(){
		return row;
	}
	/**
	* Returns the value of the column of the last move
	*/
	public int getY(){
		return column;
	}
	public int getOX(){
		return oldRow;
	}
	public int getOY(){
		return oldColumn;
	}
	/**
	* Sets the value of the row for the last move
	*/
	public void setX(int x){
		oldRow=row;
		row=x;
	}
	/**
	* Sets the value of the column for the last move
	*/
	public void setY(int y){
		oldColumn=column;
		column=y;
	}
	/**
	* nextMove function of the computer returns the value of the column to be used for putting the gamePiece
	* @param	colToBeUsed		Column being returned for DropPieces
	* @param	humanScore		Score of human according to humanCounterCalc function
	* @param	myScore			Score of Computer according to the compCounterCalc function
	*/
	public void nextMove(){
		int colToBeUsed=18;
		int humanScore=BoardField.humanScoreCalc();
		int myScore=BoardField.compScoreCalc();
		if(myScore<humanScore){
			if(BoardField.humanRow==BoardField.checkEmptyRow(BoardField.humanCol)){
				colToBeUsed=BoardField.humanCol;
			}
			else{
				colToBeUsed=BoardField.compCol;
			}
		}else if(myScore>humanScore){
				colToBeUsed=BoardField.compCol;
		}else if(myScore==humanScore){
			if(BoardField.compRow==BoardField.checkEmptyRow(BoardField.compCol)){
				colToBeUsed=BoardField.compCol;
			}else if(BoardField.humanRow==BoardField.checkEmptyRow(BoardField.humanCol)){
				colToBeUsed=BoardField.humanCol;
			}else{
				colToBeUsed=BoardField.compCol;
			}
		}
		BoardField.setColumn(colToBeUsed);
	}
}
