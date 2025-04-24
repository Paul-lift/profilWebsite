package ch.paul.window;


import ch.paul.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.Canvas;

public class Window extends Canvas{

    private JFrame frame;

    public Window(String title){
        //Takes screen Sizes
        int screenWidth = Constants.SCREEN_WIDTH;
        int screenHeight = Constants.SCREEN_HEIGHT;

        //Sets canvas to screen size
        Dimension size = new Dimension(screenWidth, screenHeight);
        setPreferredSize(size);
        setMaximumSize(size);
        setMinimumSize(size);
        setSize(screenWidth, screenHeight);

        //fokus
        setFocusable(true);
        requestFocus();

        frame = new JFrame(title);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);       //fullscreen
        frame.setUndecorated(true);                         //no window frame
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);                                       //TEMPORARY
        frame.add(this);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

}
