import java.util.Vector;

/**
* @param	MAX_ROW		max number of rows in the board
* @param	MAX_COLUMN	max number of columns in the board
* @param	gameBoard	Board to be used for playing
* @param	position	row of the last move
* @param	columnUsed	Column of the last move
* @param	winPos		row of the winning move updated by counter calculation functions
* @param	winCol		Column of the winning move updated by counter calculation functions
* @param	humanRow	row of the winning move for human
* @param	humanCol	Column of the winning move for human
* @param	compRow		row of the winning move for computer
* @param 	compCol		Column of the winning move for computer
* @param	thePlayers	Vector of type Player to contain the players playing the game
*/
public class Connect4Field {
	int MAX_ROW=9,MAX_COLUMN=25;
	public char[][] gameBoard=new char[9][25];
	int position=-1,columnUsed=-1;
	int winPos,winCol;
	int humanRow,humanCol,compRow,compCol;
	
	public Vector<Player> thePlayers=new Vector<Player>();
	/**
	* Constructor for the class initializing the board whenever an object is created
	*/
	public Connect4Field(){
		initBoard();
	}
	
	/**
	* didLastMoveWin function checks if the last move won or not
	* @param	didWin		Tells if the last move win or not
	* @param	Piece		gamePiece to be checked in gameBoard
	* @param	CaseNo		counter for going to different checks
	*
	* @return	didWin	
	*/
	boolean didLastMoveWin(){
		Boolean didWin=false;
		char Piece=gameBoard[position][columnUsed];
		int caseNo=0;
		while(!didWin){
			if(caseNo>=4){
				return didWin;
			}
			switch(caseNo){
			case 0:didWin=(didWin||checkDown(position, Piece));
				break;
			case 1:didWin=(didWin||checkDownRight(position, Piece));
				break;
			case 2:didWin=(didWin||checkDownLeft(position, Piece));
				break;
			case 3:didWin=(didWin||checkRight(position, Piece));
				break;
			}
			caseNo++;
		}
		
		return didWin;
	}
	
	/**
	* humanCounterCalc function finds the score of Human after a move when playing against computer
	* 
	* @param	counter		Stores the maximum score of Human
	* @param	tempcounter	Stores intermediate scores
	* @param	aPlayer		Refers to human player
	* @param	humanCol	gets value of last move and stores best Column at end
	* @param	humanRow	gets value of last move and stores best Row at end
	*/
	public int humanCounterCalc(int humanCol1,int humanRow1,int currentCounter){
		int counter=currentCounter,tempcounter=0;		
		humanCol=humanCol1;
		columnUsed=humanCol;
		humanRow=humanRow1;
		position=humanRow;
		char Piece=gameBoard[humanRow][humanCol];
		tempcounter=counterRight(Piece, counter);
		if(tempcounter>counter){
			counter=tempcounter;
		}
		if(counter!=3){
			tempcounter=counterUp(Piece, counter);
			if(tempcounter>=counter){
				if(winPos==checkEmptyRow(winCol)){
					counter=tempcounter;
				}
			}
			if(counter!=3){
				tempcounter=counterDownLeft(Piece, counter);
				if(tempcounter>=counter){
					if(winPos==checkEmptyRow(winCol)){
						counter=tempcounter;
					}
				}
				if(counter!=3){
					tempcounter=counterDownRight(Piece, counter);
					if(tempcounter>=counter){
						if(winPos==checkEmptyRow(winCol)){
							counter=tempcounter;
						}
					}
				}
			}
		}
		humanRow=winPos;
		humanCol=winCol;
		return counter;
	}
	/**
	 * humanScoreCalc Calculates score of human according to lastMove and Move before that
	 * 
	 * @param	counter1		stores Score of last move
	 * @param	counter2		stores Score of move before last Move
	 * 
	 * @return					Returns the Score	
	 */
	public int humanScoreCalc(){
		int counter1=0,counter2=0;
		Player aPlayer=thePlayers.elementAt(0);
		counter1 = humanCounterCalc(aPlayer.getY(),aPlayer.getX(),counter1);
		counter2 = humanCounterCalc(aPlayer.getOY(),aPlayer.getOX(),counter1);
		if(counter2>counter1){
			return counter2;
		}else{
			return counter1;
		}
	}
	
	/**
	 * compScoreCalc Calculates score of comp according to lastMove and Move before that
	 * 
	 * @param	counter1		stores Score of last move
	 * @param	counter2		stores Score of move before last Move
	 * 
	 * @return					Returns the Score	
	 */
	public int compScoreCalc(){
		int counter1=0,counter2=0;
		Player aPlayer=thePlayers.elementAt(1);
		char Piece=thePlayers.elementAt(1).getGamePiece();
		counter1 = compCounterCalc(aPlayer.getY(),aPlayer.getX(),counter1,Piece);
		counter2 = compCounterCalc(aPlayer.getOY(),aPlayer.getOX(),counter1,Piece);
		if(counter2>counter1){
			return counter2;
		}else{
			return counter1;
		}
	}
	
	/**
	* compCounterCalc function finds the score of Computer
	* 
	* @param	counter		Stores the maximum score of Computer
	* @param	tempcounter	Stores intermediate scores
	* @param	aPlayer		Refers to Computer player object
	* @param	compCol		gets value of last move and stores best Column at end
	* @param	compRow		gets value of last move and stores best Row at end
	*/
	public int compCounterCalc(int compCol1,int compRow1,int currentCounter,char compPiece){
		int counter=0,tempcounter=0;
		compCol=compCol1;
		columnUsed=compCol;
		compRow=compRow1;
		position=compRow;
		char Piece=compPiece;
		tempcounter=counterRight(Piece, counter);
		if(tempcounter>counter){
			counter=tempcounter;
		}
		if(counter!=3){
			tempcounter=counterUp(Piece, counter);
			if(tempcounter>=counter){
				if(winPos==checkEmptyRow(winCol)){
					counter=tempcounter;
				}
			}
			if(counter!=3){
				tempcounter=counterDownLeft(Piece, counter);
				if(tempcounter>=counter){
					if(winPos==checkEmptyRow(winCol)){
						counter=tempcounter;
					}
				}
				if(counter!=3){
					tempcounter=counterDownRight(Piece, counter);
					if(tempcounter>=counter){
						if(winPos==checkEmptyRow(winCol)){
							counter=tempcounter;
						}
					}
				}
			}
		}
		compRow=winPos;
		compCol=winCol;
		return counter;
	}
	
	/**
	* checkEmptyRow function finds the first Row in a given column which is empty
	* @param	rowCounter		Stores the counter of row which is being checked for emptiness
	*/
	public int checkEmptyRow(int column){
		int rowCounter=MAX_ROW-1;
		for(;rowCounter>=0;rowCounter--){
			if(gameBoard[rowCounter][column]=='o'){
				break;
			}
		}
		return rowCounter;
	}
	
	/**
	 * counterRight function checks for the moves Player can play horizontally to win the game 
	 * @param	column			Counter for column inside loop
	 * @param	row				Counter for Row inside loop
	 * @param	tempcolumn		Stores Column of probable move by Player to win game
	 * @param	temprow			Stores Row of probable move by Player to win game
	 * @param	set				Counter to find the right position in case there are multiple empty spaces where a move can be made
	 * @param	tempCounter		Stores temporary counters which are compared after loops
	 * 
	 * @return					Score of the current iteration
	 */
	public int counterRight(char Piece,int currentCounter){
		int column,row=position,counter=0,tempCounter=currentCounter;
		int temprow=-1,tempcolumn=-1;
		int set;
		for(int j=0;j<4;j++){
			set=-1;
			column=columnUsed-j;
			counter=0;
			temprow=tempcolumn=-1;
			try{
				if(gameBoard[row][column]==Piece){
					set=1;
				}
			}catch(Exception e){
				
			}
			for(int i=0;i<4;i++){
				try{
					if(gameBoard[row][column+i]==Piece){
						counter++;
					}else if(gameBoard[row][column+i]=='o'){
						if(set==1&&temprow==-1){
							temprow=row;                                                                                              
							tempcolumn=column+i;
						}else if(set==-1){
							temprow=row;                                                                                              
							tempcolumn=column+i;
						}
					}else{
						counter=0;
						break;
					}
				}catch(Exception e){
					counter=0;
					break;
				}
			}
			if(counter>=tempCounter&&temprow!=-1){
				tempCounter=counter;
				winPos=temprow;
				winCol=tempcolumn;
			}
		}
		return tempCounter;
	}
	
	boolean checkRight(int row,char gamePiece){
		boolean x=false;
		boolean brCounter;
		int column;
		row=position;
		for(int counter=0;counter<4;counter++){
			column=columnUsed-counter;
				brCounter=false;
				for(int i=0;i<4;i++){
					try{
						if(gameBoard[row][column+i]!=gamePiece){
							brCounter=true;
							break;
						}
					}catch(Exception e){
						brCounter=true;
						break;
					}
					
				}
				if(brCounter==false){
					x=true;
				}
		}		
		return x;
	}
	
	/**
	 * counterUp function checks for the moves Player can play vertically to win the game 
	 * @param	column			Counter for column inside loop
	 * @param	row				Counter for Row inside loop
	 * @param	tempcolumn		Stores Column of probable move by Player to win game
	 * @param	temprow			Stores Row of probable move by Player to win game
	 * @param	tempCounter		Stores temporary counters which are compared after loops
	 * 
	 * @return					Score of the current iteration
	 */
	public int counterUp(char Piece,int currentCounter){
		int tempCounter=currentCounter;
		int column,row=position,counter=0;
		int temprow=-1,tempcolumn=-1;
		for(int j=0;j<4;j++){
			column=columnUsed;
			row=position+j;
			counter=0;
			temprow=tempcolumn=-1;
			for(int i=0;i<4;i++){
				try{
					if(gameBoard[row-i][column]==Piece){
						counter++;
					}else if(gameBoard[row-i][column]=='o'){
						if(temprow==-1){
							temprow=row-i;
							tempcolumn=column;
						}
					}else{
						counter=0;
						break;
					}
				}catch(Exception e){
					counter=0;
					break;
				}
			}
			if(counter>=tempCounter&&temprow!=-1){
				tempCounter=counter;
				winPos=temprow;
				winCol=tempcolumn;
			}
		}
		return tempCounter;
	}
	
	boolean checkDown(int row,char gamePiece){
		boolean x=true;
			for(int i=0;i<4;i++){
				try{
					if(gameBoard[row+i][columnUsed]!=gamePiece){
						x= false;
						break;
					}
				}catch (Exception e){
					return false;
				}
			}
		return x;
	}
	
	/**
	 * counterDownRight function checks for the moves Player can play diagnoally from left to right to win the game 
	 * @param	column			Counter for column inside loop
	 * @param	row				Counter for Row inside loop
	 * @param	tempcolumn		Stores Column of probable move by Player to win game
	 * @param	temprow			Stores Row of probable move by Player to win game
	 * @param	set				Counter to find the right position in case there are multiple empty spaces where a move can be made
	 * @param	tempCounter		Stores temporary counters which are compared after loops
	 * 
	 * @return					Score of the current iteration
	 */
	public int counterDownRight(char Piece,int currentCounter){
		int tempCounter=currentCounter;
		int column,row,counter=0;
		int temprow=-1,tempcolumn=-1;
		int set;
		for(int j=0;j<4;j++){
			set=-1;
			row=position-j;
			column=columnUsed-j;
			counter=0;
			temprow=tempcolumn=-1;
			try{
				if(gameBoard[row][column]==Piece){
					set=1;
				}
			}catch(Exception e){
				
			}
			for(int i=0;i<4;i++){
				try{
					if(gameBoard[row+i][column+i]==Piece){
						counter++;
					}else if(gameBoard[row+i][column+i]=='o'){
						if(set==1&&temprow==-1){
							temprow=row+i;
							tempcolumn=column+i;
						}else if(set==-1){
							temprow=row+i;
							tempcolumn=column+i;	
						}
					}else{
						counter=0;
						break;
					}
				}catch(Exception e){
					counter=0;
					break;
				}
			}
			if(counter>=tempCounter&&temprow!=-1){
				tempCounter=counter;
				winPos=temprow;
				winCol=tempcolumn;
			}
		}
		return tempCounter;
	}
	
	boolean checkDownRight(int row,char gamePiece){
		boolean x=false;
		boolean brCounter;
		int column;
		for(int counter=0;counter<4;counter++){
			row=position-counter;
			column=columnUsed-counter;
				brCounter=false;
				for(int i=0;i<4;i++){
					try{
						if(gameBoard[row+i][column+i]!=gamePiece){
							brCounter= true;
							break;
						}
					}catch(Exception e){
						brCounter= true;
						break;
					}
				}
				if(brCounter==false){
					x=true;
				}
		}		
		return x;
	}
	
	/**
	 * counterLeftRight function checks for the moves Player can play diagonally right to left to win the game 
	 * @param	column			Counter for column inside loop
	 * @param	row				Counter for Row inside loop
	 * @param	tempcolumn		Stores Column of probable move by Player to win game
	 * @param	temprow			Stores Row of probable move by Player to win game
	 * @param	set				Counter to find the right position in case there are multiple empty spaces where a move can be made
	 * @param	tempCounter		Stores temporary counters which are compared after loops
	 * 
	 * @return					Score of the current iteration
	 */
	public int counterDownLeft(char Piece,int currentCounter){
		int tempCounter=currentCounter;
		int column,row,counter=0;
		int temprow=-1,tempcolumn=-1;
		int set;
		for(int j=0;j<4;j++){
			set=-1;
			row=position-j;
			column=columnUsed+j;
			counter=0;
			temprow=tempcolumn=-1;
			try{
				if(gameBoard[row][column]==Piece){
					set=1;
				}
			}catch(Exception e){
				
			}
			for(int i=0;i<4;i++){
				try{
					if(gameBoard[row+i][column-i]==Piece){
						counter++;
					}else if(gameBoard[row+i][column-i]=='o'){
						if(set==1&&temprow==-1){
							temprow=row+i;
							tempcolumn=column-i;
						}else if (set==-1){
							temprow=row+i;
							tempcolumn=column-i;
						}
					}else{
						counter=0;
						break;
					}
				}catch(Exception e){
					counter=0;
					break;
				}
			}
			if(counter>=tempCounter&&temprow!=-1){
				tempCounter=counter;
				winPos=temprow;
				winCol=tempcolumn;
			}
		}
		return tempCounter;
	}
	
	boolean checkDownLeft(int row,char gamePiece){
		boolean x=false;
		boolean brCounter;
		int column;
		for(int counter=0;counter<4;counter++){
			row=position-counter;
			column=columnUsed+counter;
				brCounter=false;
				for(int i=0;i<4;i++){
					try{
						if(gameBoard[row+i][column-i]!=gamePiece){
							brCounter= true;
							break;
						}
					}catch(Exception e){
						brCounter= true;
						break;
					}
				}
				if(brCounter==false){
					x=true;
				}			
		}
		return x;
	}
	
	/**
	 * This function initializes the Game Board
	 */
	public void initBoard(){
		int x=MAX_COLUMN,j=0;
		for(int i=0;i<MAX_ROW;i++){
			for(j=0;j<i;j++){
				gameBoard[i][j]=' ';
			}
			x=MAX_COLUMN-2*i;
			while(x>0){
				gameBoard[i][j]='o';
				j++;
				x--;
			}
			for(;j<MAX_COLUMN;j++){
				gameBoard[i][j]=' ';
			}
		}
	}
	
	/**
	 * This function initializes the Players that are going to play the game
	 */
	public void init( Player playerA, Player playerB )
    {
		thePlayers.add(playerA);
		thePlayers.add(playerB);
    }
	
	/**
	 * This function checks if a Piece can be dropped in a given column or not
	 */
	public boolean checkIfPiecesCanBeDroppedIn(int column){
		position=-1;
		if(column<0||column>24){
			System.out.println("Invalid column");
			return false;
		}
		for(;position<8;){
			if(gameBoard[position+1][column]=='o'){
				position++;
			}
			else break;
		}
		if(position!=-1){
			return true;
		}
		else{
			System.out.println("Column is full! Please choose something else");
			return false;
		}
	}
	
	/**
	 * This function Drops  a given character in a given column
	 */
	public void dropPieces(int column, char gamePiece){
		columnUsed=-1;
		if(checkIfPiecesCanBeDroppedIn(column)){
			gameBoard[position][column]=gamePiece;
			columnUsed=column;
		}
		
	}	
	
	/**
	 * This function returns the Board in the form of a String
	 */
	public String toString(){
		String a="";
		for(int i=0;i<9;i++){
			a+=new String(gameBoard[i])+"\n";
		}
		return a;
	}
	
	/**
	 * This function checks whether the game is Draw or Not
	 */
	public boolean isItaDraw()    {
        boolean ans = true;
        for(int count1 = 0;count1<MAX_ROW; count1 ++)    {
            for(int count2 = 0; count2<MAX_COLUMN; count2++)    {
                if(gameBoard[count1][count2] == 'o')    {
                    ans = false;
                    return ans;
                }
            }
        }
        return ans;
    }
	
	/**
	 * playGame function is where actually the game is being played between 2 players
	 * @param	column			Stores the column where current Piece is to be dropped
	 * @param	gameIsOver		Tells whether Game has ended or not
	 */
	
	public  void playTheGame()      {
        int column;
        boolean gameIsOver = false;
        do {
                for ( int index = 0; index < 2; index ++ )      {
                        System.out.println(this);
                        if ( isItaDraw() )      {
                                System.out.println("Draw");
                                gameIsOver = true;
                        } else {
                                column = thePlayers.elementAt(index).nextMove();
                                dropPieces(column, thePlayers.elementAt(index).getGamePiece() );
                                thePlayers.elementAt(index).setX(position);
                                thePlayers.elementAt(index).setY(columnUsed);
                                if ( didLastMoveWin() ) {
                                        gameIsOver = true;
                                        System.out.println("The winner is: " + thePlayers.elementAt(index).getName());
                                        break;
                                }
                        }
                }

        }  while ( ! gameIsOver  );
}
	
	public static void main(String args[]){
	}
}