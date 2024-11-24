package dungeonmania.goals;

import dungeonmania.Game;

public class TreasureGoal extends Goal {
    private int target;

    public TreasureGoal(int target) {
        this.target = target;
    }

    @Override
    public boolean achieved(Game game) {
        return game.getCollectedTreasureCount() >= target;
    }

    @Override
    public String toStringRepresentation(Game game) {
        return ":treasure";
    }

}
