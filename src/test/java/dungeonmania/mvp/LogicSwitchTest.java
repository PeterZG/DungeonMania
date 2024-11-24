package dungeonmania.mvp;

import dungeonmania.DungeonManiaController;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LogicSwitchTest {
    @Test
    @DisplayName("Empty iteration")
    public void emptyIteration() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_logicSwitchTest_simple",
                "c_DoorsKeysTest_cannotWalkClosedDoor");
        EntityResponse player = TestUtils.getPlayer(res).get();

        EntityResponse p1State = new EntityResponse(player.getId(), player.getType(), new Position(1, 1),
                false);

        assertTrue(TestUtils.entityResponsesEqual(p1State, player));

    }

    @Test
    @DisplayName("Empty iteration")
    public void testStepOnWire() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_logicSwitchTest_simple",
                "c_DoorsKeysTest_cannotWalkClosedDoor");
        EntityResponse player = TestUtils.getPlayer(res).get();

        EntityResponse p1State = new EntityResponse(player.getId(), player.getType(), new Position(1, 1),
                false);

        res = dmc.tick(Direction.RIGHT);

        player = TestUtils.getPlayer(res).get();
        EntityResponse p2State = new EntityResponse(player.getId(), player.getType(), new Position(2, 1),
                false);

        assertTrue(TestUtils.entityResponsesEqual(p2State, player));
    }

    private int countByEntityType(List<EntityResponse> res, String type) {
        return (int) res.stream().filter(e -> e.getType().equals(type)).count();
    }

    @Test
    @DisplayName("Test light on one Wire")
    public void testLightOnByOneWire() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();

        DungeonResponse res = dmc.newGame("d_logicSwitchTest_trigger_light",
                "c_DoorsKeysTest_cannotWalkClosedDoor");
        assertEquals(1, countByEntityType(res.getEntities(), "light_bulb_off"));
        res = dmc.tick(Direction.RIGHT);
        assertEquals(0, countByEntityType(res.getEntities(), "light_bulb_off"));
        assertEquals(1, countByEntityType(res.getEntities(), "light_bulb_on"));
    }


    @Test
    @DisplayName("Test light by two Wire using and logic")
    public void testLightOnByTwoWireAndLogic() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();

        DungeonResponse res = dmc.newGame("d_logicSwitchTest_and_light",
                "c_DoorsKeysTest_cannotWalkClosedDoor");
        assertEquals(1, countByEntityType(res.getEntities(), "light_bulb_off"));
        assertEquals(2, countByEntityType(res.getEntities(), "boulder"));
        assertEquals(2, countByEntityType(res.getEntities(), "switch"));
        assertEquals(1, countByEntityType(res.getEntities(), "light_bulb_off"));
        assertEquals(0, countByEntityType(res.getEntities(), "light_bulb_on"));

        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, countByEntityType(res.getEntities(), "light_bulb_off"));
        assertEquals(0, countByEntityType(res.getEntities(), "light_bulb_on"));

        res = dmc.tick(Direction.LEFT);
        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.RIGHT);


        assertEquals(0, countByEntityType(res.getEntities(), "light_bulb_off"));
        assertEquals(1, countByEntityType(res.getEntities(), "light_bulb_on"));
    }

    @Test
    @DisplayName("Test light by xor logic")
    public void testLightOnByXorLogic() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();

        DungeonResponse res = dmc.newGame("d_logicSwitchTest_xor_light",
                "c_DoorsKeysTest_cannotWalkClosedDoor");
        assertEquals(1, countByEntityType(res.getEntities(), "light_bulb_off"));
        assertEquals(2, countByEntityType(res.getEntities(), "boulder"));
        assertEquals(2, countByEntityType(res.getEntities(), "switch"));
        assertEquals(1, countByEntityType(res.getEntities(), "light_bulb_off"));
        assertEquals(0, countByEntityType(res.getEntities(), "light_bulb_on"));

        res = dmc.tick(Direction.RIGHT);
        assertEquals(0, countByEntityType(res.getEntities(), "light_bulb_off"));
        assertEquals(1, countByEntityType(res.getEntities(), "light_bulb_on"));

        res = dmc.tick(Direction.LEFT);
        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.RIGHT);


        assertEquals(1, countByEntityType(res.getEntities(), "light_bulb_off"));
        assertEquals(0, countByEntityType(res.getEntities(), "light_bulb_on"));
    }

    @Test
    @DisplayName("Test light by co_and logic")
    public void testLightOnByCoAndLogic() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();

        DungeonResponse res = dmc.newGame("d_logicSwitchTest_coand_light",
                "c_DoorsKeysTest_cannotWalkClosedDoor");
        assertEquals(1, countByEntityType(res.getEntities(), "light_bulb_off"));
        assertEquals(2, countByEntityType(res.getEntities(), "boulder"));
        assertEquals(2, countByEntityType(res.getEntities(), "switch"));
        assertEquals(1, countByEntityType(res.getEntities(), "light_bulb_off"));
        assertEquals(0, countByEntityType(res.getEntities(), "light_bulb_on"));

        res = dmc.tick(Direction.UP);
        assertEquals(1, countByEntityType(res.getEntities(), "light_bulb_off"));
        assertEquals(0, countByEntityType(res.getEntities(), "light_bulb_on"));

        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.DOWN);

        assertEquals(0, countByEntityType(res.getEntities(), "light_bulb_off"));
        assertEquals(1, countByEntityType(res.getEntities(), "light_bulb_on"));
    }

    @Test
    @DisplayName("Test light by co_and logic failed")
    public void testLightOnByCoAndLogicFailed() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();

        DungeonResponse res = dmc.newGame("d_logicSwitchTest_coand_light_failed",
                "c_DoorsKeysTest_cannotWalkClosedDoor");
        assertEquals(1, countByEntityType(res.getEntities(), "light_bulb_off"));
        assertEquals(2, countByEntityType(res.getEntities(), "boulder"));
        assertEquals(2, countByEntityType(res.getEntities(), "switch"));
        assertEquals(1, countByEntityType(res.getEntities(), "light_bulb_off"));
        assertEquals(0, countByEntityType(res.getEntities(), "light_bulb_on"));

        res = dmc.tick(Direction.UP);
        assertEquals(1, countByEntityType(res.getEntities(), "light_bulb_off"));
        assertEquals(0, countByEntityType(res.getEntities(), "light_bulb_on"));

        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.DOWN);

        assertEquals(1, countByEntityType(res.getEntities(), "light_bulb_off"));
        assertEquals(0, countByEntityType(res.getEntities(), "light_bulb_on"));
    }


}
