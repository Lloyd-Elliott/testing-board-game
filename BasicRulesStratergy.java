public class BasicRulesStratergy implements RulesStratergy {

    private final Board board;

    public BasicRulesStratergy(Board board) {
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
            board.setGameOver(true);  // must delegate to BoardStratergy
        } else if (p2 >= end2) {
            System.out.println("Player 2 Wins: GAME OVER triggered!");
            board.setGameOver(true);  // must delegate to BoardStratergy
        }
    }

    @Override
    public int calculateNewPosition(Player player, int moves, int endIndex) {
        int currentPos = player.getStartingPosition();
        int newPos = currentPos + moves;
        
        System.out.println(player.getName()+" Rolled a "+moves);
        System.out.println(player.getName() + " moves from " + currentPos + " to " + newPos);
        
        return newPos;
    }
}

