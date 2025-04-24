package ch.paul.entity;


import ch.paul.Constants;
import ch.paul.UI.CustomStatusBar;
import ch.paul.graphic.Animation;
import ch.paul.graphic.ImageLoader;
import ch.paul.graphic.particle.ParticleManager;
import ch.paul.obstacle.ObstacleManager;
import ch.paul.window.Window;


import java.awt.*;
import java.awt.image.BufferedImage;


public class Player extends Entity {

    //booleans (primary for moving)
    private boolean wPressed = false;
    private boolean aPressed = false;
    private boolean sPressed = false;
    private boolean dPressed = false;
    private boolean shiftPressed = false;
    private boolean jumpKeyPressed = false;
    private boolean canSprint = true;

    //movement speed variables
    private final double walkSpeed = Constants.WALK_SPEED;
    private final double sprintSpeed = Constants.WALK_SPEED * Constants.BASE_SPRINT_SCALE;
    private final double jumpSpeed = Constants.JUMP_SPEED;

    //accelartion
    private final double acceleration = 0.4;
    private final double maxWalkSpeed = walkSpeed;
    private final double maxSprintSpeed = sprintSpeed;

    //animation speed variables
    private final int sprintAnimationSpeed = 6;

    //animations
    private Animation currentAnimation;
    //for right / left looking
    private boolean facingRight = true;
    //idle Animations
    private final Animation idleRightAnimation;
    private final Animation idleLeftAnimation;
    //walk Animations
    private final Animation walkRightAnimation;
    private final Animation walkLeftAnimation;
    //sprint Animations
    private final Animation sprintRightAnimation;
    private final Animation sprintLeftAnimation;
    //jump animations
    private final Animation jumpRightAnimation;
    private final Animation jumpLeftAnimation;
    //fall Animations
    private final Animation fallRightAnimation;
    private final Animation fallLeftAnimation;

    //PARTICLES
    private final ParticleManager particleManager;

    //BARS
    //stamina bar
    private CustomStatusBar staminaBar;

    //constructor
    public Player(Window window, ParticleManager particleManager, ObstacleManager obstacleManager) {
        super(window, obstacleManager);
        this.obstacleManager = obstacleManager;
        this.particleManager = particleManager;

        //size
        this.entityWidth = Constants.PLAYER_WIDTH;
        this.entityHeight = Constants.PLAYER_HEIGHT;
        //startposition
        this.x = (window.getWidth() / 2) - (entityWidth / 2);
        this.y = window.getHeight() / 2;
        //speed
        this.vx = 0;
        this.vy = 0;

        //!!!OFFSETS
        offsetX = (entityWidth) / 6;
        offsetY = (entityHeight) / 6;

        hitBox = getBounds();

        //UI

        //BARS
        //stamina
        BufferedImage leftStaminaBar = ImageLoader.loadImage("res/UI/bars/staminaBar/leftStamina.png");
        BufferedImage midStaminaBar = ImageLoader.loadImage("res/UI/bars/staminaBar/midStamina.png");
        BufferedImage midEmptyStaminaBar = ImageLoader.loadImage("res/UI/bars/staminaBar/midEmptyStamina.png");
        BufferedImage rightStaminaBar = ImageLoader.loadImage("res/UI/bars/staminaBar/rightStamina.png");

        //BARS
        this .staminaBar = new CustomStatusBar(Constants.SCREEN_WIDTH * 1/100 , Constants.SCREEN_HEIGHT * 2/4,  (int) Constants.BASE_BAR_WIDTH, (int) Constants.BASE_BAR_HEIGHT, 1000, 1000, leftStaminaBar, midStaminaBar, midEmptyStaminaBar, rightStaminaBar );
        //ANIMAFTIONS

        // idleRight
        BufferedImage idleRightSheet = ImageLoader.loadImage("res/player/asymmetric/right/Player_Right_Idle_12.png");
        idleRightAnimation = new Animation("idleRightAnimation", idleRightSheet, 12, Constants.BASE_SPRITE_SIZE, Constants.BASE_SPRITE_SIZE);
        idleRightAnimation.setSpeed(Constants.IDLE_ANIMATION_SPEED);

        // idleLeft
        BufferedImage idleLeftSheet = ImageLoader.loadImage("res/player/asymmetric/left/Player_Left_Idle_12.png");
        idleLeftAnimation = new Animation("idleLeftAnimation", idleLeftSheet, 12, Constants.BASE_SPRITE_SIZE, Constants.BASE_SPRITE_SIZE);
        idleLeftAnimation.setSpeed(Constants.IDLE_ANIMATION_SPEED);

        // walkRight
        BufferedImage walkRightSheet = ImageLoader.loadImage("res/player/asymmetric/right/Player_Right_Walk_6.png");
        walkRightAnimation = new Animation("walkRightAnimation", walkRightSheet, 6, Constants.BASE_SPRITE_SIZE, Constants.BASE_SPRITE_SIZE);

        // walkLeft
        BufferedImage walkLeftSheet = ImageLoader.loadImage("res/player/asymmetric/left/Player_Left_Walk_6.png");
        walkLeftAnimation = new Animation("walkLeftAnimation", walkLeftSheet, 6, Constants.BASE_SPRITE_SIZE, Constants.BASE_SPRITE_SIZE);

        //sprintRight
        BufferedImage sprintRightSheet = ImageLoader.loadImage("res/player/asymmetric/right/Player_Right_Run_6.png");
        sprintRightAnimation = new Animation("sprintRightAnimation", sprintRightSheet, 6, Constants.BASE_SPRITE_SIZE, Constants.BASE_SPRITE_SIZE);
        sprintRightAnimation.setSpeed(sprintAnimationSpeed);

        //sprintLeft
        BufferedImage sprintLeftSheet = ImageLoader.loadImage("res/player/asymmetric/left/Player_Left_Run_6.png");
        sprintLeftAnimation = new Animation("sprintLeftAnimation", sprintLeftSheet, 6, Constants.BASE_SPRITE_SIZE, Constants.BASE_SPRITE_SIZE);
        sprintLeftAnimation.setSpeed(sprintAnimationSpeed);

        // jumpRight
        BufferedImage jumpRightSheet = ImageLoader.loadImage("res/player/asymmetric/right/Player_Right_Jump_8.png");
        jumpRightAnimation = new Animation("jumpRightAnimation", jumpRightSheet, 4, Constants.BASE_SPRITE_SIZE, Constants.BASE_SPRITE_SIZE);
        jumpRightAnimation.setLoop(false);

        // jumpLeft
        BufferedImage jumpLeftSheet = ImageLoader.loadImage("res/player/asymmetric/left/Player_Left_Jump_8.png");
        jumpLeftAnimation = new Animation("jumpLeftAnimation", jumpLeftSheet, 4, Constants.BASE_SPRITE_SIZE, Constants.BASE_SPRITE_SIZE);
        jumpLeftAnimation.setLoop(false);

        // fallRight
        BufferedImage fallRightSheet = ImageLoader.loadImage("res/player/asymmetric/right/Player_Right_Fall_8.png");
        fallRightAnimation = new Animation("fallRightAnimation", fallRightSheet, 4, Constants.BASE_SPRITE_SIZE, Constants.BASE_SPRITE_SIZE);
        fallRightAnimation.setLoop(false);

        // fallLeft
        BufferedImage fallLeftSheet = ImageLoader.loadImage("res/player/asymmetric/left/Player_Left_Fall_8.png");
        fallLeftAnimation = new Animation("fallLeftAnimation", fallLeftSheet, 4, Constants.BASE_SPRITE_SIZE, Constants.BASE_SPRITE_SIZE);
        fallLeftAnimation.setLoop(false);


    }

    @Override
    public void update() {
        updateAll();
        x += (int) vx;

        //ANIMATIONS
        handleMovementAnimations();
        currentAnimation.update();

    }

    @Override
    public void render(Graphics g) {
        super.render(g);

        g.setColor(Color.RED);      //debugging
        g.drawRect(getBounds().x, getBounds().y, getBounds().width, getBounds().height);    //debugging

        g.drawImage(currentAnimation.getCurrentFrame(), x, y, entityWidth, entityHeight, null);
        staminaBar.render(g);
    }

    public void updateAll() {
        //MOVEMENTS
        updateBars();
        updateFacingDirection();
        updateMovement();
        updateNeutralization();
        applyGravity();
        applyHorizontalCollision();

    }

    public void updateBars(){
        handleStaminaBar();
    }

    public void updateMovement(){
        jump();
        moveLeft();
        moveDown();
        moveRight();
    }

    public void updateNeutralization(){
        cancelHorizontalMovement();
        applyHorizontalFriction();
        neutralizeJump();
    }

    public void jump() {

            if (wPressed && jumpCount < maxJumps && staminaBar.getValue() > 150) {
                vy = -jumpSpeed;
                System.out.println("Jumpcount: " + jumpCount);
                jumpCount++;
                wPressed = false;
                jumpRightAnimation.reset();                //for new Animation on double jump
                jumpLeftAnimation.reset();

                if(jumpCount > 1){
                    particleManager.spawnDoubleJumpDust(x, y);
                }

                staminaBar.decrease(150);//TODO CONSTANTS
            }


    }

    public void moveLeft() {
        double targetSpeed;

        if (aPressed && shiftPressed && canSprint) {
                targetSpeed = -maxSprintSpeed;
        } else {
            targetSpeed = -maxWalkSpeed;
        }

        if (aPressed && !dPressed) {            //only try's if d is pressed and a NOT
            if (vx < targetSpeed) {              //if speed is smaller than the targetSpeed, it accelerates
                vx += acceleration;
                if (vx > targetSpeed) vx = targetSpeed;     //accelerates until it's greater than target speed, sets it to target speed
            } else if (vx > targetSpeed) {                   // if vx already greater than target speed(because of sprinting or something else), slows it down untwil reached target speed
                vx -= acceleration;
                if (vx < targetSpeed) vx = targetSpeed;
            }
        }
    }

    public void moveRight() {
        double targetSpeed;

        if (dPressed && shiftPressed && canSprint) {
            targetSpeed = maxSprintSpeed;
        } else {
            targetSpeed = maxWalkSpeed;
        }

        if (dPressed && !aPressed) {            //only try's if d is pressed and a NOT
            if (vx < targetSpeed) {              //if speed is smaller than the targetSpeed, it accelerates
                vx += acceleration;
                if (vx > targetSpeed) vx = targetSpeed;      //accelerates until it's greater than target speed, sets it to target speed
            } else if (vx > targetSpeed) {                   // if vx already greater than target speed(because of sprinting or something else), slows it down untwil reached target speed
                vx -= acceleration;
                if (vx < targetSpeed) vx = targetSpeed;
            }
        }
    }

    public void moveDown() {
        if (sPressed) {
            vy += 1;
        }
    }

    public void cancelHorizontalMovement() {
        if (aPressed && dPressed) {         //if a and d pressed, slows down speed until it reaches 0
            if (vx > 0) {
                vx -= friction;
                if (vx < 0) vx = 0;
            } else if (vx < 0) {
                vx += friction;
                if (vx > 0) vx = 0;
            }
        }
    }

    public void applyHorizontalFriction() {
        if (!aPressed && !dPressed) {
            if (vx > 0) {
                vx -= friction;
                if (vx < 0) vx = 0;
            }
            if (vx < 0) {
                vx += friction;
                if (vx > 0) vx = 0;
            }
        }
    }

    public void neutralizeJump() {
        //W and S is pressed
        if (wPressed && sPressed && onGround) {
            vy = 0;
        }
    }

    //BAR METHODS
    public void handleStaminaBar(){
        boolean tryingToSprint = shiftPressed && (aPressed || dPressed);

        if(tryingToSprint && staminaBar.getValue() > 0){
            staminaBar.decrease(Constants.SPRINT_DECREASE);
        } else if (!tryingToSprint){
            staminaBar.increase(Constants.SPRINT_INCREASE);
        }

        canSprint = staminaBar.getValue() > 10;
    }


    //ANIMATIONS METHODS
    public void updateFacingDirection() {
        if (aPressed && !dPressed) {
            facingRight = false;
        } else if (dPressed && !aPressed) {
            facingRight = true;
        }
    }

    public void handleMovementAnimations() {
        if (!onGround) {
            handleJumpAnimations();
        } else if (vx != 0) {
            if (canSprint && shiftPressed && (vx < -walkSpeed || vx > walkSpeed)){
                handleSprintAnimations();
            } else {
                handleWalkAnimations();
            }
        } else {
            handleIdleAnimations();
        }

        System.out.println(currentAnimation.getAnimationTitle());
    }

    public void handleIdleAnimations() {
        if (facingRight) {
            currentAnimation = idleRightAnimation;
        } else if (!facingRight) {
            currentAnimation = idleLeftAnimation;
        }
    }

    public void handleWalkAnimations() {

        if (vx < 0) {                                 //negative x movement = leftWalk
            currentAnimation = walkLeftAnimation;
        } else if (vx > 0) {                         //positive x movement = rightWalk
            currentAnimation = walkRightAnimation;
        }
    }

    public void handleSprintAnimations(){
        if (vx < 0) {
            currentAnimation = sprintLeftAnimation;
        } else if (vx > 0){
            currentAnimation = sprintRightAnimation;
        }
    }

    //TODO jump animation fixen
    public void handleJumpAnimations() {
        if (facingRight) {
            if (vy < 0) {
                currentAnimation = jumpRightAnimation;
            } else if (vy >= 0) {
                currentAnimation = fallRightAnimation;
            }
        } else if (!facingRight) {
            if (vy < 0) {
                currentAnimation = jumpLeftAnimation;
            } else if (vy >= 0) {
                currentAnimation = fallLeftAnimation;
            }
        }

    }

    //SETTER
    public void setsPressed(boolean sPressed) {
        this.sPressed = sPressed;
    }

    public void setwPressed(boolean wPressed) {
        this.wPressed = wPressed;
    }

    public void setaPressed(boolean aPressed) {
        this.aPressed = aPressed;
    }

    public void setdPressed(boolean dPressed) {
        this.dPressed = dPressed;
    }

    public void setJumpKeyPressed(boolean jumpKeyPressed) {
        this.jumpKeyPressed = jumpKeyPressed;
    }

    public void setShiftPressed(boolean shiftPressed){
        this.shiftPressed = shiftPressed;
    }

    public void setCanSprint(boolean canSprint) {
        this.canSprint = canSprint;
    }

    //GETTER

    public boolean iswPressed() {
        return wPressed;
    }

    public boolean isaPressed() {
        return aPressed;
    }

    public boolean issPressed() {
        return sPressed;
    }

    public boolean isdPressed() {
        return dPressed;
    }

    public boolean isJumpKeyPressed() {
        return jumpKeyPressed;
    }

    public boolean isShiftPressed(){
        return shiftPressed;
    }

    public boolean isCanSprint() {
        return canSprint;
    }

    private boolean isSprinting() {
        return shiftPressed && canSprint && Math.abs(vx) > walkSpeed;
    }

}
