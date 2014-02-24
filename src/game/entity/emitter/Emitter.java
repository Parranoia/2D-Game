package game.entity.emitter;

import game.entity.Entity;
import game.level.Level;

public class Emitter extends Entity
{
    public enum Type
    {
        PARTICLE,
        MOB
    }

    private Type type;

    public Emitter(int x, int y, Type type, int amount, Level level)
    {
        init(level);
        this.x = x;
        this.y = y;
        this.type = type;
    }
}
