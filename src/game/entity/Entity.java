package game.entity;

import game.graphics.Screen;
import game.level.Level;

import java.util.Random;

public abstract class Entity
{
    protected int x, y;
    private boolean removed = false;
    protected Level level;
    protected final Random random = new Random();

    public void update()
    {

    }

    public void render(Screen screen)
    {

    }

    public void remove()
    {
        removed = true;
    }

    public void init(Level level) { this.level = level; }

    public boolean isRemoved()
    {
        return removed;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }
}
