package dungeonmania.entities;

import dungeonmania.entities.collectables.Key;
import dungeonmania.entities.inventory.InventoryInterface;
import dungeonmania.map.GameMap;

import dungeonmania.entities.enemies.Spider;
import dungeonmania.util.Position;

public class Door extends Entity {
    private boolean open = false;
    private int number;

    public Door(Position position, int number) {
        super(position.asLayer(DOOR_LAYER));
        this.number = number;
    }

    @Override
    public boolean canMoveOnto(GameMap map, Entity entity) {
        if (open || entity instanceof Spider) {
            return true;
        }
        return (entity instanceof Player && hasKey((Player) entity));
    }

    @Override
    public void onOverlap(GameMap map, Entity entity) {
        if (!(entity instanceof Player))
            return;

        Player player = (Player) entity;
        InventoryInterface inventory = player.getInventory();
        Key key = inventory.getFirst(Key.class);

        if (hasKey(player)) {
            inventory.remove(key);
            open();
        }
    }

    @Override
    public void onMovedAway(GameMap map, Entity entity) {

    }

    @Override
    public void onDestroy(GameMap gameMap) {

    }

    private boolean hasKey(Player player) {
        InventoryInterface inventory = player.getInventory();
        Key key = inventory.getFirst(Key.class);

        return (key != null && key.getnumber() == number);
    }

    public boolean isOpen() {
        return open;
    }

    public void open() {
        open = true;
    }

}
