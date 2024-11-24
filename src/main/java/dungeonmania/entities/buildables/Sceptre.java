package dungeonmania.entities.buildables;

import dungeonmania.Game;
import dungeonmania.battles.BattleStatistics;

public class Sceptre extends Buildable {
    public Sceptre() {
        super(null);

    }

    @Override
    public BattleStatistics applyBuff(BattleStatistics origin) {
        return null;
    }

    @Override
    public void use(Game game) {

    }

    @Override
    public int getDurability() {
        return 0;
    }
}
