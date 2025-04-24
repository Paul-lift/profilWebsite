package ch.paul.engine;


import ch.paul.entity.Player;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class KeyboardInput implements KeyListener {

    private Player player;


    public KeyboardInput(Player player){
        this.player = player;
    }


    @Override
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();

        //SETS KEYPRESSED TO TRUE

        //sets wPressed to true only once, to stop double jump glitching
        if(key == KeyEvent.VK_W){
            if(!player.isJumpKeyPressed()){
                player.setJumpKeyPressed(true);
                player.setwPressed(true);

            }
        }
        if(key == KeyEvent.VK_A){player.setaPressed(true);}
        if(key == KeyEvent.VK_S){player.setsPressed(true);}
        if(key == KeyEvent.VK_D){player.setdPressed(true);}
        if(key == KeyEvent.VK_SHIFT){player.setShiftPressed(true);}
    }

    @Override
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();

        //SETS KEYPRESSED TO FALSE

        if(key == KeyEvent.VK_W){
            player.setJumpKeyPressed(false);
            player.setwPressed(false);
        };
        if(key == KeyEvent.VK_A){player.setaPressed(false);}
        if(key == KeyEvent.VK_S){player.setsPressed(false);}
        if(key == KeyEvent.VK_D){player.setdPressed(false);}
        if(key == KeyEvent.VK_SHIFT){player.setShiftPressed(false);}
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

}
