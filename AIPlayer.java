
/**
 * class for AI player 
 * take field name and the UserPlayer as opponent 
 * @author anthony snider
 *
 */
public class AIPlayer extends Player{
	private String name;
	private Player opponent;
	
	/**
	 * constructor for AIPlayer
	 * @param name name of AI
	 * @param opponent opposing player 
	 */
	public AIPlayer(String name, Player opponent) {
		this.name = name;
		this.opponent = opponent;
	}
	/**
	 * getter for opponent 
	 * @return returns opponent player
	 */
	public Player getOpponent() {
		return opponent;
	}
	/**
	 * setter for opponent
	 * @param player player which will be set to opponent 
	 */
	public void setOpponent(Player player) {
		opponent = player;
	}
	/**
	 * Overrides toString
	 * @return return string which is just name + (AI)
	 */
	@Override
	public String toString() {
		return name + " (AI)";
	}
	/**
	 * calculate the minimum value of all possible boards, represents ai's moves
	 * @param game either current board or one of the future boards when recursively called 
	 * @return returns the lowest value of all possible boards 
	 */
	public double minValue(TicTacToe game) {
		//will store minimum value and current value
		double min = 2.0;
		double value;
		//base case will check if ai has won or tied, ai cannot lose during its own turn so we wont check for it
		//return 1 for win 0 for draw
		if(game.checkWin(this) == true) {
			return 1.0;
		}
		else if(game.checkDraw() == true) {
			return 0.0;
		}
		//else go through all possible moves opponent can make a find the minimum maxValue and store it 
		else {
			TicTacToe[] temp = game.possbileMoves(opponent);
			for(int i = 0; i < temp.length; i++) {
				value = maxValue(temp[i]);
				if(value < min) {
					min = value;
				}
			}
			return min;
		}
	}
	/**
	 * calculate the maximum value of all possible boards represents players moves 
	 * @param game either current board or one of the future boards when recursively called 
	 * @return returns the maximum value of all possible boards 
	 */
	public double maxValue(TicTacToe game) {
		//will store maximum value as well as current value
		double max = -2.0;
		double value;
		//base cases, does not check for win because ai cannot win on players move
		//return -1 for loss and 0 for draw
		if(game.checkLose(this) == true) {
			return -1.0;
		}
		else if(game.checkDraw() == true) {
			return 0.0;
		}
		//else loop through all AI's moves and find the max minValue and return it after all the possible cases have been looped through
		else {
			TicTacToe[] temp = game.possbileMoves(this);
			for(int i = 0; i < temp.length; i++) {
				value = minValue(temp[i]);
				if(value > max) {
					max = value;
				}
			}
			return max;
		}
	}
	/**
	 * implements Player.chooseMove() and is the method that called the recursive methods above
	 * @param game current board that the player just returned
	 * @return returns the move the AI chooses using minValue and maxValue
	 */
	public TicTacToe chooseMove(TicTacToe game) {
		//stores maxValue, index that holds said max value
		double max = -2.0;
		int index = 0;
		double value = 0.0;
		//loop through all AI's possible moves and find the maximum minValue and return that board that contains it
		TicTacToe[] totalList = game.possbileMoves(this);
		for(int i =0; i<totalList.length; i++) {
			value = minValue(totalList[i]);
			if(value > max) {
				max = value;
				index = i;
			}
		}
		return totalList[index];
	}
	/**
	 * Overrides Player.boardValue() and returns maxValue of give TicTacToe game
	 * @param game current board being passed through
	 * @return returns maxValue of game
	 */
	@Override
	public double boardValue(TicTacToe game) {
		return maxValue(game);
	}
}
