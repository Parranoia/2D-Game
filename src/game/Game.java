package game;

import game.entity.mob.Player;
import game.graphics.Screen;
import game.input.Keyboard;
import game.input.Mouse;
import game.level.Level;
import game.level.SpawnLevel;
import game.level.TileCoordinate;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Game extends Canvas implements Runnable
{
    private static int WIDTH = 300;
    private static int HEIGHT = WIDTH / 16 * 9;
    public static int SCALE = 3;

    private Thread thread;
    private JFrame frame;
    private boolean running;

    private Screen screen;
    private Level level;
    private Player player;
    private Keyboard keyboard;
    private BufferedImage image;
    private int[] pixels;

    public Game()
    {
        running = false;

        Dimension size = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
        setPreferredSize(size);

        frame = new JFrame();
        frame.setResizable(false);
        frame.setTitle("2D Game");
        frame.add(this);
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();

        screen = new Screen(WIDTH, HEIGHT);
        keyboard = new Keyboard();
        level = new SpawnLevel("/levels/level.png");
        TileCoordinate spawnLoc = new TileCoordinate(10, 15);
        player = new Player(spawnLoc.x(), spawnLoc.y(), keyboard);
        player.init(level);

        addKeyListener(keyboard);
        Mouse mouse = new Mouse();
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
    }

    public synchronized void start()
    {
        running = true;
        thread = new Thread(this, "Display");
        thread.start();
    }

    public synchronized void stop()
    {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run()
    {
        long timer = System.currentTimeMillis();
        long lastTime = System.nanoTime();
        final double ns = 1000000000.0 / 60.0;
        double delta = 0;
        int frames = 0;
        int updates = 0;

        requestFocus();

        while (running)
        {
            long now = System.nanoTime();
            delta +=  (now - lastTime) / ns;
            lastTime = now;

            while (delta >= 1)
            {
                update();
                updates++;
                delta--;
            }

            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000)
            {
                timer += 1000;
                frame.setTitle("2D Game | " + frames + " fps");
                updates = 0;
                frames = 0;
            }
        }
        stop();
    }

    public void update()
    {
        keyboard.update();
        player.update();
        level.update();
    }

    public void render()
    {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null)
        {
            createBufferStrategy(3);
            return;
        }

        screen.clear();
        int xScroll = player.getX() - screen.getWidth() / 2;
        int yScroll = player.getY() - screen.getHeight() / 2;
        level.render(xScroll, yScroll, screen);
        player.render(screen);

        for (int i = 0; i < pixels.length; i++)
            pixels[i] = screen.getPixels()[i];

        Graphics g = bs.getDrawGraphics();

        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        g.dispose();

        bs.show();
    }

    public static int getGameWidth() { return WIDTH * SCALE; }

    public static int getGameHeight() { return HEIGHT * SCALE; }

    public static void main(String[] args)
    {
        Game game = new Game();
        game.start();
    }
}
