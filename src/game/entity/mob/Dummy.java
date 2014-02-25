package game.entity.mob;

import game.graphics.AnimatedSprite;
import game.graphics.Screen;
import game.graphics.SpriteSheet;

public class Dummy extends Mob
{
    private AnimatedSprite up = new AnimatedSprite(SpriteSheet.mob1_up, 32, 32, 3);
    private AnimatedSprite down = new AnimatedSprite(SpriteSheet.mob1_down, 32, 32, 3);
    private AnimatedSprite left = new AnimatedSprite(SpriteSheet.mob1_left, 32, 32, 3);
    private AnimatedSprite right = new AnimatedSprite(SpriteSheet.mob1_right, 32, 32, 3);

    private AnimatedSprite animSprite;

    private int time;

    public Dummy(int x, int y)
    {
        this.x = x << 4;
        this.y = y << 4;
        down.setFrame(1);
        sprite = down.getSprite();
        animSprite = down;
        walking = false;
        time = 0;
    }

    public void update()
    {
        time++;

        if (walking) animSprite.update();
        else animSprite.setFrame(1);

        int xa = 0, ya = 0;

        if (ya < 0)
        {
            animSprite = up;
            dir = Direction.UP;
        }
        else if (ya > 0)
        {
            animSprite = down;
            dir = Direction.DOWN;
        }

        if (xa < 0)
        {
            animSprite = left;
            dir = Direction.LEFT;
        }
        else if (xa > 0)
        {
            animSprite = right;
            dir = Direction.RIGHT;
        }

        if (xa != 0 || ya != 0)
        {
            move(xa, ya);
            walking = true;
        }
        else
            walking = false;

    }

    public void render(Screen screen)
    {
        sprite = animSprite.getSprite();

        screen.renderMob(x - sprite.width / 2, y - sprite.height / 2, sprite);
    }
}
