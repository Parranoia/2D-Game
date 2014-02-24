package game.level;

import game.entity.mob.Dummy;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpawnLevel extends Level
{
    public SpawnLevel(String path)
    {
        super(path);
    }

    protected void loadLevel(String path)
    {
        try
        {
            BufferedImage image = ImageIO.read(SpawnLevel.class.getResource(path));
            width = image.getWidth();
            height = image.getHeight();
            tiles = new int[width * height];
            image.getRGB(0, 0, width, height, tiles, 0, width);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        add(new Dummy(10, 20));
    }

    // Grass = 0xFF00FF00
    // Flower = 0xFFFFFF00
    // Rock = 0xFF7F7F00
    protected void generateLevel()
    {

    }
}
