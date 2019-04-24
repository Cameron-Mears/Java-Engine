package game;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.ColorModel;
import java.io.File;

import graphics.ImageParser;
import graphics.Renderer;


final class Game extends Thread implements Runnable
{
    private boolean running = true;
    private final int FPS = 1;
    private final int TPS = 1;
    private final long SEC = 1000000000;
    private final long MILLI_SEC = 1000000;
    private static long deltaNS = 0;
    public static Window window;
    public static GraphicsConfiguration graphicsConfig;
    public static ImageParser imageParser;
    public static Renderer renderer;

    public Game() throws InterruptedException
    {
        init();
        run();
    }

    void init()
    {
        window = new Window(null, 1000, 1000, false, null);
        renderer = new Renderer();
        window.getWindow().add(renderer);
        graphicsConfig = graphicsInit(graphicsConfig);
        imageParser = new ImageParser();
        String temp = System.getProperty("user.dir") + "\\JavaGame\\assets\\sprites";
        imageParser.parseFolder(new File(temp));
 
    }

    

    @Override
    public void run() 
    {

        long initialTime = System.nanoTime();
        final double timeTick = 1000000000 / TPS;
        final double timeFrame = 1000000000 / FPS;
        double deltaTick = 0, deltaFrame = 0;
        int frames = 0, ticks = 0;
        long timer = System.currentTimeMillis();

            while (running) 
            {

                long currentTime = System.nanoTime();
                deltaNS = (currentTime - initialTime);
                deltaTick += (deltaNS) / timeTick;
                deltaFrame += (deltaNS) / timeFrame;
                initialTime = currentTime;

                if (deltaTick >= 1) 
                {
                // getInput();
                    //update();
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

    }

    void update()
    {
        
    }

    static long deltaNS()
    {
        return deltaNS;
    }

    void sleepNano(long nano) throws InterruptedException
    {
        long nanos = nano % (MILLI_SEC);
        long millis = (nano - nanos)/1000000;
        sleep(millis, (int) nanos);
    }
    boolean isRunning()
    {
        return running;
    }

    private GraphicsConfiguration graphicsInit(GraphicsConfiguration config)
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