import java.util.List;

public class FixedDiceStratergy implements DiceRollStratergy{
    private final List <Integer> fixedRolls;
    private int currentIndex = 0;

    public FixedDiceStratergy(List<Integer> fixedRolls) {
        this.fixedRolls = fixedRolls;
    }
    @Override
    public int rollDice() {
        int roll = 0;
        if (currentIndex >= fixedRolls.size()) {
            throw new IllegalStateException("No more fixed rolls available.");
        }
        roll = fixedRolls.get(currentIndex);
        currentIndex++;
        return roll;
    }
}
