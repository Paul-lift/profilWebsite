package ch.paul.obstacle;

import java.awt.*;

public class SolidObstacle extends Obstacle{

    public SolidObstacle(int x, int y, int width, int height){
        super(x, y, width, height);
    }

    @Override
    public void update(){
        //static object, nothing to update
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(x, y , width, height);
    }
}
