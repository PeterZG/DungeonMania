package dungeonmania.goals;

import dungeonmania.Game;

public abstract class Goal {

    public Goal() {
    }


    /**
     * @return true if the goal has been achieved, false otherwise
     */
    public abstract boolean achieved(Game game);

    protected abstract String toStringRepresentation(Game game);

    public String toString(Game game) {
        if (this.achieved(game))
            return "";
        return toStringRepresentation(game);
    }

}
