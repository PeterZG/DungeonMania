package dungeonmania.entities.enemies.movestrategy;

import dungeonmania.Game;
import dungeonmania.entities.enemies.Enemy;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

public class FollowStrategy implements MoveStrategy {

    private Enemy current;


    public FollowStrategy(Enemy current) {
        this.current = current;
    }

    public Position getPosition() {
        return current.getPosition();
    }

    @Override
    public void move(Game game) {
        Position nextPos;
        GameMap map = game.getMap();
        nextPos = map.dijkstraPathFind(getPosition(), map.getPlayer().getPosition(), current);
        map.moveTo(current, nextPos);
    }
}
