package core;

import edu.princeton.cs.algs4.StdDraw;
import tileengine.TERenderer;

import java.awt.*;

import static core.World.HEIGHT;
import static core.World.WIDTH;
import static edu.princeton.cs.algs4.StdDraw.*;
import static java.lang.Character.getNumericValue;

public class Main {

    public static void main(String[] args) {

        startGame();

        while (true) {
            int key = getKeyInput();
            if (key == 'n' || key == 'N') {
                runNewWorld();
            } else if (key == 'q' || key == 'Q') {
                System.exit(0);
            }
        }
    }

    public static void startGame() {
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

    public static int getKeyInput() {
        while (true) {
            if (hasNextKeyTyped()) {
                return nextKeyTyped();
            }
        }
    }

    public static void runNewWorld() {
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.text(0.2, 0.4, "Enter your Seed (enter to end) : ");
        StringBuilder seed = new StringBuilder();
        while (true) {
            if (hasNextKeyTyped()) {
                int key = nextKeyTyped();
                if (key == '\n') {
                    break;
                }
                int keyNum = getNumericValue(key);
                if (keyNum < 0 || keyNum > 9) {
                    continue;
                }
                seed.append(getNumericValue(key));
                StdDraw.clear(StdDraw.BLACK);
                StdDraw.text(0.2, 0.4, "Enter your Seed (enter to end) : ");
                StdDraw.textLeft(0.4, 0.4, seed.toString());
            }
        }
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        World myWorld = World.createWorld(Integer.parseInt(seed.toString()));
        ter.renderFrame(myWorld.getWorld());
        while (!hasNextKeyTyped()) {
            char key = nextKeyTyped();
            if (key == 'q' || key == 'Q') {
                System.exit(0);
            }
        }



    }



}
