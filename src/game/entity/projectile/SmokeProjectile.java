package game.entity.projectile;

import game.graphics.Screen;
import game.graphics.Sprite;

public class SmokeProjectile extends Projectile
{
    public SmokeProjectile(int x, int y, double angle)
    {
        super(x, y, angle);

        range = 100;
        speed = 4;
        damage = 20;
        rate = 15;
        sprite = Sprite.smoke_projectile;

        nx = Math.cos(angle) * speed;
        ny = Math.sin(angle) * speed;
    }

    public void update()
    {
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
        System.out.println(Math.cos(angle));

        screen.renderProjectile((int)(x - 7 + Math.cos(angle) * 18), (int)(y - 8 + Math.sin(angle) * 20), this);
    }
}
