package src.applicationcode.player;
public class Player {
    int startingPosition = 0;
    int turns = 0;
    String name;

    public Player(String name) {
        this.name = name;
    }

    public void moveTo(int newPosition) {
        this.startingPosition = newPosition;
        this.turns++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStartingPosition() {
        return startingPosition;
    }

    public void setStartingPosition(int startingPosition) {
        this.startingPosition = startingPosition;
    }

    public int getTurns() {
        return turns;
    }

    public void setTurns(int turns) {
        this.turns = turns;
    }

}
