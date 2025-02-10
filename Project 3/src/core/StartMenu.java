package core;

import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;

public class StartMenu implements GameState{
    public StartMenu() {

    }

    @Override
    public void update(int pressed) {
        if (pressed == 'q' || pressed == 'Q') {
            System.exit(0);
        }
    }

    @Override
    public void render() {
        StdDraw.setCanvasSize(800, 600);
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.setPenColor(StdDraw.WHITE);
        Font font = new Font("Arial", Font.BOLD, 40);
        StdDraw.setFont(font);
        StdDraw.text(0.5, 0.8, "CS61B: THE GAME");
        font = new Font("Arial", Font.PLAIN, 20);
        StdDraw.setFont(font);
        StdDraw.text(0.5, 0.5, "New Game (N)");
        StdDraw.text(0.5, 0.45, "Load Game (L)");
        StdDraw.text(0.5, 0.4, "Quit (Q)");
    }

    @Override
    public void enter() {
        this.render();
    }

    @Override
    public void exit() {
        StdDraw.clear();
    }
}
