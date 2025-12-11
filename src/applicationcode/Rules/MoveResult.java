package src.applicationcode.Rules;

public class MoveResult {
    private final int oldPosition;
    private final int newPosition;
    private final boolean enteredTail;
    private final int tailPosition;
    private final boolean completedGame;
    
    public MoveResult(int oldPosition, int newPosition, boolean enteredTail, int tailPosition, boolean completedGame) {
        this.oldPosition = oldPosition;
        this.newPosition = newPosition;
        this.enteredTail = enteredTail;
        this.tailPosition = tailPosition;
        this.completedGame = completedGame;
    }
    
    public int getOldPosition() {
        return oldPosition;
    }
    
    public int getNewPosition() {
        return newPosition;
    }
    
    public boolean isEnteredTail() {
        return enteredTail;
    }
    
    public int getTailPosition() {
        return tailPosition;
    }
    
    public boolean isCompletedGame() {
        return completedGame;
    }
}
