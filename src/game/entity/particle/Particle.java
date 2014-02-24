package game.entity.particle;

import game.entity.Entity;
import game.graphics.Screen;
import game.graphics.Sprite;

public class Particle extends Entity
{
    private Sprite sprite;
    private int time;

    protected int life;
    protected double xa, ya, za;
    protected double xx, yy, zz;

    public Particle(int x, int y, int life)
    {
        this.x = x;
        this.y = y;

        this.xx = x;
        this.yy = y;
        this.zz = random.nextFloat() + 2.0;

        this.xa = random.nextGaussian();
        this.ya = random.nextGaussian();
        this.za = 0;

        this.life = life + random.nextInt(20) - 10;

        sprite = Sprite.normal_particle;
    }

    public void update()
    {
        time++;
        if (time > life) remove();
        if (time >= 10000) time = 0;

        za -= 0.1;
        if (zz < 0)
        {
            zz = 0;
            za *= -0.5;
            ya *= 0.5;
        }

        move(xx + xa, yy + ya + zz + za);

        }

    private void move(double x, double y)
    {
        if (collision(x, y))
        {
            this.xa *= -0.5;
            this.ya *= -0.5;
            this.za *= -0.5;
        }
        xx += xa;
        yy += ya;
        zz += za;
    }

    public boolean collision(double x, double y)
    {
        boolean solid = false;

        for (int c = 0; c < 4; c++)
        {
            double xt = (x - c % 2 * 16) / 16;
            double yt = (y - c / 2 * 16) / 16;

            int xp = (int)Math.ceil(xt);
            int yp = (int)Math.ceil(yt);
            if (c % 2 == 0) xp = (int)Math.floor(xt);
            if (c / 2 == 0) yp = (int)Math.floor(yt);

            if (level.getTile(xp, yp).solid()) solid = true;
        }

        return solid;
    }

    public void render(Screen screen)
    {
        screen.renderSprite((int)xx, (int)yy - (int)zz + 1, sprite, true);
    }
}
