public class Main {
	/* Constants */
    private static final int BOARD_SIZE = 10;
    private static final int[] SHIP_LENGTHS = new int[] {2, 3, 3, 4, 5};

    public static void main(String[] args) {
        System.out.println("Welcome to battleship!");

        Player humanPlayer = new HumanPlayer("You", 0);
        Player aiPlayer = new RandomlyAttackingAIPlayer("Evil AI", 1);
		Player currentPlayer = humanPlayer;
//		humanPlayer.setupBattleships(SHIP_LENGTHS, BOARD_SIZE);
//		aiPlayer.setupBattleships(SHIP_LENGTHS, BOARD_SIZE);

        play(humanPlayer, aiPlayer, currentPlayer);

        currentPlayer.printVictoryMessage();
    }
    
	/**
	 * Actually plays the game.
	 *
	 * Until not game over, alternate between the players and make a turn.
	 */
    public static void play(Player p1, Player p2, Player currentPlayer) {
		int[][] p1Board = p1.setupBattleships(SHIP_LENGTHS, BOARD_SIZE);
		int[][] p2Board = p2.setupBattleships(SHIP_LENGTHS, BOARD_SIZE);
		Game game = new Game(p1Board, p2Board);
//		int turn = 0;
		boolean p1HasWon = false;
		boolean p2HasWon = false;
		
		while (p1HasWon == false && p2HasWon == false) {
			//if (turn == 0) {
			//System.out.println("Player's turn.");
			p1HasWon = game.doTurn(p1);
			if (p1HasWon) {
				break;
			}
//			System.out.println(game.returnAIGrid().getBattleshipsHit());
			//turn = 1;
			currentPlayer = p1;
			//} else {
			//System.out.println("AI's turn.");
			p2HasWon = game.doTurn(p2);
			if (p2HasWon) {
				break;
			}
			//turn = 0;
			currentPlayer = p2;
			//}
			
		}
    }
}
