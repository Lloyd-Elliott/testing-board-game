public class Rules {

//    private final Board board;
    private final RulesStratergy rulesStratergy;

    public Rules(RulesStratergy rulesStratergy) {

        this.rulesStratergy = rulesStratergy;
    }
    public void endOfGame() {
        rulesStratergy.endOfGame();
    }

    public RulesStratergy getRulesStratergy() {
        return rulesStratergy;
    }



}
