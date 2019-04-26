package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.ColorModel;
import java.io.File;

import entities.Player;
import game.input.InputHandler;
import graphics.ImageParser;
import graphics.Renderer;
import graphics.Sprite;


public final class Game extends Thread implements Runnable
{
    private boolean running = true;
    private final int FPS = 100000000;
    private final int TPS = 1000;
    private final long SEC = 1000000000;
    private final long MILLI_SEC = 1000000;
    private static long deltaNS = 0;
    public static long deltaMS;
    public static Window window;
    public static GraphicsConfiguration graphicsConfig;
    public static ImageParser imageParser;
    public static Renderer renderer;
    public static SpriteHandler spriteHandler;
    private Handler handler;

    private Game() throws InterruptedException
    {
        init();
        run();
    }

    void init()
    {
        InputHandler IH = new InputHandler();
        window = new Window("te", 1000, 1000, false, null);
        renderer = new Renderer(window);
        renderer.addKeyListener(IH);
        graphicsConfig = graphicsInit(graphicsConfig);
        window.getWindow().setVisible(true);
        imageParser = new ImageParser();
        spriteHandler = new SpriteHandler(); 
        handler = new Handler();
        Player temp = new Player(100, 0);
        handler.tickAdd(temp);
        handler.renderAdd(temp);
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

                long currentTime = System.nanoTime();
                deltaNS = (currentTime - initialTime);
                deltaTick += (deltaNS) / timeTick;
                deltaFrame += (deltaNS) / timeFrame;
                initialTime = currentTime;

                spriteHandler.update(deltaNS);

                if (deltaTick >= 1) 
                {
                    long newTimeMS = System.currentTimeMillis();
                    deltaMS = newTimeMS - initTime;
                    initTime = newTimeMS;

                    InputHandler.update();
                    tick();
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
        Graphics g = renderer.createGraphics();
        handler.render(g);
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

    private static GraphicsConfiguration graphicsInit(GraphicsConfiguration config)
    {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        
        config = new GraphicsConfiguration(){
        
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

    public static void main(String[] args) throws Exception
    {
        new Game();
    }
}