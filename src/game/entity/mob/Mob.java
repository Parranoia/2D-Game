package game.entity.mob;

import game.entity.Entity;
import game.entity.projectile.Projectile;
import game.entity.projectile.SmokeProjectile;
import game.graphics.Screen;
import game.graphics.Sprite;

public abstract class Mob extends Entity
{
    protected Sprite sprite;
    protected Direction dir = Direction.DOWN;
    protected boolean walking = false;
    protected int fireRate;

    public void move(int xa, int ya)
    {
        if (xa > 0) dir = Direction.RIGHT;
        if (xa < 0) dir = Direction.LEFT;
        if (ya > 0) dir = Direction.DOWN;
        if (ya < 0) dir = Direction.UP;

        if (!collision(xa, 0))
            x += xa;

        if (!collision(0, ya))
            y += ya;
    }

    public abstract void update();

    public abstract void render(Screen screen);

    private boolean collision(int xa, int ya)
    {
        boolean solid = false;

        for (int c = 0; c < 4; c++)
        {
            int xt = ((x + xa) + c % 2 * 14 - 8) / 16;
            int yt = ((y + ya) + c / 2 * 12 + 3) / 16;

            if (level.getTile(xt, yt).solid()) solid = true;
        }

        return solid;
    }

    protected void shoot(double ang)
    {
        Projectile p = new SmokeProjectile((int)(x - 7 + Math.cos(ang) * 18), (int)(y - 8 + Math.sin(ang) * 20), ang);
        level.add(p);
    }
}
