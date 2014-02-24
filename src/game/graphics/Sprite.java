package game.graphics;

public class Sprite
{
    public final int size, width, height;
    private int x, y;
    private int[] pixels;
    protected SpriteSheet sheet;

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
    /*public static Sprite player_down1 = new Sprite(32, 0, 4, SpriteSheet.tiles);
    public static Sprite player_down2 = new Sprite(32, 2, 4, SpriteSheet.tiles);

    public static Sprite player_up = new Sprite(32, 1, 7, SpriteSheet.tiles);
    public static Sprite player_up1 = new Sprite(32, 0, 7, SpriteSheet.tiles);
    public static Sprite player_up2 = new Sprite(32, 2, 7, SpriteSheet.tiles);

    public static Sprite player_left = new Sprite(32, 1, 5, SpriteSheet.tiles);
    public static Sprite player_left1 = new Sprite(32, 0, 5, SpriteSheet.tiles);
    public static Sprite player_left2 = new Sprite(32, 2, 5, SpriteSheet.tiles);

    public static Sprite player_right = new Sprite(32, 1, 6, SpriteSheet.tiles);
    public static Sprite player_right1 = new Sprite(32, 0, 6, SpriteSheet.tiles);
    public static Sprite player_right2 = new Sprite(32, 2, 6, SpriteSheet.tiles);*/

    // Projectile sprites
    public static Sprite smoke_projectile = new Sprite(16, 0, 0, SpriteSheet.projectiles);

    // Particle sprites
    public static Sprite normal_particle = new Sprite(2, 0xAAAAAA);

    protected Sprite(SpriteSheet sheet, int width, int height)
    {
        this.size = width == height ? width : -1;
        this.width = width;
        this.height = height;
        this.sheet = sheet;
    }

    public Sprite(int size, int x, int y, SpriteSheet sheet)
    {
        this.width = size;
        this.height = size;
        this.size = size;
        pixels = new int[size * size];
        this.x = x * size;
        this.y = y * size;
        this.sheet = sheet;

        load();
    }

    public Sprite(int[] spritePixels, int width, int height)
    {
        size = width == height ? width : -1;
        this.width = width;
        this.height = height;
        this.pixels = spritePixels;
    }

    public Sprite(int size, int color)
    {
        this.width = size;
        this.height = size;
        this.size = size;
        pixels = new int[size * size];
        setColor(color);
    }

    public Sprite(int width, int height, int color)
    {
        this.width = width;
        this.height = height;
        this.size = -1;
        pixels = new int[width * height];
        setColor(color);
    }

    private void setColor(int color)
    {
        for (int i = 0; i < pixels.length; i++)
            pixels[i] = color;
    }

    private void load()
    {
        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++)
                pixels[x + y * width] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.width];
    }

    public int[] getPixels() { return pixels; }
}
