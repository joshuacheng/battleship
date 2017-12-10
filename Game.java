/**
 * The Game class represents the board that the players see.
 */
public class Game {
	/* Instance variables */
    private Grid[] grids = new Grid[2];

	/**
	 * Constructor of the game given two grids.
	 *
	 * @param playerOneInitialGrid  The initial grid of player one.
	 * @param playerTwoInitialGrid  The initial grid of player two.
	 */
    public Game(int[][] playerOneInitialGrid, int[][] playerTwoInitialGrid) {
		grids[0] = new Grid(playerOneInitialGrid);
		grids[1] = new Grid(playerTwoInitialGrid);
		
    }

    /**
     * Actually plays the game.
     *
     * Until the game is over, alternatives between the players and 
     */
     //public void play() {
		
     //}

    /**
     * Does a turn for the given player.
     *
     * @param player the player that is taking the turn.
     * @return whether or not the game is over.
     */
    public boolean doTurn(Player player) { //checklist: check which player's turn, attempt the move, change grid if successful, see if game is over
		int currentPlayerIndex = player.getPlayerIndex();

        Grid currentPlayerGrid = getCurrentPlayerGrid(currentPlayerIndex);
        Grid opponentPlayerGrid = getOpponentPlayerGrid(currentPlayerIndex);

        if (currentPlayerIndex == 0) {
			Point attackPoint = ((HumanPlayer)player).getNextMove(currentPlayerGrid.getCellsStatus(true), opponentPlayerGrid.getCellsStatus(false));
			opponentPlayerGrid.trySetAttack(attackPoint);
		} else {
			Point atk = ((RandomlyAttackingAIPlayer)player).getNextMove(currentPlayerGrid.getCellsStatus(true), opponentPlayerGrid.getCellsStatus(false));
			opponentPlayerGrid.trySetAttack(atk);
		}

        return opponentPlayerGrid.isLost();
    }

    /**
     * Gets the grid for the current player based on the player index.
     *
     * @param playerIndex the index of the current player.
     * @return the grid of the current player.
     */
    private Grid getCurrentPlayerGrid(int playerIndex) {
        return grids[playerIndex];
    }

    /**
     * Gets the grid of the opponent player based on the player index.
     *
     * @param playerIndex the index of the current player.
     * @return the grid of the opponent to the current player.
     */
    private Grid getOpponentPlayerGrid(int playerIndex) {
        if (playerIndex == 0) {
            return grids[1];
        } else {
            return grids[0];
        }
    }
    
    /** Returns the AI player's grid.
     * For debugging purposes.
     * @return AI's grid
     */
    public Grid returnAIGrid() {
    	return grids[1];
    }
}