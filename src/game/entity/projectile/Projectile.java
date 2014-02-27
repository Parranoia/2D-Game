package game.entity.projectile;

import game.entity.Entity;
import game.graphics.Sprite;

public abstract class Projectile extends Entity
{
    protected final double xOrigin, yOrigin;
    protected double angle;
    protected Sprite sprite;
    protected double nx, ny;
    protected double speed, damage, range;

    public Projectile(double x, double y, double angle)
    {
        xOrigin = x;
        yOrigin = y;
        this.angle = angle;
        this.x = x;
        this.y = y;
    }

    protected void move()
    {

    }

    public Sprite getSprite() { return sprite; }

    public int getSpriteSize() { return sprite.size; }
}
