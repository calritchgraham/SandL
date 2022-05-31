//225081G
public class Board {
	
	private int rows;
	private int columns;
	private Square[][] squares = new Square[rows][columns];
	private Player[] players = new Player[1];						 
	private int position = 0;								//variable outside scope of for loop to increment Square position by 1
	private static int noPlayers = 0;						//variable outside scope of loop to increase players in game by 1
	private int maxPosition;
	
	public Board(int rows, int columns) {					//constructor
		squares = new Square[rows][columns];
		this.rows = rows;
		this.columns = columns;	
		players = new Player[1];
		
		for(int i = 0;i < squares.length;i++) {				//traverse rows
			for(int j = 0;j < squares[i].length;j++) {		//traverse columns
				squares[i][j] = new Square(position++);  	//create Square object with position then incremented by 1				
			}			
		}		
	}
	
	public boolean takeTurns(Board board) {
		Player[] playersTurn = getPlayers();
		int playerTurn = 0;
		boolean go = false;
	
		do{																	//do loop as always one turn will have to be taken for a winner to be declared
			go = playersTurn[playerTurn].move(playerTurn, board);
			playerTurn++;													//next player
			if(playerTurn > noPlayers - 1){									//if next player is above total players reset to first player
				playerTurn = 0;
			}		
		}while(go == false);												//break loop when winner declared
		
		return go;
	}	

	public void addPlayer(char init){ 				//computer player
		Player[] newPlayers = getPlayers();				// get players array
		int length = newPlayers.length;
		maxPosition = getRows() * getColumns() - 1; 
				
		if(noPlayers<length) {  							//need to expand array? no
			newPlayers[noPlayers] = new Player (init);		//add player to array
			setPlayers(newPlayers);							//add array to board	
			noPlayers++;									//increase number of players in game		
		}else{
			int newLength = length + 1; 					//player array needs to be expanded
			Player[] newerPlayers = new Player[newLength];	//create new array
			
			for(int i = 0; i < length; i++) { 				//for loop to copy new to newer					
				newerPlayers[i] = newPlayers[i];
			}
			newerPlayers[newLength-1] = new Player (init);	//add new player to last position on new array
			setPlayers(newerPlayers);						//add array to game
			noPlayers++;									//increase number of players in the game 
		}
			
		setPlayersZero();									//add new player to position 0 array
		System.out.println(init + " has been added to the game.");
		System.out.println(toString());
	}

	public void addHumanPlayer(char init){ 			//human player
		Player[] newPlayers = getPlayers();				// get players array
		int length = newPlayers.length;
			
		if(noPlayers<length) {  							//need to expand array? no
			newPlayers[noPlayers] = new HumanPlayer (init);	//add player to array
			setPlayers(newPlayers);							//add array to board	
			noPlayers++;									//increase number of players in game		
		}else{
			int newLength = length + 1; 					//player array needs to be expanded
			Player[] newerPlayers = new Player[newLength];	//create new array
			
			for(int i = 0; i < length; i++) { 				//for loop to copy new to newer					
				newerPlayers[i] = newPlayers[i];
			}
			
			newerPlayers[newLength - 1] = new HumanPlayer (init);	//add new player to last position on new array
			setPlayers(newerPlayers);							// add array to game
			noPlayers++;										//increase number of players in the game 																			
		}
		
		setPlayersZero();									//add new player to position 0 array
		System.out.println(init + " has been added to the game.");
		System.out.println(toString());
	}	
			
	public void setPlayersZero() {										//add new players to starting position and...
																		//create blank array of same length of players in each array
		for(int i = 0;i < squares.length; i++) {						//traverse rows
			for(int j = 0;j < squares[i].length; j++) {					//traverse columns
					Player[] blankPlayers = new Player[noPlayers];		//create blank array 
					squares[i][j].setPlayers(blankPlayers);				//set player arrays in all squares for even formatting
					squares[i][j].setMaxPosition(maxPosition);			//set highest position in each square for formatting
			}							
		}			
		
		Player[] playersSet = getPlayers();							//get Player array from board
		Player[] playersZero = squares[0][0].getPlayers();
		int noPlayersSet = playersSet.length;						//determine number of players in game
		
		for(int i = 0; i <= noPlayersSet - 1; i++) {				//run through players array	
			if(playersZero[i] == null) {							//add players to position 0 Square if blank
				playersZero[i] = playersSet[i];
			}
		}
		
		
	}
		
	public int getRows() {			//getters and setters
		return rows;
	}

	public int getColumns() {
		return columns;
	}

	public Player[] getPlayers() {
		return players;
	}

	public void setPlayers(Player[] players) {
		this.players = players;
	}
	
	public Square[][] getSquares() {								
		return squares;
	}
	
	public Square getSquare(int position) {				//return square object from position
		
		int row = getLocationRow(position);				//get rows and columns of square
		int column = getLocationColumn(position);		//get square array
		Square[][] squares = getSquares();	
		return squares[row][column];					//return object from the location derived		
	}

	public String toString(){									//print current board method	
        String output = "";
		
        for (int i = squares.length-1; i>=0 ; i--) { 			//traverse through rows in reverse
            if (i % 2 == 0) {									// If current row is even, print from left to right            
            	for (int j = 0; j < squares[0].length; j++) {
                	 output += (squares[i][j]); 
                } 
                output +="\n";									//new line
            }             
            else { 												// If current row is odd, print from right to left             
                for (int j = squares[0].length - 1; j >= 0; j--) { 
                    output += (squares[i][j]); 
                } 
            output += "\n";										// new line
            } 			
		}
		return output;
	}

	public int getPosition(int rows, int columns) { 				//return the position value of a board reference (rows and columns)
		return((getSquares()[rows][columns]).getPosition()); 		//locate the square at [row][column] and run getPosition from Square class 		
	}
	
	public int getLocationRow(int position) { 						//find the location of position value on a board
		int result = 0;												//Initialise output		
		
		for(int i = 0; i < getSquares().length; i ++){				//traverse rows
			for(int j = 0; j< getSquares()[i].length; j ++){		// traverse columns
		      if(getSquares()[i][j].getPosition()==position) {		//if the position of the square at [rows][columns] is equal to the value being looked for
		    	  result = i;										// then return the result (row)
		      }
			}
		}
		return result;
	}
	
	public int getLocationColumn(int position) { 					//find the location of position value on a board
		int result = 0;												//Initialise output		
		
		for(int i = 0; i < getSquares().length; i ++){				//traverse rows
			for(int j = 0; j < getSquares()[i].length; j ++){		// traverse columns
		      if(getSquares()[i][j].getPosition() == position) {	//if the position of the square at [rows][columns] is equal to the value being looked for
		    	  result = j;										// then return the result (column)
		      }
			}
		}
		return result;
	}
	
	public boolean movePosition(Player toBeMoved, int count) {			//once count has been generated (depending on player type) method to move position on board and determine winner
		int currentPosition = (toBeMoved.getPosition());	//current position of player
		int newPosition = currentPosition + count;			//increase position as per throw
		System.out.println(toBeMoved + " you rolled a " + count);	//display throw
		boolean hasWon = false;
		
		Square[][] squaresMove = getSquares();							//get array of squares to edit
		int finalPosition = (getRows()*getColumns())-1;			// last position on the board
		int rowTo = 0;							//starting square will always have delta of 0, so allows program to proceed on the basis 		
		int columnTo = 0;						//that there is no delta on fictitious square beyond the last one			
			
		rowTo = getLocationRow(newPosition);
		columnTo = getLocationColumn(newPosition);
			
		int delta = squaresMove[rowTo][columnTo].getDelta();			//get delta from location of position to be moved to
		int snakeDelta = delta - delta - delta;							//creates positive from negative for formatting
		
		if(delta !=0) {													//alters location if new position has a snake/ladder
			newPosition += delta;
			rowTo = getLocationRow(newPosition);
			columnTo = getLocationColumn(newPosition);			
			
			if(delta < 0) {
				System.out.println(toBeMoved +", you have landed on a snake and go back " + snakeDelta + " places"); //advises of snake/ladder
			}else{
				System.out.println(toBeMoved +", you have landed on a ladder and progress " + delta + " places");
			}
		}
			
		if(newPosition >= finalPosition) {					//if the player has landed on the final position or beyond they have won
			System.out.println(toBeMoved + " has won.");   	//if the player has landed beyond the final position set their position to the final
			newPosition = finalPosition;
			hasWon = true;									//proceed to moving the player to the last square														
		}													
				
		Square squareFrom = getSquare(currentPosition);
		Square squareTo = getSquare(newPosition);
		
		Player playersFrom[] = squareFrom.getPlayers(); 	//current array of players at current position
		Player playersTo[] = squareTo.getPlayers();			//current array of players at new position
		
		int noPlayers = playersFrom.length;										//number of players in the game
				
		for(int i = 0; i <= noPlayers-1; i++) {						//searches the player array at the new location and adds the player to the first empty slot
			if(playersTo[i]==null){			
				playersTo[i] = toBeMoved;							// set player in turn into new square					
				break;
			}																				
		}
		
		for(int i = 0; i <= noPlayers -1; i++){								//searches the player array at the old location for the player reference and deletes it
			try {
				if(playersFrom[i].equals(toBeMoved)){						//arrays are initialised with null values so error could be generated											
					playersFrom[i] = null;									//remove Player at previous current square
				}
			}catch(NullPointerException e){																
			}	
		}	
		toBeMoved.setPosition(newPosition);									//set new position of Player
																
	System.out.println(toString());											//print board with outcome of above
	return hasWon;													//return if player has won
	}	
	
	public static void main(String[] args) {
		
		int row = 10;
		int column = 5;
		int positionTest = 0;						//variable outside scope of loop to increment position by 1
		
		Board test = new Board(row,column);			//create board object		
		Square[][] testSquares = test.getSquares();		//array of nulls here
		
		for(int i=0;i<testSquares.length;i++) {				//traverse rows
			for(int j = 0;j < testSquares[i].length;j++) {		//traverse columns
				testSquares[i][j] = new Square(positionTest++);   //create Square object with position then incremented by 1				
			}
			
		}		
		test.addPlayer('A');				// add players
		test.addPlayer('B');
		System.out.println(test);			// print board
	}
}
	
	



