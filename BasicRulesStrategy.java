public class BasicRulesStrategy implements RulesStrategy {

    private final Board board;

    public BasicRulesStrategy(Board board) {
        this.board = board;
    }

    @Override
    public void endOfGame() {
        int p1 = board.getCurrentIndex1();
        int p2 = board.getCurrentIndex2();

        int end1 = board.getEndIndex1();
        int end2 = board.getEndIndex2();

        if (p1 >= end1) {
            System.out.println("Player 1 Wins: GAME OVER triggered!");
            board.setGameOver(true);  // must delegate to BoardStrategy
        } else if (p2 >= end2) {
            System.out.println("Player 2 Wins: GAME OVER triggered!");
            board.setGameOver(true);  // must delegate to BoardStrategy
        }
    }

    @Override
    public int calculateNewPosition(Player player, int moves, int endIndex) {
        int currentPos = player.getStartingPosition();
        int newPos = currentPos + moves;
        
        int currentTile = board.getTileValue(currentPos);
        int newTile = board.getTileValue(newPos);
        
        System.out.println(player.getName() + " rolled a " + moves);
        System.out.println(player.getName() + " moves from position " + currentPos + " (tile: " + currentTile + ") to position " + newPos + " (tile: " + newTile + ")");
        
        return newPos;
    }
}

