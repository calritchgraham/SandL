//2225081G
import java.util.Random;


public class Player {
	
	private char name;
	private int position;
	
	public Player(char name) {					//constructor
		this.name = name;
		this.position = 0;						//all players start at location 0
	}
	
	public int getPosition() {					//getters and setters
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String toString() {						// player toString
		return (Character.toString(getName()));		//cast to string and return		
	}
		
	public char getName() {
		return name;
	}
	
	public boolean move(int player, Board board) {
		Player [] playersMove = board.getPlayers();
		Player toBeMoved = playersMove[player];				//player in turn
		
		int count = 0;													
		
		Random r = new Random();					//random number for dice throw between 1 and 6
		count = r.nextInt(6) + 1; 								
		
		return board.movePosition(toBeMoved, count);
		}
	
	public static void main(String[] args) {
		
		Player playerOne = new Player ('C');		//creation of player
		Square squareOne  = new Square(47);			//creation of Square
		squareOne.players[0] = playerOne;			//assign Player to Square
						
		System.out.println(playerOne);				//stringTo Player
		System.out.println(squareOne);				//stringTo Square
		
	}	
		

}
