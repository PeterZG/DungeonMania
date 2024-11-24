package dungeonmania.entities;

import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

public class StaticEntity extends PassiveEntity {
    public StaticEntity(Position position) {
        super(position);
    }

    @Override
    public void onOverlap(GameMap map, Entity entity) {

    }

}
