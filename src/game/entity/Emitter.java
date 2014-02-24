package game.entity;

import game.entity.particle.Particle;
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
        this.x = x;
        this.y = y;
        this.type = type;

        for (int i = 0; i < amount; i++)
        {
            if (type == Type.PARTICLE)
            {
                level.add(new Particle(x, y, 50));
            }
        }
    }
}
