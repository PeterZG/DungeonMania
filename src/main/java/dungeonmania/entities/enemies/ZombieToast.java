package dungeonmania.entities.enemies;

import dungeonmania.Game;
import dungeonmania.entities.collectables.potions.InvincibilityPotion;
import dungeonmania.entities.enemies.movestrategy.InvincibleStrategy;
import dungeonmania.entities.enemies.movestrategy.MoveStrategy;
import dungeonmania.entities.enemies.movestrategy.RandomStrategy;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

public class ZombieToast extends Enemy {
    public static final double DEFAULT_HEALTH = 5.0;
    public static final double DEFAULT_ATTACK = 6.0;
    private MoveStrategy randStrategy;
    private MoveStrategy invincibleStrategy;

    public ZombieToast(Position position, double health, double attack) {
        super(position, health, attack);
        randStrategy = new RandomStrategy(this);
        invincibleStrategy = new InvincibleStrategy(this);
    }

    @Override
    public void move(Game game) {
        GameMap map = game.getMap();
        if (map.getPlayer().getEffectivePotion() instanceof InvincibilityPotion) {
            invincibleStrategy.move(game);
        } else {
            randStrategy.move(game);
        }
    }

}
