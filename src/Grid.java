/**
 * Class Grid represents each player's 2D grid of cells.
 * It holds where their ships are, and where hits were made on the ships.
 */
public class Grid {
	/* Instance variables */
    Cell[][] grid;
    private int totalBattleshipsHit;
    private int totalBattleships;

	/**
	 * Constructor of a grid, given a 2D integer array.
	 * Would be nice to count the total number of battleships, so we can easily check for the win condition of all hits.
	 *
	 * @param initialStats The initial status of the board as a 2D integer array.
	 */
    public Grid(int[][] initialStatus) {
        totalBattleshipsHit = 0;
        grid = new Cell[initialStatus.length][initialStatus[0].length];
		totalBattleships = 0;
		
        for (int i = 0; i < initialStatus.length; i++) {
            for (int j = 0; j < initialStatus[0].length; j++) { // INITIALIZE WHETHER A CELL IS A BATTLESHIP OR NOT
            	if (initialStatus[i][j] == 3) {
					grid[i][j] = new Cell(true); 
					totalBattleships++;
				} else if (initialStatus[i][j] == 0){
					grid[i][j] = new Cell(false);
				}
            }
        }
    }

	/**
	 * Attempt to attack a specific point on the grid.
	 * Would be nice to increment totalBattleshipsHit if the hit succeeded on a battleship. This would allow to easily check for the win condition.
	 *
	 * @param coordinate The point attempting to be attacked.
	 * @return whether the attack succeeded or not. You can not attack the same coordinate twice.
	 */
    public boolean trySetAttack(Point coordinate) { //main playing method
		int row = coordinate.getRowIndex();
		int col = coordinate.getColumnIndex();
		grid[row][col].attackThisCell();
		if (grid[row][col].getStatus() == 2) {
			totalBattleshipsHit++;
			return true;
		}
		return false;
    }

	/**
	 * Get an integer array representation of the grid, for one of the players.
	 *
	 * If you show all battleships, this would be your grid, and you can see where someone hit or not.
	 * If you do not show all battleships, this would be the radar, showing your attempted hits and whether they hit a battleship or not.
	 *
	 * @return 2D integer array for display purposes.
	 */
    public int[][] getCellsStatus(boolean showAllBattleships) { //goes into HumanPlayer's getNextMove
        int[][] toReturn = new int[grid.length][grid[0].length];

        if (showAllBattleships == true) {
			for (int i = 0; i < toReturn.length; i++) {
				for (int j = 0; j < toReturn[0].length; j++) {
					if (grid[i][j].getStatus() == 2) {
						toReturn[i][j] = 2;
					} else if (grid[i][j].getStatus() == 3)  {
						toReturn[i][j] = 3;
					} else if (grid[i][j].getStatus() == 1) {
						toReturn[i][j] = 1;
					} else {
						toReturn[i][j] = 0;
					}
				}
			}
		} else {
			for (int i = 0; i < toReturn.length; i++) {
				for (int j = 0; j < toReturn.length; j++) {
					if (grid[i][j].getStatus() == 0) {
						toReturn[i][j] = 0;
					} else if (grid[i][j].getStatus() == 1) {
						toReturn[i][j] = 1;
					} else if (grid[i][j].getStatus() == 2) {
						toReturn[i][j] = 2;
					}
				}
			}
		}
        return toReturn;
    }

    /**
     * Checks to see whether or not this grid has lost
     *
     * We store the total number of battleships vs. the number hit, in order to easily calculate this.
     *
     * @return Whether the every battleship has been hit or not.
     */
    public boolean isLost() { //helper method
    	return totalBattleshipsHit == totalBattleships;
    }
	
	public Cell[][] returnGrid() {
		return grid;
	}
	
	public int getBattleshipsHit() {
		return totalBattleshipsHit;
	}
}
