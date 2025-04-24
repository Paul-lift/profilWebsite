package ch.paul.graphic.particle;


import ch.paul.graphic.Animation;

import java.awt.*;

public class Particle {
    private int x, y;
    private double vx, vy;
    private int height, width;
    private Animation animation;
    private int lifeTime;
    private int age = 0;
    private boolean destroyed = false;

    public Particle(int x, int y, double vx, double vy, int height, int width, Animation animation, int lifeTime) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.height = height;
        this.width = width;
        this.animation = animation;
        this.lifeTime = lifeTime;
    }

    public void update(){
        x += vx;
        y += vy;

        animation.update();

        age++;
        if(age >= lifeTime){
            destroyed = true;
        }
    }
    public void render(Graphics g){
        g.drawImage(animation.getCurrentFrame(), x, y, width, height, null);
    }


    //GETTER
    public boolean isDestroyed() {
        return destroyed;
    }
}
