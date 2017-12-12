import java.util.InputMismatchException;
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
		
		 // TODO: FIX SO THAT IF COL FAILS, IT ONLY ASKS FOR COL AGAIN
		while (validCoordinate == false) {
			try {
				System.out.println(name + ", what row index do you want to attack?");
				row = Integer.parseInt(scanner.nextLine());
				System.out.println(name + ", what column index do you want to attack?");
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
			} catch (NumberFormatException e) {
				System.out.println("Please enter a number.");
			}
		}
//		scanner.close();
		return new Point(row, col);
	}
	public int[][] setupBattleships(int[] ships, int gridSize) {
		int[][] newBoard = new int[gridSize][gridSize];
		Scanner scan = new Scanner(System.in);		
		System.out.println(name + ", would you like to 0: place your own ships, or 1: have them randomly placed for you? (0 or 1)");
		int answer = 0;
		do {
			answer = scan.nextInt();
		} while (!(answer == 1 || answer == 0));
		
		if (answer == 0) {
			String[] shipNames = new String[] {"Destroyer", "Cruiser", "Submarine", "Battleship", "Aircraft Carrier"};
			for (int i = 0; i < ships.length; i++) {
				boolean success;
				do {
					success = placeBattleship(newBoard, ships[i], shipNames[i], gridSize);
				} while (!success);
			}
		} else {
			for (int i = 0; i < ships.length; i++) {
				boolean success;
				do {
					success = randomlyPlaceBattleships(newBoard, ships[i]);
				} while (!success);
			}
		}

//		scan.close();
		return newBoard;
	}
	
	private boolean randomlyPlaceBattleships(int[][] board, int shipLength) {
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

	private boolean placeBattleship(int[][] board, int shipLength, String shipName, int gridSize) { //helper method
		Scanner scan = new Scanner(System.in);
		int placeRow = 0;
		int placeCol = 0;
		String tempDir = "";
		if (shipName.charAt(0) == 'A' || shipName.charAt(0) == 'E' || shipName.charAt(0) == 'I' || shipName.charAt(0) == 'O' || shipName.charAt(0) == 'U') {
			System.out.println(name + ", place an " + shipName + "(length: " + shipLength +")");
		} else {
			System.out.println(name + ", place a " + shipName + "(length: " + shipLength +")");
		}
		System.out.println(name + ", enter your row:");

		boolean error = true; 
		do {
			try {
				placeRow = scan.nextInt();
				if ((placeRow >= gridSize) || (placeRow < 0)) {
					System.out.println("Please enter a value between 0 and " + (gridSize - 1));
				}
				error = false;
			} catch (InputMismatchException e) {
				System.out.println("Please enter a number.");
				scan.nextLine();
			}
		} while ((placeRow >= gridSize) || (placeRow < 0) || error);
		
		error = true;
		System.out.println(name + ", enter your column:");
		do {
			try {
				placeCol = scan.nextInt();
				if ((placeCol >= gridSize) || (placeCol < 0)) {
					System.out.println("Please enter a value between 0 and " + (gridSize - 1));
				}
				error = false;
			} catch (InputMismatchException e) {
				System.out.println("Enter a number. ");
				scan.nextLine();
			}				
		} while ((placeCol >= gridSize) || (placeCol < 0) || error);

		System.out.println(name + ", enter your orientation (N, S, E, W):");
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
				if (board[placeRow][placeCol - i] == 3) {
					System.out.println("Cannot overlap ships, try again.\n");
					return false;
				}
			}
			for (int i = 0; i < shipLength; i++) {
				board[placeRow][placeCol - i] = 3;
			}

		}
		System.out.println("Ship placed for " + name + "!\n");
		System.out.println(name + "'s current board: \n");
		printColumnLabelHeader(board.length);
		printGrid(board);
		System.out.println("");
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
