package dungeonmania.entities.logical;

import dungeonmania.Game;
import dungeonmania.entities.Entity;
import dungeonmania.util.Position;

import java.util.*;

public class CoAndLogicStrategy extends LogicalRuleStrategyBase {
    @Override
    public boolean isEntityActive(Game game, Position pos) {
        Map<Integer, Integer> tickCount = new HashMap<>();
        List<Position> neighbors = pos.getCardinallyAdjacentPositions();
        for (Position position : neighbors) {
            List<Entity> entities = game.getMap().getEntities(position);
            for (Entity entity : entities) {
                int tick = this.getActiveTick(entity);
                if (tick != -1) {
                    if (tickCount.containsKey(tick)) {
                        tickCount.put(tick, tickCount.get(tick) + 1);
                    } else {
                        tickCount.put(tick, 1);
                    }
                }
            }
        }
        for (int tick : tickCount.keySet()) {
            int cnt = tickCount.get(tick);
            // there are 2 or more cardinally adjacent activated conductors,
            // which are all activated on the same tick
            if (cnt >= 2) {
                return true;
            }
        }
        return false;
    }
}
