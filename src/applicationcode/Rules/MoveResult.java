package src.applicationcode.Rules;

public class MoveResult {
    private final int oldPosition;
    private final int newPosition;
    private final boolean enteredTail;
    private final int tailPosition;
    private final boolean completedGame;
    private final boolean overshot;
    
    public MoveResult(int oldPosition, int newPosition, boolean enteredTail, int tailPosition, boolean completedGame, boolean overshot) {
        this.oldPosition = oldPosition;
        this.newPosition = newPosition;
        this.enteredTail = enteredTail;
        this.tailPosition = tailPosition;
        this.completedGame = completedGame;
        this.overshot = overshot;
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
    
    public boolean isOvershot() {
        return overshot;
    }
}
