package game.level;

import game.entity.Entity;
import game.entity.mob.Mob;
import game.entity.mob.Player;
import game.graphics.Screen;
import game.level.tile.Tile;

import java.util.ArrayList;
import java.util.List;

public class Level
{
    protected int width, height;
    protected int[] tilesInt;
    protected int[] tiles;

    private List<Entity> entities = new ArrayList<Entity>();

    public Level(int width, int height)
    {
        this.width = width;
        this.height = height;
        tilesInt = new int[width * height];

        generateLevel();
    }

    public Level(String path)
    {
        loadLevel(path);
        generateLevel();
    }

    protected void generateLevel()
    {

    }

    protected void loadLevel(String path)
    {

    }

    public void update()
    {
        for (int i = 0; i < entities.size(); i++)
        {
            entities.get(i).update();
            if (entities.get(i).isRemoved())
                entities.remove(i);
        }
    }

    private void time()
    {

    }

    public boolean tileCollision(int x, int y, int size, int xOffset, int yOffset)
    {
        boolean solid = false;

        for (int c = 0; c < 4; c++)
        {
            int xt = (x - c % 2 * size + xOffset) >> 4;
            int yt = (y - c / 2 * size + yOffset) >> 4;

            if (getTile(xt, yt).solid()) solid = true;
        }

        return solid;
    }

    public void render(int xScroll, int yScroll, Screen screen)
    {
        screen.setOffset(xScroll, yScroll);
        int x0 = xScroll >> 4;
        int x1 = (xScroll + screen.getWidth() + 16) >> 4;
        int y0 = yScroll >> 4;
        int y1 = (yScroll + screen.getHeight() + 16) >> 4;

        for (int y = y0; y < y1; y++)
        {
            for (int x = x0; x < x1; x++)
            {
                getTile(x, y).render(x, y, screen);
            }
        }

        for (int i = 0; i < entities.size(); i++)
            entities.get(i).render(screen);
    }

    public Tile getTile(int x, int y)
    {
        if (x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile;

        if (tiles[x + y * width] == 0xFF00FF00) return Tile.grass;
        if (tiles[x + y * width] == 0xFFFFFF00) return Tile.flower;
        if (tiles[x + y * width] == 0xFF7F7F00) return Tile.rock;
        if (tiles[x + y * width] == 0xFFFF0000) return Tile.brick;
        if (tiles[x + y * width] == 0xFFFF9600) return Tile.wood;
        if (tiles[x + y * width] == 0xFF516251) return Tile.hex;
        return Tile.voidTile;
    }

    public void add(Entity e)
    {
        e.init(this);
        entities.add(e);

        if (e instanceof Mob)
            ((Mob)e).initAI();
    }

    public List<Player> getPlayers()
    {
        List<Player> players = new ArrayList<Player>();
        for (Entity e : entities)
            if (e instanceof Player)
                players.add((Player)e);

        return players;
    }
}
