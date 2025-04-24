package ch.paul;


import ch.paul.engine.GameLoop;
import ch.paul.window.Window;

public class Main {
    public static void main(String[] args) {
        Window window = new Window("Pauls erstes Game");
        GameLoop loop = new GameLoop(window);
        loop.start();
    }



}