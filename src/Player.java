import java.util.*;

/* An abstract class cannot be instantiated. */
public abstract class Player {
	/* Instance variables.
	 * 
	 * Make them protected here so that HumanPlayer and AIPlayer can access them.
	 */
    protected int playerIndex; //playerIndex is whose turn it is. 0 is for the player and 1 is for the AI.
    protected String name;

	/**
	 * Constructor of a player.
	 */
    public Player(String name, int playerIndex) {
		this.name = name;
		this.playerIndex = playerIndex;
    }

	/**
	 * Getter for the player index.
	 */
    public final int getPlayerIndex() {
        return this.playerIndex;
    }

	/**
	 * Prints the victory message.
	 */
    public void printVictoryMessage() { //main playing method
        System.out.println("Congrats " + name + " you won!!!");
    }

    /**
     * Creates a 2D integer array with the ships on the board.
     *
     * Abstract method because humans will choose their placement and AI will be random.
     * A subclass can override this functionality.
     *
     * @param ships The lengths of all the ships to place.
     * @param gridSize The size of the 2D array to create.
     * @return A 2D integer array with either EMPTY or NOT_HIT_BATTLESHIP.
     */
    public abstract int[][] setupBattleships(int[] ships, int gridSize);

//    /**
//     * Optional helper method that puts one ship of a given length on the board.
//     *
//     * @param board The 2D integer array to add a ship to.
//     * @param shipLength The length of the ship to put.
//     */
//    private void placeBattleship(int[][] board, int shipLength) { //helper method
//		Random positioning = new Random();
//		if (positioning.nextInt(2) == 0) {
//			int rowStart = positioning.nextInt(board.length);
//			int colStart = positioning.nextInt(board.length - shipLength + 1);
//			for (int i = colStart; i < colStart + shipLength; i++) {
//				board[rowStart][i] = 3; 
//			}
//		} else {
//			int colStart = positioning.nextInt(board.length);
//			int rowStart = positioning.nextInt(board.length - shipLength + 1);
//			for (int i = rowStart; i < rowStart + shipLength; i++) {
//				board[i][colStart] = 3;
//			}
//		}
//    }

	/**
	 * ABSTRACT method.
	 *
	 * Abstract methods define what a function should be named and what parameters they have, but not what code runs.
	 * This allows the HumanPlayer and AIPlayer to have different codes, even if there is no default code.
	 * Do not change.
	 */
    public abstract Point getNextMove(int[][] playerCellsStatus, int[][] opponentCellsStatus);
}
