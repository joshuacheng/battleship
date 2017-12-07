import java.util.Random;
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
		scanner.close();
		return new Point(row, col);
	}

	public int[][] setupBattleships(int[] ships, int gridSize) {
		int[][] newBoard = new int[gridSize][gridSize];
		String[] shipNames = new String[] {"Destroyer", "Cruiser", "Submarine", "Battleship", "Aircraft Carrier"};
		for (int i = 0; i < ships.length; i++) {
			boolean success;
			do {
				success = placeBattleship(newBoard, ships[i], shipNames[i], gridSize);
			} while (!success);
		}
		return newBoard;
	}

	private boolean placeBattleship(int[][] board, int shipLength, String shipName, int gridSize) { //helper method
		Scanner scan = new Scanner(System.in);
		int placeRow = 0;
		int placeCol = 0;
		String tempDir = "";
		System.out.println("Place a " + shipName + "(length: " + shipLength +")");
		System.out.println("Enter your row:");
		do {
			placeRow = scan.nextInt();
			if ((placeRow >= gridSize) || (placeRow < 0)) {
				System.out.println("Please enter a value between 0 and " + (gridSize - 1));
			}
		} while ((placeRow >= gridSize) || (placeRow < 0));
		System.out.println("Enter your column:");
		do {
			placeCol = scan.nextInt();
			if ((placeCol >= gridSize) || (placeCol < 0)) {
				System.out.println("Please enter a value between 0 and " + (gridSize - 1));
			}
		} while ((placeCol >= gridSize) || (placeCol < 0));

		System.out.println("Enter your orientation (N, S, E, W):");
		scan.nextLine();
		do {
			tempDir = scan.nextLine();
//			System.out.println("tempdir is [" + tempDir + "]");
			if (!(tempDir.equals("N") || tempDir.equals("n") || tempDir.equals("North") || tempDir.equals("north") 
					|| tempDir.equals("E") || tempDir.equals("e") || tempDir.equals("East") || tempDir.equals("east") 
					|| tempDir.equals("S") || tempDir.equals("s") || tempDir.equals("South") || tempDir.equals("south") 
					|| tempDir.equals("W") || tempDir.equals("w") || tempDir.equals("West") || tempDir.equals("west"))) {
				System.out.println("Please enter a valid orientation.");
			}
		} while (!(tempDir.equals("N") || tempDir.equals("n") || tempDir.equals("North") || tempDir.equals("north") 
				|| tempDir.equals("E") || tempDir.equals("e") || tempDir.equals("East") || tempDir.equals("east") 
				|| tempDir.equals("S") || tempDir.equals("s") || tempDir.equals("South") || tempDir.equals("south") 
				|| tempDir.equals("W") || tempDir.equals("w") || tempDir.equals("West") || tempDir.equals("west")));

		if (tempDir.equals("N") || tempDir.equals("n") || tempDir.equals("North") || tempDir.equals("north")) {
			if (placeRow - shipLength < 0) {
				System.out.println("Ship out of bounds, try again.\n");
				return false;
			} 
			for (int i = 0; i < shipLength; i++) {
				if (board[placeRow - i][placeCol] == 3) {
					System.out.println("Cannot overlap ships, try again.\n");
					return false;
				}
			}
			for (int i = 0; i < shipLength; i++) {
				board[placeRow - i][placeCol] = 3;
			}
		} else if (tempDir.equals("E") || tempDir.equals("e") || tempDir.equals("East") || tempDir.equals("east")) {
			if (placeCol + shipLength >= gridSize) {
				System.out.println("Ship out of bounds, try again.\n");
				return false;
			}
			for (int i = 0; i < shipLength; i++) {
				if (board[placeRow][placeCol + i] == 3) {
					System.out.println("Cannot overlap ships, try again.\n");
					return false;
				}
			}
			for (int i = 0; i < shipLength; i++) {
				board[placeRow][placeCol + i] = 3;
			}
		} else if (tempDir.equals("S") || tempDir.equals("s") || tempDir.equals("South") || tempDir.equals("south")) {
			if (placeRow + shipLength >= gridSize) {
				System.out.println("Ship out of bounds, try again.\n");
				return false;
			}
			for (int i = 0; i < shipLength; i++) {
				if (board[placeRow + i][placeCol] == 3) {
					System.out.println("Cannot overlap ships, try again.\n");
					return false;
				}
			}
			for (int i = 0; i < shipLength; i++) {
				board[placeRow + i][placeCol] = 3;
			}
		} else if (tempDir == "S" || tempDir == "s" || tempDir == "South" || tempDir == "south") {
			if (placeRow + shipLength >= gridSize) {
				System.out.println("Ship out of bounds, try again.\n");
				return false;
			}
			for (int i = 0; i < shipLength; i++) {
				if (board[placeRow + i][placeCol] == 3) {
					System.out.println("Cannot overlap ships, try again.\n");
					return false;
				}
			}
			for (int i = 0; i < shipLength; i++) {
				board[placeRow + i][placeCol] = 3;
			}
		} else {
			if (placeCol - shipLength < 0) {
				System.out.println("Ship out of bounds, try again.\n");
				return false;
			}
			for (int i = 0; i < shipLength; i++) {
				if (board[placeRow][placeCol - 1] == 3) {
					System.out.println("Cannot overlap ships, try again.\n");
					return false;
				}
			}
			for (int i = 0; i < shipLength; i++) {
				board[placeRow][placeCol - 1] = 3;
			}

		}
		System.out.println("Ship placed!");
		return true;

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
