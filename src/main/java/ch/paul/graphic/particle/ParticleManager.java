package ch.paul.graphic.particle;



import ch.paul.graphic.Animation;
import ch.paul.graphic.ImageLoader;
import ch.paul.Constants;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ParticleManager {
    //random speed (later)
    private int vx = 0; //TODO
    private int vy = 0;

    //List for the Particles
    private final List<Particle> particles = new ArrayList<>();

    //dust Animations
    Animation doubleJumpDust;

    //constructor
    public ParticleManager() {
        //doubleJumpDustAnimation
        BufferedImage doubleJumpDustSheet = ImageLoader.loadImage("res/player/effects/Double_Jump_Dust_5.png");
        doubleJumpDust = new Animation("doubleJumpDustParticleAnimation", doubleJumpDustSheet, 5, Constants.BASE_SPRITE_SIZE, Constants.BASE_SPRITE_SIZE);
    }

    //iterates over all particles, updates its, and removes it after lifetime
    public void updateAll() {
        Iterator<Particle> it = particles.iterator();

        while (it.hasNext()){
            Particle p = it.next();
            p.update();
            if(p.isDestroyed()){
                it.remove();
            }
        }
    }

    //renders all particles in particles list
    public void renderAll(Graphics g){
        for(Particle p : particles){
            p.render(g);
        }
    }



    public void spawnDoubleJumpDust(int x, int y){
        Animation  doubleJumpDust = new Animation(this.doubleJumpDust);
        Particle doubleJumpParticle = new Particle(x, y + Constants.DOUBLE_JUMP_PARTICLE_OFFSET_Y,  vx, vy, Constants.PARTICLE_SIZE,Constants.PARTICLE_SIZE, doubleJumpDust, Constants.BASE_PARTICLE_LIFETIME);
        particles.add(doubleJumpParticle);
    }


}
