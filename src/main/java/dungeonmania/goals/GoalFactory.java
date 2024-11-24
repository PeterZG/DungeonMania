package dungeonmania.goals;

import org.json.JSONArray;
import org.json.JSONObject;

public class GoalFactory {
    public static Goal createGoal(JSONObject jsonGoal, JSONObject config) {
        JSONArray subgoals;
        switch (jsonGoal.getString("goal")) {
            case "AND":
                subgoals = jsonGoal.getJSONArray("subgoals");
                Goal g1 = createGoal(subgoals.getJSONObject(0), config);
                Goal g2 = createGoal(subgoals.getJSONObject(1), config);
                return new AndGoal(g1, g2);
            case "OR":
                subgoals = jsonGoal.getJSONArray("subgoals");
                Goal g11 = createGoal(subgoals.getJSONObject(0), config);
                Goal g22 = createGoal(subgoals.getJSONObject(1), config);
                return new OrGoal(g11, g22);
            case "exit":
                return new ExitGoal();
            case "boulders":
                return new BouldersGoal();
            case "treasure":
                int treasureGoal = config.optInt("treasure_goal", 1);
                return new TreasureGoal(treasureGoal);
            case "enemies":
                int killCount = config.optInt("enemy_goal", 1);
                return new EnemyGoal(killCount);
            default:
                return null;
        }
    }
}
