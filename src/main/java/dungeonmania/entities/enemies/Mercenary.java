package dungeonmania.entities.enemies;

import dungeonmania.Game;
import dungeonmania.battles.BattleStatistics;
import dungeonmania.entities.Interactable;
import dungeonmania.entities.buildables.Sceptre;
import dungeonmania.entities.collectables.SunStone;
import dungeonmania.entities.collectables.Treasure;
import dungeonmania.entities.collectables.potions.InvincibilityPotion;
import dungeonmania.entities.collectables.potions.InvisibilityPotion;
import dungeonmania.entities.enemies.movestrategy.FollowStrategy;
import dungeonmania.entities.enemies.movestrategy.InvincibleStrategy;
import dungeonmania.entities.enemies.movestrategy.MoveStrategy;
import dungeonmania.entities.enemies.movestrategy.RandomStrategy;
import dungeonmania.entities.Entity;
import dungeonmania.entities.Player;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

public class Mercenary extends Enemy implements Interactable {
    public static final int DEFAULT_BRIBE_AMOUNT = 1;
    public static final int DEFAULT_BRIBE_RADIUS = 1;
    public static final double DEFAULT_ATTACK = 5.0;
    public static final double DEFAULT_HEALTH = 10.0;
    public static final int DEFAULT_MIND_CONTROL_DURATION = 2;

    private int bribeAmount = Mercenary.DEFAULT_BRIBE_AMOUNT;
    private int bribeRadius = Mercenary.DEFAULT_BRIBE_RADIUS;

    private double allyAttack;
    private double allyDefence;
    private boolean allied = false;
    private boolean isMindControlled = false;
    private boolean isAdjacentToPlayer = false;
    private int mindControlDuration;
    private int curMindControlDuration;

    private MoveStrategy randomStrategy;
    private MoveStrategy invincibleStrategy;
    private MoveStrategy followStrategy;

    public Mercenary(Position position, double health, double attack, int bribeAmount, int bribeRadius,
            double allyAttack, double allyDefence, int mindControlDuration) {
        super(position, health, attack);
        this.bribeAmount = bribeAmount;
        this.bribeRadius = bribeRadius;
        this.allyAttack = allyAttack;
        this.allyDefence = allyDefence;
        this.mindControlDuration = mindControlDuration;

        randomStrategy = new RandomStrategy(this);
        invincibleStrategy = new InvincibleStrategy(this);
        followStrategy = new FollowStrategy(this);
    }

    public boolean isAllied() {
        return allied;
    }

    @Override
    public void onOverlap(GameMap map, Entity entity) {
        if (allied)
            return;
        super.onOverlap(map, entity);
    }

    /**
     * check whether the current merc can be bribed
     * @param player
     * @return boolean
     */
    private boolean canBeBribed(Player player) {
        if (player.countEntityOfType(Treasure.class) == player.countEntityOfType(SunStone.class)) return false;
        return bribeRadius >= 0 && player.countEntityOfType(Treasure.class) >= bribeAmount;
    }

    /**
     * check whether the current merc can be mind-controlled
     * @param player
     * @return
     */
    private boolean canBeMindControlled(Player player) {
        return player.countEntityOfType(Sceptre.class) >= 1;
    }

    /**
     * bribe the merc
     */
    private void bribe(Player player) {
        for (int i = 0; i < bribeAmount; i++) {
            player.use(Treasure.class);
        }

    }

    @Override
    public void interact(Player player, Game game) {
        allied = true;
        if (!canBeMindControlled(player)) {
            bribe(player);
        } else {
            this.isMindControlled = true;
            this.curMindControlDuration = mindControlDuration;
        }
        if (!isAdjacentToPlayer && Position.isAdjacent(player.getPosition(), getPosition()))
            isAdjacentToPlayer = true;
    }

    @Override
    public void move(Game game) {
        System.out.println(isAllied());
        Position nextPos;
        GameMap map = game.getMap();
        Player player = game.getPlayer();
        if (isMindControlled) {
            this.curMindControlDuration--;
            if (this.curMindControlDuration <= 0) {
                allied = false;
            }
        }
        if (allied) {
            nextPos = isAdjacentToPlayer ? player.getPreviousDistinctPosition()
                    : map.dijkstraPathFind(getPosition(), player.getPosition(), this);
            if (!isAdjacentToPlayer && Position.isAdjacent(player.getPosition(), nextPos))
                isAdjacentToPlayer = true;
            map.moveTo(this, nextPos);
        } else if (map.getPlayer().getEffectivePotion() instanceof InvisibilityPotion) {
            randomStrategy.move(game);
        } else if (map.getPlayer().getEffectivePotion() instanceof InvincibilityPotion) {
            invincibleStrategy.move(game);
        } else {
            followStrategy.move(game);
        }
    }

    @Override
    public boolean isInteractable(Player player) {
        return !allied && (canBeBribed(player) || canBeMindControlled(player));
    }

    @Override
    public BattleStatistics getBattleStatistics() {
        if (!allied)
            return super.getBattleStatistics();
        return new BattleStatistics(0, allyAttack, allyDefence, 1, 1);
    }
}
