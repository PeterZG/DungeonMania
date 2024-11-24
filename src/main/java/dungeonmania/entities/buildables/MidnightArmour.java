package dungeonmania.entities.buildables;

import dungeonmania.Game;
import dungeonmania.battles.BattleStatistics;

public class MidnightArmour extends Buildable {
    private double midnightArmourAttack;
    private double midnightArmourDefence;
    public MidnightArmour(double attack, double defence) {
        super(null);
        this.midnightArmourAttack = attack;
        this.midnightArmourDefence = defence;
    }

    @Override
    public BattleStatistics applyBuff(BattleStatistics origin) {
        return BattleStatistics.applyBuff(origin, new BattleStatistics(0,
        midnightArmourAttack, midnightArmourDefence, 1, 1));
    }

    @Override
    public void use(Game game) {

    }

    @Override
    public int getDurability() {
        return 999;
    }
}
