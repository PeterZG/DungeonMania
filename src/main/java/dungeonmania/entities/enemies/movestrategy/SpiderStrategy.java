package dungeonmania.entities.enemies.movestrategy;

import dungeonmania.Game;
import dungeonmania.entities.Boulder;
import dungeonmania.entities.Entity;
import dungeonmania.entities.enemies.Enemy;
import dungeonmania.util.Position;

import java.util.List;

public class SpiderStrategy implements MoveStrategy {
    private List<Position> movementTrajectory;
    private int nextPositionElement;
    private boolean forward;

    private Enemy target;

    public SpiderStrategy(Position position, Enemy target) {
        this.target = target;
        movementTrajectory = position.getAdjacentPositions();
        forward = true;
        nextPositionElement = 1;
    }

    private void updateNextPosition() {
        if (forward) {
            nextPositionElement++;
            if (nextPositionElement == 8) {
                nextPositionElement = 0;
            }
        } else {
            nextPositionElement--;
            if (nextPositionElement == -1) {
                nextPositionElement = 7;
            }
        }
    }

    @Override
    public void move(Game game) {
        Position nextPos = movementTrajectory.get(nextPositionElement);
        List<Entity> entities = game.getMap().getEntities(nextPos);
        if (entities != null && entities.size() > 0 && entities.stream().anyMatch(e -> e instanceof Boulder)) {
            forward = !forward;
            updateNextPosition();
            updateNextPosition();
        }
        nextPos = movementTrajectory.get(nextPositionElement);
        entities = game.getMap().getEntities(nextPos);
        if (entities == null || entities.size() == 0
                || entities.stream().allMatch(e -> e.canMoveOnto(game.getMap(), target))) {
            game.getMap().moveTo(target, nextPos);
            updateNextPosition();
        }
    }
}
