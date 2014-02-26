package game.ai;

import game.entity.mob.Mob;
import game.level.Level;

public class BasicAI extends AI
{
    public BasicAI(Mob mob, Level level)
    {
        super(mob, level);
    }

    public void update()
    {
        time++;
    }
}
