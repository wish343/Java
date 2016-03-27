
public interface PlayerInterface {

	// this is how your constructor has to be
	// Player(Connect4FieldInterface theField, String name, char gamePiece)
		
		public char getGamePiece();
		public String getName();
		public void  nextMove();
		public int getX();
		public int getY();
		public void setX(int x);
		public void setY(int y);
	}