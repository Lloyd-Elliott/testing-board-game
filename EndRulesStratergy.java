public class EndRulesStratergy implements RulesStratergy{

    private final Board board;

    public EndRulesStratergy(Board board) {
        this.board = board;
    }



    @Override
    public void endOfGame() {
        // Check exact position win
        if (board.getBoardStratergy() instanceof SmallBoard) {
            if (board.getCurrentIndex1() == board.getEndIndex1()) {
                System.out.println("P1 WINS!");
                board.setGameOver(true);
            } else if (board.getCurrentIndex2() == board.getEndIndex2()) {
                System.out.println("P2 WINS!");
                board.setGameOver(true);
            }
        }
    }



    @Override
    public int calculateNewPosition(Player player, int moves, int endIndex) {
        int currentPos = player.getStartingPosition();
        int newPos = currentPos + moves;

        System.out.println(player.getName()+" Rolled a "+moves);
        
        // Overshoot rule: stay at previous position (must land exactly)
        if (newPos > endIndex) {
            System.out.println(player.getName() + " overshot and must land exactly - stays at " + currentPos);
            return currentPos; // Stay put
        } else if (newPos == endIndex) {
            System.out.println(player.getName() + " landed EXACTLY on the end at position " + newPos + "!");
        } else {
            System.out.println(player.getName() + " moves from " + currentPos + " to " + newPos);
        }
        
        return newPos;
    }
}
