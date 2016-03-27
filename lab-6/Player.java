

import java.util.Observable;

public class Player extends Observable implements PlayerInterface {
	
	char gamePiece;
	String Name;
	modelConnect BoardField;
	public int row,column,oldRow,oldColumn;
	public Player(){
		
	}
	public Player(modelConnect Field,String nameIs,char Piece){
		Name=nameIs;
		gamePiece=Piece;
		BoardField=Field;
	}
	public char getGamePiece(){
		return gamePiece;
	}
	public String getName(){
		return Name;
	}
	public void nextMove(){
		setChanged();
		notifyObservers("input");
		clearChanged();
		
		//BoardField.dropPieces(position, gamePiece);
		//return position;
	}
	public int getX(){
		return row;
	}
	public int getY(){
		return column;
	}
	public int getOX(){
		return oldRow;
	}
	public int getOY(){
		return oldColumn;
	}
	public void setX(int x){
		oldRow=row;
		row=x;
	}
	public void setY(int y){
		oldColumn=column;
		column=y;
	}
}
