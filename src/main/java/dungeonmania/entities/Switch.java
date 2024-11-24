package dungeonmania.entities;

import java.util.ArrayList;
import java.util.List;

import dungeonmania.entities.collectables.Bomb;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

public class Switch extends Entity {
    private boolean activated;
    private int activeTick = 0;

    private List<Bomb> bombs = new ArrayList<>();

    public Switch(Position position) {
        super(position.asLayer(ITEM_LAYER));
    }

    public void subscribe(Bomb b) {
        bombs.add(b);
    }

    public void subscribe(Bomb bomb, GameMap map) {
        bombs.add(bomb);
        if (activated) {
            bombs.stream().forEach(b -> b.notify(map));
        }
    }

    public void unsubscribe(Bomb b) {
        bombs.remove(b);
    }

    @Override
    public boolean canMoveOnto(GameMap map, Entity entity) {
        return true;
    }

    @Override
    public void onOverlap(GameMap map, Entity entity) {
        if (entity instanceof Boulder) {
            activated = true;
            activeTick = map.getGame().getTick();
            bombs.stream().forEach(b -> b.notify(map));
            update(map, activeTick);
        }
    }

    public void update(GameMap gameMap, int activationTick) {
        List<Position> neighbors = getPosition().getCardinallyAdjacentPositions();

        for (Position position : neighbors) {
            List<Entity> entities = gameMap.getEntities(position);
            for (Entity entity : entities) {
                if (entity instanceof Wire) {
                    Wire wire = (Wire) entity;
                    if (!wire.isActivated()) {
                        wire.update(gameMap, activationTick + 1);
                    }
                }
            }
        }

        for (Entity entity: gameMap.getEntities()) {
            if (entity instanceof LightBulb) {
                ((LightBulb) entity).updateActivation(gameMap.getGame());
            }
        }
    }


    @Override
    public void onMovedAway(GameMap map, Entity entity) {
        if (entity instanceof Boulder) {
            activated = false;
            activeTick = -1;
        }
    }

    public boolean isActivated() {
        return activated;
    }

    @Override
    public void onDestroy(GameMap gameMap) {
        return;
    }
}
