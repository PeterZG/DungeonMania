package dungeonmania.entities.inventory;

public class InventoryFactory {
    public InventoryInterface createInventory() {
        return new Inventory();
    }

}
