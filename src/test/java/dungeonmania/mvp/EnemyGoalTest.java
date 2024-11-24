package dungeonmania.mvp;

import dungeonmania.Game;
import dungeonmania.entities.Player;
import dungeonmania.goals.EnemyGoal;
import dungeonmania.goals.Goal;
import dungeonmania.goals.GoalFactory;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EnemyGoalTest {
    @Test
    @DisplayName("Test factory")
    public void testFactory() {
        JSONObject jsonGoal = new JSONObject();
        jsonGoal.put("goal", "enemies");
        JSONObject config = new JSONObject();
        config.put("enemy_goal", 2);

        Goal goal = GoalFactory.createGoal(jsonGoal, config);
        assertTrue(goal instanceof EnemyGoal);


    }


    @Test
    @DisplayName("Test Goal Achieved")
    public void testGoalAchieved() {
        JSONObject jsonGoal = new JSONObject();
        jsonGoal.put("goal", "enemies");
        JSONObject config = new JSONObject();
        config.put("enemy_goal", 2);

        Goal goal = GoalFactory.createGoal(jsonGoal, config);
        assertTrue(goal instanceof EnemyGoal);

        EnemyGoal enemyGoal = (EnemyGoal) goal;
        Game game = new Game("test");
        GameMap map = new GameMap();
        Player player = new Player(new Position(0, 0), 100, 1, null);
        map.addEntity(player);
        map.setPlayer(player);
        game.setMap(map);
        map.setGame(game);
        game.init();

        assertFalse(enemyGoal.achieved(game));
        assertEquals(":enemies", enemyGoal.toStringRepresentation(game));
    }


    @Test
    @DisplayName("Test Goal Achieved2")
    public void testGoalAchieved2() {
        JSONObject jsonGoal = new JSONObject();
        jsonGoal.put("goal", "enemies");
        JSONObject config = new JSONObject();
        config.put("enemy_goal", 2);

        Goal goal = GoalFactory.createGoal(jsonGoal, config);
        assertTrue(goal instanceof EnemyGoal);

        EnemyGoal enemyGoal = (EnemyGoal) goal;
        Game game = new Game("test");
        GameMap map = new GameMap();
        Player player = new Player(new Position(0, 0), 100, 1, null);
        map.addEntity(player);
        map.setPlayer(player);
        game.setMap(map);
        map.setGame(game);
        game.init();

        player.incrementKillCount();
        player.incrementKillCount();
        player.incrementKillCount();
        player.incrementKillCount();

        assertTrue(enemyGoal.achieved(game));
        assertEquals("", enemyGoal.toStringRepresentation(game));
    }


}
