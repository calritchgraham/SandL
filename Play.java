//2225081G
public class Play {
	
	public static void main(String[] args) {			
		
		Board board = new Board(25,25);			//create board
									
		board.getSquares()[1][3].setDelta(-3);	//set deltas
		board.getSquares()[3][4].setDelta(3);
		board.getSquares()[3][0].setDelta(-6);
		board.getSquares()[2][2].setDelta(5);
		board.getSquares()[0][4].setDelta(-2);
		board.getSquares()[4][3].setDelta(-5);
		
		board.addPlayer('E');			//add players
		board.addPlayer('C');

		board.takeTurns(board);			//play game	
	}

}
