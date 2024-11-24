package dungeonmania.entities.inventory;

import dungeonmania.entities.BattleItem;
import dungeonmania.entities.Entity;
import dungeonmania.entities.EntityFactory;
import dungeonmania.entities.Player;

import java.util.List;

public interface InventoryInterface {
    boolean add(InventoryItem item);

    void remove(InventoryItem item);

    List<String> getBuildables();

    InventoryItem checkBuildCriteria(Player p, boolean remove, boolean forceShield, EntityFactory factory);

    <T extends InventoryItem> T getFirst(Class<T> itemType);

    <T extends InventoryItem> int count(Class<T> itemType);

    Entity getEntity(String itemUsedId);

    List<Entity> getEntities();

    <T> List<T> getEntities(Class<T> clz);

    boolean hasWeapon();

    BattleItem getWeapon();

}
