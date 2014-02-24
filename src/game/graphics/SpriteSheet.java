package game.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpriteSheet
{
    private String path;
    public final int size;
    public final int width, height;
    public int[] pixels;
    private Sprite[] sprites;

    public static SpriteSheet tiles = new SpriteSheet("/textures/sheets/SpriteSheet.png", 256);
    public static SpriteSheet projectiles = new SpriteSheet("/textures/sheets/projectiles/projectile.png", 48);
    // Player
    public static SpriteSheet player = new SpriteSheet("/textures/sheets/player.png", 96, 128);
    public static SpriteSheet player_up = new SpriteSheet(player, 0, 3, 3, 1, 32);
    public static SpriteSheet player_down = new SpriteSheet(player, 0, 0, 3, 1, 32);
    public static SpriteSheet player_left = new SpriteSheet(player, 0, 1, 3, 1, 32);
    public static SpriteSheet player_right = new SpriteSheet(player, 0, 2, 3, 1, 32);

    public SpriteSheet(SpriteSheet sheet, int x, int y, int width, int height, int spriteSize)
    {
        int xx = x * spriteSize;
        int yy = y * spriteSize;
        int w = width * spriteSize;
        int h = height * spriteSize;

        this.size = width == height ? width : -1;
        this.width = w;
        this.height = h;
        pixels = new int[w * h];

        for (int yp = 0; yp < h; yp++)
        {
            int ya = yy + yp;
            for (int xp = 0; xp < w; xp++)
            {
                int xa = xx + xp;
                pixels[xp + yp * w] = sheet.pixels[xa + ya * sheet.width];
            }
        }

        sprites = new Sprite[width * height];

        int pos = 0;

        for (int ya = 0; ya < height; ya++)
        {
            for (int xa = 0; xa < width; xa++)
            {
                int[] spritePixels = new int[spriteSize * spriteSize];

                for (int yp = 0; yp < spriteSize; yp++)
                {
                    for (int xp = 0; xp < spriteSize; xp++)
                    {
                         spritePixels[xp + yp * spriteSize] = pixels[(xp + xa * spriteSize) + (yp + ya * spriteSize) * this.width];
                    }
                }
                sprites[pos++] = new Sprite(spritePixels, spriteSize, spriteSize);
            }
        }

    }

    public SpriteSheet(String path, int size)
    {
        this.path = path;
        this.size = size;
        this.width = size;
        this.height = size;
        pixels = new int[size * size];

        load();
    }

    public SpriteSheet(String path, int width, int height)
    {
        this.path = path;
        this.size = -1; // not being used
        this.width = width;
        this.height = height;
        pixels = new int[width * height];

        load();
    }

    public Sprite[] getSprites()
    {
        return sprites;
    }

    private void load()
    {
        try
        {
            BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
            int w = image.getWidth();
            int h = image.getHeight();

            image.getRGB(0, 0, w, h, pixels, 0, w);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
