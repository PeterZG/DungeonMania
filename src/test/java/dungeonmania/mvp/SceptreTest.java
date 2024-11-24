package dungeonmania.mvp;

import dungeonmania.DungeonManiaController;
import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class SceptreTest {
    @Test
    @Tag("16-1")
    @DisplayName("Test crafting a sceptre.")
    public void testSceptreBuildable() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_sceptreTest_craft",
                "c_sceptreTest_craft");
        assertThrows(InvalidActionException.class, () -> dmc.build("sceptre"));

        assertEquals(0, TestUtils.getInventory(res, "wood").size());
        assertEquals(0, TestUtils.getInventory(res, "sun_stone").size());
        assertEquals(0, TestUtils.getInventory(res, "key").size());

        //pick up sword
        res = dmc.tick(Direction.DOWN);
        assertEquals(1, TestUtils.getInventory(res, "wood").size());
        assertEquals(0, TestUtils.getInventory(res, "sun_stone").size());
        assertEquals(0, TestUtils.getInventory(res, "key").size());

        //pick up sun_stone
        res = dmc.tick(Direction.DOWN);
        assertEquals(1, TestUtils.getInventory(res, "wood").size());
        assertEquals(1, TestUtils.getInventory(res, "sun_stone").size());
        assertEquals(0, TestUtils.getInventory(res, "key").size());

        //pick up key
        res = dmc.tick(Direction.DOWN);
        assertEquals(1, TestUtils.getInventory(res, "wood").size());
        assertEquals(1, TestUtils.getInventory(res, "sun_stone").size());
        assertEquals(1, TestUtils.getInventory(res, "key").size());

        //build sceptre
        assertEquals(0, TestUtils.getInventory(res, "sceptre").size());
        res = assertDoesNotThrow(() -> dmc.build("sceptre"));
        assertEquals(1, TestUtils.getInventory(res, "sceptre").size());
        assertEquals(0, TestUtils.getInventory(res, "wood").size());
        assertEquals(1, TestUtils.getInventory(res, "sun_stone").size());
        assertEquals(0, TestUtils.getInventory(res, "key").size());

    }

    @Test
    @Tag("16-2")
    @DisplayName("Test mind control by sceptre.")
    public void testSceptreMindControl() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_sceptreTest_mindControl",
                "c_sceptreTest_mindControl");
        String mercId = TestUtils.getEntitiesStream(res, "mercenary").findFirst().get().getId();

        res = dmc.tick(Direction.DOWN);
        assertThrows(InvalidActionException.class, () -> dmc.interact(mercId));
        res = dmc.tick(Direction.DOWN);
        assertThrows(InvalidActionException.class, () -> dmc.interact(mercId));
        res = dmc.tick(Direction.DOWN);
        assertThrows(InvalidActionException.class, () -> dmc.interact(mercId));
        res = assertDoesNotThrow(() -> dmc.build("sceptre"));
        assertEquals(1, TestUtils.getInventory(res, "sceptre").size());
        assertDoesNotThrow(() -> dmc.interact(mercId));
    }

    @Test
    @Tag("16-3")
    @DisplayName("Test mind control by sceptre lasts for 2 ticks.")
    public void testSceptreMindControlDuration() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_sceptreTest_mindControlDuration",
                "c_sceptreTest_mindControlDuration");
        String mercId = TestUtils.getEntitiesStream(res, "mercenary").findFirst().get().getId();

        res = dmc.tick(Direction.DOWN);
        assertThrows(InvalidActionException.class, () -> dmc.interact(mercId));
        res = dmc.tick(Direction.DOWN);
        assertThrows(InvalidActionException.class, () -> dmc.interact(mercId));
        res = dmc.tick(Direction.DOWN);
        assertThrows(InvalidActionException.class, () -> dmc.interact(mercId));
        res = assertDoesNotThrow(() -> dmc.build("sceptre"));
        assertEquals(1, TestUtils.getInventory(res, "sceptre").size());
        assertDoesNotThrow(() -> dmc.interact(mercId));

        assertThrows(InvalidActionException.class, () -> dmc.interact(mercId));
        dmc.tick(Direction.DOWN);
        assertDoesNotThrow(() -> dmc.interact(mercId));
    }
}
