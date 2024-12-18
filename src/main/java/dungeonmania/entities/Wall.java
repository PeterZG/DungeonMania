package dungeonmania.entities;

import dungeonmania.entities.enemies.Spider;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

public class Wall extends StaticEntity {
    public Wall(Position position) {
        super(position.asLayer(CHARACTER_LAYER));
    }

    @Override
    public boolean canMoveOnto(GameMap map, Entity entity) {
        return entity instanceof Spider;
    }

}
