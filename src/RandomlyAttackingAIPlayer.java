import java.util.Random;

/* This class extends Player
 *
 * This means RandomlyAttackingAIPlayer IS a Player.
 * It has the same variables and methods as Player, and new ones.
 * In addition it can 'override' methods - give them new code.
 * If you want to override a method and still call the original code, use the 'super' keyword.
 */
public class RandomlyAttackingAIPlayer extends Player {
	/* Instance variables */
    private Random random;

	/**
	 * Constructor of the random AI.
	 *
	 * Note that this doesn't need to do anything different, so it can call 'super': Player class.
	 * For ease, we can also set the random instance variable for future use.
	 */
    public RandomlyAttackingAIPlayer(String name, int playerIndex) {
        super(name, playerIndex);

        this.random = new Random();
    }
    
    /** 
     * 
     */
    public int[][] setupBattleships(int[] ships, int gridSize) {
    	int[][] newBoard = new int[gridSize][gridSize];
    	for (int i = 0; i < ships.length; i++) {
    		boolean success = false;
    		do {
    			success = placeBattleship(newBoard, ships[i]);
    		} while (!success);
    	}
    	return newBoard;
    }

    private boolean placeBattleship(int[][] board, int shipLength) { //helper method
    	Random positioning = new Random();
    	if (positioning.nextInt(2) == 0) {
    		int rowStart = positioning.nextInt(board.length);
    		int colStart = positioning.nextInt(board.length - shipLength + 1);
    		for (int i = colStart; i < colStart + shipLength; i++) {
    			if (board[rowStart][i] == 3) {
    				return false;
    			}
    		}
    		for (int i = colStart; i < colStart + shipLength; i++) {
    			board[rowStart][i] = 3; 
    		}
    	} else {
    		int colStart = positioning.nextInt(board.length);
    		int rowStart = positioning.nextInt(board.length - shipLength + 1);
    		for (int i = rowStart; i < rowStart + shipLength; i++) {
    			if (board[i][colStart] == 3) {
    				return false;
    			}
    		}
    		for (int i = rowStart; i < rowStart + shipLength; i++) {
    			board[i][colStart] = 3;
    		}
    	}
    	return true;
    }
    

    /**
     * Get the next random move that AI makes.
     *
     * @param playerCellsStatus
     * @param opponentCellsStatus
     * @return a random Point chosen to play at.
     */
    @Override
    public Point getNextMove(int[][] playerCellsStatus, int[][] opponentCellsStatus) {//main playing method
		int row = random.nextInt(opponentCellsStatus.length);
		int col = random.nextInt(opponentCellsStatus.length);
		
		while (opponentCellsStatus[row][col] == 1 || opponentCellsStatus[row][col] == 2) {
			row = random.nextInt(opponentCellsStatus.length);
			col = random.nextInt(opponentCellsStatus.length);
		}
		return new Point(row, col);
    }
}