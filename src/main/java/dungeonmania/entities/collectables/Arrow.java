package dungeonmania.entities.collectables;

import dungeonmania.entities.Entity;
import dungeonmania.entities.PickUpEntity;
import dungeonmania.entities.inventory.InventoryItem;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

public class Arrow extends PickUpEntity implements InventoryItem {
    public Arrow(Position position) {
        super(position);
    }

    @Override
    public boolean canMoveOnto(GameMap map, Entity entity) {
        return true;
    }


}
