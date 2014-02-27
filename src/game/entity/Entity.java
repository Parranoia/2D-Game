package game.entity;

import game.entity.mob.Mob;
import game.graphics.Screen;
import game.level.Level;

import java.util.Random;

public abstract class Entity
{
    protected double x, y;
    private boolean removed = false;
    protected Level level;
    protected final Random random = new Random();

    public void update() { }

    public void render(Screen screen) { }

    public void remove()
    {
        removed = true;
    }

    public double distanceTo(Entity e)
    {
        return Math.sqrt((e.x - x) * (e.x - x) + (e.y - y) * (e.y - y));
    }

    public void init(Level level)
    {
        this.level = level;
        if (this instanceof Mob)
            ((Mob)this).initAI();
    }

    public boolean isRemoved()
    {
        return removed;
    }

    public double getX()
    {
        return x;
    }

    public double getY() { return y;}
}
