import java.util.Scanner;
/**
 * UserPlayer class represent a real person playing the game 
 * fields are name of the player and the input for chooseMove
 * @author anthony snider
 *
 */
public class UserPlayer extends Player{
	private String name;
	private Scanner input = new Scanner(System.in);
	
	/**
	 * constructor which initializes fields 
	 * @param input input scanner used to choose which board they choose
	 * @param name name of the player 
	 */
	public UserPlayer(Scanner input, String name) {
		this.name = name;
		this.input = input;
	}
	/**
	 * toString Override which just returns player name 
	 * @return returns name of player
	 */
	@Override
	public String toString() {
		return name;
	}
	/**
	 * implements Player.chooseMove
	 * prints out current board
	 * prints out all possible moves
	 * asks the player to pick a move
	 * @param game current game
	 * @return returns the board the player picks
	 */
	public TicTacToe chooseMove(TicTacToe game) {
		System.out.println(game);
		TicTacToe[] allMoves = game.possbileMoves(this);
		for(int i =0; i< allMoves.length; i++) {
			System.out.println(i);
			System.out.println(allMoves[i]);
		}
		System.out.println("Select one of these options");
		int index = Integer.parseInt(input.next());
		return allMoves[index];
	}
}
