package ch.paul.entity;

import ch.paul.Constants;
import ch.paul.obstacle.Obstacle;
import ch.paul.obstacle.ObstacleManager;
import ch.paul.window.Window;


import javax.sound.sampled.Line;
import java.awt.*;

public abstract class Entity {
    //position
    protected int x, y;
    //speed
    protected double vx, vy;
    //size
    protected int entityWidth, entityHeight;
    //gravity
    protected final double gravityUp = Constants.BASE_GRAVITY_UP;
    protected final double gravityDown = Constants.BASE_GRAVITY_DOWN;
    //window
    protected Window window;
    //onGround for jumping
    protected boolean onGround = false;
    //jumpCount and maximal jumps
    protected int jumpCount = 0;
    protected int maxJumps = 2;
    //friction
    protected double friction = 0.6;


    protected ObstacleManager obstacleManager;
    //for hitboxes, each Entity NEEDS to have a defined offset
    protected int offsetX;
    protected int offsetY;
    protected Rectangle hitBox;



    //Constructor
    public Entity(Window window, ObstacleManager obstacleManager) {
        this.window = window;
        this.obstacleManager = obstacleManager;
    }

    //Methods
    public abstract void update();

    public boolean shouldCull(){

        return false; //todo check if is in screen space
    }
    public  void render(Graphics g){
        if(shouldCull()) return;
    };


    public void applyGravity() {
        vy += gravityDown;

        updateHitBox();

        //future hitbox
        Rectangle futureHitbox = getBounds();
        futureHitbox.y += vy;


        boolean willCollide = false;
        onGround = false;

        for (Obstacle o : obstacleManager.getObstacles()) {
            Rectangle obstacleBounds = o.getBounds();


            //if hitbox intersects with obstacle
            if (futureHitbox.intersects(obstacleBounds)) {
                //collision for standing on obstacles
                /*if vy > 0(falling) and y position(lower part of hitbox) smaller(above) than upper part of obstacle
                * then sets position on upper part of obstacle
                * same principe for head*/
                if (vy > 0 && hitBox.y <= obstacleBounds.y + obstacleBounds.height) {
                    y = obstacleBounds.y - hitBox.height - offsetY + 1;
                    onGround = true;
                    jumpCount = 0;
                    vy = 0;
                    willCollide = true;
                    break;
                }

                //collision for not jumping through obstacles
                if (vy < 0 && hitBox.y >= obstacleBounds.y + obstacleBounds.height ) {
                    y = obstacleBounds.y + obstacleBounds.height - offsetY;
                    vy = 0;
                    willCollide = true;
                    break;
                }


            }
        }
        //if nothing will collide applys gravity and if moving on y axis sets onGround to false
        if (!willCollide) {
            y += vy;
            if (vy > 0) {
                onGround = false;
            }
        }
        updateHitBox();
    }

    public void applyHorizontalCollision(){
        Rectangle futureHitbox = getBounds();
        futureHitbox.x += vx;
        updateHitBox();

        for(Obstacle o: obstacleManager.getObstacles()){
            Rectangle obstacleBounds = o.getBounds();

            if (futureHitbox.intersects(obstacleBounds)) {
                //collsion Left
                if (vx > 0 && hitBox.x + hitBox.width <= obstacleBounds.x + 2) {
                    x = obstacleBounds.x - hitBox.width - offsetX + 1;
                    vx = 0;
                    break;
                }
                //collision right
                if (vx < 0 && hitBox.x >= obstacleBounds.x + obstacleBounds.width - 2) {
                    x = obstacleBounds.x + obstacleBounds.width - offsetX -1;
                    vx = 0;
                    break;
                }


            }
        }
    }



    public Rectangle getBounds() {
        int width = entityWidth - 2 * offsetX;
        int height = entityHeight - offsetY;
        return new Rectangle(x + offsetX, y + offsetY, width, height);

    }


    //COLLISIONS


    public void checkCollision() {

    }


    public void updateHitBox() {
        hitBox.x = x + offsetX;
        hitBox.y = y + offsetY;
    }


}
