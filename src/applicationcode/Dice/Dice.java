package src.applicationcode.Dice;
import java.util.Objects;

public final class Dice {
    private final DiceRollStrategy diceRollStrategy;

    public Dice(DiceRollStrategy diceRollStrategy) {
        this.diceRollStrategy = diceRollStrategy;
    }

    public int roll() {
        return diceRollStrategy.rollDice();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dice dice = (Dice) o;
        return Objects.equals(diceRollStrategy, dice.diceRollStrategy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(diceRollStrategy);
    }

    @Override
    public String toString() {
        return "Dice{strategy=" + diceRollStrategy.getClass().getSimpleName() + "}";
    }
}