import java.util.Scanner;

/* This class extends Player
 *
 * This means HumanPlayer IS a Player.
 * It has the same variables and methods as Player, and new ones.
 * In addition it can 'override' methods - give them new code.
 * If you want to override a method and still call the original code, use the 'super' keyword.
 */
public class HumanPlayer extends Player {
	/* Instance variables */
	
	
	/**
	 * Constructor of the HumanPlayer.
	 *
	 * Note that this doesn't need to do anything different, so it can call 'super': Player class.
	 */
    public HumanPlayer(String name, int playerIndex) {
        super(name, playerIndex);
    }

    /**
     * Asks the user to make another move.
     *
     * @param playerCellsStatus
     * @param opponentCellsStatus
     * @return
     */
    @Override
    public Point getNextMove(int[][] playerCellsStatus, int[][] opponentCellsStatus) { //prints boards, asks for coordinates to attack, goes into Game's doTurn and Grid's trySetAttack
        prettyPrint(playerCellsStatus, opponentCellsStatus);
		
        Scanner scanner = new Scanner(System.in);

        int row = 0;
		int col = 0;
        boolean validCoordinate = false;
		while (validCoordinate == false) {
			System.out.println("What row index do you want to attack?");
			row = Integer.parseInt(scanner.nextLine());
			System.out.println("What column index do you want to attack?");
			col = Integer.parseInt(scanner.nextLine());
			if (opponentCellsStatus[row][col] == 1 || opponentCellsStatus[row][col] == 2) {
				System.out.println("You already tried that!");
				continue;
			}
			if (row < playerCellsStatus.length && row >= 0 && col < playerCellsStatus.length && col >= 0) {
				validCoordinate = true;
			} else {
				System.out.println("Those are not valid coordinates!");
			}
		}
        return new Point(row, col);
    }

	/**
	 * Nicely prints the player's two views. Their board and their radar.
	 */
    private void prettyPrint(int[][] playerCellsStatus, int[][] opponentCellsStatus) { //helper method
        System.out.println("--- Opponent Grid ---");
		printColumnLabelHeader(opponentCellsStatus.length);
        printGrid(opponentCellsStatus);
        
        System.out.println("--- Your Grid ---");
		printColumnLabelHeader(playerCellsStatus.length);
        printGrid(playerCellsStatus);
    }

	/**
	 * Helper method to print the grid header.
	 *
	 * No need to change.
	 */
    private void printColumnLabelHeader(int numberOfMarks) { //helper method
        System.out.print("  ");
        for (int i = 0; i < numberOfMarks; i++) {
            System.out.print(" " + i + " ");
        }
        System.out.print("\n");
    }

	/**
	 * Prints a grid.
	 */
    private void printGrid(int[][] grid) { //helper method
        for (int r = 0; r < grid.length; r++) {
            System.out.print(r + " ");
            for (int c = 0; c < grid.length; c++) {//PRINT DIFFERENT CELL STATUSES
				System.out.print(cellStatusToString(grid[r][c]));
            }
            System.out.print("\n");
        }
    }

	/**
	 * Helper method to help convert a cellStatus from the Cell class to a human readable string.
	 */
    private String cellStatusToString(int cellStatus) {
        switch (cellStatus) {
            case 0:
                return "[ ]";
            case 1:
                return  "[O]";
            case 2:
                return  "[X]";
            case 3:
                return  "[S]";
            default:
                return "";
        }
    }
}
