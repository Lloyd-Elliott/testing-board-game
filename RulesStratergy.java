public interface RulesStratergy {
    void endOfGame();
     int calculateNewPosition(Player player, int moves, int endIndex);
}