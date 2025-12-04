public class Main {
    public static void main(String[] args) {

        // Choose any board type here:
        BoardStratergy small = new SmallBoard();
        // BoardStratergy medium = new MediumBoard();
        // BoardStratergy large = new LargeBoard();



        // Wrap the chosen board strategy inside Board
        Board board = new Board(small);

        // Rules apply to ANY board
        Rules rules = new Rules(new EndRulesStratergy(board));

        // Dice
        Dice dice = new Dice(new TwoDiceStratergy());


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
//        Dice dice2 = new Dice(new OneDiceStratergy());
//
//
//        Rules rules2 = new Rules(new EndRulesStratergy(board2));
//
//        Game game2 = new Game(dice2, board2, rules2);
//        GamePlay play = new GamePlay(game2);
//
//        play.playGame();
    }
}
