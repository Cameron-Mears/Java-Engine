package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;

import entities.LevelConstructor;
import entities.Player;
import entities.Player2;
import game.input.InputHandler;
import gameobjects.Block;
import graphics.ImageParser;
import graphics.Renderer;
import graphics.Sprite;


public final class Game extends Thread implements Runnable
{
    private boolean running = true;
    private final int FPS = 30000;
    private final int TPS = 100;
    private final long SEC = 1000000000;
    private final long MILLI_SEC = 1000000;
    private static long deltaNS = 0;
    public static long deltaMS;
    public static int windowHeight = 32*32;
    public static int windowWidth = 52*32;
    public static Window window;
    public static GraphicsConfiguration graphicsConfig;
    public static ImageParser imageParser;
    public static Renderer renderer;
    public static SpriteHandler spriteHandler;
    public static Handler handler;
    private LevelConstructor levelConstructor;
    public static Level level;
    public static boolean renderLevel = true;

    //private constrcut as their is no need to rerun this thing.
    private Game()
    {
        init();
        run();
    }

    void init()
    {
        imageParser = new ImageParser();
        graphicsConfig = graphicsInit(graphicsConfig);
        InputHandler IH = new InputHandler();
        window = new Window("things and stuff", windowWidth, windowHeight, true, ImageParser.parseFolder(new File(System.getProperty("user.dir") + "\\JavaGame\\assets\\sprites\\sadad"))[0]);
        renderer = new Renderer(window);
        renderer.addKeyListener(IH);
        renderer.addMouseMotionListener(IH);
        renderer.addMouseListener(IH);
        renderer.addMouseWheelListener(IH);
        window.getWindow().setVisible(true);
        spriteHandler = new SpriteHandler(); 
        handler = new Handler();
        Player2 temp2 = new Player2(150, 150);
        handler.add(temp2);
        renderer.add(10, temp2);
        levelConstructor = new LevelConstructor();
        level = levelConstructor.newLevel(level);
        renderer.requestFocus();
        window.getWindow().requestFocus();
    }

    

    @Override
    public void run() 
    {

        long initialTime = System.nanoTime();
        final double timeTick = SEC / TPS;
        final double timeFrame = SEC / FPS;
        double deltaTick = 0, deltaFrame = 0;
        int frames = 0, ticks = 0;
        long timer = System.currentTimeMillis();
        long initTime = timer;


            while (running) 
            {
                /*
                    Checks time passed until the next time the game needs to update or render
                    to reach target frames/ticks.
                */

                long currentTime = System.nanoTime();
                deltaNS = (currentTime - initialTime);
                deltaTick += (deltaNS) / timeTick;
                deltaFrame += (deltaNS) / timeFrame;
                initialTime = currentTime;


                if (deltaTick >= 1) 
                {
                    /*
                        the physics, sprites etc. update from the deltaMS
                        instead of DeltaNS due to loss of accuarcy when dividing by 1 billion
                    */
                    long newTimeMS = System.currentTimeMillis();
                    deltaMS = newTimeMS - initTime;
                    initTime = newTimeMS;
                    spriteHandler.update(deltaMS);
                    tick();
                    InputHandler.update();
                    ticks++;
                    deltaTick--;
                }

                if (deltaFrame >= 1) 
                {
                    render();
                    frames++;
                    deltaFrame--;
                }

                if (System.currentTimeMillis() - timer > 1000) 
                {                
                    System.out.println(String.format("TPS: %s, FPS: %s", ticks, frames));
                
                    frames = 0;
                    ticks = 0;
                    timer += 1000;
                }
            }
    }

    void render()
    {
        Graphics2D g = renderer.createGraphics();
        renderer.render(g);
        renderer.show();
    }

    void tick()
    {
        handler.tick();
    }

    static long deltaNS()
    {
        return deltaNS;
    }

    static long deltaMS()
    {
        return deltaMS;
    }

    boolean isRunning()
    {
        return running;
    }

    /*
        Setup a graphics configuration this will use the GPU if avaible and help images render more effienly
    */

    private static GraphicsConfiguration graphicsInit(GraphicsConfiguration config)
    {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        
        config = new GraphicsConfiguration()
        {
        
            @Override
            public AffineTransform getNormalizingTransform() {
                return new AffineTransform();
            }
        
            @Override
            public GraphicsDevice getDevice() {
                return ge.getDefaultScreenDevice();
            }
        
            @Override
            public AffineTransform getDefaultTransform() {
                return new AffineTransform();
            }
        
            @Override
            public ColorModel getColorModel(int transparency) {
                return null;
            }
        
            @Override
            public ColorModel getColorModel() 
            {
                return ColorModel.getRGBdefault();
            }
        
            @Override
            public Rectangle getBounds() 
            {
                return ge.getMaximumWindowBounds();
            }
        };

        return config;
    }

    public static void main(String[] args)
    {
        new Game();
    }
}