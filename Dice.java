import java.util.Random;

public class Dice {
    //    commit

    private DiceRollStrategy diceRollStrategy;

    public Dice(DiceRollStrategy diceRollStrategy) {
        this.diceRollStrategy = diceRollStrategy;
        }

        public int roll() {
            return diceRollStrategy.rollDice();
        }

    }

