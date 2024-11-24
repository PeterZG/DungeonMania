package dungeonmania.entities;

import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

public class PickUpEntity extends Entity {
    public PickUpEntity(Position position) {
        super(position);
    }

    @Override
    public void onOverlap(GameMap map, Entity entity) {
        if (entity instanceof Player) {
            if (!((Player) entity).pickUp(this))
                return;
            map.destroyEntity(this);
        }
    }

    @Override
    public void onMovedAway(GameMap map, Entity entity) {

    }

    @Override
    public void onDestroy(GameMap gameMap) {

    }
}
