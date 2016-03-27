import java.util.Scanner;

public class Computer extends Player{
	static Scanner sc=new Scanner(System.in);
	char gamePiece;
	String Name;
	public int row,column;
	Connect4Field BoardField;
	public Computer(Connect4Field Field,String nameIs,char Piece){
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
	public int getX(){
		return row;
	}
	public int getY(){
		return column;
	}
	public void setX(int x){
		row=x;
	}
	public void setY(int y){
		column=y;
	}
	public int nextMove(){
		int colToBeUsed=18;
		int humanScore=BoardField.humanCounterCalc();
		int myScore=BoardField.compCounterCalc();
		if(myScore<humanScore){
			if(BoardField.humanRow==BoardField.checkEmptyRow(BoardField.humanCol)){
				colToBeUsed=BoardField.humanCol;
			}
		}else {
			colToBeUsed=BoardField.winCol;
		}
		return colToBeUsed;
	}
}
