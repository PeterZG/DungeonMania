package dungeonmania.entities.logical;

import dungeonmania.Game;
import dungeonmania.util.Position;

public interface LogicalRuleStrategy {
    boolean isEntityActive(Game game, Position pos);

}
