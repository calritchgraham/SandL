import java.util.InputMismatchException;
import java.util.Scanner;

public class HumanPlayer extends Player {
	
	public HumanPlayer(char name){			//constructor
		super(name);
	}
	
	public boolean move(int player, Board board) {
		Player [] playersMove = board.getPlayers();
		Player toBeMoved = playersMove[player];				//player in turn	
		int count = 0;										
		Scanner s = new Scanner(System.in);			
		boolean hasWon = false;						
		
		do{												//must be one iteration				
			System.out.println(toBeMoved + ", it is your turn, please enter an integer between 1 and 6");
			try {														
				count  = s.nextInt();								//allows player to enter roll		
				if(count > 6) {										//if roll is greater than 6 repeat loop
					System.out.println("The number you entered is above 6.");
				}
				else if(count < 1) {										//if roll is less than 1 repeat loop
					System.out.println("The number you entered is below 1.");
					count = 7;
				}
			}catch(InputMismatchException e) {					//if anything other than integer is entered					
				System.out.println("You did not enter and integer, please do so now");
			}				
		}while(count > 6);	
			
		hasWon = board.movePosition(toBeMoved, count);
		if(hasWon){
			s.close();
		}
		return hasWon;
	}
	
}
