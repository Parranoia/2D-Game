package game.ai;

import game.entity.mob.Mob;
import game.entity.mob.Player;
import game.level.Level;

public class BasicAI extends AI
{
    public BasicAI(Mob mob, Level level)
    {
        super(mob, level);
    }

    public void update()
    {
        time = time > 10000 ? 0 : ++time;

        Player target = getClosestPlayer();

        if (target == null)
            return;

        if (target.distanceTo(me) < 60.0)
        {

            int xa = target.getX() - me.getX();
            int ya = target.getY() - me.getY();

            xa = xa == 0 ? xa : xa / Math.abs(xa);
            ya = ya == 0 ? ya : ya / Math.abs(ya);

            me.move(xa, ya);
        }
    }
}
