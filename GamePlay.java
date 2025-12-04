public class GamePlay {

    private final Game game;

    public GamePlay(Game game) {
        this.game = game;
    }

    public void playGame() {
        int round = 1;
        game.board.setupBoard();

        while (!game.board.hasWinner()) {
            System.out.println("\n=== ROUND " + round + " ===");
            int roll = game.dice.roll();
            game.board.movePlayer(roll,game.rules.getRulesStratergy());
            round++;
        }

        System.out.println("\nFINAL STATUS: WINNER DETECTED.");
    }
}

