public interface RulesStrategy {
    void endOfGame();
     int calculateNewPosition(Player player, int moves, int endIndex);
}
