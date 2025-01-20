package core;

import edu.princeton.cs.algs4.StdDraw;
import tileengine.TERenderer;

import java.awt.*;

import static core.World.HEIGHT;
import static core.World.WIDTH;
import static core.World.DEFAULT_SEED;
import static edu.princeton.cs.algs4.StdDraw.*;
import static java.lang.Character.getNumericValue;

public class Main {
    public static void main(String[] args) {
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

        while (true) {
            if (hasNextKeyTyped()) {
                int key = nextKeyTyped();

                if (key == 'n' || key == 'N') {
                    StdDraw.clear(StdDraw.BLACK);
                    StdDraw.text(0.2, 0.4, "Enter your Seed(enter to end): ");
                    StringBuilder seed = new StringBuilder();
                    while (true) {
                        if (hasNextKeyTyped()) {
                            key = nextKeyTyped();
                            if (key == '\n') {
                                break;
                            }
                            seed.append(getNumericValue(key));
                            StdDraw.clear(StdDraw.BLACK);
                            StdDraw.text(0.2, 0.4, "Enter your Seed(enter to end): ");
                            StdDraw.textLeft(0.4, 0.4, seed.toString());
                        }
                    }
                    TERenderer ter = new TERenderer();
                    ter.initialize(WIDTH, HEIGHT);
                    World myWorld = World.createWorld(Integer.parseInt(seed.toString()));
                    ter.renderFrame(myWorld.getWorld());

                    break;
                }
            }
        }
    }
}
