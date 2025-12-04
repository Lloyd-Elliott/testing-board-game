public class Rules {

//    private final Board board;
    private final RulesStrategy rulesStrategy;

    public Rules(RulesStrategy rulesStrategy) {

        this.rulesStrategy = rulesStrategy;
    }
    public void endOfGame() {
        rulesStrategy.endOfGame();
    }

    public RulesStrategy getRulesStrategy() {
        return rulesStrategy;
    }



}
