package dungeonmania.entities.logical;

import dungeonmania.Game;
import dungeonmania.entities.Entity;
import dungeonmania.util.Position;

import java.util.List;

public class AndLogicStrategy extends LogicalRuleStrategyBase {
    @Override
    public boolean isEntityActive(Game game, Position pos) {
        int count = 0;
        List<Position> neighbors = pos.getCardinallyAdjacentPositions();
        for (Position position : neighbors) {
            List<Entity> entities = game.getMap().getEntities(position);

            count += (int) entities.stream()
                .filter(this::isEntityActivated).count();
        }

        return count >= 2;
    }
}
