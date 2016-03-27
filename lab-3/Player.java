import java.util.Scanner;

public class Player implements PlayerInterface{
	static Scanner sc=new Scanner(System.in);
	char gamePiece;
	String Name;
	Connect4Field BoardField;
	public int row,column;
	public Player(){
		
	}
	public Player(Connect4Field Field,String nameIs,char Piece){
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
	public int nextMove(){
		System.out.println("Please enter the column number where you want to put the PIECE");
		int position=sc.nextInt();
		//BoardField.dropPieces(position, gamePiece);
		return position;
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
}
