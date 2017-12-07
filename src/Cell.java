public class Cell {
	/* Constants */
    public static final int EMPTY = 0;
    public static final int ATTACK_MISS = 1;
    public static final int HIT_BATTLESHIP = 2;
    public static final int NOT_HIT_BATTLESHIP = 3;
    
	/* Instance variables */
    private boolean isHit;
    private boolean isBattleship;

	/* Constructor or a cell. It is initially not yet hit.
	 *
	 * @param isBattleship   If the cell is in a battleship or not.
	 */
    public Cell(boolean isBattleship) {
		this.isBattleship = isBattleship;
		isHit = false;
    }

    /**
     * Tries to set this cell as being attacked.
     *
     * @return whether or not the cell has been hit before. If not, then set it to hit.
     */
    public boolean isHit() {
		return isHit;
    }

	/**
	 * Get the status of the cell.
	 *
	 * @return HIT_BATTLESHIP or ATTACK_MISS or NOT_HIT_BATTLESHIP or EMPTY.
	 */
    public int getStatus() {
		if (isBattleship == true && isHit == true) {
			return HIT_BATTLESHIP;
		} else if (isBattleship == true && isHit == false) {
			return NOT_HIT_BATTLESHIP;
		} else if (isBattleship == false && isHit == true) {
			return ATTACK_MISS;
		} else {
			return EMPTY;
		}
    }

	/**
	 * Get if this cell is a battleship or not.
	 *
	 * @return If the cell is a battleship or not.
	 */
    public boolean getIsBattleship() {
		return isBattleship;
    }

	/*
	Change this cell to hit.
	*/
	public void attackThisCell() {
		isHit = true;
	}
}