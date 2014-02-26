package game.ai;

import game.entity.mob.Mob;
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
}
