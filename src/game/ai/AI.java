package game.ai;

import game.entity.mob.Mob;
import game.entity.mob.Player;
import game.level.Level;

public abstract class AI
{
    protected int time;
    protected Mob me;
    protected Level level;

    public AI(Mob mob, Level level)
    {
        me = mob;
        this.level = level;
        time = 0;
    }

    public abstract void update();

    public Player getClosestPlayer()
    {
        Player temp = level.getPlayers().get(0);
        int x = me.getX();
        int y = me.getY();
        for (Player p : level.getPlayers())
            if (me.distanceTo(p) < me.distanceTo(temp))
                temp = p;
        return temp;
    }
}
