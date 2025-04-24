package ch.paul.graphic;

import java.awt.image.BufferedImage;

public class Animation {
    private String animationTitle;
    private BufferedImage[] frames;
    private int frameCount;
    private int frameWidth;
    private int frameHeight;
    private boolean loop = true;
    private boolean finished;

    private int currentFrame = 0;
    private int tick = 0;
    private int speed = 10;

    public Animation(String animationTitle, BufferedImage spriteSheet, int frameCount, int frameWidth, int frameHeight) {
        this.animationTitle = animationTitle;
        this.frames = new BufferedImage[frameCount];
        this.frameCount = frameCount;
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;

        //splits spirteSheet
        for (int i = 0; i < frameCount; i++) {
            frames[i] = spriteSheet.getSubimage(i * frameWidth, 0, frameWidth, frameHeight);
        }
    }

    //copy Construcotr
    public Animation(Animation other) {
        this.frames = other.frames;
        this.frameCount = other.frameCount;
        this.frameWidth = other.frameWidth;
        this.frameHeight = other.frameHeight;
        this.speed = other.speed;
        this.loop = other.loop;

        this.currentFrame = 0;
        this.tick = 0;
    }


    public void update(){
        tick++;
        if(tick >= speed){
            tick = 0;
            currentFrame++;

            if(currentFrame >= frameCount){
                if (loop){
                    currentFrame = 0;
                } else {
                    currentFrame = frames.length -1;
                    finished = true;
                }
            }
        }
    }

    public void reset(){
        currentFrame = 0;
        tick = 0;
        finished = false;
    }

    public BufferedImage getCurrentFrame(){
        return frames[currentFrame];
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }

    public String getAnimationTitle(){
        return animationTitle;
    }
}
