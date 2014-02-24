package game.graphics;

public class AnimatedSprite extends Sprite
{
    private int frame;
    private Sprite sprite;
    private int rate;
    private int time;
    private int animLength;

    public AnimatedSprite(SpriteSheet sheet, int width, int height, int animLength)
    {
        super(sheet, width, height);

        time = 0;
        frame = 0;
        rate = 8;
        sprite = sheet.getSprites()[1];
        this.animLength = animLength;

        if (animLength > sheet.getSprites().length)
            System.err.println("Animated Sprites length is too long!");
    }

    public void update()
    {
        time++;
        if (time % rate == 0)
        {
            frame = frame >= animLength - 1 ? 0 : ++frame;

            sprite = sheet.getSprites()[frame];
        }
    }

    public Sprite getSprite() { return sprite; }

    public void setFrame(int frame) { sprite = sheet.getSprites()[frame]; }

    public void setRate(int rate) { this.rate = rate; }
}
