package dungeonmania.entities;

import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

import java.util.List;

public class Wire extends StaticEntity {
    private boolean activated;
    private int activatedTick = -1;

    public Wire(Position position) {
        super(position.asLayer(ITEM_LAYER));
    }

    @Override
    public boolean canMoveOnto(GameMap map, Entity entity) {
        return true;
    }

    public boolean isActivated() {
        return activated;
    }

    public int getActivatedTick() {
        return activatedTick;
    }

    public void update(GameMap gameMap, int activationTick) {
        this.activated = true;
        this.activatedTick = activationTick;
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
    }
}
