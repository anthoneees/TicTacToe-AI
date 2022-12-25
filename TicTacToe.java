
/**
 * TicTacToe class takes a 3x3 board and 2 players
 * and will create a board and 
 * contains methods such as checkWin/Lose/Draw as 
 * well as calculate all possible moves 
 * @author anthony snider
 *
 */
public class TicTacToe {
	private char[][] board = new char[3][3];
	private Player x;
	private Player o;
	
	/**
	 * Constructor which initializes each player and creates
	 * an empty board
	 * @param x player who will have 'X'
	 * @param o player who will have 'O'
	 */
	public TicTacToe(Player x, Player o) {
		this.x = x;
		this.o = o;
		// loops through board to input '_' char on each index 
		for(int i = 0; i<3; i++) {
			for(int j = 0; j<3; j++) {
				board[i][j] = '_';
			}
		}
	}
	/**
	 * getter for board
	 * @return return current board 
	 */
	public char[][] getBoard(){
		return board;
	}
	/**
	 * setter for board
	 * @param boardSet what the board will be set to 
	 */
	public void setBoard(char[][] boardSet) {
		board = boardSet;
	}
	/**
	 * getter for player x
	 * @return returns player x
	 */
	public Player getX() {
		return x;
	}
	/**
	 * setter for player x
	 * @param xSet sets player x to xSet
	 */
	public void setX(Player xSet) {
		x = xSet;
	}
	/**
	 * getter for player o
	 * @return returns player o 
	 */
	public Player getO() {
		return o;
	}
	/**
	 * setter for o
	 * @param oSet sets player o to oSet
	 */
	public void setO(Player oSet) {
		o = oSet;
	}
	/**
	 * will count and return how many blanks are left on the board
	 * @return returns amount of blanks left on the board
	 */
	public int countBlanks() {
		int count = 0;
		//loops through each index and when '_' is found add 1 to count 
		for(int i = 0; i<3; i++) {
			for(int j = 0; j<3; j++) {
				if (board[i][j] == '_') {
					count++;
				}
			}
		} 
		return count;
	}
	/**
	 * given player return which marker they are using 
	 * @param player player that marker needs to be found for
	 * @return returns marker of give player, either 'X' or 'O'
	 */
	public char markerForPlayer(Player player) {
		if(player.equals(x)) {
			return 'X';
		}
		else{
			return 'O';
		}
	}
	/**
	 * given a player check if that player has won the current game
	 * @param player 
	 * @return
	 */
	public boolean checkWin(Player player) {
		int countChar = 0;
		char match = markerForPlayer(player);
		/*go through each column and if they match the player
		 * character add 1 to count and if at end of loop count
		 * totals up to 3 then there is a win 
		 * reset count to 0 after each outer loop iteration
		 */
		for(int row =0; row<3; row++) {
			for(int col = 0; col<3; col++) {
				if(board[row][col] == match) {
					countChar++;
				}
			}
			if(countChar == 3) {
				return true;
			}
			countChar = 0;
		}
		/**
		 * same things as above but reversed to check for rows 
		 */
		for(int col =0; col<3; col++) {
			for(int row = 0; row<3; row++) {
				if(board[row][col] == match) {
					countChar++;
				}
			}
			if(countChar == 3) {
				return true;
			}
			countChar = 0;
		}
		/**
		 * check if either diagonals are filled, if they are return true, else return false
		 */
		if(board[0][0] == match && board[1][1] == match && board[2][2] == match) {
			return true;
		}
		else if(board[0][2] == match && board[1][1] == match && board[2][0] == match) {
			return true;
		}
		else {
			return false;
		}
	}
	/**
	 * will check if the inputed player has lost 
	 * @param player player which will check if they have lost
	 * @return return true if player has lost
	 */
	public boolean checkLose(Player player) {
		//if player is x check if o has won
		if(Character.compare(markerForPlayer(player), 'X') == 0) {
			if(checkWin(o) == true) {
				return true;
			}
			else {
				return false;
			}
		}
		//else if player is o check if x has won 
		else {
			if(checkWin(x) == true) {
				return true;
			}
			else {
				return false;
			}
		}
	}
	/**
	 * checks if there is a draw 
	 * @return returns true if there is a draw
	 */
	public boolean checkDraw() {
		//this loop checks if the board is filled up and if its not return false 
		if(countBlanks() > 0) {
			return false;
		}
		//if the board is filled up and no one has a win return true 
		else if(checkWin(x) == false && checkLose(x) == false) {
			return true;
		}
		else {
			return false;
		}
	}
	/**
	 * toString override for printing out board 
	 */
	@Override
	public String toString() {
		String temp = "";
		for(char[] x: board) {
			for(char y:x) {
				temp += y + " ";
			}
			temp += "\n";
		}
		return temp;
	}
	/**
	 * will return an array of all possible move the inputed player can make 
	 * @param player player for which we are checking possible moves
	 * @return return array list of all possible moves 
	 */
	public TicTacToe[] possbileMoves(Player player){
		TicTacToe[] totalList = new TicTacToe[countBlanks()];
		// this loop checks for empty spaces, and if there is an empty space
		//create a new board with that space filled in and add it to the list
		//do this for all empty spaces and return the final list 
		int count = 0;
		for(int i = 0; i< 3; i++) {
			for (int j = 0; j<3; j++) {
				if(board[i][j] == '_') {
					TicTacToe mirror = new TicTacToe(x, o);
					for(int n = 0; n<3; n++) {
						for(int m = 0; m<3; m++) {
							mirror.getBoard()[n][m] = board[n][m];
						}
					}
					mirror.getBoard()[i][j] = markerForPlayer(player);
					totalList[count] = mirror;
					count++;
				}
			}
		}
		return totalList;
	}	
}

