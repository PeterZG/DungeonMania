package dungeonmania.entities.enemies.movestrategy;

import dungeonmania.Game;
import dungeonmania.entities.enemies.Enemy;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class RandomStrategy implements MoveStrategy {

    private Enemy current;


    public RandomStrategy(Enemy current) {
        this.current = current;

    }

    public Position getPosition() {
        return current.getPosition();
    }

    @Override
    public void move(Game game) {
        Position nextPos;
        GameMap map = game.getMap();
        // Move random
        Random randGen = new Random();
        List<Position> pos = getPosition().getCardinallyAdjacentPositions();
        pos = pos.stream().filter(p -> map.canMoveTo(current, p)).collect(Collectors.toList());
        if (pos.size() == 0) {
            nextPos = getPosition();
            map.moveTo(current, nextPos);
        } else {
            nextPos = pos.get(randGen.nextInt(pos.size()));
            map.moveTo(current, nextPos);
        }
    }
}
