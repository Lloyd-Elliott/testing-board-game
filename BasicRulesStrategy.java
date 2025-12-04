public class BasicRulesStrategy implements RulesStrategy {

    private final Board board;

    public BasicRulesStrategy(Board board) {
        this.board = board;
    }

    @Override
    public void endOfGame() {
        // Check spaces traveled, not board position
        int spacesTraveled1 = board.getSpacesTraveled1();
        int spacesTraveled2 = board.getSpacesTraveled2();

        int winningDistance = board.getEndIndex1();

        if (spacesTraveled1 >= winningDistance) {
            System.out.println("Player 1 Wins: Completed the circle and tail! GAME OVER!");
            board.setGameOver(true);
        } else if (spacesTraveled2 >= winningDistance) {
            System.out.println("Player 2 Wins: Completed the circle and tail! GAME OVER!");
            board.setGameOver(true);
        }
    }

    @Override
    public int calculateNewPosition(Player player, int moves, int winningDistance) {
        // Player holds their board position in startingPosition field
        int currentBoardPos = player.getStartingPosition();
        
        // Get current spaces traveled from board
        int currentSpacesTraveled = (player.getName().equals("P1")) ? 
            board.getSpacesTraveled1() : board.getSpacesTraveled2();
        
        int newSpacesTraveled = currentSpacesTraveled + moves;
        
        System.out.println(player.getName() + " rolled a " + moves);
        System.out.println(player.getName() + " at board position " + currentBoardPos + ", traveled " + currentSpacesTraveled + " spaces total");
        System.out.println(player.getName() + " will travel to " + newSpacesTraveled + " spaces total");
        
        return newSpacesTraveled;
    }
}

