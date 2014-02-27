package game.entity.mob;

import game.ai.AI;
import game.ai.BasicAI;
import game.graphics.AnimatedSprite;
import game.graphics.Screen;
import game.graphics.SpriteSheet;

public class Dummy extends Mob
{
    private AI ai;

    private AnimatedSprite up = new AnimatedSprite(SpriteSheet.mob1_up, 32, 32, 3);
    private AnimatedSprite down = new AnimatedSprite(SpriteSheet.mob1_down, 32, 32, 3);
    private AnimatedSprite left = new AnimatedSprite(SpriteSheet.mob1_left, 32, 32, 3);
    private AnimatedSprite right = new AnimatedSprite(SpriteSheet.mob1_right, 32, 32, 3);

    private AnimatedSprite animSprite;

    public Dummy(int x, int y)
    {
        this.x = x << 4;
        this.y = y << 4;
        down.setFrame(1);
        sprite = down.getSprite();
        animSprite = down;
        walking = false;
        speed = 1.0;
    }

    public void initAI()
    {
        ai = new BasicAI(this, level);
    }

    public void update()
    {
        if (walking) animSprite.update();
        else animSprite.setFrame(1);

        if (dir == Direction.UP)
            animSprite = up;
        if (dir == Direction.DOWN)
            animSprite = down;
        if (dir == Direction.LEFT)
            animSprite = left;
        if (dir == Direction.RIGHT)
            animSprite = right;

        walking = false;

        ai.update();
    }

    public void render(Screen screen)
    {
        sprite = animSprite.getSprite();

        screen.renderMob((int)x - sprite.width / 2, (int)y - sprite.height / 2, sprite);
    }
}
