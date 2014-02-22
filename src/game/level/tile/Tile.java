package game.level.tile;

import game.graphics.Screen;
import game.graphics.Sprite;

public class Tile
{
    public int x, y;
    public Sprite sprite;

    public static Tile grass = new GrassTile(Sprite.grass);
    public static Tile flower = new FlowerTile(Sprite.flower);
    public static Tile rock = new RockTile(Sprite.rock);
    public static Tile brick = new BrickTile(Sprite.brick);
    public static Tile wood = new WoodTile(Sprite.wood);
    public static Tile hex = new HexTile(Sprite.hex);
    public static Tile voidTile = new VoidTile(Sprite.voidSprite);

    public Tile(Sprite sprite)
    {
        this.sprite = sprite;
    }

    public void render(int x, int y, Screen screen)
    {

    }

    public boolean solid()
    {
        return false;
    }
}
