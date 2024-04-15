package Game;

import KeyManager.KeyManager;
import MouseManager.MouseManager;
import State.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable {
    private Display display;
    private int width, height;
    public String title;
    private Thread thread;
    private boolean running_flag = false;
    private BufferStrategy bs;
    private Graphics g;
    private int fps;
    //States
    public GameState gameState;
    public State menuState, helpState, winState, intermediateState, intermediateMenuState;
    //Camera
    private GameCamera gameCamera;
    //Input
    private KeyManager keyManager;
    private MouseManager mouseManager;
    //Handler
    private Handler handler;


    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Game(String title, int width, int height){
        this.width = width;
        this.height = height;
        this.title = title;
        keyManager = new KeyManager();
        mouseManager = new MouseManager(null);
    }

    private void init(){
        handler = new Handler(this);
        gameCamera = new GameCamera(handler, 0,0);

        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);
        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);

        Assets.init();
        gameState = new GameState(handler);
        menuState = new MenuState(handler);
        State.setState(menuState);
    }

    public GameCamera getGameCamera(){
        return gameCamera;
    }

    public synchronized void start(){
        if (running_flag)
            return;
        running_flag = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop(){
        if (!running_flag)
            return;
        running_flag = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        init();

        int fps = 60;
        double timePerTick = 1000000000/fps;
        double delta = 0;
        long now, lastTime = System.nanoTime();
        int ticks = 0;
        long timer = 0;

        while(running_flag){
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;


            if (delta >= 1){
                tick();
                render();
                delta--;
                ticks++;
            }
            if (timer >= 1000000000){
                System.out.println("Ticks and Frames : " + ticks);
                this.fps = ticks;
                ticks = 0;
                timer = 0;
            }
        }
        stop();
    }

    public KeyManager getKeyManager(){
        return  keyManager;
    }

    public MouseManager getMouseManager() { return mouseManager; }

    private void render() {
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null){
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        //Clear Screen
        g.clearRect(0,0,width,height);

        //Start Drawing
        if (State.getState() != null){
            State.getState().render(g);
        }
        //End Drawing
        bs.show();
        g.dispose();

    }

    private void tick(){
        keyManager.tick();
        if (State.getState() != null){
            State.getState().tick();
        }
    }

    public int GetFps(){
        return fps;
    }
}
