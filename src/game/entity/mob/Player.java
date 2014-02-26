package game.entity.mob;

import game.Game;
import game.entity.projectile.SmokeProjectile;
import game.graphics.AnimatedSprite;
import game.graphics.Screen;
import game.graphics.SpriteSheet;
import game.input.Keyboard;
import game.input.Mouse;

public class Player extends Mob
{
    private Keyboard keyboard;
    private AnimatedSprite up = new AnimatedSprite(SpriteSheet.player_up, 32, 32, 3);
    private AnimatedSprite down = new AnimatedSprite(SpriteSheet.player_down, 32, 32, 3);
    private AnimatedSprite left = new AnimatedSprite(SpriteSheet.player_left, 32, 32, 3);
    private AnimatedSprite right = new AnimatedSprite(SpriteSheet.player_right, 32, 32, 3);

    private AnimatedSprite animSprite;

    public Player(int x, int y, Keyboard keyboard)
    {
        this.x = x;
        this.y = y;
        this.keyboard = keyboard;
        fireRate = SmokeProjectile.FIRE_RATE;
        down.setFrame(1);
        sprite = down.getSprite();
        animSprite = down;
        walking = false;
    }

    public void update()
    {
        if (walking) animSprite.update();
        else animSprite.setFrame(1);

        int xa = 0, ya = 0;

        if (keyboard.up)
        {
            animSprite = up;
            ya--;
        }
        else if (keyboard.down)
        {
            animSprite = down;
            ya++;
        }
        if (keyboard.left)
        {
            animSprite = left;
            xa--;
        }
        else if (keyboard.right)
        {
            animSprite = right;
            xa++;
        }

        if (xa != 0 || ya != 0)
        {
            move(xa, ya);
            walking = true;
        }
        else
            walking = false;

        if (fireRate > 0) fireRate--;

        if (Mouse.getButton() == 1 && fireRate <= 0)
            calcShoot();
    }

    private void calcShoot()
    {
        fireRate = SmokeProjectile.FIRE_RATE;

        double dx = Mouse.getX() - Game.getGameWidth() / 2;
        double dy = Mouse.getY() - Game.getGameHeight() / 2;

        double ang = Math.atan2(dy, dx);

        shoot(ang);
    }

    public void render(Screen screen)
    {
        sprite = animSprite.getSprite();

        screen.renderMob(x - 16, y - 16, sprite);
    }

    public void initAI() { }
}
