package src.applicationcode.dice;
import java.util.Random;

public class TwoDiceStrategy implements  DiceRollStrategy {
    //    commit


    private int sides = 6;
    private final Random random;
    public TwoDiceStrategy() {
        this.random = new Random();
    }

    @Override
    public int rollDice() {
        int die1 = random.nextInt(sides) + 1;
        int die2 = random.nextInt(sides) + 1;
        return die1 + die2;

    }
}
