package game.entity.mob;

import game.Game;
import game.entity.projectile.SmokeProjectile;
import game.graphics.Screen;
import game.graphics.Sprite;
import game.input.Keyboard;
import game.input.Mouse;

public class Player extends Mob
{
    private Keyboard keyboard;
    private int anim;
    private boolean walking = false;

    public Player(Keyboard keyboard)
    {
        this.keyboard = keyboard;
        sprite = Sprite.player_down;
    }

    public Player(int x, int y, Keyboard keyboard)
    {
        this.x = x;
        this.y = y;
        this.keyboard = keyboard;
        sprite = Sprite.player_down;
        fireRate = SmokeProjectile.FIRE_RATE;
    }

    public void update()
    {
        int xa = 0, ya = 0;
        anim = anim < 10000 ? ++anim : 0;

        if (keyboard.up) ya--;
        if (keyboard.down) ya++;
        if (keyboard.left) xa--;
        if (keyboard.right) xa++;

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
        if (dir == Direction.UP)
        {
            sprite = Sprite.player_up;
            if (walking)
            {
                if (anim % 20 > 10)
                    sprite = Sprite.player_up1;
                else
                    sprite = Sprite.player_up2;
            }
        }
        if (dir == Direction.DOWN)
        {
            sprite = Sprite.player_down;
            if (walking)
            {
                if (anim % 20 > 10)
                    sprite = Sprite.player_down1;
                else
                    sprite = Sprite.player_down2;
            }
        }
        if (dir == Direction.LEFT)
        {
            sprite = Sprite.player_left;
            if (walking)
            {
                if (anim % 20 > 10)
                    sprite = Sprite.player_left1;
                else
                    sprite = Sprite.player_left2;
            }
        }
        if (dir == Direction.RIGHT)
        {
            sprite = Sprite.player_right;
            if (walking)
            {
                if (anim % 20 > 10)
                    sprite = Sprite.player_right1;
                else
                    sprite = Sprite.player_right2;
            }
        }

        screen.renderPlayer(x - 16, y - 16, sprite);
    }
}
