package dungeonmania.entities.enemies.movestrategy;

import dungeonmania.Game;
import dungeonmania.entities.enemies.Enemy;
import dungeonmania.map.GameMap;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class InvincibleStrategy implements MoveStrategy {
    private Enemy current;


    public InvincibleStrategy(Enemy current) {
        this.current = current;
    }

    public Position getPosition() {
        return current.getPosition();
    }


    @Override
    public void move(Game game) {
        Position nextPos;
        GameMap map = game.getMap();

        Position plrDiff = Position.calculatePositionBetween(map.getPlayer().getPosition(), getPosition());

        Position moveX = (plrDiff.getX() >= 0) ? Position.translateBy(getPosition(),
        Direction.RIGHT) : Position.translateBy(getPosition(), Direction.LEFT);

        Position moveY = (plrDiff.getY() >= 0) ? Position.translateBy(getPosition(),
        Direction.UP) : Position.translateBy(getPosition(), Direction.DOWN);

        Position offset = getPosition();
        if (plrDiff.getY() == 0 && map.canMoveTo(current, moveX)) offset = moveX;
        else if (plrDiff.getX() == 0 && map.canMoveTo(current, moveY)) offset = moveY;
        else if (Math.abs(plrDiff.getX()) >= Math.abs(plrDiff.getY())) {
            if (map.canMoveTo(current, moveX)) offset = moveX;
            else if (map.canMoveTo(current, moveY)) offset = moveY;
            else offset = getPosition();
        } else {
            if (map.canMoveTo(current, moveY)) offset = moveY;
            else if (map.canMoveTo(current, moveX)) offset = moveX;
            else offset = getPosition();
        }
        nextPos = offset;
        map.moveTo(current, nextPos);
    }
}
