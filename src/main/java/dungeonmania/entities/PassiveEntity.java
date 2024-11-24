package dungeonmania.entities;

import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

public abstract class PassiveEntity extends Entity {
    public PassiveEntity(Position position) {
        super(position);
    }


    @Override
    public void onMovedAway(GameMap map, Entity entity) {

    }

    @Override
    public void onDestroy(GameMap gameMap) {

    }
}
