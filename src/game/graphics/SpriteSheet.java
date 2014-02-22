package game.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpriteSheet
{
    private String path;
    public final int size;
    public int[] pixels;

    public static SpriteSheet tiles = new SpriteSheet("/textures/sheets/SpriteSheet.png", 256);
    public static SpriteSheet projectiles = new SpriteSheet("/textures/sheets/projectiles/projectile.png", 48);

    public SpriteSheet(String path, int size)
    {
        this.path = path;
        this.size = size;
        pixels = new int[size * size];

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
