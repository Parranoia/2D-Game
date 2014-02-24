package game.entity.particle;

import game.entity.Entity;
import game.graphics.Screen;
import game.graphics.Sprite;

public class Particle extends Entity
{
    private Sprite sprite;

    protected int life;
    protected double xa, ya, xx, yy;

    public Particle(int x, int y, int life)
    {
        this.x = x;
        this.y = y;
        this.xx = x;
        this.yy = y;
        this.life = life;
        this.xa = random.nextGaussian();
        this.ya = random.nextGaussian();

        sprite = Sprite.normal_particle;
    }

    public void update()
    {
        xx += xa;
        yy += ya;
    }

    public void render(Screen screen)
    {
        screen.renderSprite((int)xx, (int)yy, sprite, true);
    }

    public int getLife() { return life; }
}
