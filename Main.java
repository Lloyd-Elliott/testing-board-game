public class Main {
    public static void main(String[] args) {

        // Create players
        Player p1 = new Player("P1");
        Player p2 = new Player("P2");

        // Choose any board type here:
        BoardStrategy small = new SmallBoard(p1, p2);
        // BoardStrategy medium = new MediumBoard();
        // BoardStrategy large = new LargeBoard();



        // Wrap the chosen board strategy inside Board
        Board board = new Board(small);

        // Rules apply to ANY board
        Rules rules = new Rules(new EndRulesStrategy(board));

        // Dice
        Dice dice = new Dice(new TwoDiceStrategy());


        // Create the game
        Game game = new Game(dice, board, rules);

        // Gameplay handler
        GamePlay gameplay = new GamePlay(game);

        // Start the game!
        gameplay.playGame();
//
//        SmallBoard smallBoardStrategy = new SmallBoard();
//        Board board2 = new Board(smallBoardStrategy);
//
//        Dice dice2 = new Dice(new OneDiceStrategy());
//
//
//        Rules rules2 = new Rules(new EndRulesStrategy(board2));
//
//        Game game2 = new Game(dice2, board2, rules2);
//        GamePlay play = new GamePlay(game2);
//
//        play.playGame();
    }
}
