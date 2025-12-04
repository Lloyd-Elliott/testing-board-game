import java.util.Objects;

public final class Dice {
    // Value Object: Immutable dice with equality based on strategy
    
    private final DiceRollStrategy diceRollStrategy;

    public Dice(DiceRollStrategy diceRollStrategy) {
        this.diceRollStrategy = diceRollStrategy;
    }

    public int roll() {
        return diceRollStrategy.rollDice();
    }

    // Value Object methods: equals, hashCode, toString
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