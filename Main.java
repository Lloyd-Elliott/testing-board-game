public class Main {
    public static void main(String[] args) {

        // Create players using factory
        Player p1 = PlayerFactory.createPlayer("P1");
        Player p2 = PlayerFactory.createPlayer("P2");

        // Choose any board type here:
        BoardStrategy small = new SmallBoard(p1, p2);
        // BoardStrategy medium = new MediumBoard();
        // BoardStrategy large = new LargeBoard();



        // Wrap the chosen board strategy inside Board
        Board board = new Board(small);

        // Rules apply to ANY board
        Rules rules = new Rules(new BasicRulesStrategy(board));

        // Dice
        Dice dice = new Dice(new TwoDiceStrategy());


        // Create the game
        Game game = new Game(dice, board, rules);

        // Gameplay handler
        GamePlay gameplay = new GamePlay(game);

        // Start the game!
        gameplay.playGame();
    }
}
