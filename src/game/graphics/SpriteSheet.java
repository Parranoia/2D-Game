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

    public static SpriteSheet tiles = new SpriteSheet("/textures/sheets/SpriteSheet.png", 256);
    public static SpriteSheet projectiles = new SpriteSheet("/textures/sheets/projectiles/projectile.png", 48);
    // Player
    public static SpriteSheet player = new SpriteSheet("/textures/sheets/player.png", 96, 128);
    public static SpriteSheet player_up = new SpriteSheet(player, 0, 0, 3, 1, 32);
    public static SpriteSheet player_down = new SpriteSheet(player, 3, 0, 3, 1, 32);
    public static SpriteSheet player_left = new SpriteSheet(player, 1, 0, 3, 1, 32);
    public static SpriteSheet player_right = new SpriteSheet(player, 2, 0, 3, 1, 32);

    public SpriteSheet(SpriteSheet sheet, int x, int y, int width, int height, int spriteSize)
    {
        int xx = x * spriteSize;
        int yy = y * spriteSize;

        this.path = sheet.path;
        this.size = width == height ? width : -1;
        this.width = width * spriteSize;
        this.height = height * spriteSize;
        pixels = new int[this.width * this.height];

        for (int yp = 0; y < this.height; y++)
        {
            int ya = yy + yp;
            for (int xp = 0; x < this.width; x++)
            {
                int xa = xx + xp;
                pixels[xp + yp * this.height] = sheet.pixels[xa + ya * sheet.width];
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
