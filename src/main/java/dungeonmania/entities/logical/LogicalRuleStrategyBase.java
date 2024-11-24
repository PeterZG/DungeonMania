package dungeonmania.entities.logical;

import dungeonmania.entities.Entity;
import dungeonmania.entities.Wire;

public abstract class LogicalRuleStrategyBase implements LogicalRuleStrategy {

    public boolean isEntityActivated(Entity entity) {
        if (entity instanceof Wire) {
            return ((Wire) entity).isActivated();
        }
        return false;

    }

    public int getActiveTick(Entity entity) {
        if (entity instanceof Wire) {
            return ((Wire) entity).getActivatedTick();
        }
        return -1;
    }


}
