package ch.paul;

import java.awt.*;

public class Constants {
    //BASE SIZES
    public static final int BASE_SPRITE_SIZE = 32;
    public static final int SPRITE_SCALE = 3;
    public static final int TILE_SIZE = BASE_SPRITE_SIZE * SPRITE_SCALE;

    public static final int PLAYER_WIDTH = TILE_SIZE;
    public static final int PLAYER_HEIGHT = TILE_SIZE;

    public static final int PARTICLE_SIZE = TILE_SIZE;

    //BASE SPEED VARIABLES
    public static final double BASE_WALK_SPEED = 1.5;
    public static final double BASE_SPRINT_SCALE = 1.75;
    public static final double BASE_JUMP_SPEED = 4.5;
    public static final double BASE_GRAVITY_UP = 0.4;
    public static final double BASE_GRAVITY_DOWN = 0.5;

    public static final double JUMP_SPEED = BASE_JUMP_SPEED * SPRITE_SCALE;
    public static final double WALK_SPEED = BASE_WALK_SPEED * SPRITE_SCALE;

    //BASE PARTICLE LIFETIME
    public static final int BASE_PARTICLE_LIFETIME = 25;
    //particleOffsets
    public static final int DOUBLE_JUMP_PARTICLE_OFFSET_Y = 30;

    //screen sizes
    public static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    public static int SCREEN_WIDTH = SCREEN_SIZE.width;
    public static int SCREEN_HEIGHT = SCREEN_SIZE.height;

    //BARS
    public static double BASE_BAR_WIDTH = SCREEN_WIDTH / 3.5;
    public static double BASE_BAR_HEIGHT = SCREEN_HEIGHT / 10;

    //STATS
    public static int SPRINT_DECREASE = 5;
    public static int SPRINT_INCREASE = 3;

    //ANIMATION SPEED
    public static int IDLE_ANIMATION_SPEED = 15;

}
