package dungeonmania.entities.logical;

import dungeonmania.Game;
import dungeonmania.entities.Entity;
import dungeonmania.util.Position;

import java.util.List;

public class OrLogicStrategy extends LogicalRuleStrategyBase {
    @Override
    public boolean isEntityActive(Game game, Position pos) {
        int count = 0;
        List<Position> cardinallyAdjPos = pos.getCardinallyAdjacentPositions();

        for (Position position : cardinallyAdjPos) {
            List<Entity> entities = game.getMap().getEntities(position);

            count += (int) entities.stream()
                .filter(this::isEntityActivated).count();
        }

        return count >= 1;
    }

}
