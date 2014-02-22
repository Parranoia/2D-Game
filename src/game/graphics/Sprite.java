package game.graphics;

public class Sprite
{
    public final int size;
    private int x, y;
    public int[] pixels;
    private SpriteSheet sheet;

    // Level sprites
    public static Sprite grass = new Sprite(16, 0, 0, SpriteSheet.tiles);
    public static Sprite flower = new Sprite(16, 1, 0, SpriteSheet.tiles);
    public static Sprite rock = new Sprite(16, 2, 0, SpriteSheet.tiles);
    public static Sprite brick = new Sprite(16, 3, 0, SpriteSheet.tiles);
    public static Sprite hex = new Sprite(16, 1, 1, SpriteSheet.tiles);
    public static Sprite wood = new Sprite(16, 2, 1, SpriteSheet.tiles);
    public static Sprite voidSprite = new Sprite(16, 0x1B87E0);

    // Player sprites
    public static Sprite player_down = new Sprite(32, 1, 4, SpriteSheet.tiles);
    public static Sprite player_down1 = new Sprite(32, 0, 4, SpriteSheet.tiles);
    public static Sprite player_down2 = new Sprite(32, 2, 4, SpriteSheet.tiles);

    public static Sprite player_up = new Sprite(32, 1, 7, SpriteSheet.tiles);
    public static Sprite player_up1 = new Sprite(32, 0, 7, SpriteSheet.tiles);
    public static Sprite player_up2 = new Sprite(32, 2, 7, SpriteSheet.tiles);

    public static Sprite player_left = new Sprite(32, 1, 5, SpriteSheet.tiles);
    public static Sprite player_left1 = new Sprite(32, 0, 5, SpriteSheet.tiles);
    public static Sprite player_left2 = new Sprite(32, 2, 5, SpriteSheet.tiles);

    public static Sprite player_right = new Sprite(32, 1, 6, SpriteSheet.tiles);
    public static Sprite player_right1 = new Sprite(32, 0, 6, SpriteSheet.tiles);
    public static Sprite player_right2 = new Sprite(32, 2, 6, SpriteSheet.tiles);

    // Projectile sprites
    public static Sprite smoke_projectile = new Sprite(16, 0, 0, SpriteSheet.projectiles);

    public Sprite(int size, int x, int y, SpriteSheet sheet)
    {
        this.size = size;
        pixels = new int[size * size];
        this.x = x * size;
        this.y = y * size;
        this.sheet = sheet;

        load();
    }

    public Sprite(int size, int color)
    {
        this.size = size;
        pixels = new int[size * size];
        setColor(color);
    }

    private void setColor(int color)
    {
        for (int i = 0; i < pixels.length; i++)
            pixels[i] = color;
    }

    private void load()
    {
        for (int y = 0; y < size; y++)
            for (int x = 0; x < size; x++)
                pixels[x + y * size] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.size];
    }
}
