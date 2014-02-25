package game.entity.projectile;

import game.entity.emitter.ParticleEmitter;
import game.graphics.Screen;
import game.graphics.Sprite;

public class SmokeProjectile extends Projectile
{
    public static final int FIRE_RATE = 15;

    public SmokeProjectile(int x, int y, double angle)
    {
        super(x, y, angle);

        range = 150;
        speed = 4;
        damage = 20;
        sprite = Sprite.smoke_projectile;

        nx = Math.cos(angle) * speed;
        ny = Math.sin(angle) * speed;
    }

    public void update()
    {
        if (level.tileCollision((int)(x + nx), (int)(y + ny), 6, 5, 5))
        {
            level.add(new ParticleEmitter((int) x, (int) y, 30, 50, level));
            remove();
        }
        else
            move();
    }

    protected void move()
    {
        x += nx;
        y += ny;

        if (distance() > range) remove();
    }

    private double distance()
    {
        return Math.sqrt((xOrigin - x) * (xOrigin - x) + (yOrigin - y) * (yOrigin - y));
    }

    public void render(Screen screen)
    {
        screen.renderProjectile((int)x, (int)y, this);
    }
}
