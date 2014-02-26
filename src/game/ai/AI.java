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
            if (Math.sqrt((p.getX() - x) * (p.getX() - x) + (p.getY() - y) * (p.getY() - y)) <
                Math.sqrt((temp.getX() - x) * (temp.getX() - x) + (temp.getY() - y) * (temp.getY() - y)))
                temp = p;
        return temp;
    }
}
