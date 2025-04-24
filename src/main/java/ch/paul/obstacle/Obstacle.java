package ch.paul.obstacle;

import java.awt.*;

public abstract class Obstacle {
    protected int x, y, width, height;

    public Obstacle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public abstract void update();

    public abstract void render(Graphics g);


    public Rectangle getBounds(){
        return new Rectangle(x, y, width, height);
    }
}
