package ch.paul.obstacle;


import ch.paul.Constants;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.Graphics;


public class ObstacleManager {

    private final List<Obstacle> obstacles = new ArrayList<>();
    int screenHeight = Constants.SCREEN_HEIGHT;
    int tileSize = Constants.TILE_SIZE;

    public ObstacleManager(){

        obstacles.add(new SolidObstacle(0,screenHeight - tileSize , Constants.SCREEN_WIDTH, 1000));
        obstacles.add(new SolidObstacle(400,tileSize * (23/2), tileSize, tileSize));
        obstacles.add(new SolidObstacle(tileSize,Constants.SCREEN_HEIGHT- (2 * Constants.PLAYER_HEIGHT), tileSize, tileSize));
    }

    public void updateAll(){
        for (Obstacle o : obstacles){
            o.update();
        }
    }

    public void renderAll(Graphics g){
        for (Obstacle o : obstacles){
            o.render(g);
        }
    }

    public void addObstacle(Obstacle obstacle){
        obstacles.add(obstacle);
    }

    public List<Obstacle> getObstacles(){
        return obstacles;
    }

}
