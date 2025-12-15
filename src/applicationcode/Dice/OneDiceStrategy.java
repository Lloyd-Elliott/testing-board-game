package src.applicationcode.Dice;
import java.util.Random;

public class OneDiceStrategy implements DiceRollStrategy{
    private int sides = 6;
    private final Random random;

    public OneDiceStrategy() {
        this.random = new Random();
    }

    @Override
    public int rollDice() {
        return random.nextInt(sides) + 1;
    }
}
