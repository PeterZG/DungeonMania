package dungeonmania.entities;

import dungeonmania.Game;
import dungeonmania.entities.logical.LogicalRuleStrategy;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

public class LightBulb extends StaticEntity {
    private final LogicalRuleStrategy logicalRule;
    private boolean activated;

    public LightBulb(Position position, LogicalRuleStrategy logicalRule) {
        super(position);
        this.logicalRule = logicalRule;
        activated = false;
    }

    @Override
    public boolean canMoveOnto(GameMap map, Entity entity) {
        return true;
    }

    public boolean isActivated() {
        return activated;
    }

    public void updateActivation(Game game) {
        activated = logicalRule.isEntityActive(game, getPosition());
    }
}
