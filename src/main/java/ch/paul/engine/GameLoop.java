package ch.paul.engine;



import ch.paul.entity.Player;
import ch.paul.graphic.particle.ParticleManager;
import ch.paul.obstacle.ObstacleManager;
import ch.paul.window.Window;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class GameLoop implements Runnable {
    private Window window;
    private boolean running;
    private Thread thread;
    private Player player;

    //particle
    private ParticleManager particleManager;

    //Obstacles
    private ObstacleManager obstacleManager;

    //constructor
    public GameLoop(Window window) {
        this.window = window;
        this.particleManager = new ParticleManager();
        this.obstacleManager = new ObstacleManager();
        this.player = new Player(window, particleManager, obstacleManager);

        window.addKeyListener(new KeyboardInput(player));
    }

    //starts Thread
    public void start() {
        if (running) {
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();                 //calls run();
    }

    //stops Thread and catches error if interrupted
    public synchronized void stop() {
        if (!running) {
            return;
        }
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    long lastRenderTick = -1;
    int ticks = 10;

    //loops update and render while window is running
    public void run() {
        while (running) {


            if (System.currentTimeMillis() - lastRenderTick
            >ticks){
                update();
                lastRenderTick = System.currentTimeMillis();
            }


            render();


        }
        stop();
    }

    //updates player position and speed
    public void update() {
        player.update();
        particleManager.updateAll();
        obstacleManager.updateAll();
    }

    //creates Buffer, renders player
    public void render() {

        BufferStrategy bs = window.getBufferStrategy();
        if (bs == null) {
            window.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.clearRect(0, 0, window.getWidth(), window.getHeight());   //clears every frame

        player.render(g);
        particleManager.renderAll(g);
        obstacleManager.renderAll(g);

        g.dispose(); //stops "drawing"
        bs.show();   //shows images


    }


}
