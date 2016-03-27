import java.util.Vector;

public class Connect4Field {
	int MAX_ROW=9,MAX_COLUMN=25;
	public char[][] gameBoard=new char[9][25];
	int position=-1,columnUsed=-1;
	int winPos,winCol;
	int humanRow,humanCol,compRow,compCol;
	
	public Vector<Player> thePlayers=new Vector<Player>();
	public Connect4Field(){
		initBoard();
	}
	
	boolean didLastMoveWin(){
		Boolean didWin=false;
		char Piece=gameBoard[position][columnUsed];
		System.out.println(Piece);
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
	
	public int humanCounterCalc(){
		int counter=0,tempcounter=0;
		System.out.println("The Current size is"+thePlayers.size());
		Player aPlayer=thePlayers.elementAt(0);
		humanCol=aPlayer.getY();
		columnUsed=humanCol;
		humanRow=aPlayer.getX();
		position=humanRow;
		char Piece=gameBoard[humanRow][humanCol];
		tempcounter=counterRight(Piece, counter);
		if(tempcounter>counter){
			counter=tempcounter;
		}
		if(counter!=3){
			tempcounter=counterUp(Piece, counter);
			if(tempcounter>counter){
				counter=tempcounter;
			}
			if(counter!=3){
				tempcounter=counterDownRight(Piece, counter);
				if(tempcounter>counter){
					counter=tempcounter;
				}
				if(counter!=3){
					tempcounter=counterDownLeft(Piece, counter);
					if(tempcounter>counter){
						counter=tempcounter;
					}
				}
			}
		}
		humanRow=winPos;
		humanCol=winCol;
		return counter;
	}
	
	public int compCounterCalc(){
		int counter=0,tempcounter=0;
		columnUsed=compCol=thePlayers.elementAt(1).getY();
		position=compRow=thePlayers.elementAt(1).getX();
		char Piece=thePlayers.elementAt(1).getGamePiece();
		tempcounter=counterRight(Piece, counter);
		if(tempcounter>counter){
			counter=tempcounter;
		}
		if(counter!=3){
			tempcounter=counterUp(Piece, counter);
			if(tempcounter>counter){
				counter=tempcounter;
			}
			if(counter!=3){
				tempcounter=counterDownRight(Piece, counter);
				if(tempcounter>counter){
					counter=tempcounter;
				}
				if(counter!=3){
					tempcounter=counterDownLeft(Piece, counter);
					if(tempcounter>counter){
						counter=tempcounter;
					}
				}
			}
		}
		return counter;
	}
	
	public int checkEmptyRow(int column){
		int j=MAX_ROW-1;
		for(;j>=0;j--){
			if(gameBoard[j][column]=='o'){
				break;
			}
		}
		return j;
	}
	public int counterRight(char Piece,int currentCounter){
		int column,row=position,counter=0,tempCounter=currentCounter;
		int temprow=-1,tempcolumn=-1;
		for(int j=0;j<4;j++){
			column=columnUsed-j;
			counter=0;
			for(int i=0;i<4;i++){
				try{
					if(gameBoard[row][column+i]==Piece){
						counter++;
					}else if(gameBoard[row][column+i]=='o'){
						if(temprow==-1){
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
			if(counter>tempCounter){
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
	
	public int counterUp(char Piece,int currentCounter){
		int tempCounter=currentCounter;
		int column,row=position,counter=0;
		int temprow=-1,tempcolumn=-1;
		for(int j=0;j<4;j++){
			column=columnUsed;
			row=position+j;
			counter=0;
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
			if(counter>tempCounter){
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
	
	public int counterDownRight(char Piece,int currentCounter){
		int tempCounter=currentCounter;
		int column,row,counter=0;
		int temprow=-1,tempcolumn=-1;
		for(int j=0;j<4;j++){
			row=position-j;
			column=columnUsed-j;
			counter=0;
			for(int i=0;i<4;i++){
				try{
					if(gameBoard[row+i][column+i]==Piece){
						counter++;
					}else if(gameBoard[row+i][column+i]=='o'){
						if(temprow==-1){
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
			if(counter>tempCounter){
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
	
	public int counterDownLeft(char Piece,int currentCounter){
		int tempCounter=currentCounter;
		int column,row,counter=0;
		int temprow=-1,tempcolumn=-1;
		for(int j=0;j<4;j++){
			row=position-j;
			column=columnUsed+j;
			counter=0;
			for(int i=0;i<4;i++){
				try{
					if(gameBoard[row+i][column-i]==Piece){
						counter++;
					}else if(gameBoard[row+i][column-i]=='o'){
						if(temprow==-1){
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
			if(counter>tempCounter){
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
	
	public void init( Player playerA, Player playerB )
    {
		thePlayers.add(playerA);
		thePlayers.add(playerB);
		System.out.println("After initialization size is"+thePlayers.size());
    }
	
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
	
	public void dropPieces(int column, char gamePiece){
		columnUsed=-1;
		if(checkIfPiecesCanBeDroppedIn(column)){
			gameBoard[position][column]=gamePiece;
			columnUsed=column;
		}
		
	}	
	
	public String toString(){
		String a="";
		for(int i=0;i<9;i++){
			a+=new String(gameBoard[i])+"\n";
		}
		return a;
	}
	
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
	//	Connect4Field aConnect4Field=new Connect4Field();
	}
}