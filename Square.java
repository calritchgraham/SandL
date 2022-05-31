public class Square {
	
	protected int position;
	protected int delta;
	protected Player[] players = new Player[1];
	protected int maxPosition;		
	
	public Square(int position){		//constructor
		
		this.position = position;
		delta = 0;
		players = new Player[1];
		maxPosition = 0;
	}			

	public String toString() {								//toString					
		
		int formatPos = String.valueOf(getMaxPosition()).length() + 2;		//max position of square array to format position
		String format = "%" + formatPos + "d";		
		String play = "";
		String pos = String.format(format,getPosition());
		String del = String.format("%3s", getDelta());                //deltas 3 characters in length
		
		for(int i = 0; i<players.length; i++) {						//display players
			if(players[i]!=null) {				
				play +=String.format("%2s", players[i]);			//if player present display character with space
			}else {
				play+="  ";											//if no player at display 2 spaces for formatting
			}		
		}
		
		if(del.equals("  0")) {										//if delta is 0 display blank
			return (play+pos+("("+"   "+")"));
		}else {
			return (play+pos+("("+del+")"));						//if non-zero delta present display			
		}
	}	 						
	
	public int getMaxPosition() {					//getters and setters
		return maxPosition;
	}

	public void setMaxPosition(int maxPosition) {
		this.maxPosition = maxPosition;
	}

	public int getDelta() {				
		return delta;
	}

	public int getPosition() {
		return position;
	}
	
	public Player[] getPlayers() {
		return players;
	}

	public void setPlayers(Player[] players) {
		this.players = players; 
	}

	public void setDelta(int delta) {
		this.delta = delta;
	}
}
