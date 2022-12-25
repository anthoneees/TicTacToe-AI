/**
 * abstract class which hold the bones for both AIPlayer and UserPlayer classes
 * @author anthony snider
 *
 */
public abstract class Player {
	/**
	 * abstract method which will allow each player to choose from a list
	 *of all possible moves 
	 * @param game current game 
	 * @return returns chosen TicTacToe board 
	 */
	public abstract TicTacToe chooseMove(TicTacToe game);
	/**
	 * gives boardValue by checking if player has won lost or draw
	 * @param game current game
	 * @return returns value depending on status of game
	 */
	public double boardValue(TicTacToe game) {
		if(game.checkWin(this) == true) {
			return 1.0;
		}
		else if(game.checkLose(this) == true) {
			return -1.0;
		}
		else {
			return 0.0;
		}
	}
}
