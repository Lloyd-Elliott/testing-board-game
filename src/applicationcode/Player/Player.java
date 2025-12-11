package src.applicationcode.Player;

public class Player {
    private int position = 0;
    private int startingPosition = 0;
    private int turns = 0;
    private String name;
    private boolean inTail = false;
    private int tailPosition = 0;
    private boolean completedLap = false;

    public Player(String name) {
        this.name = name;
    }

    public void moveTo(int newPosition) {
        this.position = newPosition;
        this.turns++;
    }
    
    public boolean isInTail() {
        return inTail;
    }
    
    public void setInTail(boolean inTail) {
        this.inTail = inTail;
    }
    
    public int getTailPosition() {
        return tailPosition;
    }
    
    public void setTailPosition(int tailPosition) {
        this.tailPosition = tailPosition;
    }
    
    public int getStartingPosition() {
        return startingPosition;
    }
    
    public void setStartingPosition(int startingPosition) {
        this.startingPosition = startingPosition;
    }
    
    public boolean hasCompletedLap() {
        return completedLap;
    }
    
    public void setCompletedLap(boolean completedLap) {
        this.completedLap = completedLap;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getTurns() {
        return turns;
    }

    public void setTurns(int turns) {
        this.turns = turns;
    }

}
